package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.TasksPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase
{
	String sheetname="contacts";
	LoginPage loginpage;
	TasksPage taskpage;
	HomePage homepage;
	TestUtil testutil;
	ContactsPage contactspage;
	DealsPage dealspage;
	
	public ContactsPageTest()
	{
		super();
		
	}
	
	@BeforeMethod
	public void setUp()
	{
		initialization();
		testutil = new TestUtil();
	  loginpage = new LoginPage();
	  contactspage = new ContactsPage();
	  dealspage = new DealsPage();
	  homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
	  testutil.switchToFrame();
	  homepage.clickOnContactsLink();
	 
	}
	
	@DataProvider
	public Object[][] getCRMTestData()
	{
		Object data[][]=TestUtil.getTestData(sheetname);
		return data;
		
	}
	
	@Test(priority=1)
	public void verifyContactsPageLabelTest()
	{
		Assert.assertTrue(contactspage.verifyContactsLabel(),"contacts label is missing on the page");
		
	}
	
	@Test(priority=2)
	public void selectSingleContactTest()
	{
		contactspage.selectByContactsName("tom peter");
		
	}
	
	@Test(priority=3)
	public void selectMultipleContactTest()
	{
		contactspage.selectByContactsName("tom peter");
		contactspage.selectByContactsName("arjun m");
		
	}
	
	@Test(priority=4, dataProvider="getCRMTestData")
	public void createNewContactTest(String title,String firstname,String lastname,String company)
	{
		homepage.clickOnNewContactLink();
		//contactspage.createNewContact("Mr.", "sankar", "Deva", "CGX");
		contactspage.createNewContact(title, firstname, lastname, company);
		
		
		
		
		
	}
	
	
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
