package tests;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import interactions.Customize;
import interactions.Editor;
import interactions.HomePage;
import interactions.Language;
import interactions.Load;
import interactions.Save;
import interactions.Spining;
import myUtil.ImageUtil;
import myUtil.MyData;
import myUtil.MyRobot;
import myUtil.MyUtil;

public class TestClass extends BaseTest {

	HomePage homePage;
	Editor editor;
	Spining spining;
	Customize customize;
	Save save;
	MyData mydata;
	Load load;
	Language language;

	@Test(priority = 0)
	public void test01_open_home_page_and_verify() {

		homePage = new HomePage(driver);
		homePage.openPage.apply("https://wheelofnames.com/");

		String title = homePage.getTitle.apply(driver);
		String names = homePage.getNamesFromPad.apply(driver);
		List<String> list = MyUtil.toList.apply(names);

		Assert.assertEquals(list,
				Arrays.asList("Ali", "Beatriz", "Charles", "Diya", "Eric", "Fatima", "Gabriel", "Hanna"));
		Assert.assertTrue(title.contains("Wheel of Names"));
	}

	@Test(priority = 1)
	public void test02_edit_page_title_and_description() {

		homePage.editPageDetails.apply(driver);
		String[] data = homePage.verifyNewDetails.apply(driver);

		Assert.assertTrue(data[0].contains("vladan " + homePage.getCurTime.apply(null).substring(0, 5))
				&& data[1].contains("This is new wheel " + homePage.getCurTime.apply(null).substring(0, 5)));
	}

	@Test(priority = 2)
	public void test03_adding_names_into_pad() {

		editor = new Editor(driver);
		int target = 5;
		String data = MyUtil.wheelData.apply(target);

		editor.addNewNames.apply(data);
		String counterValue = editor.namesCounter.apply(driver);
		String names = homePage.getNamesFromPad.apply(driver);

		Assert.assertEquals(target + 1, Integer.valueOf(counterValue));
		Assert.assertEquals(MyUtil.toList.apply(data), MyUtil.toList.apply(names));
	};

	@Test(priority = 3)
	public void test04_shuffle_the_names_in_pad() {

		List<String> namesBeforeShuffle = MyUtil.toList.apply(homePage.getNamesFromPad.apply(driver));

		editor.shuffle.apply(driver);
		List<String> namesAfterShuffle = MyUtil.toList.apply(homePage.getNamesFromPad.apply(driver));

		Assert.assertTrue(MyUtil.areEqualIgnorOrder(namesBeforeShuffle, namesAfterShuffle));
		Assert.assertFalse(namesBeforeShuffle.equals(namesAfterShuffle));
	};

	@Test(priority = 4)
	public void test05_sort_the_names_in_pad() {

		List<String> namesBeforeSort = MyUtil.toList.apply(homePage.getNamesFromPad.apply(driver));
		editor.sort.apply(driver);
		List<String> namesAfterSort = MyUtil.toList.apply(homePage.getNamesFromPad.apply(driver));

		Assert.assertFalse(MyUtil.isSorted(namesBeforeSort));
		Assert.assertTrue(MyUtil.isSorted(namesAfterSort));
	};

	@Test(priority = 5, enabled = false, description = "skip 10 seconds of default spin")
	public void test06_basic_spin_of_the_wheel() {

		spining = new Spining(driver);
		Long startTime = spining.spin();
		Object[] obj = spining.winnerPopUpDetails.apply(driver);
		String message = (String) obj[0];
		String winner = (String) obj[1];
		Long endTime = (Long) obj[2];
		spining.closeWinnerPopup.apply(driver);

		Assert.assertTrue(message.equals("We have a winner!"));
		Assert.assertTrue(winner.length() > 2);
		Assert.assertTrue((endTime - startTime) < 11000 && (endTime - startTime) > 9000);

	}

	@Test(priority = 6)
	public void test07_customize_spiningTime_and_message() {

		spining = new Spining(driver);
		String rndMessage = MyUtil.rndMessage();
		customize = new Customize(driver);
		customize.setupSpinningTimeAndMessage(0, rndMessage);

		Long startTime = spining.spin();
		Object[] obj = spining.winnerPopUpDetails.apply(driver);
		String message = (String) obj[0];
		String winner = (String) obj[1];
		Long endTime = (Long) obj[2];
		spining.closeWinnerPopup.apply(driver);

		Assert.assertEquals(rndMessage, message);
		Assert.assertTrue((endTime - startTime) < 2500);
	};

