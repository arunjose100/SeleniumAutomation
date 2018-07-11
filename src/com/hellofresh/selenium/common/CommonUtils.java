package com.hellofresh.selenium.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

public class CommonUtils {
    
    private WebDriver driver;
    private WebDriverWait myWait;
    protected Properties propertiesObject;
    private int i;
    ATUTestRecorder atuObject;
    
    public CommonUtils(WebDriver driver) throws FileNotFoundException, IOException{
	this.driver = driver;
	propertiesObject = new Properties();
	propertiesObject.load(new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\hellofresh\\selenium\\properties\\HelloFresh.properties"));
    }
    
    public void screenMaximize(){
	System.out.println("Maximizing Window Size to Full Screen..");
	driver.manage().window().maximize();
	System.out.println("Window Maximized to fit Desktop");
	
    }
    
    public void clearCookies(){
	System.out.println("Clearing all Cookies..");
	driver.manage().deleteAllCookies();
    }
    
    public void explicitWait(WebElement element, int time){
	this.myWait = new WebDriverWait(driver, time);
	myWait.until(ExpectedConditions.visibilityOf(element));
    }
    
    public void defaultBrowerHold(int timeInMs) throws Exception{
	System.out.println("Browser into Sleep state");
	Thread.sleep(timeInMs);
    }
    
    public void browserExit(){
	System.out.println("Exiting Test...");
	driver.close();
    }
    
    public boolean atuRecorder() throws ATUTestRecorderException{
	String comment = propertiesObject.getProperty("Required");
	boolean recording;
	if(comment=="Yes"){
	    atuObject = new ATUTestRecorder(System.getProperty("user.dir")+"\\Videos", "RecorderingInstance"+i, false);
	    i++;
	    atuObject.start();
	    recording = true;
	}else{
	    recording = false;
	}
	
	return recording;
    }

}
