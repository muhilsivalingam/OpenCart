package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
@FindBy(xpath="//a[@title='My Account']") WebElement linkMyaccount;
@FindBy(xpath="//a[normalize-space()='Register']") WebElement linkReg;
@FindBy(linkText="Login") WebElement linkLogin;


public void clickMyaccount()
{
	linkMyaccount.click();
}
public void clickRegister()
{
	linkReg.click();
}
public void clickLogin()
{
	linkLogin.click();
}

}