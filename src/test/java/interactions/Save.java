package interactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import interfaces.Helper;

public class Save extends BaseClass {

	public Save(WebDriver driver) {
		super(driver);

	}

	public Helper<Boolean, WebDriver> saveMyWheel = (WebDriver driver) -> {
		click.apply(By.xpath("//i[@class='q-icon fas fa-bars']"));
		click.apply(By.xpath("/html/body/div[4]/div/div/div[4]/div[2]/i"));
		click.apply(By.xpath(
				"//button[@class='q-btn q-btn-item non-selectable no-outline q-btn--unelevated q-btn--rectangle bg-grey-7 text-white q-btn--actionable q-focusable q-hoverable q-btn--no-uppercase']"));
		click.apply(By.xpath(
				"//button[@class='q-btn q-btn-item non-selectable no-outline q-btn--unelevated q-btn--rectangle bg-primary text-white q-btn--actionable q-focusable q-hoverable q-btn--no-uppercase']"));
		return true;
	};

	public Helper<String, WebDriver> saveConfirmPopUp = (WebDriver driver) -> {
		waiting.apply(By.xpath("//div[@class='q-notification__message col']"));
		return driver.findElement(By.xpath("//div[@class='q-notification__message col']")).getText();
	};

}
