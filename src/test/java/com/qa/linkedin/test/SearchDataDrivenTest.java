package com.qa.linkedin.test;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.pages.LinkedinHomePage;
import com.qa.linkedin.pages.LinkedinLoggedinPage;
import com.qa.linkedin.pages.SearchResultPage;
//import com.qa.linkedin.testcases.SearchDataDrivenTest;
import com.qa.linkedin.util.ExcelUtils;

public class SearchDataDrivenTest extends TestBase{
	
	
	private Logger log = Logger.getLogger(SearchDataDrivenTest.class);
	private String path=System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\linkedin\\data\\searchdata.xlsx";
	
	LinkedinHomePage lHmPage;
	LinkedinLoggedinPage llPage;
	SearchResultPage srPage;
  
  @BeforeClass
  public void beforeClass() throws Exception {
	  
	  
	  log.debug("page initilization");
	  lHmPage=new LinkedinHomePage();
	  llPage=new LinkedinLoggedinPage();
	  srPage=new SearchResultPage();
	  log.debug("calling title verifiation");
	  lHmPage.verifyLinkedinHomePageTitle();
	  lHmPage.verifyLinkedLogo();
	  lHmPage.clickSingninLink();
	  lHmPage.verifySigninHeaderText();
	  llPage=lHmPage.doLogin(readPropertyValue("username"), readPropertyValue("password"));
	  llPage.verifyProfileRailCard();
  }

  @AfterClass
  public void afterClass() throws Exception {
	  
	  
		log.debug("Perform the logout operations from the application");
		  llPage.doLogut();
		// lHmPage.verifyLinkedinHomePageTitle();
		  
	  
	  
  }
  
  
  @Test(dataProvider="getData")
  public void SearchDataDrivenTest(String keyword) throws Exception {
	  
	  
	  log.debug("started executing the search test  for people"+keyword);
	  srPage=llPage.doPeppleSearch(keyword);
	  srPage.validateSearchResultPageTitle();
	 
	 long count= srPage.getResultCount();
	 log.debug("search result count for:"+keyword +" is "+count);
	 Thread.sleep(1000);
	 log.debug("click on  home tab go to logedinpage");
	 srPage.clickHomeTab();
	 Thread.sleep(1000);
	 
	 log.debug("***************************one iteration is done*************************");
	  
  }
  
  
  @DataProvider
  public Object[][]  getData() throws InvalidFormatException, IOException
  {
	  Object[][] data= new ExcelUtils().getTestData(path, "Sheet1");
	  return data;
  }

}
