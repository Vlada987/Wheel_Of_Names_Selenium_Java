package interactions;

import java.awt.AWTException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.tika.language.LanguageIdentifier;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import interfaces.Helper;
import myUtil.MyRobot;

public class Language extends BaseClass {

	public Language(WebDriver driver) {
		super(driver);
	}

	public Helper<List<String>, WebDriver> getAllLanguages = (WebDriver driver) -> {
		click.apply(By.xpath("//i[@class='q-icon fas fa-bars']"));
		click.apply(By.xpath("/html/body/div[3]/div/div/div[8]/div[3]"));
		waiting.apply(By.xpath("//input[@placeholder='Search']"));
		try {
			MyRobot.tab(4);
			MyRobot.end();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		return driver.findElements(By.xpath("//div[@class='row items-start q-gutter-md']/div")).stream()
				.map(el -> el.getText()).skip(4).collect(Collectors.toList());
	};

	public String setRandomLanguage() {
		String randomLanguage = getLanguage("randomKey");

		driver.findElements(By.xpath("//div[@class='row items-start q-gutter-md']/div")).stream()
				.filter(el -> el.getText().equals(randomLanguage)).findFirst().get().click();
		return randomLanguage;
	};

	public static String languageDetect(String text) {
		LanguageIdentifier identifier = new LanguageIdentifier(text);
		String language = identifier.getLanguage();
		return language;
	};

	public static <T> T getLanguage(String what) {

		HashMap<String, String> languageMap = new HashMap<>();
		languageMap.put("Azərbaycanca", "az");
		languageMap.put("Bahasa Indonesia", "id");
		languageMap.put("Bahasa Melayu", "ms");
		languageMap.put("Bosanski", "bs");
		languageMap.put("Català", "ca");
		languageMap.put("Čeština", "cs");
		languageMap.put("Cymraeg", "cy");
		languageMap.put("Dansk", "da");
		languageMap.put("Deutsch", "de");
		languageMap.put("English", "en");
		languageMap.put("English (Pirate)", "en_pirate");
		languageMap.put("Español", "es");
		languageMap.put("Estonian", "et");
		languageMap.put("Eλληνικά", "el");
		languageMap.put("Filipino", "tl");
		languageMap.put("Français", "fr");
		languageMap.put("Galego", "gl");
		languageMap.put("Hrvatski", "hr");
		languageMap.put("Íslenskur", "is");
		languageMap.put("Italiano", "it");
		languageMap.put("Latviešu", "lv");
		languageMap.put("Lietuvių", "lt");
		languageMap.put("Magyar", "hu");
		languageMap.put("Mакедонски", "mk");
		languageMap.put("Mонгол", "mn");
		languageMap.put("Nederlands", "nl");
		languageMap.put("Norsk", "no");
		languageMap.put("Oʻzbekcha", "uz");
		languageMap.put("Polski", "pl");
		languageMap.put("Português", "pt");
		languageMap.put("Pусский", "ru");
		languageMap.put("Română", "ro");
		languageMap.put("Shqip", "sq");
		languageMap.put("Slovenščina", "sl");
		languageMap.put("Soomaali", "so");
		languageMap.put("Srpski", "sr");
		languageMap.put("Suomi", "fi");
		languageMap.put("Svenska", "sv");
		languageMap.put("Tiếng Việt", "vi");
		languageMap.put("Türkçe", "tr");
		languageMap.put("български", "bg");
		languageMap.put("Қазақ тілі", "kk");
		languageMap.put("Українська", "uk");
		languageMap.put("ქართული", "ka");
		languageMap.put("Հայերեն", "hy");
		languageMap.put("עברית", "he");
		languageMap.put("اردو", "ur");
		languageMap.put("العربية", "ar");
		languageMap.put("فارسی", "fa");
		languageMap.put("አማርኛ", "am");
		languageMap.put("हिंदी", "hi");
		languageMap.put("বাংলা", "bn");
		languageMap.put("ਪੰਜਾਬੀ", "pa");
		languageMap.put("ગુજરાતી", "gu");
		languageMap.put("தமிழ்", "ta");
		languageMap.put("ಕನ್ನಡ", "kn");
		languageMap.put("മലയാളം", "ml");
		languageMap.put("ไทย", "th");
		languageMap.put("ພາສາລາວ", "lo");
		languageMap.put("မြန်မာစာ", "my");
		languageMap.put("한국어", "ko");
		languageMap.put("日本語", "ja");
		languageMap.put("简体中文", "zh");
		if (what.equals("all")) {
			return (T) languageMap;
		} else if (what.equals("randomValue")) {
			return (T) languageMap.values().stream().skip(new Random().nextInt(languageMap.size())).findFirst().get();
		} else if (what.equals("randomKey")) {
			return (T) languageMap.keySet().stream().skip(new Random().nextInt(languageMap.size())).findFirst().get();
		} else {
			return (T) languageMap.get(what);
		}
	}

}
