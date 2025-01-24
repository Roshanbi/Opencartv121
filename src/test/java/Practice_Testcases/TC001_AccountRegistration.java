package Practice_Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.BaseClass;
import Practiec_PageObjects.HomePage;
import pageObjects.AccountRegistrationPage;


public class TC001_AccountRegistration extends BaseClass{
	
	@Test(groups={"Master","Regression"})
	public void verify_Account_Registration() {
		try {
			logger.info("Starting TC001_Account registration test");
		HomePage hp = new HomePage(driver);
		
		hp.clickMyaccount();
		logger.info("Clicked on account");
		
		hp.clickRegister();
		logger.info("Clicked on Register");
		
		AccountRegistrationPage regPage=new AccountRegistrationPage(driver);
		logger.info("providing customer details");
		regPage.setFirstName(randomString());
		regPage.setLastName(randomString());
		regPage.setEmail(randomString()+"@gmail.com");
		regPage.setTelephone(randomNumber());
		
		String password=randomAlphaNumeric();
		regPage.setPassword(password);
		regPage.setConfirmPassword(password);
		regPage.setPrivacyPolicy();
		regPage.clickContinue();
		
		String ConfMsg=regPage.getConfirmationMsg();
		//validation
		logger.info("Validating confirmation msg");
		if(ConfMsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
		}
		}
		catch(Exception e) {
			Assert.fail();
		}
		finally {
			logger.info("TC001 registration test execution completed.");
		}
		
	}

}
