package Practiec_PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver) {
		
		super(driver);
	}
	
	//Locators
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txt_EmailAddress;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txt_Password;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement btn_Login;
	
	//Action methods
	
	public void setEmailaddress(String email) {
		txt_EmailAddress.sendKeys(email);
	}
	
	public void setPassword(String pass) {
		txt_Password.sendKeys(pass);
	}
	public void clickLogin() {
		btn_Login.click();
	}

}
