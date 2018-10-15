package pandacommander.duproprio_scrapper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import pandacommander.duproprio_scrapper.crawler.Crawler;
import pandacommander.duproprio_scrapper.crawler.DuproprioCrawler;
import pandacommander.duproprio_scrapper.geocoding.GeoCoder;
import pandacommander.duproprio_scrapper.geocoding.GoogleGeoCoder;
import pandacommander.duproprio_scrapper.parsing.DuproprioListingParser;
import pandacommander.duproprio_scrapper.parsing.ListingParser;
import pandacommander.duproprio_scrapper.template.ListingTemplateEngine;
import pandacommander.duproprio_scrapper.template.ThymeLeafTemplateEngine;

public class App {
	public static void main(String[] args) throws IOException, ConfigurationException {
		
		Configuration secrets = ConfigurationLoader.loadProperties("secret.properties");
		Configuration options = ConfigurationLoader.loadProperties("options.properties");
		
		GeoCoder geoCoder = new GoogleGeoCoder(secrets,options);
		
		// headless chrome driver
		ChromeDriverService.Builder builder = new ChromeDriverService.Builder().withSilent(true);
		ChromeDriverService service = builder.usingAnyFreePort().build();
		ChromeOptions driverOptions = new ChromeOptions();
		driverOptions.addArguments("headless");
		WebDriver driver = new ChromeDriver(service, driverOptions);
		
		Crawler crawler = new DuproprioCrawler(options, driver);
		ListingParser parser = new DuproprioListingParser(options, geoCoder);
		
		ListingTemplateEngine templateEngine = new ThymeLeafTemplateEngine();
		
		ScraperApplication app = new ScraperApplication(crawler, parser, templateEngine);
		app.scrap();
	}
}
