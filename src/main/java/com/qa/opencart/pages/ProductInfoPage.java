package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	
	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By productQuantity = By.id("input-quantity");
	private By addToCartbtn = By.id("button-cart");
	
	private Map<String, String> productInfoMap;

	private ElementUtil elementUtil;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getProductHeaderText() {
		return elementUtil.doGetText(productHeader);
	}
	
	public int getProductImagesCount() {
		return elementUtil.getElements(productImages).size();
	}
	
	public Map<String, String> getProductInfo() {
		productInfoMap = new HashMap<String, String>();
		productInfoMap.put("name", getProductHeaderText());
		
		List<WebElement> metaDataList = elementUtil.getElements(productMetaData);
		System.out.println("Total product meta data list :"+metaDataList.size());
		
		for(WebElement e : metaDataList) {
			String[] metaData = e.getText().split(":");
			String metaKey = metaData[0].trim();
			String metaValue = metaData[1].trim();
			productInfoMap.put(metaKey,metaValue);
		}
		
		//price data
		
		List<WebElement> priceList = elementUtil.getElements(productPriceData);
		System.out.println("total product price list " + priceList.size());
		
		String price = priceList.get(0).getText().trim();
		String exPrice = priceList.get(1).getText().trim();
		
		productInfoMap.put("price", price);
		productInfoMap.put("exTaxPrice", exPrice);
		
		return productInfoMap;
		
	}
	
	

}
