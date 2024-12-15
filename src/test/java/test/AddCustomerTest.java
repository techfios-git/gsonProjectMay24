package test;

import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import page.AddCustomerPage;
import page.DashboardPage;
import page.LoginPage;
import util.BrowserFactory;


public class AddCustomerTest {
	
	WebDriver driver;
//	JsonElement jsoneleObj;
	
	
	String userName = null;
	String password = null;
	String dashboardHeaderPage = null;
	String addCustomerHeader = null;
	String fullName = null;
	String company = null;
	
	/*
	 * @SuppressWarnings("deprecation")
	 * 
	 * @BeforeMethod(alwaysRun = true) public void readJson() {
	 * 
	 * try {
	 * 
	 * FileReader reader = new
	 * FileReader("src\\main\\java\\testData\\TF_TestData.json");
	 * 
	 * @SuppressWarnings("deprecation") JsonParser perserObj = new JsonParser();
	 * jsoneleObj = perserObj.parse(reader);
	 * 
	 * 
	 * 
	 * } catch (IOException e) { e.getStackTrace(); }
	 * 
	 * }
	 */
	
	@Test(groups = {"regression"})
	public void validUserShouldBeAbleToAddCustomer() {
		
		driver = BrowserFactory.init();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.login(BrowserFactory.getJsoneleObj().getAsJsonObject().get("LoginInfo").getAsJsonObject().get("UserName").getAsString(), BrowserFactory.getJsoneleObj().getAsJsonObject().get("LoginInfo").getAsJsonObject().get("Password").getAsString());
		
		
		DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
		Assert.assertEquals(dashboardPage.validateDashboarPage(), BrowserFactory.getJsoneleObj().getAsJsonObject().get("LoginInfo").getAsJsonObject().get("ValidationTextLogin").getAsString(), "Dashboard page is not available!");
		dashboardPage.clickOnCustomer();
		dashboardPage.clickOnAddCustomer();
		
		AddCustomerPage addCustomerPage = PageFactory.initElements(driver, AddCustomerPage.class);
		Assert.assertEquals(addCustomerPage.validateAddCustomerPage(), BrowserFactory.getJsoneleObj().getAsJsonObject().get("AddContact").getAsJsonObject().get("ValidationTextAddCustomer").getAsString(), "Add Customer page is not available!");
		addCustomerPage.insertFullName(BrowserFactory.getJsoneleObj().getAsJsonObject().get("AddContact").getAsJsonObject().get("FullName").getAsString());
		addCustomerPage.selectCompany(BrowserFactory.getJsoneleObj().getAsJsonObject().get("AddContact").getAsJsonObject().get("Company").getAsString());
		addCustomerPage.insertEmail(BrowserFactory.getJsoneleObj().getAsJsonObject().get("AddContact").getAsJsonObject().get("Email").getAsString());
		addCustomerPage.insertPhone(BrowserFactory.getJsoneleObj().getAsJsonObject().get("AddContact").getAsJsonObject().get("Phone").getAsString());
		addCustomerPage.insertAddress(BrowserFactory.getJsoneleObj().getAsJsonObject().get("AddContact").getAsJsonObject().get("Address").getAsJsonArray().get(0).getAsJsonObject().get("Street").getAsString());
		addCustomerPage.insertCity(BrowserFactory.getJsoneleObj().getAsJsonObject().get("AddContact").getAsJsonObject().get("Address").getAsJsonArray().get(0).getAsJsonObject().get("City").getAsString());
		addCustomerPage.insertZip(BrowserFactory.getJsoneleObj().getAsJsonObject().get("AddContact").getAsJsonObject().get("Address").getAsJsonArray().get(0).getAsJsonObject().get("Zip").getAsString());
		addCustomerPage.selectCountry(BrowserFactory.getJsoneleObj().getAsJsonObject().get("AddContact").getAsJsonObject().get("Address").getAsJsonArray().get(0).getAsJsonObject().get("Country").getAsString());
		addCustomerPage.clickSaveButton();
		
		Assert.assertEquals(addCustomerPage.validateListCustomerPage(), "Customer List", "List Customer page is not available!");
		addCustomerPage.validateInsertedNameAndDelete();
		
		
	}

}
