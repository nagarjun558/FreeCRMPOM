package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.TasksPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase 
{
	LoginPage loginpage;
	TasksPage taskpage;
	HomePage homepage;
	TestUtil testutil;
	ContactsPage contactspage;
	DealsPage dealspage;
	
	public HomePageTest()
	{
		super();
	}
	
	//test case should be independent each other
	//before each test case launch the browser and login
	//@test -- execute the test cases
	//after each test case close the browser
	
	@BeforeMethod
	public void setUp()
	{
		initialization();
		testutil = new TestUtil();
	  loginpage = new LoginPage();
	  contactspage = new ContactsPage();
	  dealspage = new DealsPage();
	  homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority = 1)
	public void verifyHomePageTitleTest()
	{
		String homepageTitle=homepage.verifyHomePageTitle();
		Assert.assertEquals(homepageTitle, "CRMPRO","HomePage Title not amtched");
	}
	
	@Test(priority = 2)
	public void verifyUsernameTest()
	{
		testutil.switchToFrame();
		Assert.assertTrue(homepage.verifyUsername());
	}
	
	@Test(priority=3)
	public void clickOnContactsLinkTest()
	{
		testutil.switchToFrame();
		contactspage = homepage.clickOnContactsLink();
	}
	
	public void clickOnDealsLinkTest()
	{
		testutil.switchToFrame();
		dealspage = homepage.clickOnDealsLink();
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
