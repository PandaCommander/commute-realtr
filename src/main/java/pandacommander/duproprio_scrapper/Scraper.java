package pandacommander.duproprio_scrapper;

import java.util.List;

import org.openqa.selenium.WebDriver;

public abstract class Scraper {

	protected int regionCode;
	protected int minPrice;
	protected int maxPrice;
	protected WebDriver driver;

	public Scraper(int regionCode, int minPrice, int maxPrice, WebDriver driver) {
		super();
		this.regionCode = regionCode;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.driver = driver;
	}

	public abstract List<Listing> getListings(int maxPages);

}