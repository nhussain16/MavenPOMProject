package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Description;

public class AccountsPageTest extends BaseTest{

	@BeforeClass
	public void accPageSetup() {
		accPage = loginPage.DoLogin(prop.getProperty("username"),prop.getProperty("password"));		
	}
	
	@Description("AccPageTitleTest...")
	@Test(priority=1)
	public void AccPageTitleTest() {
		String accPageTitle = accPage.GetAccountPageTitle();
		Assert.assertEquals(accPageTitle, Constants.ACCOUNT_PAGE_TITLE);
	}
	
	@Description("AccPageHeaderTest...")
	@Test(priority=2)
	public void AccPageHeaderTest() {
		String accPageTitle = accPage.GetAccountPageHeader();
		Assert.assertEquals(accPageTitle, Constants.PAGE_HEADER);
	}
	
	@Description("AccSectionListTest...")
	@Test(priority=3)
	public void AccSectionListTest() {
		List<String> actAccSectionList = accPage.GetAccountSectionsList();
		System.out.println("Actual sections " + actAccSectionList);
		Assert.assertEquals(actAccSectionList,Constants.EXPECTED_ACC_SEC_LIST);
	}
	
	@Description("LogoutLinkExistTest...")
	@Test(priority=4)
	public void LogoutLinkExistTest() {		
		Assert.assertTrue(accPage.IsLogoutLinkExist());
	}
	
	@DataProvider
	public Object[][] GetSearchData() {
		return new Object[][] {
								{"Macbook Air"},
								{"Macbook Pro"},
								{"Apple"}
								};
	}
	
	@Description("SearchTest product name: {0}...")
	@Test(priority=5, dataProvider = "GetSearchData")
	public void SearchTest(String productName) {
		resultsPage = accPage.doSearch(productName);
		String resultHeader = resultsPage.GetSearchPageHeader();
		System.out.println("Result header is "+ resultHeader);
		Assert.assertTrue(resultHeader.contains(productName));
	}
	
	/*
	 * @DataProvider public Object[][] getProductSelectData() { return new
	 * Object[][] { {"Macbook","MacBook Air"}, {"Macbook","MacBook Pro"}, {"Apple",
	 * "Apple Cinema 30\""} };
	 * 
	 * }
	 */
	
	@DataProvider public Object[][] getProductSelectData() {
		return ExcelUtil.getTestData(Constants.SEARCH_PRODUCT_SHEET_NAME);
	}
	
	@Description("selectProductTest product name: {0} main product {1}...")
	@Test(priority=6, dataProvider = "getProductSelectData")
	public void selectProductTest(String productName, String MainProductName) {		
		resultsPage = accPage.doSearch(productName);		
		prodInfoPage = resultsPage.selectProduct(MainProductName);
		String header = prodInfoPage.getProductHeaderText();
		System.out.println("Product Header is "+ header);
		Assert.assertEquals(header, MainProductName);
	}
}
