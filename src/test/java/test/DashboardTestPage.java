package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.DashboardPage;
import util.BrowserFactory;

public class DashboardTestPage {

	WebDriver driver;
	DashboardPage dashboardPage;
	String categoryName;

	@BeforeMethod
	public void initializeBrowser() {
		driver = BrowserFactory.init();
		dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
		dashboardPage.clickonAddCategoryButton();

	}

	@Test
	public void addCatagoryAndValidate() {
		categoryName = dashboardPage.insertIntoCategoryField("Furniture");
		dashboardPage.selectFromColorDropDown("Burnt Orange");
		dashboardPage.clickonAddCategoryButton();
		dashboardPage.clickOnConfirmColorChange();
	}

	@Test
	public void userShouldNotBeAbleToAddDuplicateCategory() {
		String constantCategory = "MagentusTheCreamy";
		dashboardPage.insertIntoCategoryFieldConstantValue(constantCategory);
		dashboardPage.clickonAddCategoryButton();
		dashboardPage.validateIfCategoryAlreadyExists(constantCategory);

	}

	@Test
	public void validateMonthDropDown() {
		dashboardPage.validateMonths();
	}
}
