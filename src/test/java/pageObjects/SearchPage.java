package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class SearchPage extends BasePage{
	
  public SearchPage(WebDriver driver)
  {
	  super(driver);
  }
  
  
  
  
  @FindBy(xpath="//div[@id='content']//div[3]//img")
  List<WebElement> searchProducts;
  
  @FindBy(name="quantity")
  WebElement txtQuantity;
  
  @FindBy(xpath=" //button[@id='button-cart']")
  WebElement btnAddToCart;
  
  @FindBy(xpath="//div[contains(text(),'Success: You have added ')]")
  WebElement cnfMsg;
  
  public boolean isProductExist(String productName)//search product
  {
	  
	  boolean flag=false;
	  
	  for(WebElement product:searchProducts)
	  {
		  if(product.getAttribute("title").equals(productName))
		  {
			  flag=true;
			 break;
		  }
	  }
	  
	    return flag;
	  
  }
  
	public void selectProduct(String productName)//add to cart page
  {
	  for(WebElement product:searchProducts)
	  {
		  if(product.getAttribute("title").equals(productName))
		  {
			  product.click();
		  }
	  }
	  
	  
 }
  
  public void setQuantity(String qty)//add to cart page
  {
	  txtQuantity.clear();
	  txtQuantity.sendKeys(qty);
	  
  }
  public void addToCart()
  {
	  btnAddToCart.click();
  }
  
  public boolean checkCnfMsg()//add to cart page
  {
	  
	 try { 
	return cnfMsg.isDisplayed();
	 }
	 catch(Exception e)
	 {
		 return false;
	 }
	  
  }
  
}
