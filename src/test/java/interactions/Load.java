package interactions;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import interfaces.Helper;
import myUtil.MyRobot;

public class Load extends BaseClass {

	public Load(WebDriver driver) {
		super(driver);

	}

	public Helper<Boolean, String> loadWheel2 = (String fileName) -> {
		click.apply(By.xpath("//i[@class='q-icon fas fa-bars']"));
		click.apply(By.xpath("/html/body/div[4]/div/div/div[3]/div[3]"));
		click.apply(By.xpath("//*[text()='Open a local file']"));
		MyRobot.copyTextToClipboard(fileName);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			MyRobot.pasteValue();
			Thread.sleep(300);
			MyRobot.down();
			Thread.sleep(350);
			MyRobot.enter();
		} catch (AWTException | InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	};

	public Helper<Boolean, String> loadWheel = (String fileName) -> {
		click.apply(By.xpath("//i[@class='q-icon fas fa-bars']"));
		click.apply(By.xpath("/html/body/div[4]/div/div/div[3]/div[3]"));
		click.apply(By.xpath("//*[text()='Open a local file']"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			MyRobot.writeText(fileName);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		try {
			MyRobot.down();
			MyRobot.enter();

		} catch (AWTException e) {
			e.printStackTrace();
		}
		return true;

	};

}
