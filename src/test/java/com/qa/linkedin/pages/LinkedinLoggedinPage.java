package com.qa.linkedin.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.log4testng.Logger;

public class LinkedinLoggedinPage  extends BasePageWeb{
	
	private Logger log=Logger.getLogger(LinkedinLoggedinPage.class);
	
	//create a constructor
	
	public LinkedinLoggedinPage() {
		PageFactory.initElements(driver, this);
	}
	
	//identify profille railcard
	@FindBy(xpath="//div[contains(@class,'feed-identity-module')]")
	private WebElement profileRailCard;
	
	//identify profile img icon
	@FindBy(xpath="//img[contains(@class,'global-nav__me-photo ember-view')]")
	private WebElement profileImgIcon;
	//identify Signout link
	@FindBy(xpath="//div[contains(@id,'ember36')]")
	private WebElement signOutLink;
	
	//identify the search edit box
	@FindBy(xpath="//input[contains(@class,'search-global-typeahead__input always-show-placeholder')]")
	private WebElement searchEditBox;
	




public void verifyProfileRailCard() {
	
	log.debug("wait for the profile rail card");
	wait.until(ExpectedConditions.visibilityOf(profileRailCard));
	Assert.assertTrue(profileRailCard.isDisplayed(), "profile rail caid is not available");
	 		
	}

public void doLogut() throws InterruptedException
{
	log.debug("perform the logout operation from the application");
	click(profileImgIcon);
	click(signOutLink);
}


public SearchResultPage doPeppleSearch(String keyword) throws Exception
{
	log.debug("perform the peopek search");
	sendKey(searchEditBox, keyword);
	Thread.sleep(2000);
	log.debug("press the enter key to sudmit the search form");
	searchEditBox.sendKeys(Keys.ENTER);
	return new SearchResultPage();
	
}
}