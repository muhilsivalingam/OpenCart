package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;
@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="DataDriven")
public class TC003_DDT_Login extends BaseClass {
	public void verifyDDTLogin(String email,String pwd,String expected)
	{
		try
		{
		logger.info("********starting TC_003_DDT_Login********");
		HomePage hp=new HomePage(driver);
		hp.clickMyaccount();
		hp.clickLogin();
		
	
	//LoginPage
	 LoginPage lp = new LoginPage(driver);
     lp.setEmail(email);
     lp.setPassword(pwd);
     lp.clickLogin();

     // MyAccount
     MyAccountPage macc = new MyAccountPage(driver);
     boolean targetPage = macc.isMyAccountPageExists();
     if(expected.equalsIgnoreCase("Valid"))
     {
    	 if(targetPage==true)
    	 {
    		 Assert.assertTrue(true);
    		 lp.clickLogout();
    	 }
     }
     
     if(expected.equalsIgnoreCase("Invalid"))
     {
    	 if(targetPage==true)
    	 {
    		 lp.clickLogout();
    		 Assert.assertTrue(false);
    	 }
    	 else
    	 {
    		 Assert.assertTrue(true);
    	 }
     }
		}
		catch(Exception e)
		{
			Assert.fail();
		}
     logger.info("***********Finished TC_003_DDTogin************");

}
}
