package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

public class Accountspage {

	private WebDriver driver;
	private ElementUtil eleutil;
	private By logouticon = By.xpath("//div[@class='list-group']//a[text()='Logout']");
	private By sectionheaders = By.xpath("//div[@id='content']//h2");
	private By searchfield = By.xpath("//input[@placeholder='Search']");
	private By searchicon=By.xpath("//div[@id='search']//button[@type='button']");
	
	
	public Accountspage(WebDriver driver) {

		this.driver = driver;
		eleutil = new ElementUtil(this.driver);
	}

	public String getAccountsPageTitle() {

		return eleutil.waitForTitleIs(Constants.ACCOUNTS_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}

	public String getAccountsPageUrl() {

		return eleutil.waitForUrlContains(Constants.ACCOUNTS_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}

	public List<String> getAccountsPageSectionList() {

		List<WebElement> seclist = eleutil.getElements(sectionheaders);
		List<String> secValList = new ArrayList<String>();
		for (WebElement e : seclist) {
			String text = e.getText();
			secValList.add(text);
		}
		return secValList;

	}

	public boolean isLogoutExist() {

		return eleutil.waitForElementVisible(logouticon, Constants.DEFAULT_WEBELEMENT_TIME_OUT).isDisplayed();
	}

	public boolean isSearchExist() {

		return eleutil.waitForElementVisible(searchfield, Constants.DEFAULT_WEBELEMENT_TIME_OUT).isDisplayed();
	}

	public void doLogout() {
		if (isLogoutExist()) {

			eleutil.doClick(logouticon);
		}

		
	}

	public SearchResultsPage dosearch(String SearchKey) {
		if (isSearchExist()) {
			eleutil.doSendKeys(searchfield, SearchKey);
			eleutil.doClick(searchicon);
			return new SearchResultsPage(driver);
			
		}
		return null;
		
	}
}

