package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {

	public CheckoutPage(WebDriver driver) {
		super(driver);
	}

	// locators

	@FindBy(xpath = "//input[@id='input-payment-firstname']")
	WebElement txtFirstName;

	@FindBy(xpath = "//input[@id='input-payment-lastname']")
	WebElement txtLastName;

	@FindBy(xpath = "//input[@id='input-payment-address-1']")
	WebElement txtAddress1;

	@FindBy(xpath = "//input[@id='input-payment-address-2']")
	WebElement txtAddress2;

	@FindBy(xpath = "//input[@id='input-payment-city']")
	WebElement txtCity;

	@FindBy(xpath = "//input[@id='input-payment-postcode']")
	WebElement txtPostCode;

	@FindBy(xpath = "//select[@id='input-payment-country']")
	WebElement drpCountry;

	@FindBy(xpath = "//select[@id='input-payment-zone']")
	WebElement drpState;

	@FindBy(xpath = "//input[@id='button-payment-address']")
	WebElement btnContinueBillingAddress;

	@FindBy(xpath = "//input[@id='button-shipping-address']")
	WebElement btnContinueDeliveryAddress;

	@FindBy(xpath = "//div[@id='collapse-shipping-method']//textarea[@name='comment']")
	WebElement txtDeliveryMethod;

	@FindBy(xpath = " //input[@id='button-shipping-method']")
	WebElement btnContinueShippingAddress;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkboxTerms;

	@FindBy(xpath = " //input[@id='button-payment-method']")
	WebElement btnContinuePaymentMethod;

	@FindBy(xpath = "//strong[text()='Total:']//following::td")
	WebElement lblTotalPrice;

	@FindBy(xpath = "//input[@id='button-confirm']")
	WebElement btnConfOrder;

	@FindBy(xpath = "//h1[normalize-space()='Your order has been placed!']")
	WebElement lblOrderConfMsg;

	// Action Methods

	public void setFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}

	public void setLastName(String lname) {
		txtLastName.sendKeys(lname);
	}

	public void setAddress1(String address1) {
		txtAddress1.sendKeys(address1);
	}

	public void setAddress2(String address2) {
		txtAddress2.sendKeys(address2);
	}

	public void setCity(String city) {
		txtCity.sendKeys(city);
	}

	public void setPostCode(String postcode) {
		txtPostCode.sendKeys(postcode);
	}

	public void setCountry(String country) {
		new Select(drpCountry).selectByVisibleText(country);
	}

	public void setState(String state) {
		new Select(drpState).selectByVisibleText(state);
	}

	public void clickOnContinueAfterBillingAddress() {
		btnContinueBillingAddress.click();
	}

	public void clickOnContinueAfterDeliveryAddress() {
		btnContinueDeliveryAddress.click();
	}

	public void setDeliveryMethodComment(String deliverymsg) {
		txtDeliveryMethod.sendKeys(deliverymsg);
	}

	public void clickOnContinueAfterDeliveryMethod() {
		btnContinueShippingAddress.click();
	}

	public void selectTermsAndConditions() {
		chkboxTerms.click();
	}

	public void clickOnContinueAfterPaymentMethod() {
		btnContinuePaymentMethod.click();
	}

	public String getTotalPriceBeforeConfOrder() {
		return lblTotalPrice.getText();

	}

	public void clickOnConfirmOrder() {
		btnConfOrder.click();
	}

	public boolean isOrderPlaced()
	{
		try {
		driver.switchTo().alert().accept();
		btnConfOrder.click();
		if(lblOrderConfMsg.getText().equals("Your order has been placed!"))
				
		 return true;
		
		else
		
		return false;
		}
		catch(Exception e)
		{
			return false;
		}
	}

}
