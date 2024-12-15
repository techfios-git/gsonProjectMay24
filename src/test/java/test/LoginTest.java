package test;

import java.io.FileReader;
import java.io.IOException;
import java.sql.PseudoColumnUsage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import page.DashboardPage;
import page.LoginPage;
import util.BrowserFactory;


public class LoginTest {
	
	WebDriver driver;
//	JsonElement jsoneleObj;
	
	
	
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
	 * } catch (IOException e) { e.getStackTrace(); }
	 * 
	 * }
	 */
	
	
	@Test(groups = {"smoke", "regression"})
	public void validUserShouldBeAbleToLogin() {
		
		driver = BrowserFactory.init();		
		
//		LoginPage loginPage1 = new LoginPage(driver);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.insertUserName(BrowserFactory.getJsoneleObj().getAsJsonObject().get("LoginInfo").getAsJsonObject().get("UserName").getAsString());
		loginPage.insertPassword(BrowserFactory.getJsoneleObj().getAsJsonObject().get("LoginInfo").getAsJsonObject().get("Password").getAsString());
		loginPage.clickSigninButton();
		
		DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
		String actualDashboardHeader = dashboardPage.validateDashboarPage();
		Assert.assertEquals(actualDashboardHeader, BrowserFactory.getJsoneleObj().getAsJsonObject().get("LoginInfo").getAsJsonObject().get("ValidationTextLogin").getAsString(), "Dashboard page not found!");
		
		BrowserFactory.tearDown();
		
	}
	
	@Test(groups = "regression")
	public void validateAlertMessages() {
		
		driver = BrowserFactory.init();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.clickSigninButton();
		String userAlertMsg = loginPage.validateAlertMsg();
		Assert.assertEquals(userAlertMsg, BrowserFactory.getJsoneleObj().getAsJsonObject().get("LoginInfo").getAsJsonObject().get("expectedUserAlertMsg").getAsString(), "User alert msg doesn't match!");
		
		loginPage.insertUserName(BrowserFactory.getJsoneleObj().getAsJsonObject().get("LoginInfo").getAsJsonObject().get("UserName").getAsString());
		loginPage.clickSigninButton();
		String passwordAlertMsg = loginPage.validateAlertMsg();
		Assert.assertEquals(passwordAlertMsg, BrowserFactory.getJsoneleObj().getAsJsonObject().get("LoginInfo").getAsJsonObject().get("expectedPasswordAlertMsg").getAsString(), "Password alert msg doesn't match!");
		
		BrowserFactory.tearDown();
		
	}
	
	@Test(groups = "smoke")
	public void testSmoke() {
		System.out.println("run smoke");
	}

	@Test(groups = "regression")
	public void testRegression() {
		System.out.println("run Regression");
	}

}
