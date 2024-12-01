package myUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ImageUtil {

	public static File fullPrScreen(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	};

	public static File elementPrScreen(WebDriver driver, By by) {
		WebElement element = driver.findElement(by);
		return element.getScreenshotAs(OutputType.FILE);
	};

	public static int countBlackPixels(File imageFile) throws IOException {

		BufferedImage image = ImageIO.read(imageFile);
		int blackPixelCount = 0;

		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				int pixel = image.getRGB(x, y);

				int red = (pixel >> 16) & 0xFF;
				int green = (pixel >> 8) & 0xFF;
				int blue = pixel & 0xFF;
				if (red == 0 && green == 0 && blue == 0) {
					blackPixelCount++;
				}
			}
		}
		return blackPixelCount;
	}

}
