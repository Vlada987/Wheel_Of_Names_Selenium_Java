package interactions;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import interfaces.Helper;

public class HomePage extends BaseClass {

	public HomePage(WebDriver driver) {
		super(driver);

	}

	public Helper<Boolean, String> openPage = (String url) -> {
		driver.get(url);
		return true;
	};

	public Helper<String, WebDriver> getTitle = (WebDriver driver) -> {
		return driver.getTitle();
	};

	public Helper<String, WebDriver> getNamesFromPad = (WebDriver driver) -> {
		try {
			click.apply(By.xpath("//*[text()='Entries']"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		waiting.apply(By.xpath("//div[@class='basic-editor']"));
		return getText.apply(By.xpath("//div[@class='basic-editor']"));
	};

//********************************************************************
	public Helper<Boolean, WebDriver> editPageDetails = (WebDriver driver) -> {
		removeAds.apply(driver);
		click.apply(By.xpath(
				"//button[@class='q-btn q-btn-item non-selectable no-outline q-btn--unelevated q-btn--round bg-primary text-white q-btn--actionable q-focusable q-hoverable q-btn--dense']"));
		writeTxt(By.xpath("//input[@placeholder='Wheel title']"), "vladan " + getCurTime.apply(null));
		writeTxt(By.xpath("//textarea[@placeholder='Wheel description']"),
				"This is new wheel " + getCurTime.apply(null));
		click.apply(By.xpath(
				"//button[@class='q-btn q-btn-item non-selectable no-outline q-btn--unelevated q-btn--rectangle bg-primary text-white q-btn--actionable q-focusable q-hoverable q-btn--no-uppercase']"));
		return true;
	};

	public Helper<String[], WebDriver> verifyNewDetails = (WebDriver driver) -> {
		String[] s = new String[2];
		s[0] = getTitle.apply(driver);
		s[1] = getText.apply(By.xpath("//div[@class='break-long-words']"));
		return s;
	};

	public Helper<Boolean, WebDriver> newWheel = (WebDriver driver) -> {
		try {
			click.apply(By.xpath("/html/body/div[4]/div/div/div[2]/div[3]"));
		} catch (Exception e) {
			click.apply(By.xpath("//i[@class='q-icon fas fa-bars']"));
			click.apply(By.xpath("/html/body/div[4]/div/div/div[2]/div[3]"));
		}
		return true;
	};

	public Helper<Boolean, WebDriver> refresh = (WebDriver driver) -> {
		driver.navigate().refresh();
		return true;
	};

	public Helper<Boolean, WebDriver> openLink = (WebDriver driver) -> {

		try {
			click.apply(By.xpath("//a[text()='Join our Discord server']"));
		} catch (Exception e) {
			click.apply(By.xpath("//a[text()='Add our bot']"));
		}
		return true;
	};

	public Helper<Boolean, WebDriver> setDarkTheme = (WebDriver driver) -> {
		click.apply(By.xpath("//i[@class='q-icon fas fa-bars']"));
		click.apply(By.xpath("/html/body/div[4]/div/div/div[7]/div[3]"));
		return true;
	};

	public Helper<List<String>, WebDriver> getButtonTitles = (WebDriver driver) -> {
		click.apply(By.xpath("//i[@class='q-icon fas fa-bars']"));
		return driver.findElements(By.xpath("/html/body/div[3]/div/div/div/div[3]")).stream().map(el -> el.getText())
				.collect(Collectors.toList());
	};

	public Helper<Boolean, WebDriver> closeAds = (WebDriver driver) -> {
		click.apply(By.xpath("//i[@class='q-icon fas fa-times-circle']"));
		return true;
	};

}
