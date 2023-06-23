package appiumtests;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import Utils.PropertyFileReader;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {

	public AppiumDriverLocalService service;
	public AndroidDriver driver;
	// PropertyFile prop1= new PropertyFile();
	PropertyFileReader prop = new PropertyFileReader();

	@BeforeTest
	public void ConfigureAppium() throws IOException {

		service = new AppiumServiceBuilder().withAppiumJS(new File(prop.getProperty("filePath")))
				.withIPAddress(prop.getProperty("ipAddress")).usingPort(prop.getPropertyInt("port"))
				.withTimeout(Duration.ofSeconds(300)).build();
		service.start();
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(prop.getProperty("deviceName"));
		//options.setApp(prop.getProperty("apiDemosAppPath")); //-->API-Demos App Path
		options.setApp(prop.getProperty("generalStoreAppPath"));  //-->General-Store App Path
		options.setChromedriverExecutable(prop.getProperty("chromeDriverPath"));
		
		driver = new AndroidDriver(new URL(prop.getProperty("url")), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	public void longPressAction(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(),
								"duration", 2000));
	}

	public void scrollToElementByAndroidUIAutomator(String ele) {
		WebElement scrolled;
		do {
			scrolled = driver.findElement(
					AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text("+ele+"));"));
		} while (!scrolled.isDisplayed());
	}
	
	
	public void scrollToEndAction() {
		boolean canScrollMore;
		do {
		canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
		    "left", 200, "top", 200, "width", 400, "height", 400,
		    "direction", "down",
		    "percent", 3.0
		));
		}while(canScrollMore);
	}
	
	public void swipeAction(WebElement ele,String direction)
	{
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement)ele).getId(),
			    "direction", direction,
			    "percent", 0.75
			));	
	}
	
	public void dragAndDrop(WebElement ele, int Xcord, int Ycord )
	{
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(),
						"endX", Xcord,
						"endY", Ycord));
	}
	
	public Double getFormattedAmount(String amount)
	{
		Double price = Double.parseDouble(amount.substring(1));
		return price;
		
	}

	@AfterTest
	public void tearDown() throws InterruptedException {
		
		service.stop();

	}

	@Test(enabled = false)
	public void OpenCalculator() throws IOException {

		service = new AppiumServiceBuilder().withAppiumJS(new File(prop.getProperty("filePath")))
				.withIPAddress(prop.getProperty("ipAddress")).usingPort(prop.getPropertyInt("port"))
				.withTimeout(Duration.ofSeconds(300)).build();
		service.start();
		UiAutomator2Options cap = new UiAutomator2Options();
		cap.setCapability("deviceName", "I2202");
		cap.setCapability("udid", "13888980160004Y");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "13");
		cap.setCapability("appPackage", "com.vivo.calculator");
		cap.setCapability("appActivity", "com.vivo.calculator.Calculator");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), cap);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
}

