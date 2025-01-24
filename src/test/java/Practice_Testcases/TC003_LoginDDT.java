package Practice_Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.BaseClass;
import Practiec_PageObjects.HomePage;
import Practiec_PageObjects.LoginPage;
import Practiec_PageObjects.MyAccountPage;
import utilities.DataProviders1;

public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders1.class,groups= {"Master","DataDriven"})
	public void verify_LoginDDT(String email, String pass, String exp) {
		
		logger.info("Starting Execution TC003_LoginDDT");
		try {
			
		HomePage hp = new HomePage(driver);
		
		//Home page
		hp.clickMyaccount();
		logger.info("clicked on MyAccount link");
		hp.clickLogin();
		logger.info("clicked on Login link");
		
		//Login page
		LoginPage lp = new LoginPage(driver);
		
		lp.setEmailaddress(email);
		lp.setPassword(pass);
		lp.clickLogin();
		
		//my account page
		MyAccountPage mp= new MyAccountPage(driver);
		boolean targetPage=mp.isMyAccountPageExists();
		
		if(exp.equalsIgnoreCase("Valid")) {
			if(targetPage==true) {
				mp.clickLogout();
				logger.info("cicked on logout");
				Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(false);
			}
			
		}
		if(exp.equalsIgnoreCase("Invalid")) {
			if(targetPage==true) {
				mp.clickLogout();
				logger.info("cicked on logout");
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e) {
			Assert.fail();
		}
		finally {
			logger.info("TC003_LoginDDAT execution completed..");
		}
	}

}
