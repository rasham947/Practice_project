package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	// 1. Private By Locator

	private By email = By.xpath("//input[@placeholder='E-Mail Address']");
	private By password = By.xpath("//input[@placeholder='Password']");
	private By loginbtn = By.xpath("//input[@value='Login']");
	private By forgotpwdlink = By.xpath("//div[@class='list-group']//a[text()='Forgotten Password']");
	private By registerlink=By.xpath("//a[text()='Register' and @class='list-group-item']");

	// 2. Page Constructor
	public LoginPage(WebDriver driver) {

		this.driver = driver;
		eleutil = new ElementUtil(this.driver);
	}

	// 3. Page Actions

	public String getLoginPageTitle() {

		return eleutil.waitForTitleIs(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}

	public String getLoginPageUrl() {

		return eleutil.waitForUrlContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}

	public Accountspage doLogin(String un, String pwd) {

		System.out.println("Login cred are :" + un + " : " + pwd);
		eleutil.waitForElementVisible(email, Constants.DEFAULT_WEBELEMENT_TIME_OUT).sendKeys(un);
		eleutil.doSendKeys(password, pwd);
		eleutil.doClick(loginbtn);
		return new Accountspage(driver);
	}

	public boolean isForgotPwdLinkExist() {

		return eleutil.doIsdisplayed(forgotpwdlink);
	}
	
	public RegisterPage gotoRegisterPage() {
		
		eleutil.doClick(registerlink);
		return new RegisterPage(driver);
	}
	
	

}

