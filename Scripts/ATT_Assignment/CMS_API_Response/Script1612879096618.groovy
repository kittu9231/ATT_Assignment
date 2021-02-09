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
import groovy.json.JsonOutput
import groovy.json.JsonSlurper

//Send the Request and load the Response value
def Response= WS.sendRequest(findTestObject('ATT_Assignment/ATT_API'), FailureHandling.STOP_ON_FAILURE)

//Verify the response code, Assertion added from the variable section
WS.verifyResponseStatusCode(Response, Assertion)

//Get the response in Raw format
System.out.println(Response.getResponseText())

//System.out.println(Response.get(label).get(master).get(infinity))



//I didnt get a clear picture of CMS configuration with home page, If I am not wrong we should match the JSON response for a particular component with the same component in home page//

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.homepage)
text = WebUI.getText(findTestObject('ATT_Assignment/Deals_Element'))
System.out.println(text)
WebUI.closeBrowser();
