package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class DashboardPage extends BasePage {

	WebDriver driver;

	public DashboardPage(WebDriver driver) {
		this.driver = driver;

	}

	@FindBy(how = How.CSS, using = "input[name='categorydata']")
	WebElement categoryField;
	@FindBy(how = How.CSS, using = "input[value='Add category']")
	WebElement addCategoryButton;
	@FindBy(how = How.CSS, using = "select[name='colour']")
	WebElement cDrop;
	@FindBy(how = How.LINK_TEXT, using = "Add the category with this colour")
	WebElement confirmColorChange;
	@FindBy(how = How.CSS, using = "div.controls>a>span")
	List<WebElement> categoryList;
	@FindBy(how = How.LINK_TEXT, using = "Nevermind")
	WebElement neverMindLink;
	@FindBy(how = How.CSS, using = "select[name='due_month']>option")
	List<WebElement> months;

	public String insertIntoCategoryField(String categoryName) {
		String categoryFormattedName = categoryName + generateRandomNumber();
		categoryField.sendKeys(categoryFormattedName);
		return categoryFormattedName;
	}

	public void clickonAddCategoryButton() {
		addCategoryButton.click();
	}

	public void selectFromColorDropDown(String color) {
		Select colorDropDown = new Select(cDrop);
		colorDropDown.selectByVisibleText(color);
	}

	public void clickOnConfirmColorChange() {
		if (confirmColorChange.isDisplayed()) {
			confirmColorChange.click();
		}

	}

	public void validateAddedCategoryIsDisplayed(String categoryName) {
		String lastInsertedCategoryValue = categoryList.get(categoryList.size() - 1).getText();
		Assert.assertEquals(lastInsertedCategoryValue, categoryName);
	}

	public String insertIntoCategoryFieldConstantValue(String categoryName) {
		categoryField.sendKeys(categoryName);
		return categoryName;
	}

	public void validateIfCategoryAlreadyExists(String categoryName) {

		if (driver.getCurrentUrl().equals("https://techfios.com/test/107/todo.php")) {

			neverMindLink.click();
		}
		for (WebElement e : categoryList) {
			if (e.getText().equals(categoryName)) {
				SoftAssert softAssertion = new SoftAssert();
				softAssertion.assertFalse(e.getText().equals(categoryName), "The Category Already Exists");
				System.out.printf("The category you want to add already exists: %s\n", categoryName);
			}
		}
	}

	public void validateMonths() {
		String[] monthsArr = { "", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		for (int i = 1; i < monthsArr.length; i++) {
			System.out.print(months.get(i).getText() + " ");
			Assert.assertEquals(months.get(i).getText(), monthsArr[i]);
		}
		System.out.println();
	}
}
