package com.hellofresh.selenium.common;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTest implements IRetryAnalyzer{
    int count = 0;
    int retry = 3;
    
	@Override
	public boolean retry(ITestResult result) {
		if (count < retry) {
            System.out.println("Retrying test " + result.getName() + " with status "
                    + getResultStatusName(result.getStatus()) + " for the " + (count+1) + " time(s).");
            count++;
            return true;
        }
		return false;
	}
	
	public String getResultStatusName(int status) {
    	String resultName = null;
    	if(status==1)
    		resultName = "SUCCESS";
    	if(status==2)
    		resultName = "FAILURE";
    	if(status==3)
    		resultName = "SKIP";
		return resultName;
    }

}
