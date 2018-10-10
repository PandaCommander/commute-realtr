package pandacommander.duproprio_scrapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration2.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Scraper {

	private static String baseUrl = "https://duproprio.com/en/search/list?search=true&regions[0]=%1$d&min_price=%2$d&max_price=%3$d&is_for_sale=1&with_builders=1&parent=1&pageNumber=%4$d&sort=-published_at";

	private int regionCode = 6;
	private int minPrice = 125000;
	private int maxPrice = 275000;
	private WebDriver driver;

	public Scraper(int regionCode, int minPrice, int maxPrice, WebDriver driver) {
		super();
		this.regionCode = regionCode;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.driver = driver;
	}

	public List<Listing> getListings(int maxPages) {
		String firstPageUrl = String.format(baseUrl, regionCode, minPrice, maxPrice, 1);
		driver.get(firstPageUrl);

		List<Listing> listingModels = new ArrayList<Listing>();

		// Get last page number
		WebElement paginationList = driver.findElement(By.cssSelector("ul.pagination__list"));
		List<WebElement> paginations = paginationList.findElements(By.tagName("li"));
		WebElement lastPagePagination = paginations.get(paginations.size() - 1).findElement(By.tagName("a"));
		int lastPageNumber = Integer.parseInt(lastPagePagination.getText());

		maxPages = maxPages <= lastPageNumber ? maxPages : lastPageNumber;
		for (int pageNumber = 1; pageNumber <= maxPages; pageNumber++) {
			System.out.println(pageNumber);
			String newPageUrl = String.format(baseUrl, regionCode, minPrice, maxPrice, pageNumber);
			driver.get(newPageUrl);

			List<WebElement> listings = driver.findElements(By.cssSelector("li[id^='listing-']"));

			for (WebElement listing : listings) {
				listingModels.add(getListingModel(listing));
			}
		}
		return listingModels;
	}

	private Listing getListingModel(WebElement listing) {
		String id = listing.getAttribute("id");
		String longitude = listing.findElement(By.cssSelector("meta[property=longitude]")).getAttribute("content");
		String latitude = listing.findElement(By.cssSelector("meta[property=latitude]")).getAttribute("content");

		String price;
		try {
			WebElement priceContainer = listing
					.findElement(By.cssSelector("div[class=search-results-listings-list__item-description__price]"));
			price = priceContainer.findElement(By.tagName("h2")).getText();
		} catch (Exception e3) {
			price = "";
		}

		String address;
		try {
			WebElement addressContainer = listing.findElement(By.cssSelector("div[class*='description__address']"));
			address = addressContainer.getText();
		} catch (Exception e2) {
			address = "";
		}

		String city;
		try {
			WebElement cityContainer = listing.findElement(By.cssSelector("div[class*='description__city-wrap']"));
			city = cityContainer.findElement(By.tagName("span")).getText();
		} catch (Exception e1) {
			city = "";
		}

		String description;
		try {
			WebElement descriptionContainer = listing
					.findElement(By.cssSelector("div[class*='item-description__type-and-intro']"));
			description = descriptionContainer.getText();
		} catch (Exception e) {
			description = "";
		}
		
		String imageUrl;
		try {
			WebElement imageContainer = listing
					.findElement(By.tagName("img"));
			imageUrl = imageContainer.getAttribute("src");
		} catch (Exception e) {
			imageUrl = "";
		}

		List<WebElement> characteristics = listing.findElements(By.cssSelector("div[class*='characteristics__item']"));
		String bedrooms = "";
		String bathrooms = "";
		String buildingDimensions = "";
		String lotDimensions = "";
		for (WebElement characteristic : characteristics) {
			try {
				String characteristicClass = characteristic.findElement(By.tagName("svg")).getAttribute("class");

				if (characteristicClass.contains("bedrooms")) {
					bedrooms = characteristic.getText();
				}
				if (characteristicClass.contains("bathrooms")) {
					bathrooms = characteristic.getText();
				}
				if (characteristicClass.contains("building-dimensions")) {
					buildingDimensions = characteristic.getText();
				}
				if (characteristicClass.contains("lot-dimensions")) {
					lotDimensions = characteristic.getText();
				}
			} catch (Exception e) {
			}
		}

		return new Listing(id, longitude, latitude, price, address, city, description, bedrooms, bathrooms,
				buildingDimensions, lotDimensions, imageUrl);
	}

}
