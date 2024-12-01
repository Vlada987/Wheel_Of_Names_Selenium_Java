package interactions;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import myUtil.MyRobot;

public class Customize extends BaseClass {

	public Customize(WebDriver driver) {
		super(driver);
	}

	Actions actions = new Actions(driver);

	public void setupSpinningTimeAndMessage(int position, String message) {

		click.apply(By.xpath("//i[@class='q-icon fas fa-bars']"));
		click.apply(By.xpath("/html/body/div[4]/div/div/div[1]/div[2]"));

		waiting.apply(By.xpath(
				"//div[@class='q-slider__thumb q-slider__thumb--h q-slider__thumb--h-ltr absolute non-selectable']"));
		WebElement slider = driver.findElements(By.xpath(
				"//div[@class='q-slider__thumb q-slider__thumb--h q-slider__thumb--h-ltr absolute non-selectable']"))
				.get(1);
		actions.moveToElement(slider).build().perform();
		actions.clickAndHold(slider).moveByOffset(-slider.getLocation().getX(), position).release().perform();

		click.apply(By.xpath("//*[contains(text(), 'After')]"));
		wait11.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='We have a winner!']")))
				.sendKeys(message);
		click.apply(By.xpath("//*[text()='OK']"));
	}
	
	
	public List<String> getSomeText() throws AWTException, InterruptedException{
		List<String>list=new ArrayList<>();
		click.apply(By.xpath("//i[@class='q-icon fas fa-bars']"));
		click.apply(By.xpath("/html/body/div[3]/div/div/div[1]/div[3]"));
		list.add(getText.apply(By.xpath("/html/body/div[3]/div/div[2]/div/div[1]/div[2]/div/div/div[4]/b")));
		list.add(getText.apply(By.xpath("/html/body/div[3]/div/div[2]/div/div[1]/div[2]/div/div/div[4]/small")));
		click.apply(By.xpath("//button[@class='q-btn q-btn-item non-selectable no-outline q-btn--unelevated q-btn--rectangle q-btn--actionable q-focusable q-hoverable q-btn--no-uppercase']"));
		return list;
		
		
	}

}
