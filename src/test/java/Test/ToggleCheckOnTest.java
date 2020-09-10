package Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import Page.ToggleCheckOn;
import Util.BasePage;
import Util.BrowserFactory;

public class ToggleCheckOnTest {
	WebDriver driver;
	ToggleCheckOn toggleCheckOn;

	@Test(priority=1)
	public void validateToggleChecked() {
		driver = BrowserFactory.lunchWebsite();
		toggleCheckOn = PageFactory.initElements(driver, ToggleCheckOn.class);
		toggleCheckOn.ADD_LIST_ON_TOGGLE();
		toggleCheckOn.CLICK_CHECKBOX_TOGGLE_ALL();
		Assert.assertTrue(toggleCheckOn.VERIFY_ALL_CHECKBOX_CHECK_ON(), "Toggle items are not checked on");
		BasePage.takeSnapshot(driver, " ToggleCheckedOn ");
	}
	
	@Test(priority=2)
	public void validateSingleToggleItemRemove() {
		driver.navigate().refresh();
		toggleCheckOn.REMOVE_SINGLE_TOGGLE_ITEM("3");
		Assert.assertTrue(toggleCheckOn.VALIDATE_REMOVE_SINGLE_TOGGLE_ITEM(),"Single check toggle item is not remove");
		driver.navigate().refresh();
		BasePage.takeSnapshot(driver, " Item_3_Remove ");
	}
	
	@Test(priority=3)
	public void validateAllToggleItemRemove() throws InterruptedException {
		toggleCheckOn.CLICK_CHECKBOX_TOGGLE_ALL();
		toggleCheckOn.CLICK_REMOVE_BUTTON();
		driver.navigate().refresh();		
		Assert.assertTrue(toggleCheckOn.VALIDATE_REMOVE_ALL_TOGGLE_ITEM(),"All toggle item are not remove");
		BasePage.takeSnapshot(driver, " RemoveALL_ToggleItem ");
		BasePage.tearDown(driver);
	}
}
