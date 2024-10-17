package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

	// constructor
	

	public HomePage(WebDriver driver) 
	{
       super(driver);
	}
	
	
	//Locators
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement lnkMyaccount;
	
	@FindBy(xpath ="//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(linkText = "Login")//for login
	WebElement lnkLogin;
	
	@FindBy(xpath=" //input[@placeholder='Search']")//for search product test
	WebElement txtSearchbox;
	
	@FindBy(xpath="//div[@id='search']//button[@type='button']")//for search product test
	WebElement btnSearch;
	
	
	//Actionmethods
	
	public void clickMyAccount() {
		lnkMyaccount.click();
	}
	
	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clickLogin()
	{
		lnkLogin.click();
	}
	
	public void enterProductName(String pName)//for search product
	{
		txtSearchbox.sendKeys(pName);
	}
	
	public void clickSearch()//for search product
	{
		btnSearch.click();
	}
	
	
	
	
	
	
	
	
	
}
