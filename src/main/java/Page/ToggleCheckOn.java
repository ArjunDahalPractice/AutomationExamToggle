package Page;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import Util.BasePage;

public class ToggleCheckOn {

	WebDriver driver;

	public ToggleCheckOn(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.CSS, using = "input[name=data]")
	WebElement ADD_LIST_ON_TOGGLE;
	@FindBy(how = How.CSS, using = "input[value=Add]")
	WebElement CLICK_ADD_BUTTON;
	@FindBy(how = How.CSS, using = "input[name=allbox]")
	WebElement CLICK_CHECKBOX_TOGGLE_ALL;
	@FindBy(how = How.CSS, using = "input[value='Remove']")
	WebElement CLICK_REMOVE_BUTTON;
		
	public void ADD_LIST_ON_TOGGLE() {
		int totalToggle = driver.findElements(By.cssSelector("input[name^=todo]")).size();
		for (int i = totalToggle; i < totalToggle + 5; i++) {
			ADD_LIST_ON_TOGGLE.sendKeys(BasePage.randomStringWithNumber());
			CLICK_ADD_BUTTON.click();
			driver.navigate().refresh();
		}
	}

	public void CLICK_CHECKBOX_TOGGLE_ALL() {
		CLICK_CHECKBOX_TOGGLE_ALL.click();
	}

	public void CLICK_REMOVE_BUTTON() {
		CLICK_REMOVE_BUTTON.click();
	}

	public boolean VERIFY_ALL_CHECKBOX_CHECK_ON() {
		boolean verifyAllCheckBoxCheckOn = false;
		int totalToggle = driver.findElements(By.cssSelector("input[name^=todo]")).size();
		for (int i = 0; i < totalToggle; i++) {
			WebElement allItem = driver.findElement(By.cssSelector("input[name='todo[" + i + "]']"));
			if (allItem.isSelected()) {
				verifyAllCheckBoxCheckOn = true;
			}
		}
		return verifyAllCheckBoxCheckOn;
	}

	List<String> beforeRemovelist = new LinkedList<String>();

	public void createToggleItemList() {
		int totalToggle = driver.findElements(By.cssSelector("input[name^=todo]")).size();
		for (int i = totalToggle; i < totalToggle + 5; i++) {
			String allItemName = driver.findElement(By.cssSelector("form[name='todo']")).getText();
			String updateItemName = allItemName.substring(allItemName.length() - 7);
			beforeRemovelist.add(updateItemName);
		}
	}

	public void REMOVE_SINGLE_TOGGLE_ITEM(String removeToggleItemNumberBetween0to4) {
		WebElement checkSingleItem = driver
				.findElement(By.cssSelector("input[name='todo[" + removeToggleItemNumberBetween0to4 + "]']"));
		checkSingleItem.click();
		CLICK_REMOVE_BUTTON.click();
		driver.navigate().refresh();
	}

	public boolean VALIDATE_REMOVE_SINGLE_TOGGLE_ITEM() {
		boolean validateItem = false;
		List<String> afterRemovelist = new LinkedList<String>();
		int totalToggle = driver.findElements(By.cssSelector("input[name^=todo]")).size();
		for (int i = 0; i < totalToggle; i++) {
			String allItemName = driver.findElement(By.cssSelector("form[name='todo']")).getText();
			String updateItemName = allItemName.substring(allItemName.length() - 7);
			afterRemovelist.add(updateItemName);
			if (!beforeRemovelist.containsAll(afterRemovelist)) {
				validateItem = true;
			}
		}
		return validateItem;
	}

	public boolean VALIDATE_REMOVE_ALL_TOGGLE_ITEM() {
		boolean warningMessage = false;
		String warningString = driver.findElement(By.xpath("//b[contains(.,'Warning')]")).getText();
		if (warningString.equals("Warning")) {
			warningMessage = true;
		}
		return warningMessage;
	}

}
