package interactions;

import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import interfaces.Helper;

public class Spining extends BaseClass {

	Actions actions = new Actions(driver);

	public Spining(WebDriver driver) {
		super(driver);
	}

	public Long spin() {
		try {
			click.apply(By.xpath("//canvas"));
		} catch (Exception e) {
			e.printStackTrace();
			actions.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).perform();
		}
		return System.currentTimeMillis();
	};

	public Helper<Object[], WebDriver> winnerPopUpDetails = (WebDriver driver) -> {
		Object[] data = new Object[3];
		data[0] = getText.apply(By.xpath("//div[@class='text-h6']"));
		data[1] = getText.apply(By.xpath("//span[@class='q-pr-md winner-text']/span"));
		data[2] = System.currentTimeMillis();
		return data;
	};

	public Helper<Boolean, WebDriver> closeWinnerPopup = (WebDriver driver) -> {
		return click.apply(By.xpath(
				"//button[@class='q-btn q-btn-item non-selectable no-outline q-btn--unelevated q-btn--rectangle q-btn--actionable q-focusable q-hoverable q-btn--no-uppercase']"));
	};

	public Helper<Boolean, Integer> fullSpinCiklus = (Integer i) -> {
		for (int a = 0; a < i; a++) {
			spin();
			closeWinnerPopup.apply(driver);
		}
		return true;
	};
	
	public Helper<Boolean,WebDriver>editorButtonsDuringSpin=(WebDriver driver)->{
		Boolean buttonsStatus=false;
		
		if(driver.findElements(By.xpath("//button[@class='q-btn q-btn-item non-selectable no-outline q-btn--unelevated q-btn--rectangle bg-grey-3 text-grey-9 q-btn--actionable q-focusable q-hoverable q-btn--no-uppercase']"))
		.stream().filter(WebElement::isEnabled)  
        .collect(Collectors.toList()).size()==0) {
			buttonsStatus=true;
		}
		return buttonsStatus;
	};
	
	

}
