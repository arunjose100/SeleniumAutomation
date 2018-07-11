package com.hellofresh.selenium.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


public class BaseClass {
    
    public static WebDriver driver;
    static DesiredCapabilities caps;
    static ChromeOptions chromeOptions;
    
    public static WebDriver browserInitialization(String browserType){
    
    switch(browserType){
    
    case "Firefox":
	    driver= new FirefoxDriver();
	    System.out.println("Initialized Firefox Driver");
	    break;
	    
    case "IE":
	    System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\drivers\\IEDriverServer.exe");
	    caps = DesiredCapabilities.internetExplorer();
	    caps.setCapability("EnableNativeEvents", false);
	    caps.setCapability("ignoreZoomSetting", true);
	    caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
	    driver= new InternetExplorerDriver(caps);
	    System.out.println("Initialized Internet Explorer Driver");
	    break;
	    
    case "Chrome":
	    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
	    caps = DesiredCapabilities.chrome();
	    chromeOptions = new ChromeOptions();
	    chromeOptions.addArguments("--disable-infobars");
	    caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	    caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
	    
	    driver= new ChromeDriver(caps);
	    System.out.println("Initialized Chrome Driver");
	    break;
	    
	default:
		System.out.println("No compactable Driver Found!!");
		driver = null;
    }
	
	return driver;
	
    }

}
