package testCase;

import org.testng.annotations.Test;

import testBase.BaseClass;
import org.testng.Assert;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
public class TC002_LoginTest extends BaseClass{
	@Test(groups={"Sanity","Master"})
	public void verify_login() {

	    logger.info("****** Startign TC_002_LoginTest ******");

	    try
	    {
	        // HomePage
	        HomePage hp = new HomePage(driver);
	        hp.clickMyaccount();
	        hp.clickLogin();

	        // Login
	        LoginPage lp = new LoginPage(driver);
	        lp.setEmail(p.getProperty("email"));
	        lp.setPassword(p.getProperty("password"));
	        lp.clickLogin();

	        // MyAccount
	        MyAccountPage macc = new MyAccountPage(driver);
	        boolean targetPage = macc.isMyAccountPageExists();

	        Assert.assertTrue(targetPage);   // Assert.assertEquals(targetPage, true, "Login failed");
	    }
	    catch (Exception e)
	    {
	    	Assert.fail();
	    }
	    }
}


