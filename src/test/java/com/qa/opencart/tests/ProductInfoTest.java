package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class ProductInfoTest extends BaseTest{
	
	@BeforeClass
	public void productInfoPageSetup() {
		accPage = loginPage.DoLogin(prop.getProperty("username"),prop.getProperty("password"));		
	}
	
	@Test(priority=1)
	public void ProductImagesTest() {
		resultsPage = accPage.doSearch("iMac");
		prodInfoPage = resultsPage.selectProduct("iMac");
		Assert.assertEquals(prodInfoPage.getProductImagesCount(),3);
	}

		@DataProvider
		public Object[][] getProductInfo(){
			return ExcelUtil.getTestData(Constants.SELECT_PRODUCT_SHEET_NAME);
			
		}
		@Test(dataProvider = "getProductInfo")
		public void ProdInfoTest(String search, String MP, String name, String brand, String code, String pts, String price) {
			
			resultsPage = accPage.doSearch(search);
			prodInfoPage = resultsPage.selectProduct(MP);
			Map<String, String> actualProductInfoMap = prodInfoPage.getProductInfo();
			
			softAssert.assertEquals(actualProductInfoMap.get("name"), MP);
			softAssert.assertEquals(actualProductInfoMap.get("Brand"), brand+"11");
			
			softAssert.assertEquals(actualProductInfoMap.get("Product Code"),code); 
			softAssert.assertEquals(actualProductInfoMap.get("Reward Points"), pts);
			softAssert.assertEquals(actualProductInfoMap.get("price"), price);
			softAssert.assertAll();
			 			
		}
		
	
	/*
	 * @Test(priority=1) public void ProdInfoTest() { resultsPage =
	 * accPage.doSearch("Macbook"); prodInfoPage =
	 * resultsPage.selectProduct("MacBook Pro"); Map<String, String>
	 * actualProductInfoMap = prodInfoPage.getProductInfo();
	 * softAssert.assertEquals(actualProductInfoMap.get("name"), "MacBook Pro");
	 * softAssert.assertEquals(actualProductInfoMap.get("Brand"), "Apple");
	 * softAssert.assertEquals(actualProductInfoMap.get("Product Code"),
	 * "Product 18");
	 * softAssert.assertEquals(actualProductInfoMap.get("Reward Points"), "800");
	 * softAssert.assertEquals(actualProductInfoMap.get("price"), "$2,000.00");
	 * softAssert.assertAll(); }
	 */
	 	
}
