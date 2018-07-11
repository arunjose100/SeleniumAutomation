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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.hellofresh.selenium.browser.BaseClass;
import com.hellofresh.selenium.common.CommonUtils;
import com.hellofresh.selenium.pages.CheckOutPage;
import com.hellofresh.selenium.pages.LoginPage;

@Listeners(com.hellofresh.selenium.common.TestListner.class)
public class CheckOutTest {
	
	protected LoginPage LoginPageObject;
    protected Properties propertiesObject;
    protected WebDriver driver;
    protected CommonUtils utilsObject;
    protected WebDriverWait wait;
    protected CheckOutPage CheckOutPageObject;
    By ElementOne = By.xpath("//li[@class='step_done step_done_last four']");
    By ElementTwo = By.xpath("//li[@id='step_end' and @class='step_current last']");
    By ElementThree = By.xpath("//*[@class='cheque-indent']/strong");
    
    @BeforeMethod
    public void beforeTestMethod() throws FileNotFoundException, IOException{
    	propertiesObject = new Properties();
    	propertiesObject.load(new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\hellofresh\\selenium\\properties\\HelloFresh.properties"));
    	this.driver = BaseClass.browserInitialization(propertiesObject.getProperty("BrowserType"));
    	int waitTime = Integer.parseInt(propertiesObject.getProperty("WaitTimeForPageLoad"));
    	utilsObject = new CommonUtils(driver);
     
    	utilsObject.screenMaximize();
    	utilsObject.clearCookies();
    	this.driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
    	wait = new WebDriverWait(driver, 10, 50);
    	CheckOutPageObject = new CheckOutPage(driver);
    }
	
	@Test(priority=0,description="CheckOut-Validation")
    public void testMethodFour(){
		driver.get(propertiesObject.getProperty("URL"));
		CheckOutPageObject.checkOut(propertiesObject);
		
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
        assertEquals("ORDER CONFIRMATION", heading.getText());
        assertTrue(driver.findElement(ElementOne).isDisplayed());
        assertTrue(driver.findElement(ElementTwo).isDisplayed());
        assertTrue(driver.findElement(ElementThree).getText().contains(propertiesObject.getProperty("Textvalidation")));
        assertTrue(driver.getCurrentUrl().contains("controller=order-confirmation"));
    }
	
	@AfterClass
    public void afterClassMethod(){
	utilsObject.clearCookies();
	utilsObject.browserExit();
    }


}
