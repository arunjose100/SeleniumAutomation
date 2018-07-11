package com.hellofresh.selenium.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hellofresh.selenium.common.CommonUtils;

public class LoginPage {
	
	private WebDriver driver;
    private CommonUtils utils;
    private WebDriverWait wait;
    Select select;
    private By Login = By.className("login");
    private By Email = By.id("email_create");
    private By Submit = By.id("SubmitCreate");
    private By Gender = By.id("id_gender2");
    private By FirstName = By.id("customer_firstname");
    private By LastName = By.id("customer_lastname");
    private By pwd = By.id("passwd");
    private By Days = By.id("days");
    private By Months = By.id("months");
    private By Years = By.id("years");
    private By Company = By.id("company");
    private By AddressOne = By.id("address1");
    private By Addresstwo = By.id("address2");
    private By City = By.id("city");
    private By State = By.id("id_state");
    private By PostCode = By.id("postcode");
    private By Other = By.id("other");
    private By Phone = By.id("phone");
    private By Mobile = By.id("phone_mobile");
    private By Alias = By.id("alias");
    private By SubmitButton = By.id("submitAccount");
    private By EmailId = By.id("email");
    private By SubmitLogin = By.id("SubmitLogin");

    
    public LoginPage(WebDriver driver) throws FileNotFoundException, IOException{
	this.driver  = driver;
	this.utils = new CommonUtils(driver);
	this.wait = new WebDriverWait(driver, 10, 50);
	}
    
    public void SignUpAction(Properties prop){
    	try{
        wait.until(ExpectedConditions.visibilityOfElementLocated(Login)).click();
        String timestamp = String.valueOf(new Date().getTime());
        String email = "hf_challenge_" + timestamp + "@hf" + timestamp.substring(7) + ".com";
        String name = prop.getProperty("nameToUsed");
        String surname = prop.getProperty("surname");
        driver.findElement(Email).sendKeys(email);
        driver.findElement(Submit).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Gender)).click();
        driver.findElement(FirstName).sendKeys(name);
        driver.findElement(LastName).sendKeys(surname);
        
        driver.findElement(pwd).sendKeys(prop.getProperty("Pwd"));
        dropDownSelection(driver.findElement(Days));
        select.selectByValue("1");
        dropDownSelection(driver.findElement(Months));
        select.selectByValue("1");
        dropDownSelection(driver.findElement(Years));
        select.selectByValue("2000");
        driver.findElement(Company).sendKeys(prop.getProperty("Company"));
        driver.findElement(AddressOne).sendKeys(prop.getProperty("AddressOne"));
        driver.findElement(Addresstwo).sendKeys(prop.getProperty("AddressTwo"));
        driver.findElement(City).sendKeys(prop.getProperty("City"));
        dropDownSelection(driver.findElement(State));
        select.selectByVisibleText("Colorado");
        driver.findElement(PostCode).sendKeys(prop.getProperty("PostCode"));
        driver.findElement(Other).sendKeys(prop.getProperty("Others"));
        driver.findElement(Phone).sendKeys(prop.getProperty("Phone"));
        driver.findElement(Mobile).sendKeys(prop.getProperty("Mobile"));
        driver.findElement(Alias).sendKeys(prop.getProperty("Alias"));
        driver.findElement(SubmitButton).click();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    public void LogInAction(Properties prop){
        wait.until(ExpectedConditions.visibilityOfElementLocated(Login)).click();
        driver.findElement(EmailId).sendKeys(prop.getProperty("existingUserEmail"));
        driver.findElement(pwd).sendKeys(prop.getProperty("existingUserPassword"));
        driver.findElement(SubmitLogin).click();
    }
    
    public void dropDownSelection(WebElement element){
    	System.out.println("Performing DropDown Value Selection");
    	try{
    	select = new Select(element);
    	}catch(Exception e){
    	    System.out.println("Element is not visible/displayed..");
    	}
    }
    
    
    public String getTitle(){
    	String url = driver.getCurrentUrl();
    	String title = driver.getTitle();
    	System.out.println("Page url is>>"+url);
    	return title;
    }
        
    public void elementClickByText(String text){
    	WebElement element = driver.findElement(By.linkText(text));
    	
    	try{
    	if(element.isDisplayed()){
    	    element.click();
    	    utils.defaultBrowerHold(5000);
    	}
    	System.out.println("Clicked on>>"+element.getText());
    	}catch(Exception e){
    	    System.out.println("Element is not visible/displayed..");
    	}
    	
        }
        

}
