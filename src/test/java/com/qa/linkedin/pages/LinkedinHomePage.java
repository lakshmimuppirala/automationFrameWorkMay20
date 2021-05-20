package com.qa.linkedin.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.log4testng.Logger;

public class LinkedinHomePage  extends BasePageWeb{

	private Logger log=Logger.getLogger(LinkedinHomePage.class);
	
	//create a constructor
	
	public LinkedinHomePage() {
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//a[contains(.,'Sign in')]")
	private WebElement linkedinLogo;
	
	//identify the sigin link lest
	@FindBy(linkText="Sign in")
	private WebElement signInLink;
	
	//identify the signin header text
	@FindBy(xpath="//h1[contains(.,'Sign in')]")
	private WebElement signInHeaderText;
	
	//identify the username editbox
	@FindBy(id="username")
	private WebElement signInEditbox;
	
	//identify the password edit box
	@FindBy(name="session_password")
	private WebElement pwdEditbox;
	
	//identify the signin button
	@FindBy(xpath="//button[@type='submit']")
	private WebElement SignInButton;
	String  signinPageTitle ="LinkedIn Login, Sign in | LinkedIn";
	String HomePageTitle="LinkedIn: Log In or Sign Up";
	
	public void verifyLinkedinHomePageTitle()
	{
		log.debug("Wait for the linked in home page title");
		wait.until(ExpectedConditions.titleIs(HomePageTitle));
		Assert.assertEquals(driver.getTitle(), HomePageTitle);
	}
	
	public void verifyLinkedLogo()
	{
		log.debug("wait for the linkedin logo");
		wait.until(ExpectedConditions.visibilityOf(linkedinLogo));
		Assert.assertTrue(linkedinLogo.isDisplayed(),"Linkedin logo is not present");
	}
	
	public void clickSingninLink() throws InterruptedException
	{
		log.debug("click on signin link in home page");
    	click(signInLink);
    	
	}
	
	public void verifyLinkedinSigninPageTitle()
	{
		log.debug("Wait for the linked in signinPageTitle page title");
		wait.until(ExpectedConditions.titleContains(signinPageTitle));
		Assert.assertEquals(driver.getTitle(), signinPageTitle);
	}
	public void verifySigninHeaderText()
	{
		log.debug("wait for the verifySigninHeaderText");
		wait.until(ExpectedConditions.visibilityOf(signInHeaderText));
		Assert.assertTrue(signInHeaderText.isDisplayed(),"signInHeaderText  is not present");
	}
	
	public void clickSingninButton() throws InterruptedException
	{
		log.debug("click on signin linButtonk in signin page");
    	click(SignInButton);
    	
	}
	
	public LinkedinLoggedinPage doLogin(String uname,String pwd) throws Exception
	{
		log.debug("started login process.....");
		sendKey(signInEditbox, uname);
		sendKey(pwdEditbox, pwd);
		clickSingninButton();
		/* try
		  {
			  if(isPresentElement(skipBtn)) {
				  click(skipBtn);
			  }
		  }catch(Exception e)
		  {
			  e.printStackTrace(); 
		  }*/
	
	return  new LinkedinLoggedinPage();
}

}