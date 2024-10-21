package testCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.AccountRegistrationPage;
import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.SearchPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

public class TC006_EndToEndTest extends BaseClass {

	@Test(groups = { "master" })
	public void verify_EndToEndTest() throws InterruptedException {
		logger.info("***starting TC006_EndToEndTest***");
		// SoftAssert for validation
		SoftAssert myassert = new SoftAssert();

		// Account Registration
		System.out.println("Account Registration...");

		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();

		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		regpage.setFirstName(randomString());
		regpage.setLastName(randomNumber());

		String email = randomString() + "@gmail.com";
		regpage.setEmail(email);
		regpage.setTelephone(randomNumber());

		String password = randomAlphaNumeric();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		regpage.setPrivacyPolicy();
		regpage.clickContinue();

		String confMsg = regpage.getConfirmationMsg();
		System.out.println(confMsg);
		myassert.assertEquals(confMsg, "Your Account Has Been Created!");// validation

		MyAccountPage map = new MyAccountPage(driver);
		map.clickLogout();
		Thread.sleep(3000);

		// Login
		System.out.println("Login to application...");

		hp.clickMyAccount();
		hp.clickLogin();

		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(password);
		lp.clickLogin();

		System.out.println("Going to my account page?");
		boolean status = map.isMyAccountPageExists();
         System.out.println(status);
		myassert.assertEquals(status, true);// validation
		
		//search & add product to cart
		System.out.println("Search & Add product to cart...");
		hp.enterProductName(p.getProperty("searchProductName"));
		hp.clickSearch();
		
		
		SearchPage sp = new SearchPage(driver);
		if(sp.isProductExist(p.getProperty("searchProductName")))
		{
			sp.selectProduct(p.getProperty("searchProductName"));
			sp.setQuantity("2");
			sp.addToCart();
		}
		Thread.sleep(3000);
		System.out.println("Added product to the cart?" +sp.checkCnfMsg());//validation
		myassert.assertEquals(sp.checkCnfMsg(),true);
		
		
		//shopingCart
		System.out.println("Shopping Cart.....");
		
		ShoppingCartPage sc = new ShoppingCartPage(driver);
		sc.clickItemsToNavigateToCart();
		sc.clickViewCart();
		Thread.sleep(3000);
		String totalPrice = sc.getTotalPrice();
		System.out.println("total price in shopping cart: "+totalPrice);
		myassert.assertEquals(totalPrice,"$246.40");//validation
		sc.clickCheckout();//navigate to checkout page
		Thread.sleep(3000);
		
		//checkout product
		System.out.println("Checkout Product.....");
     CheckoutPage ch = new CheckoutPage(driver);
     ch.setFirstName(randomString().toUpperCase());
     ch.setLastName(randomString().toUpperCase());
     ch.setAddress1("address1");
     ch.setAddress2("address2");
     ch.setCity("London");
     ch.setPostCode("SE8 4LB");
     ch.setCountry("United Kingdom");
     ch.setState("Kent");
 	Thread.sleep(1000);
     ch.clickOnContinueAfterBillingAddress();
 	Thread.sleep(1000);
     ch.clickOnContinueAfterDeliveryAddress();
 	Thread.sleep(1000);
     ch.setDeliveryMethodComment("Handle With Care");
 	Thread.sleep(1000);
     ch.clickOnContinueAfterDeliveryMethod();
 	Thread.sleep(1000);
     ch.selectTermsAndConditions();
 	Thread.sleep(1000);
     ch.clickOnContinueAfterPaymentMethod();
 	Thread.sleep(1000);
    String total_price_inOrderPage=ch.getTotalPriceBeforeConfOrder();
    System.out.println("Total price in confirm order page:" +total_price_inOrderPage);
    myassert.assertEquals(total_price_inOrderPage, "$254.40");//validation
    
   // Below code works only if you configure SMTP for emails 
    /*ch.clickOnConfirmOrder();
   boolean orderconf= ch.isOrderPlaced();
   System.out.println("Is order placed? "+ orderconf);
   myassert.assertEquals(orderconf,true);*/
   
   myassert.assertAll();//conclusion
   
   logger.info("***Ending TC006_EndToEndTest***");
	}

}
