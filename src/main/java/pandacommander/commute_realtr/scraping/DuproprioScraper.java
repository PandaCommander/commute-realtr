package pandacommander.commute_realtr.scraping;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.configuration2.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

import pandacommander.commute_realtr.crawling.WebDriverRepository;
import pandacommander.commute_realtr.listing.Listing;

public class DuproprioScraper extends Scraper {

	static String BASE_URL = "https://duproprio.com/en/search/list?search=true"
			+ "&regions[0]=%1$d&min_price=%2$d&max_price=%3$d&is_for_sale=1"
			+ "&with_builders=1&parent=1&pageNumber=%4$d&sort=-published_at";
	
	private List<Listing> listings;

	public DuproprioScraper(Configuration options, WebDriverRepository driverRepository) {
		super(options, driverRepository);
		listings = new ArrayList<>();
	}

	@Override
	public List<Listing> getListings() {
		int maxPages = getOptions().getInt("page.number.max");
		int regionCode = getRegionCode();
		int minPrice = getMinPrice();
		int maxPrice = getMaxPrice();
		String firstPageUrl = String.format(BASE_URL, regionCode, minPrice, maxPrice, 1);

		WebDriver driver = getDriverRepository().getNewWebDriver();
		driver.get(firstPageUrl);

		// Get last page number
		WebElement paginationList = driver.findElement(By.cssSelector("ul.pagination__list"));
		List<WebElement> paginations = paginationList.findElements(By.tagName("li"));
		WebElement lastPagePagination = paginations.get(paginations.size() - 1).findElement(By.tagName("a"));
		int lastPageNumber = Integer.parseInt(lastPagePagination.getText());

		ExecutorService executor = Executors.newWorkStealingPool();
		List<Callable<List<Listing>>> callableTasks = new ArrayList<>();

		maxPages = maxPages <= lastPageNumber ? maxPages : lastPageNumber;
		for (int pageNumber = 1; pageNumber <= maxPages; pageNumber++) {
			callableTasks.add(getCallableListings(regionCode, minPrice, maxPrice,
					getDriverRepository().getNewWebDriver(), pageNumber));
		}

		try {
			List<Future<List<Listing>>> futureListings = executor.invokeAll(callableTasks);
			listings = new ArrayList<>();
			futureListings.forEach(l -> {
				try {
					listings.addAll(l.get());
				} catch (InterruptedException | ExecutionException e) {
				}
			});
		} catch (Exception e) {
		}
		
		getDriverRepository().stopAllDrivers();
		return listings;
	}

	protected Callable<List<Listing>> getCallableListings(int regionCode, int minPrice, int maxPrice, WebDriver driver,
			int pageNumber) {
		Callable<List<Listing>> runnableTask = () -> {
			List<Listing> listingModels = new ArrayList<Listing>();
			String newPageUrl = String.format(BASE_URL, regionCode, minPrice, maxPrice, pageNumber);
			driver.get(newPageUrl);
			// Wait for all photos to load
			loadPhotos(driver, pageNumber);
			List<WebElement> listings = driver.findElements(By.cssSelector("li[id^='listing-']"));
			for (WebElement listing : listings) {
				listingModels.add(getListingModel(listing));
			}
			return listingModels;
		};
		return runnableTask;
	}

	protected void loadPhotos(WebDriver driver, int pageNumber) {
		try {
			// Simulate navigation on page to force load photos
			Thread.sleep(100);
			JavascriptExecutor jsx = (JavascriptExecutor) driver;
			jsx.executeScript("window.focus()", "");
			Thread.sleep(100);
			driver.manage().window().maximize();
			Thread.sleep(10);
			jsx.executeScript("window.scrollBy(0,500)");
			Thread.sleep(10);
			jsx.executeScript("window.scrollBy(0,500)");
			Thread.sleep(10);
			jsx.executeScript("window.scrollBy(0,500)");
			Thread.sleep(10);
			jsx.executeScript("window.scrollBy(0,500)");
			Thread.sleep(10);
			jsx.executeScript("window.scrollBy(0,500)");
			Thread.sleep(10);
			jsx.executeScript("window.scrollBy(0,500)");
			Thread.sleep(10);
			jsx.executeScript("window.scrollBy(0,-200)");
			Thread.sleep(10);
			jsx.executeScript("window.scrollBy(0,-200)");
			Thread.sleep(10);
			jsx.executeScript("window.scrollBy(0,-200)");
			Thread.sleep(10);
			jsx.executeScript("window.scrollBy(0,-200)");
			Thread.sleep(10);
			jsx.executeScript("window.scrollBy(0,-200)");
			Thread.sleep(10);
			jsx.executeScript("window.scrollBy(0,-200)");
			Thread.sleep(10);
			jsx.executeScript("window.scrollBy(0,-200)");
			Thread.sleep(10);
			jsx.executeScript("window.scrollBy(0,-200)");
			Thread.sleep(10);
			jsx.executeScript("window.focus()", "");
			Thread.sleep(100);
			driver.manage().window().maximize();
			Thread.sleep(100);
			WebDriverWait wait = new WebDriverWait(driver, 5);
			By listingImage = By.cssSelector("img[class*='search-results-listings-list__item-photo']");
			wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(listingImage, 8));
		} catch (Exception e) {
			System.out.println("Failed to load all photos for page " + pageNumber);
		}
	}

	private Listing getListingModel(WebElement listingElements) {
		String id = listingElements.getAttribute("id");
		String longitude = listingElements.findElement(By.cssSelector("meta[property=longitude]"))
				.getAttribute("content");
		String latitude = listingElements.findElement(By.cssSelector("meta[property=latitude]"))
				.getAttribute("content");

		String price;
		try {
			WebElement priceContainer = listingElements
					.findElement(By.cssSelector("div[class=search-results-listings-list__item-description__price]"));
			price = priceContainer.findElement(By.tagName("h2")).getText();
		} catch (Exception e3) {
			price = "";
		}

		String address;
		try {
			WebElement addressContainer = listingElements
					.findElement(By.cssSelector("div[class*='description__address']"));
			address = addressContainer.getText();
		} catch (Exception e2) {
			address = "";
		}

		String city;
		try {
			WebElement cityContainer = listingElements
					.findElement(By.cssSelector("div[class*='description__city-wrap']"));
			city = cityContainer.findElement(By.tagName("span")).getText();
		} catch (Exception e1) {
			city = "";
		}

		String description;
		try {
			WebElement descriptionContainer = listingElements
					.findElement(By.cssSelector("div[class*='item-description__type-and-intro']"));
			description = descriptionContainer.getText();
		} catch (Exception e) {
			description = "";
		}

		String imageUrl;
		try {
			WebElement imageContainer = listingElements.findElement(By.cssSelector("img[class*='photo']"));
			imageUrl = imageContainer.getAttribute("src");
		} catch (Exception e) {
			imageUrl = "";
		}

		String listingUrl;
		try {
			WebElement imageContainer = listingElements
					.findElement(By.cssSelector("a[class*='search-results-listings-list__item-image-link']"));
			listingUrl = imageContainer.getAttribute("href");
		} catch (Exception e) {
			listingUrl = "";
		}

		List<WebElement> characteristics = listingElements
				.findElements(By.cssSelector("div[class*='characteristics__item']"));
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
				buildingDimensions, lotDimensions, imageUrl, listingUrl);
	}
}
