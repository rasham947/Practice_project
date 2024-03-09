package com.qa.opencart.testcases;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterTest extends BaseTest {

	@BeforeClass
	public void registerSetup() {

		registerpage = loginpage.gotoRegisterPage();
	}

	@DataProvider
	public Object[][] getRegisterData() {

		Object regData[][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return regData;

	}
	
	public String getRandomEmail() {
		
		Random random=new Random();
		String email= "marchautomation"+ random.nextInt(1000) + "@gmail.com";
		return email;
	}

	@Test(dataProvider = "getRegisterData")
	public void registerUserTest(String firstname, String lastname,  String telephone, String password,
			String subscribe) {

		Assert.assertTrue(registerpage.registerUser(firstname, lastname, getRandomEmail(), telephone, password, subscribe));
	}

}
