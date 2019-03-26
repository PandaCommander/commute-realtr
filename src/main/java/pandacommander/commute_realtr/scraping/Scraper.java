package pandacommander.commute_realtr.scraping;

import java.util.List;

import org.apache.commons.configuration2.Configuration;
import org.openqa.selenium.WebDriver;

import pandacommander.commute_realtr.listing.Listing;

public abstract class Scraper {

	private int regionCode;
	private int minPrice;
	private int maxPrice;
	private WebDriver driver;
	private Configuration options;

	public Scraper(Configuration options, WebDriver driver) {
		super();
		this.regionCode = options.getInt("region.code");
		this.minPrice = options.getInt("price.min");
		this.maxPrice = options.getInt("price.max");
		this.driver = driver;
		this.options = options;
	}

	public abstract List<Listing> getListings();

	protected int getRegionCode() {
		return regionCode;
	}

	protected void setRegionCode(int regionCode) {
		this.regionCode = regionCode;
	}

	protected int getMinPrice() {
		return minPrice;
	}

	protected void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	protected int getMaxPrice() {
		return maxPrice;
	}

	protected void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

	protected WebDriver getDriver() {
		return driver;
	}

	protected void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	protected Configuration getOptions() {
		return options;
	}

	protected void setOptions(Configuration options) {
		this.options = options;
	}
	
	

}