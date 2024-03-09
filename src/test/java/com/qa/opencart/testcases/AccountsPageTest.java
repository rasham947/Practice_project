package com.qa.opencart.testcases;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accSetup() {

		accpage = loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());

	}

	@Test(priority = 1)
	public void accPageTitleTest() {

		String actitle = accpage.getAccountsPageTitle();
		System.out.println("Accounts page title is :" + actitle);
		Assert.assertEquals(actitle, Constants.ACCOUNTS_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void accPageUrlTest() {

		String actURL = accpage.getAccountsPageUrl();
		System.out.println("Accounts page title is :" + actURL);
		Assert.assertTrue(actURL.contains(Constants.ACCOUNTS_PAGE_URL_FRACTION));
	}

	@Test(priority = 3)
	public void accPageSectionsTest() {
		List<String> actAccSecList = accpage.getAccountsPageSectionList();
		System.out.println("Acc Accounts section list :" + actAccSecList);
		Assert.assertEquals(actAccSecList, Constants.EXPECTED_ACCOUNTS_SECTION_LIST);
	}

	@DataProvider
	public Object[][] getSearchKey() {

		return new Object[][] { { "Macbook" }, { "iMac" }, { "Apple" }, { "Samsung" } };
	}

	@Test(priority = 4, dataProvider = "getSearchKey")
	public void searchTest(String SearchKey) {

		searResPage = accpage.dosearch(SearchKey);
		Assert.assertTrue(searResPage.getSearchResultsCount() > 0);
	}

//	@DataProvider
//	public Object[][] getproductName() {
//
//		return new Object[][] { { "Macbook", "MacBook Pro" }, { "iMac", "iMac" }, { "Apple", "Apple Cinema 30\"" },
//				{ "Samsung", "Samsung SyncMaster 941BW" } };
//	}
	 
	  @DataProvider
	public Object[][] getproductName(){
		
		return ExcelUtil.getTestData(Constants.PRODUCT_SHEET_NAME);
	}

	@Test(priority = 5, dataProvider = "getproductName")
	public void selectproductTest(String searchKey, String productName) {
		searResPage = accpage.dosearch(searchKey);
		productinfopage = searResPage.selectProduct(productName);
		String productheader = productinfopage.getProductHeaderName();
		System.out.println(productheader);
		Assert.assertEquals(productheader, productName);

	}

	@DataProvider
	public Object[][] getproductData() {

		return new Object[][] { { "Macbook", "MacBook Pro", 4 }, { "Samsung", "Samsung SyncMaster 941BW", 1 } };
	}

	@Test(priority = 6, dataProvider = "getproductData")
	public void productImageTest(String Searchkey, String productName, int productImageCount) {

		searResPage = accpage.dosearch(Searchkey);
		productinfopage = searResPage.selectProduct(productName);
		Assert.assertEquals(productinfopage.getProductImagesCount(), productImageCount);

	}

	@Test(priority = 7)
	public void productinfotest() {

		searResPage = accpage.dosearch("Macbook");
		productinfopage = searResPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = productinfopage.getProductInformation();
	}

	@Test(priority = 8)
	public void addtoCartTest() {

		searResPage = accpage.dosearch("Macbook");
		productinfopage = searResPage.selectProduct("MacBook Pro");
		String actsuccessmessage = productinfopage.enterQuantity("2").clickOnAddtocart().getCartSuccessMessage();
		// String actsuccessmessage=productinfopage.getCartSuccessMessage();

		System.out.println("Cart Message is :" + actsuccessmessage);

		softAsserts.assertTrue(actsuccessmessage.contains("MacBook Pro"));
		String actcartitemtext = productinfopage.getCartItemtext();
		softAsserts.assertTrue(actcartitemtext.contains("2" + " items"));
		softAsserts.assertAll();

		// SoftAssert.assertTrue(false)

	}

//	@AfterClass
//	public void doLogout() {
//		accpage.doLogout();
//		System.out.println("Logout Successfully");
//		
//	}
//	

}
