package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	@Test(groups = { "Regression", "Master" })
	public void verify_Login() {

		logger.info("****starting TC002_LoginTest****");
		try {
			// home page
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("***clicked MyAccount****");

			hp.clickLogin();
			logger.info("***clicked login****");

			// login Page
			logger.info("***providing login details****");
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(p.getProperty("email"));// from properties file
			lp.setPassword(p.getProperty("password"));
			lp.clickLogin();

			// MyAccount Page

			MyAccountPage map = new MyAccountPage(driver);

			logger.info("***validating MY Account Page****");

			boolean targetpage = map.isMyAccountPageExists();

			// validation

			Assert.assertEquals(targetpage, true);
		} catch (Exception e) {
			Assert.fail();
		} finally {
			logger.info("***finished TC002_LoginTest***");
		}

	}

}