	@Test(priority = 7)
	public void test08_results_counter() {

		String currentCount = editor.resultsCounter.apply(driver);
		String currentData = editor.resultsData.apply(driver);

		spining.fullSpinCiklus.apply(4);

		String afterCount = editor.resultsCounter.apply(driver);
		String afterData = editor.resultsData.apply(driver);

		Assert.assertEquals(currentCount, "1");
		Assert.assertEquals(MyUtil.toList.apply(currentData).size(), 1);
		Assert.assertEquals(afterCount, "5");
		Assert.assertEquals(MyUtil.toList.apply(afterData).size(), 5);
	};

	@Test(priority = 8)
	public void test09_save_wheel_on_myPC() throws AWTException {

		save = new Save(driver);
		save.saveMyWheel.apply(driver);
		String message = save.saveConfirmPopUp.apply(driver);

		Assert.assertTrue(message.contains("vladan"));
		Assert.assertTrue(message.contains("saved successfully"));
	};

	@BeforeGroups(groups = "new_wheel")
	public void test09_01_save_the_data_for_later_comparison() throws AWTException {
		
		MyRobot.esecape();
		String names = homePage.getNamesFromPad.apply(driver);
		String title = homePage.getTitle.apply(driver);
		mydata = new MyData(title,names);
	};

	@Test(priority = 9, groups = "new_wheel")
	public void test10_new_wheel_functionality() throws AWTException {

		homePage.refresh.apply(driver);
		homePage.newWheel.apply(driver);
		String names = homePage.getNamesFromPad.apply(driver);
		String title = homePage.getTitle.apply(driver);

		Assert.assertTrue(names.contains("Beatriz"));
		Assert.assertEquals(title, "Wheel of Names | Random name picker");
	};

	@Test(priority = 10)
	public void test11_load_my_saved_wheel() throws AWTException {

		load = new Load(driver);
		load.loadWheel2.apply(mydata.getTitle().replace(":", "_"));

		String names = homePage.getNamesFromPad.apply(driver);
		String title = homePage.getTitle.apply(driver);

		Assert.assertTrue(title.equals(mydata.getTitle()));
		Assert.assertTrue(names.equals(mydata.getNames()));
	};
	
	@Test(priority=11)
	public void test12_shuffle_and_sort_buttons_disabled_during_spin() {
	
		spining.spin();
		Boolean status = spining.editorButtonsDuringSpin.apply(driver);
		spining.closeWinnerPopup.apply(driver);
		
		Assert.assertTrue(status);
		
	};
	
	@Test(priority=12,enabled=false)
	public void test13_redirection_comunity_link_check() throws InterruptedException {
		
		homePage.openLink.apply(driver);
		Thread.sleep(3222);
		String title=homePage.getTitle.apply(driver);
		
		Assert.assertTrue(title.contains("FAQ"));
		
	};
	
	@Test(priority=13)
	public void test14_set_and_verify_dark_theme() throws InterruptedException, IOException {
		
		homePage.setDarkTheme.apply(driver);
		File image = ImageUtil.fullPrScreen(driver);
		int blackPixels = ImageUtil.countBlackPixels(image);
		
		Assert.assertTrue(blackPixels>500);
		
	};
	
	@BeforeGroups(groups="localization")
	public void closeAllAds() {
		
		homePage.closeAds.apply(driver);
	}
	
	@Test(priority=14,groups="localization")
	public void test15_Languages_setting_and_verification() throws AWTException, InterruptedException {

		language=new Language(driver);
		List<String> allLanguages = language.getAllLanguages.apply(driver);
		
		Assert.assertEquals(allLanguages.size(), 64);
		
		String selectedLanguage = language.setRandomLanguage();		
		List<String>sentences=customize.getSomeText();
		List<String>buttonTitles=homePage.getButtonTitles.apply(driver);
		
		List<String>data=
		(List<String>) MyUtil.mergeLists(sentences, buttonTitles).stream()
		.map(l->language.languageDetect((String) l)).collect(Collectors.toList());
		
		String langResult = MyUtil.findElementOccurringMoreThanTwoTimes(data);
		String langugeAbrActual=language.getLanguage(selectedLanguage);
		
		Assert.assertEquals(langugeAbrActual, langResult);
		};

}
