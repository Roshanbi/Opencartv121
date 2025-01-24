package Practice_Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.BaseClass;
import Practiec_PageObjects.HomePage;
import Practiec_PageObjects.LoginPage;
import Practiec_PageObjects.MyAccountPage;


public class TC002_LoginTest extends BaseClass {
	
	@Test(groups={"Master","Sanity"})
	public void verify_LoginTest(){
		try {
		logger.info("starting execution TC002_Login test");
		HomePage hp = new HomePage(driver);
		
		//Home page
		hp.clickMyaccount();
		logger.info("clicked on MyAccount link");
		hp.clickLogin();
		logger.info("clicked on Login link");
		
	//Login page
		LoginPage lp = new LoginPage(driver);
		logger.info("Entering username and password...");
		lp.setEmailaddress(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		logger.info("clicked on Login button");
		lp.clickLogin();
		
		//my account page
		MyAccountPage Mp=new MyAccountPage(driver);
		logger.info("Validating myAccount page");
	boolean status=	Mp.isMyAccountPageExists();
	
	//validation
	Assert.assertEquals(status, true);
		}
		catch(Exception e){
			Assert.assertTrue(false);
		}
		finally {
			logger.info("TC002_LoginTest execution completed");
		}
	}
}
