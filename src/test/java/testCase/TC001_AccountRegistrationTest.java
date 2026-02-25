package testCase;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistration;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	

@Test(groups={"Regression","Master"})
public void verify_accountRegistration()
{
	logger.info("*****  Starting TC001_AccountRegistrationTest  *****");
	try
	{
	HomePage hp=new HomePage(driver);
	hp.clickMyaccount();
	logger.info("Clicked on MyAccount Link..");
	hp.clickRegister();
	logger.info("Clicked on Register Link..");
	AccountRegistration regpage=new AccountRegistration(driver);
	logger.info("Providing customer details..");
	// Generate random data
	regpage.setFirstName(randomString(6).toUpperCase());
	regpage.setLastName(randomString(6).toUpperCase());
	regpage.setEmail(randomString(6)+ "@gmail.com");
	regpage.setTelephone(randomNumber(10));

	String password = randomAlphanumeric(11);

	regpage.setPassword(password);
	regpage.setConfirmPassword(password);

	regpage.setPrivacyPolicy();
	regpage.clickContinue();
	logger.info("Validating expected message..");
	String confirmMsg=regpage.getConfirmationMsg();
	if(confirmMsg.equals("Your Account Has Been Created!"))
	{
		Assert.assertTrue(true);
	}
	else
	{
		logger.error("Test Failed");
		logger.debug("Debug logs");
		Assert.assertTrue(false);
	}
	
	
}

catch(Exception e)
{
	Assert.fail();
}
}
	
}
