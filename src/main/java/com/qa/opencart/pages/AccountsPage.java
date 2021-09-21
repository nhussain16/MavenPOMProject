package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By accSections = By.cssSelector("div#content h2");
	private By header = By.cssSelector("div#logo h1 a");
	private By logoutLink = By.linkText("Logout");
	private By searchField = By.name("search");
	private By searchButton = By.cssSelector("div#search button");
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String GetAccountPageTitle() {
		return elementUtil.waitForTitleIs(Constants.ACCOUNT_PAGE_TITLE, 5);
	}
	
	public String GetAccPageURL() {
		return elementUtil.waitForUrlContains(Constants.ACCOUNT_PAGE_URL_FRACTION, 5);
	}
	
	public String GetAccountPageHeader() {
		return elementUtil.doGetText(header);
	}
	
	public List<String> GetAccountSectionsList() {
		List<String> AccSecValueList = new ArrayList<String>();
		List<WebElement> AccSecList = elementUtil.waitForElementsToBeVisible(accSections, 5);
		for(WebElement e : AccSecList) {
			AccSecValueList.add(e.getText());
		}
		return AccSecValueList;
	}
	
	public Boolean IsLogoutLinkExist() {
		return elementUtil.doIsDisplayed(logoutLink);		
	}
	
	public ResultsPage doSearch(String productName) {
		System.out.println("Searching product "+ productName);
		elementUtil.doSendKeys(searchField, productName);
		elementUtil.doClick(searchButton);
		return new ResultsPage(driver);
	}
}
