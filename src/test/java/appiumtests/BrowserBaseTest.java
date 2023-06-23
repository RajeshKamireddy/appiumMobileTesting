package appiumtests;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import Utils.PropertyFileReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BrowserBaseTest {

	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	PropertyFileReader prop = new PropertyFileReader();
	@BeforeTest
	public void ConfigureAppium() throws IOException, InterruptedException {

		service = new AppiumServiceBuilder().withAppiumJS(new File(prop.getProperty("filePath")))
				.withIPAddress(prop.getProperty("ipAddress")).usingPort(prop.getPropertyInt("port"))
				.withTimeout(Duration.ofSeconds(400)).build();
		service.start();

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(prop.getProperty("deviceName"));
		options.setChromedriverExecutable(prop.getProperty("chromeDriverPath"));
		options.setCapability("browserName", "Chrome");

		driver = new AndroidDriver(new URL(prop.getProperty("url")), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	public Double getFormattedAmount(String amount) {
		Double price = Double.parseDouble(amount.substring(1));
		return price;

	}

	@AfterTest
	public void tearDown() {
		driver.quit();
		service.stop();
	}

}
