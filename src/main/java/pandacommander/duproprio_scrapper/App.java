package pandacommander.duproprio_scrapper;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import pandacommander.duproprio_scrapper.scraping.Scraper;

public class App {
	public static void main(String[] args) throws IOException {

		int regionCode = 6;
		int minPrice = 125000;
		int maxPrice = 275000;
		int maxPageNumber = 2;
		
		// headless chrome driver
		ChromeDriverService.Builder builder = new ChromeDriverService.Builder().withSilent(true);
		ChromeDriverService service = builder.usingAnyFreePort().build();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		ChromeDriver driver = new ChromeDriver(service, options);

		Scraper scraper = new Scraper(regionCode, minPrice, maxPrice, driver);
		List<Listing> listings = scraper.getListings(maxPageNumber);
		for (Listing listing : listings) {
			System.out.println(listing.toString());
		}
	}
}
