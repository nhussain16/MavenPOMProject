package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationsPageTest extends BaseTest{
	
	@BeforeClass
	public void RegSetup() {
		regPage = loginPage.ClickOnRegisterLink();
	}
	
	public String getRandomEmail() {
		Random rand = new Random();
		String email = "testautomation"+rand.nextInt(5000)+"@gmail.com";
		System.out.println(email);
			return email;
	}
	
	@DataProvider
	public Object[][] GetRegTestData() {
		return ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		/*
		 * @DataProvider
		 * public Object[][] GetRegTestData() {
		 * return new Object[][] { {"Anabiya", "Fahad", "0123456789", "12345678",
		 * "yes"}, {"Nadia", "Zeeshan", "0123333789", "12345678", "no"}, {"Omar",
		 * "Fahad", "258888888", "12345678", "no"} };
		 */
	}

	@Test(dataProvider="GetRegTestData")
	public void registrationsTest(String firstName, String lastName, String phoneNo, String pwd, String subscribe) {
		
		Assert.assertTrue(regPage.accountRegistration(firstName, lastName, getRandomEmail(), phoneNo, pwd, subscribe));
	}
}
