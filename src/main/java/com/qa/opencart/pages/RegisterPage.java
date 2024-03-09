package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil eleutil;
	private By firstName = By.xpath("//input[@placeholder='First Name']");
	private By lastName = By.xpath("//input[@placeholder='Last Name']");
	private By email = By.xpath("//input[@placeholder='E-Mail']");
	private By telephone = By.xpath("//input[@placeholder='Telephone']");
	private By password = By.xpath("//input[@placeholder='Password']");
	private By confirmpassword = By.xpath("//input[@placeholder='Password Confirm']");
	private By subscribeyes = By.xpath("//label[@class='radio-inline']//input[@value='1']");
	private By subscribeno = By.xpath("//label[@class='radio-inline']//input[@value='0']");
	private By agreecheckbox = By.xpath("//input[@name='agree']");
	private By continuebtn = By.xpath("//input[@value='Continue']");
	private By registerSuccessMsg=By.xpath("//div[@id='content']//h1");
	private By logoutLink=By.xpath("//a[@class='list-group-item'and text()='Logout']");
	private By registerLink =By.xpath("//a[@class='list-group-item' and text()='Register']");
	
	public RegisterPage(WebDriver driver) {

		this.driver = driver;
		eleutil = new ElementUtil(this.driver);
	}

	public boolean  registerUser(String firstName, String lastName, String email, String telephone, String password,
			String subscribe) {
		WebElement firstName_ele=eleutil.waitForElementVisible(this.firstName, Constants.DEFAULT_TIME_OUT);
		firstName_ele.clear();
		firstName_ele.sendKeys(firstName);
		eleutil.doSendKeys(this.lastName, lastName);
		eleutil.doSendKeys(this.email, email);
		eleutil.doSendKeys(this.telephone, telephone);
		eleutil.doSendKeys(this.password, password);
		eleutil.doSendKeys(this.confirmpassword, password);
		if(subscribe.equalsIgnoreCase("yes")) {
			eleutil.doClick(subscribeyes);
		}else {
			eleutil.doClick(subscribeno);
		}
		eleutil.doClick(agreecheckbox);
		eleutil.doClick(continuebtn);
		String SuccessMessage=eleutil.waitForElementVisible(registerSuccessMsg,Constants.DEFAULT_TIME_OUT).getText();
		if(SuccessMessage.contains(Constants.ACCOUNT_REGISTER_SUCCESS_MSG)) {
			eleutil.doClick(logoutLink);
			eleutil.doClick(registerLink);
			return true;
		}else {
			//eleutil.doClick(logoutLink);
			eleutil.doClick(registerLink);
			
		}
		return false;
	}

}
