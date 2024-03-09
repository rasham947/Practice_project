package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
	private WebDriver driver;
	private ElementUtil eleutil;
	private By SearchResults = By
			.xpath("//div[@class='product-layout product-grid col-lg-3 col-md-3 col-sm-6 col-xs-12']");
	private By productNameLink;

	public SearchResultsPage(WebDriver driver) {

		this.driver = driver;
		eleutil = new ElementUtil(this.driver);
	}

	public int getSearchResultsCount() {

		return eleutil.waitForElementsVisible(SearchResults, Constants.DEFAULT_WEBELEMENT_TIME_OUT).size();
	}

	public ProductInfoPage selectProduct(String productName) {
		
		productNameLink=By.linkText(productName);
		eleutil.doClick(productNameLink);
		return new ProductInfoPage(driver);

	}

}

