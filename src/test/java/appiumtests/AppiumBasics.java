package appiumtests;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utils.PropertyFileReader;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumBasics extends BaseTest {

	@Test(enabled = true)
	public void WifiSettingsName() throws InterruptedException, IOException {
		
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
		driver.findElement(By.className("android.widget.CheckBox")).click();
		driver.findElement(By.xpath("//android.widget.LinearLayout[2]")).click();
		driver.findElement(By.id("android:id/edit")).sendKeys("Rajesh");
		driver.findElement(By.id("android:id/button1")).click();
	}
}
