package pandacommander.commute_realtr.crawling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;

public abstract class WebDriverRepository {
	private List<WebDriver> drivers;

	public WebDriverRepository() {
		super();
		drivers = Collections.synchronizedList(new ArrayList<>());
	}

	public abstract WebDriver getNewWebDriver();

	public void stopAllDrivers() {
		drivers.forEach(driver -> driver.close());
	}

	protected void addWebDriver(WebDriver driver) {
		drivers.add(driver);
	}

}
