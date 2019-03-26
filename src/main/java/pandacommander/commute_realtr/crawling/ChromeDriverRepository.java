package pandacommander.commute_realtr.crawling;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverRepository extends WebDriverRepository {

	public ChromeDriverRepository() {
		super();
	}

	@Override
	public WebDriver getNewWebDriver() {
		ChromeDriverService.Builder builder = new ChromeDriverService.Builder().withSilent(true);
		ChromeDriverService service = builder.usingAnyFreePort().build();
		ChromeOptions driverOptions = new ChromeOptions();
		driverOptions.addArguments("headless");
		driverOptions.addArguments("--log-level=3");
		driverOptions.addArguments("--silent");
		WebDriver driver = new ChromeDriver(service, driverOptions);
		addWebDriver(driver);

		return driver;
	}
}
