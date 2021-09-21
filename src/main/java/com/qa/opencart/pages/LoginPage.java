package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	//private By locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.xpath("//div[@class='form-group']//a[text()='Forgotten Password']");
	private By header = By.cssSelector("div#logo h1 a");	
	private By RegisterLink = By.linkText("Register");
	
	//Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	//Page actions/methods/behavior/functionality
	@Step("getting login page title...")
	public String GetLoginPageTitle() {
		return elementUtil.waitForTitleIs(Constants.LOGIN_PAGE_TITLE, 5);
	}
	
	@Step("getting login page header...")
	public String GetPageHeaderText() {
		return elementUtil.doGetText(header);
	}
	
	@Step("checking forgot password link exist...")
	public Boolean IsForgotPwdLinkExist() {
		return elementUtil.doIsDisplayed(forgotPwdLink);
	}
	
	@Step("login to app with username{0} and password {1}...")
	public AccountsPage DoLogin(String un, String pwd) {
		elementUtil.doSendKeys(emailId, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	@Step("click on registration link from login page...")
	public RegistrationsPage ClickOnRegisterLink() {
		elementUtil.doClick(RegisterLink);
		return new RegistrationsPage(driver);
	}

}
