package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleutil;
	private By productheader = By.xpath("//div[@class='row']//h1");
	private By productimages = By.xpath("//ul[@class='thumbnails']//img");
	private By productmetadata = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productpricingdata = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	private By quantity = By.xpath("//input[@name='quantity']");
	private By addtocarbtn = By.xpath("//button[@id='button-cart']");
	private By cartsuccessmessage = By.xpath("//div[@class='alert alert-success alert-dismissible']");
	private By cartitemtext = By.xpath("//div[@id='cart']/button[@type='button']");
	Map<String, String> productinfoMap;

	public ProductInfoPage(WebDriver driver) {

		this.driver = driver;
		eleutil = new ElementUtil(this.driver);
	}

	public String getProductHeaderName() {

		return eleutil.waitForElementVisible(productheader, Constants.DEFAULT_WEBELEMENT_TIME_OUT).getText();
	}

	public int getProductImagesCount() {

		return eleutil.waitForElementsVisible(productimages, Constants.DEFAULT_WEBELEMENT_TIME_OUT).size();
	}

	public Map<String, String> getProductInformation() {
		productinfoMap = new HashMap<String, String>();
		productinfoMap.put("name", getProductHeaderName());
		getProductMetaData();
		getProductpriceData();
		productinfoMap.forEach((k, v) -> System.out.println(k + ":" + v));

		return productinfoMap;
		// metadata:

		// pricedata

	}

	private void getProductMetaData() {

		List<WebElement> metadatalist = eleutil.getElements(productmetadata);
		System.out.println("Total Metadata list are : " + metadatalist.size());
		for (WebElement e : metadatalist) {

			String meta[] = e.getText().split(":");
			String metakey = meta[0].trim();
			String metavalue = meta[1].trim();
			productinfoMap.put(metakey, metavalue);
		}

	}

	private void getProductpriceData() {

		List<WebElement> pricelist = eleutil.getElements(productpricingdata);
		String price = pricelist.get(0).getText().trim();
		String ex_price = pricelist.get(1).getText().trim();
		productinfoMap.put("price", price);
		productinfoMap.put("ex_price", ex_price);

	}

	public ProductInfoPage enterQuantity(String qty) {

		eleutil.doActionSendKeys(quantity, qty);
		return this;

	}

	public ProductInfoPage clickOnAddtocart() {
		eleutil.doActionsClick(addtocarbtn);
		return this;

	}

	public String getCartSuccessMessage() {

		// return eleutil.doGetText(cartsuccessmessage);
		return eleutil.waitForElementVisible(cartsuccessmessage, Constants.DEFAULT_WEBELEMENT_TIME_OUT).getText();

	}

	public String getCartItemtext() {

		return eleutil.doGetText(cartitemtext);
	}

}
