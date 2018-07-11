package com.hellofresh.selenium.pages;

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
import org.testng.annotations.Test;

import com.hellofresh.selenium.browser.BaseClass;
import com.hellofresh.selenium.common.CommonUtils;

public class CheckOutPage {
	
	private WebDriver driver;
    private CommonUtils utils;
    private WebDriverWait wait;
    LoginPage loginPageObject;
    private By Women = By.linkText("Women");
    private By ElementOne = By.xpath("//a[@title='Faded Short Sleeve T-shirts']/ancestor::li");
    private By SubmitButton = By.name("Submit");
    private By ElementThree = By.xpath("//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']");
    private By ElementFour = By.xpath("//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']");
    private By ElementFive = By.name("processAddress");
    private By ElementSix = By.id("uniform-cgv");
    private By ElementSeven = By.name("processCarrier");
    private By ElementEight = By.className("bankwire");
    private By CartButton = By.xpath("//*[@id='cart_navigation']/button");
    
	
	public CheckOutPage(WebDriver driver) throws FileNotFoundException, IOException{
		this.driver  = driver;
		this.utils = new CommonUtils(driver);
		this.wait = new WebDriverWait(driver, 10, 50);
		this.loginPageObject = new LoginPage(driver);
		}
	
	public void checkOut(Properties prop){
		loginPageObject.LogInAction(prop);
		
        wait.until(ExpectedConditions.visibilityOfElementLocated(Women)).click();
        driver.findElement(ElementOne).click();
        driver.findElement(ElementOne).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(SubmitButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(ElementThree)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(ElementFour)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(ElementFive)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(ElementSix)).click();
        driver.findElement(ElementSeven).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(ElementEight)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(CartButton)).click();
	}
}
