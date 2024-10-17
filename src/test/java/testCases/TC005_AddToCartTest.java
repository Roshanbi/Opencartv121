package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC005_AddToCartTest extends BaseClass {

	@Test(groups = { "master" })
	public void verify_AddToCart() {
		logger.info("*** starting TC005_AddToCartTest ***");
		try {
			HomePage hp = new HomePage(driver);
			hp.enterProductName("iPhone");
			hp.clickSearch();

			SearchPage sp = new SearchPage(driver);

			if (sp.isProductExist("iPhone")) {
				sp.selectProduct("iPhone");
				sp.setQuantity("2");
				sp.addToCart();
			}

			Assert.assertEquals(sp.checkCnfMsg(), true);
		} catch (Exception e) {
			Assert.fail();
		}

		logger.info("*** Finished TC005_AddToCartTest ***");

	}

}
