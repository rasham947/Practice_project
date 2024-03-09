package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	WebDriver driver;
	/**
	 * This method is used to intialize the browser on the basis of given browser
	 * name
	 * 
	 * @param browserName
	 * @returns the driver
	 */
	Properties prop;

	public WebDriver init_driver(Properties prop) {
		String browserName = prop.getProperty("browser").trim();

		System.out.println("Your Browser Name is :" + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {

			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {

			driver = new FirefoxDriver();

		} else {
			System.out.println("please pass the right browser Name :" + browserName);
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url").trim());
		return driver;

	}

	/**
	 * This method is used to intialize the properties
	 * 
	 */
	public Properties init_pop() {

		try {
			FileInputStream ip = new FileInputStream("./src/test/resource/config/config.properties");
			prop = new Properties();
			prop.load(ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
}

