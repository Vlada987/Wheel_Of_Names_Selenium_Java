package interactions;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import interfaces.Helper;

public class BaseClass {

	public WebDriver driver;
	public WebDriverWait wait11;
	public WebDriverWait wait2;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	public BaseClass(WebDriver driver) {
		this.driver = driver;
		wait11 = new WebDriverWait(driver, Duration.ofSeconds(11));
		wait2 = new WebDriverWait(driver, Duration.ofSeconds(2));

	}

	public Helper<Boolean, By> waiting = (By by) -> {
		wait11.until(ExpectedConditions.visibilityOfElementLocated(by));
		return true;
	};

	public Helper<Boolean, By> click = (By by) -> {
		wait11.until(ExpectedConditions.elementToBeClickable(by));
		
		driver.findElement(by).click();
		return true;
	};

	public void writeTxt(By by, String text) {
		waiting.apply(by);
		driver.findElement(by).sendKeys(text);
	}

	public Helper<String, By> getText = (By by) -> {
		waiting.apply(by);
		return driver.findElement(by).getText();
	};

	public Helper<Boolean, WebDriver> removeAds = (WebDriver driver) -> {
		try {
			String removeAdsScript = "var elements = document.querySelectorAll('div.ad, iframe, .ad-banner, .ads');"
					+ "elements.forEach(function(el) { el.style.display = 'none'; });";
			js.executeScript(removeAdsScript);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	};

	public Helper<String, Void> getCurTime = (Void v) -> {
		LocalTime currentTime = LocalTime.now();
		return currentTime.toString();
	};

}
