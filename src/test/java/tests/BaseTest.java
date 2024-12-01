package tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import webDrivers.SetupDriver;

public class BaseTest {

	public WebDriver driver;

	@BeforeTest
	@Parameters({ "os", "browser" })
	public void setRemoteToParallel(String os, String browser) throws MalformedURLException {
		driver = SetupDriver.remoteForParallel(browser, os);
	}

// @BeforeTest
	public void setupLocalMozzila() {

		driver = SetupDriver.localMozila();

	}

	@AfterTest
	public void shutDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
