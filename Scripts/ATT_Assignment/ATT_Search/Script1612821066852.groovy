import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import java.util.Iterator;
import java.util.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import java.net.MalformedURLException;

        //Selenium WebDriver to wait until the initial HTML document has been completely loaded and parsed
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
		
		//Open the web browser and load it to ATT Home Page
		WebUI.openBrowser('');
		WebUI.navigateToUrl(GlobalVariable.homepage); //The URL is definded under profiles
		
		//search the ATT home page with a search term defined in variables of this test cases
		WebUI.setText(findTestObject('ATT_Assignment/Set_Search'), Searchterm);
		
		//Click the search button
		WebUI.click(findTestObject('ATT_Assignment/Search_button'));
		
		//Assertion for the search term
		def text = WebUI.getText(findTestObject('ATT_Assignment/Assertion_Search'));
		WebUI.verifyMatch(text, Assertion, true, FailureHandling.STOP_ON_FAILURE);
		
		//Close the browser
		WebUI.closeBrowser();

