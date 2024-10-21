package testCases;

import org.testng.Assert;

import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	/**
	 * We need to add all the groups before and after classes, even though you extend Base class it wont trigger.
	 * In xml file we named methods thats why only look for that method
	 */
	@Test(groups= {"Sanity","Master"}) 
	public void verify_account_Registration() {
		
		
		logger.info("...starting TC001_Account registration test...");//log info msg
		try {
		HomePage hp = new HomePage(driver);
		
		hp.clickMyAccount();
		logger.info("..clicked on MyAccount...");
		
		hp.clickRegister();
		logger.info("..clicked on Register...");
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
        logger.info("...providing customer details");
		regpage.setFirstName(randomString());
		regpage.setLastName(randomString());
		regpage.setEmail(randomString() + "@gmail.com");
		regpage.setTelephone(randomNumber());

		String password = randomAlphaNumeric();
		regpage.setPassword(password);// it will generate password 1st time
		regpage.setConfirmPassword(password);// secondtime it will generate new passwrod so it wont match thats why
												// store in variable

		regpage.setPrivacyPolicy();
		regpage.clickContinue();
 
		logger.info("..validating expected message..");
		String confmsg = regpage.getConfirmationMsg();
         
		if(confmsg.equals("Your Account Has Been Created!"))//tried to  fail by adding !!
		{
			logger.info("Test passed");
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test failed");
			logger.debug("..Debug logs..");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		finally
		{
			logger.info("..Finished TC001_Account Registration...");
		}
	}

}
