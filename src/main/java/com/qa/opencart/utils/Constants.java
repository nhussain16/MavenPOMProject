package com.qa.opencart.utils;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.util.Arrays;
import java.util.List;

public class Constants {

	public static final int DEFAULT_TIME_OUT = 5;
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String PAGE_HEADER = "Your Store";
	
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	public static final String ACCOUNT_PAGE_URL_FRACTION = "route=account/account";
	
	public static final List<String> EXPECTED_ACC_SEC_LIST = Arrays.asList("My Account",
																		   "My Orders",
																		   "My Affiliate Account",
																		   "Newsletter");
	
	public static final String REGISTER_SUCCESS_MSG = "Your Account Has Been Created";
	public static final String REGISTER_SHEET_NAME = "register";
	public static final String SEARCH_PRODUCT_SHEET_NAME = "search";
	public static final String SELECT_PRODUCT_SHEET_NAME = "product";
}
