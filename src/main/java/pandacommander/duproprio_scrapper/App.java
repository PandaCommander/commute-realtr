package pandacommander.duproprio_scrapper;

import java.io.IOException;
import java.util.List;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class App {
	public static void main(String[] args) throws IOException, ConfigurationException {
		
		Configuration secrets = ConfigurationLoader.loadProperties("secret.properties");
		Configuration options = ConfigurationLoader.loadProperties("options.properties");

		int regionCode = options.getInt("region.code");
		int minPrice = options.getInt("price.min");
		int maxPrice = options.getInt("price.max");
		int maxPageNumber = options.getInt("page.number.max");
		
		String commuteAddress = options.getString("commute.address");
		int maxCommuteTime = options.getInt("commute.time.max");
		List<String> metroStations = options.getList(String.class,"metro.stations");
		
		GeoCoder geoCoder = new GoogleGeoCoder(secrets);
		AddressCoordinates commuteDestinationCoordinates = geoCoder.getAddressCoordinates(commuteAddress);
		
		// headless chrome driver
		ChromeDriverService.Builder builder = new ChromeDriverService.Builder().withSilent(true);
		ChromeDriverService service = builder.usingAnyFreePort().build();
		ChromeOptions driverOptions = new ChromeOptions();
		driverOptions.addArguments("headless");
		ChromeDriver driver = new ChromeDriver(service, driverOptions);

		Scraper scraper = new DuproprioScraper(regionCode, minPrice, maxPrice, driver);
		List<Listing> listings = scraper.getListings(maxPageNumber);
		for (Listing listing : listings) {
			System.out.println(geoCoder.getCommuteTimeSeconds(listing.getAddressCoordinates(),commuteDestinationCoordinates)/60);
		}

	}
}
