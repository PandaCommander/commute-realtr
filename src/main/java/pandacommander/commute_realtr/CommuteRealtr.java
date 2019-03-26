package pandacommander.commute_realtr;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import pandacommander.commute_realtr.geocoding.GeoCoder;
import pandacommander.commute_realtr.geocoding.GoogleGeoCoder;
import pandacommander.commute_realtr.parsing.DefaultListingParser;
import pandacommander.commute_realtr.parsing.ListingParser;
import pandacommander.commute_realtr.scraping.Scraper;
import pandacommander.commute_realtr.scraping.DuproprioScraper;
import pandacommander.commute_realtr.template.ListingTemplateEngine;
import pandacommander.commute_realtr.template.ThymeLeafTemplateEngine;

public class CommuteRealtr {
	public static void main(String[] args) throws IOException, ConfigurationException {

		Configuration secrets = ConfigurationLoader.loadProperties("secret.properties");
		Configuration options = ConfigurationLoader.loadProperties("options.properties");

		GeoCoder geoCoder = new GoogleGeoCoder(secrets, options);

		// headless chrome driver
		ChromeDriverService.Builder builder = new ChromeDriverService.Builder().withSilent(true);
		ChromeDriverService service = builder.usingAnyFreePort().build();
		ChromeOptions driverOptions = new ChromeOptions();
		driverOptions.addArguments("headless");
		WebDriver driver = new ChromeDriver(service, driverOptions);

		Scraper crawler = new DuproprioScraper(options, driver);
		ListingParser parser = new DefaultListingParser(options, geoCoder);

		ListingTemplateEngine templateEngine = new ThymeLeafTemplateEngine();

		ListingGenerator app = new ListingGenerator(crawler, parser, templateEngine);
		app.GenerateListings();
	}
}
