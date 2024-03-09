package com.qa.opencart.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constants;

public class LoginPageTest extends BaseTest {
	

	@Test
	public void loginPageTitleTest() {

		String actTitle = loginpage.getLoginPageTitle();
		System.out.println("Login page Title: " + actTitle);
		Assert.assertEquals(actTitle, Constants.LOGIN_PAGE_TITLE);

	}

	@Test
	public void loginPageURLTest() {

		String actUrl = loginpage.getLoginPageUrl();
		System.out.println("Login page URL is: " + actUrl);
		Assert.assertTrue(actUrl.contains(Constants.LOGIN_PAGE_URL_FRACTION));

	}

	@Test
	public void forgotPwdLinkExistTest() {

		Assert.assertTrue(loginpage.isForgotPwdLinkExist());
	}

	@Test
	public void loginTest() {
		Assert.assertTrue(loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim())
				.isLogoutExist());

	}

}

