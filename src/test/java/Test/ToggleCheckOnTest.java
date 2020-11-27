package Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Page.ToggleCheckOn;
import Util.BrowserFactory;

public class ToggleCheckOnTest {
	public WebDriver driver;
	ToggleCheckOn toggleCheckOn;

	@Test(priority=1)
	public void validateToggleChecked() {
		driver = BrowserFactory.lunchWebsite();
		toggleCheckOn = PageFactory.initElements(driver, ToggleCheckOn.class);
		toggleCheckOn.ADD_LIST_ON_TOGGLE();
		toggleCheckOn.CLICK_CHECKBOX_TOGGLE_ALL();
		Assert.assertTrue(toggleCheckOn.VERIFY_ALL_CHECKBOX_CHECK_ON(), "Toggle items are not checked on");
	}
	
	@Test(priority=2)
	public void validateSingleToggleItemRemove() {
		driver.navigate().refresh();
		toggleCheckOn.REMOVE_SINGLE_TOGGLE_ITEM("3");
		Assert.assertTrue(toggleCheckOn.VALIDATE_REMOVE_SINGLE_TOGGLE_ITEM(),"Single check toggle item is not remove");
		driver.navigate().refresh();
	}
	
	@Test(priority=3)
	public void validateAllToggleItemRemove() throws InterruptedException {
		toggleCheckOn.CLICK_CHECKBOX_TOGGLE_ALL();
		toggleCheckOn.CLICK_REMOVE_BUTTON();
		SoftAssert softAssert = new SoftAssert();
		softAssert.fail("Executing Failed Test Method");
		driver.navigate().refresh();
		//Assert.assertTrue(toggleCheckOn.VALIDATE_REMOVE_ALL_TOGGLE_ITEM(),"All toggle item are not remove");
		softAssert.assertAll();
	}
	
	@Test(priority=4)
	public void tearDown() {
		driver.quit();
	}
	
	
//	@Test(priority=4)
//	public void testFailed() {
//		System.out.println("Executing Failed Test Method");
//		Assert.fail("Executing Failed Test Method");
//	}
//	@Test(priority=5)
//	public void testSkipped() {
//		System.out.println("Executing Skipped Test Method");
//		throw new SkipException("Executing Skipped Test Method");
//	}
	
	
}
