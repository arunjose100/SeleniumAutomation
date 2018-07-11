package com.hellofresh.selenium.test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.hellofresh.selenium.browser.BaseClass;
import com.hellofresh.selenium.common.CommonUtils;
import com.hellofresh.selenium.pages.LoginPage;

@Listeners(com.hellofresh.selenium.common.TestListner.class)
public class LogInPageTestOne {
	
    protected WebDriver driver;
    protected CommonUtils utilsObject;
    protected LoginPage LoginPageObject;
    protected Properties propertiesObject;
    protected WebElement searchbox;
    protected WebDriverWait wait;
    String pageTitle;
    private By HeadingTitle = By.cssSelector("h1");
    private By Acount = By.className("account");
    private By InfoAccount = By.className("info-account");
    private By LogOutLink = By.className("logout");
	
	@BeforeClass
    public void beforeClassMethod() throws FileNotFoundException, IOException{
		
	propertiesObject = new Properties();
	propertiesObject.load(new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\hellofresh\\selenium\\properties\\HelloFresh.properties"));
	this.driver = BaseClass.browserInitialization(propertiesObject.getProperty("BrowserType"));
	int waitTime = Integer.parseInt(propertiesObject.getProperty("WaitTimeForPageLoad"));
	utilsObject = new CommonUtils(driver);
	LoginPageObject = new LoginPage(driver);
	utilsObject.screenMaximize();
	utilsObject.clearCookies();
	this.driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
	wait = new WebDriverWait(driver, 10, 50);
	
    }
	
	@Test(priority=0,description="URL Navigation Test Case")
    public void testMethodOne(){
	driver.get(propertiesObject.getProperty("URL"));
	pageTitle = LoginPageObject.getTitle();
	System.out.println("Page title is >>"+pageTitle);
	assertEquals(pageTitle.trim(), "My Store");
    }
	
	@Test(priority=1,description="Sign-Up",dependsOnMethods={"testMethodOne"})
    public void testMethodTwo(){
		LoginPageObject.SignUpAction(propertiesObject);
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(HeadingTitle));
        assertEquals(heading.getText(), "MY ACCOUNT");
        assertEquals(driver.findElement(Acount).getText(), 
        		propertiesObject.getProperty("nameToUsed")+ " " + 
        		propertiesObject.getProperty("surname"));
        assertTrue(driver.findElement(InfoAccount).getText().contains("Welcome to your account."));
        assertTrue(driver.findElement(LogOutLink).isDisplayed());
        assertTrue(driver.getCurrentUrl().contains("controller=my-account"));
    }
	
	@AfterClass
    public void afterClassMethod(){
	utilsObject.clearCookies();
	utilsObject.browserExit();
    }

}
