package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.Accountspage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {

	public DriverFactory df;
	public WebDriver driver;
	public LoginPage loginpage;
	public Properties prop;
	public Accountspage accpage;
	public SearchResultsPage searResPage;
	public ProductInfoPage productinfopage;
	public SoftAssert softAsserts;
	protected RegisterPage registerpage;

	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.init_pop();
		driver = df.init_driver(prop);
		loginpage = new LoginPage(driver);
		softAsserts=new SoftAssert();

	}

	@AfterTest
	public void tearDown() {

		driver.quit();
	}
	public void dologout() {
		
		
	}

}

