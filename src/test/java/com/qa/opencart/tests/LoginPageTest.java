package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Errors;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("100- Design login page for open cart app...")
@Story("US 101: Login page with different features...")
public class LoginPageTest extends BaseTest{

	@Description("Login page title test...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void LoginPageTitleTest() {
		
		String title = loginPage.GetLoginPageTitle();
		System.out.println("Login page title is : " + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE,Errors.TITLE_ERROR_MESSG);
	}
	
	@Description("Login page header test...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void LoginPageHeaderTest() {
		
		String header = loginPage.GetPageHeaderText();
		System.out.println("Login page header is : " + header);
		Assert.assertEquals(header, Constants.PAGE_HEADER,Errors.HEADER_ERROR_MESSG);
	}
	
	@Description("Login page forget password link test...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void forgotPasswordLinkTest() {
		
		Assert.assertTrue(loginPage.IsForgotPwdLinkExist(),Errors.FORGOTPASSWORD_ERROR_MESSG);
	}
	
	@Description("Login test...")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 4)
	public void LoginTest() {
		AccountsPage accPage = loginPage.DoLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertTrue(accPage.IsLogoutLinkExist());
	}
}
