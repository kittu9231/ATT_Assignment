import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import java.util.Iterator;
import java.util.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import java.net.MalformedURLException;

        String url = "";
        HttpURLConnection huc = null;
        int respCode = 200;
		Set<String> response_code = new HashSet<String>();
		Set<String> broken_link = new HashSet<String>();
		Set<String> valid_link = new HashSet<String>();
		//Loaded the variables and started the ATT Home Page
        WebUI.openBrowser('')
        def driver = DriverFactory.getWebDriver();
	    //driver.manage().window().maximize();
        
        driver.get(GlobalVariable.homepage);
        
		//List the web elements that start with the tag name 'a', and print the size of elements
        List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Total links ATT Home Page: " + links.size());
        
		//Iterator to pass through all the links
        Iterator<WebElement> it = links.iterator();
        
        while(it.hasNext()){
            
            url = it.next().getAttribute("href");
            System.out.println(url);
        
            
			//Check the Response code and catch if have any IO Exceptions
            try {
					huc = (HttpURLConnection)(new URL(url).openConnection());
					
					huc.setRequestMethod("HEAD");
					
					huc.connect();
					
					respCode = huc.getResponseCode();
					
					 if(respCode > 400 && respCode != 405 && respCode != 503 && respCode != 504 && respCode != 999){
						System.out.println(url+" is a broken link with responsecode "+ respCode);
						broken_link.add(url);
						response_code.add(respCode);
					}
					else{
						System.out.println(url+" is a valid link with responsecode "+ respCode);
						valid_link.add(url);
					}
					
					
				}	
				
			 catch (IOException e) {
                 //TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
		//Assertions for any broken links
		assert broken_link.size() == 0
		if(broken_link.size() > 0 ){
		System.out.println("Total Number of broken links in ATT home page " + broken_link.size())
		System.out.println("All broken links "+ broken_link )
		System.out.println("Response code for broken links "+ response_code )
		}
		
		else {
			System.out.println("Total Number of Valid links in ATT home page " + valid_link.size())
		}
		WebUI.closeBrowser()