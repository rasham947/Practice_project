package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	
	private WebDriver driver;

	public ElementUtil(WebDriver driver) {

		this.driver = driver;

	}

	public By getBy(String locatortype, String locatorvalue) {

		By locator = null;
		switch (locatortype.toLowerCase()) {

		case "id":
			locator = By.id(locatorvalue);
			break;
		case "name":
			locator = By.name(locatorvalue);
			break;
		case "classname":
			locator = By.className(locatorvalue);
			break;
		case "xpath":
			locator = By.xpath(locatorvalue);
			break;
		case "css":
			locator = By.cssSelector(locatorvalue);
			break;
		case "linktext":
			locator = By.linkText(locatorvalue);
			break;
		case "partiallinktext":
			locator = By.partialLinkText(locatorvalue);
			break;
		case "tagname":
			locator = By.tagName(locatorvalue);
			break;
		default:
			break;
		}
		return locator;
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public void doClick(By locator) {

		getElement(locator).click();
	}

	public boolean doIsdisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public boolean doIsEnabled(By locator) {
		return getElement(locator).isEnabled();
	}

	public String getAttributevalue(By locator, String attrname) {
		return getElement(locator).getAttribute(attrname);

	}

	public WebElement getElement(By Locator) {
		return driver.findElement(Locator);

	}

	public void doSendKeys(String locatortype, String locatorvalue, String value) {
		getElement(getBy(locatortype, locatorvalue)).sendKeys(value);

	}

	public void doSendKeys(By locator, String value) {
		
		WebElement e=getElement(locator);
		e.clear();
		e.sendKeys(value);

	}

	public List<WebElement> getElements(By locator) {

		return driver.findElements(locator);
	}

	public int getElementCount(By locator) {

		return getElements(locator).size();
	}

	public List<String> getElementsAttributeList(By locator, String attrname) {

		List<WebElement> elelist = getElements(locator);
		List<String> eleAttrlist = new ArrayList<String>();
		for (WebElement e : elelist) {

			String arrval = e.getAttribute(attrname);
			eleAttrlist.add(arrval);
		}
		return eleAttrlist;
	}

	public List<String> getElementsTextList(By locator) {

		List<WebElement> elelist = getElements(locator);
		List<String> eletextlist = new ArrayList<String>();
		for (WebElement e : elelist) {

			String eletext = e.getText();
			eletextlist.add(eletext);
		}
		return eletextlist;
	}

	public void SelectSuggestion(By locator, String suggval) throws InterruptedException {

		List<WebElement> sugglist = driver.findElements(locator);

		for (WebElement e : sugglist) {

			// System.out.println(e.getText());
			String text = e.getText();
			System.out.println(text);
			if (text.contains(suggval)) {
				e.click();
				break;
			}

		}

	}

	public List<String> getSuggestList(By locator, String searchKey) throws InterruptedException {

		List<WebElement> sugglist = driver.findElements(locator);
		List<String> suggValList = new ArrayList<String>();

		for (WebElement e : sugglist) {

			// System.out.println(e.getText());
			String text = e.getText();
			System.out.println(text);
			suggValList.add(text);

		}
		return suggValList;

	}

	public boolean isElementDisplayed(By locator) {

		WebElement e = getElement(locator);
		List<WebElement> elelist = getElements(locator);

		if (elelist.size() > 0) {
			if (e.isDisplayed())
				return true;
		}

		return false;

	}

	public boolean isElementDisplayedWithSize(By locator) {

		List<WebElement> elelist = getElements(locator);

		if (elelist.size() > 0) {

			return true;
		}

		return false;

	}

	// *****************************************Dropdown Utils********************//

	public void selectDropdownByIndex(By locator, int index) {

		Select select = new Select(getElement(locator));
		select.selectByIndex(index);

	}

	public void selectDropdownByVisbleText(By locator, String visibletext) {

		Select select = new Select(getElement(locator));
		select.selectByVisibleText(visibletext);

	}

	public void selectDropdownByValue(By locator, String value) {

		Select select = new Select(getElement(locator));
		select.selectByValue(value);

	}

	public void dropDownSelectValuewithgetOptions(By locator, String value) {

		Select select = new Select(getElement(locator));
		List<WebElement> countryoptions = select.getOptions();
		for (WebElement e : countryoptions) {

			String text = e.getText();
			System.out.println(text);
			if (text.equals(value)) {

				e.click();
				break;
			}
		}

	}

	public void dropDownSelectValue(By locator, String value) {

		List<WebElement> list = getElements(locator);
		for (WebElement e : list) {

			String text = e.getText();
			if (text.equals("India")) {
				e.click();
				break;

			}
		}

	}

	// ***********************************Action
	// utils*******************************//

	public void levelTwoMunuHandling(By parentmenu, By childmenu) throws InterruptedException {

		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentmenu)).perform();
		Thread.sleep(1500);
		getElement(childmenu).click();
	}

	public void multiLevelMenuHandling(By parentmenu, String l1, String l2, String l3) throws InterruptedException {

		getElement(parentmenu).click();
		Thread.sleep(1500);

//	Actions action=new Actions(driver);
//	action.moveToElement(parentmenu).perform();
		Actions action = new Actions(driver);
		action.moveToElement(getElement(By.linkText(l1))).perform();
		Thread.sleep(1500);
		action.moveToElement(getElement(By.linkText(l2))).perform();
		Thread.sleep(1500);
		// action.moveToElement(driver.findElement(By.linkText("Green Tea"))).perform();
		// Thread.sleep(1500);
		getElement((By.linkText(l3))).click();
	}

	public void selectRightClickMenu(By rightClickElementLocator, By rightMenuItem) {
		Actions act = new Actions(driver);
		act.contextClick(getElement(rightClickElementLocator)).perform();
		getElement(rightMenuItem).click();

	}

	public List<String> getRightClickMenuList(By rightClickElementLocator, By rightMenuItems) {

		List<String> rightMenuList = new ArrayList<String>();
		Actions act = new Actions(driver);
		act.contextClick(getElement(rightClickElementLocator)).perform();

		List<WebElement> menuitems = driver.findElements(rightMenuItems);
		for (WebElement e : menuitems) {

			String text = e.getText();
			rightMenuList.add(text);

		}
		return rightMenuList;

	}

	public void dragandDropAction(By source, By target) {

		Actions act = new Actions(driver);
		act.clickAndHold(getElement(source)).moveToElement(getElement(target)).release().perform();
	}

	public void doActionSendKeys(By locator, String Value) {

		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), Value).perform();

	}

	public void doActionsClick(By locator) {

		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();

	}

	// Wait Utils

	public WebElement waitForElementPresence(By locator, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	public WebElement waitForElementVisible(By locator, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	// *********************** Wait Utils For Non-WebElements*********************//

	public String waitForTitleContains(String titlefraction, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		if (wait.until(ExpectedConditions.titleContains(titlefraction))) {

			return driver.getTitle();
		}
		return null;

	}

	public String waitForTitleIs(String title, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		if (wait.until(ExpectedConditions.titleIs(title))) {

			return driver.getTitle();
		}
		return null;

	}

	public String waitForUrlContains(String urlFraction, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		if (wait.until(ExpectedConditions.urlContains(urlFraction))) {

			return driver.getCurrentUrl();
		}
		return null;
	}

	public String waitForUrlIs(String url, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		if (wait.until(ExpectedConditions.urlToBe(url))) {

			return driver.getCurrentUrl();
		}
		return null;
	}

	public void acceptAlert(int timeout) {

		waitForAlert(timeout).accept();
	}

	public void dismissAlert(int timeout) {

		waitForAlert(timeout).dismiss();
	}

	public void alertSendKeys(int timeout, String value) {

		waitForAlert(timeout).sendKeys(value);
	}

	public String doGetAlertText(int timeout) {

		return waitForAlert(timeout).getText();
	}

	public Alert waitForAlert(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public void waitForFrameByLocator(By FrameLocator, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(FrameLocator));
	}

	public void waitForFrameByIndex(int frameIndex, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public void waitForFrameByElement(WebElement frameElement, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	public void clickElementWhenReady(By locator, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public void waitForElementToBeClickableWithPolling(By locator, int timeOut, int pollingTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(pollingTime));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();

	}

	public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	/**
	 * An expectation for checking that there is at least one element present on a
	 * web page.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> waitForElementsPresence(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	public void waitForElementPresenceWithFluentWait(By locator, int timeOut, int pollingTime, String message) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofMillis(pollingTime))
				.ignoring(NoSuchElementException.class, StaleElementReferenceException.class).withMessage(message);

		wait.until(ExpectedConditions.presenceOfElementLocated(locator)).click();
	}



}

