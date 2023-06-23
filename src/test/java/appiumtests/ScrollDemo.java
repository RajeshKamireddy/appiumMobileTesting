package appiumtests;

import java.net.MalformedURLException;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class ScrollDemo extends BaseTest {

	@Test
	public void ScrollDemoTest() throws MalformedURLException, InterruptedException {

		driver.findElement(AppiumBy.accessibilityId("Views")).click();

		// 1st Method, where to scroll is known prior
		// scrollToElementByAndroidUIAutomator("\"WebView\"");

		// 2nd Method, No prior idea
		scrollToEndAction();
		
		Thread.sleep(5000);
		// driver.quit();

	}

}
