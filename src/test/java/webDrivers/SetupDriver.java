package webDrivers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SetupDriver {

	static WebDriver driver;
	static String path = "C:\\Users\\zikaz\\OneDrive\\Desktop\\projects\\wheelnames\\geckodriver.exe";
	static String downloadDir = "C:\\Users\\zikaz\\OneDrive\\Desktop";
	static String sauceUrl = "https://ondemand.eu-central-1.saucelabs.com:443/wd/hub";

	public static WebDriver remoteForParallel(String browser, String os) throws MalformedURLException {

		String username = System.getenv("sauceUsername");
		String accessKey = System.getenv("saucePassword");

		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions browserOptions = new ChromeOptions();
			browserOptions.setPlatformName(os);
			browserOptions.setBrowserVersion("latest");
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("download.default_directory", downloadDir);
			prefs.put("download.prompt_for_download", false);
			prefs.put("download.directory_upgrade", true);
			prefs.put("safebrowsing.enabled", true);
			prefs.put("zoom.level", 0.8);
			browserOptions.setExperimentalOption("prefs", prefs);
			Map<String, Object> sauceOptions = new HashMap<>();
			sauceOptions.put("username", username);
			sauceOptions.put("accessKey", accessKey);
			sauceOptions.put("build", "1.12.24");
			sauceOptions.put("name", "Sunday 1.12");
			sauceOptions.put("screenResolution", "1280x960");
			browserOptions.setCapability("sauce:options", sauceOptions);
			URL url = new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub");
			driver = new RemoteWebDriver(url, browserOptions);

		} else if (browser.equalsIgnoreCase("firefox")) {
			FirefoxOptions browserOptions = new FirefoxOptions();
			browserOptions.setPlatformName(os);
			browserOptions.setBrowserVersion("latest");
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("zoom.minPercent", 80);
			profile.setPreference("zoom.maxPercent", 80);
			profile.setPreference("zoom.currentValue", 80);
			profile.setPreference("browser.download.folderList", 2);
			profile.setPreference("browser.download.dir", downloadDir);
			profile.setPreference("browser.download.useDownloadDir", true);
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf,application/octet-stream");
			browserOptions.setProfile(profile);
			Map<String, Object> sauceOptions = new HashMap<>();
			sauceOptions.put("username", username);
			sauceOptions.put("accessKey", accessKey);
			sauceOptions.put("build", "1.12.24");
			sauceOptions.put("name", "Sunday 1.12");
			sauceOptions.put("screenResolution", "1280x960");
			browserOptions.setCapability("sauce:options", sauceOptions);
			URL url = new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub");
			driver = new RemoteWebDriver(url, browserOptions);

		}
		return driver;
	}

	public static WebDriver localMozila() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("zoom.minPercent", 80);
		profile.setPreference("zoom.maxPercent", 80);
		profile.setPreference("zoom.currentValue", 80);
		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.download.dir", downloadDir);
		profile.setPreference("browser.download.useDownloadDir", true);
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf,application/octet-stream");

		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(profile);
		System.setProperty("webdriver.gecko.driver", path);
		driver = new FirefoxDriver(options);
		driver.manage().window().maximize();
		return driver;
	};

}
