package interactions;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import interfaces.Helper;

public class Editor extends BaseClass {

	public Editor(WebDriver driver) {
		super(driver);
	}

	WebElement shuffleButton = driver.findElements(By.xpath(
			"//button[@class='q-btn q-btn-item non-selectable no-outline q-btn--unelevated q-btn--rectangle bg-grey-3 text-grey-9 q-btn--actionable q-focusable q-hoverable q-btn--no-uppercase']"))
			.get(0);
	WebElement sortButton = driver.findElements(By.xpath(
			"//button[@class='q-btn q-btn-item non-selectable no-outline q-btn--unelevated q-btn--rectangle bg-grey-3 text-grey-9 q-btn--actionable q-focusable q-hoverable q-btn--no-uppercase']"))
			.get(1);

	public Helper<Boolean, String> addNewNames = (String data) -> {
		driver.findElement(By.xpath("//div[@class='basic-editor']")).clear();
		writeTxt(By.xpath("//div[@class='basic-editor']"), data);
		return true;
	};

	public Helper<String, WebDriver> namesCounter = (WebDriver driver) -> {
		WebElement e = driver.findElements(By.xpath("//div[@role='status'][@data-v-fc64632c='']")).get(0);
		return e.getText();
	};

	public Helper<String, WebDriver> resultsCounter = (WebDriver driver) -> {
		WebElement e = driver.findElements(By.xpath(
				"//div[@class='q-badge flex inline items-center no-wrap q-badge--single-line bg-grey-7 q-badge--rounded']"))
				.get(1);
		return e.getText();
	};

	public Helper<Boolean, WebDriver> shuffle = (WebDriver driver) -> {
		wait11.until(ExpectedConditions.elementToBeClickable(shuffleButton)).click();
		return true;
	};

	public Helper<Boolean, WebDriver> sort = (WebDriver driver) -> {
		wait11.until(ExpectedConditions.elementToBeClickable(sortButton)).click();
		return true;
	};

	public Helper<String, WebDriver> resultsData = (WebDriver driver) -> {
		try {
			click.apply(By.xpath("//*[text()='Results']"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getText.apply(By.xpath("//div[@class='results-textbox']"));
	};

}
