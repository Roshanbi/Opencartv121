package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

// remember these steps

/* Data is valid-login succes-test pass-logout
 *              -login unsucces-test fail
 * 
 * Data is invalid-login succes-test fail-logout
 *                -login unsucces-test pass
 */
public class TC003_LoginDDT extends BaseClass {

	
	
	 
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups="DataDriven")	// getting data provider from different class so explicitly mention .class
	public void verify_LoginDDT(String email, String pwd, String exp)// exp means expected results
	{
		
		logger.info("*** starting TC003_LoginDDT ***");

		try 
		{
			// home page
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			// login Page

			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);// from properties file
			lp.setPassword(pwd);
			lp.clickLogin();

			// MyAccount Page

			MyAccountPage macc = new MyAccountPage(driver);
			boolean targetpage = macc.isMyAccountPageExists();

			if (exp.equalsIgnoreCase("Valid"))
			{
				if (targetpage == true) 
				{
					macc.clickLogout();
					Assert.assertTrue(true);
				} 
				else
				{
					Assert.assertTrue(false);// target page is false
				}
			}
			if (exp.equalsIgnoreCase("Invalid")) 
			{
				if (targetpage == true)
				{
					macc.clickLogout();
					Assert.assertTrue(false);
				} else 
				{
					Assert.assertTrue(true);// target page is true
				}
			}
		} 
		catch (Exception e)
		{
			Assert.fail();
		}
		finally
		{
			logger.info("***Finished TC003_LoginDDT ***");
		}
	}
}
