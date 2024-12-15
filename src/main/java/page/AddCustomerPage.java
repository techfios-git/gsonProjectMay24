package page;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class AddCustomerPage extends BasePage{
	
	WebDriver driver;
	
	public AddCustomerPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/section/div/div[2]/div/div[1]/div[1]/div/div/header/div/strong") WebElement NEW_CUSTOMER_HEADER_ELEMENT;
	@FindBy(how = How.XPATH, using = "//*[@id=\"general_compnay\"]/div[1]/div/input") WebElement FULL_NAME_ELEMENT;
	@FindBy(how = How.XPATH, using = "//*[@id=\"general_compnay\"]/div[2]/div/select") WebElement COMPANY_DROPDWN_ELEMENT;
	@FindBy(how = How.XPATH, using = "//*[@id=\"general_compnay\"]/div[3]/div/input") WebElement EMAIL_ELEMENT;
	@FindBy(how = How.XPATH, using = "//*[@id=\"phone\"]") WebElement PHONE_ELEMENT;
	@FindBy(how = How.XPATH, using = "//*[@id=\"general_compnay\"]/div[5]/div/input") WebElement ADDRESS_ELEMENT;
	@FindBy(how = How.XPATH, using = "//*[@id=\"general_compnay\"]/div[6]/div/input") WebElement CITY_ELEMENT;
	@FindBy(how = How.XPATH, using = "//*[@id='port']") WebElement ZIP_ELEMENT;
	@FindBy(how = How.XPATH, using = "//select[@name='country']") WebElement COUNTRY_DROPDWN_ELEMENT;
	@FindBy(how = How.XPATH, using = "//*[@id=\"save_btn\"]") WebElement SAVE_BUTTON_ELEMENT;
	@FindBy(how = How.XPATH, using = "//strong[text()='Customer List']") WebElement LIST_CUSTOMER_HEADER_ELEMENT;
	
	
	public String validateAddCustomerPage() {
		String actualText = NEW_CUSTOMER_HEADER_ELEMENT.getText();
		return actualText;
	}
	
	String insertedName;
	public void insertFullName(String fullName) {
		insertedName = fullName + generateRandomNum(999);
		FULL_NAME_ELEMENT.sendKeys(insertedName);
	}
	
	public void selectCompany(String company) {
		selectFromDropdown(COMPANY_DROPDWN_ELEMENT, company);
	}
	
	public void insertEmail(String email) {
		EMAIL_ELEMENT.sendKeys(generateRandomNum(9999) + email);
	}
	
	public void insertPhone(String phone) {
		PHONE_ELEMENT.sendKeys(phone + generateRandomNum(999));
	}
	
	public void insertAddress(String address) {
		ADDRESS_ELEMENT.sendKeys(address);
	}
	
	public void insertCity(String city) {
		CITY_ELEMENT.sendKeys(city);
	}
	
	public void insertZip(String zip) {
		ZIP_ELEMENT.sendKeys(zip);
	}
	
	public void selectCountry(String country) {
		selectFromDropdown(COUNTRY_DROPDWN_ELEMENT, country);
	}
		
	public void clickSaveButton() {
		SAVE_BUTTON_ELEMENT.click();
	}
	
	
	public String validateListCustomerPage() {
		
		waitForElement(driver, 60, LIST_CUSTOMER_HEADER_ELEMENT);
		
		String actualText = LIST_CUSTOMER_HEADER_ELEMENT.getText();
		return actualText;
	}
	
	

	//tbody/tr[1]/td[2]/a
	//tbody/tr[2]/td[2]/a
	//tbody/tr[3]/td[2]/a
	//tbody/tr[i]/td[2]/a
	//tbody/tr[1]/td[9]/button
	
	String before_xpath = "//tbody/tr[";
	String after_xpath = "]/td[2]/a";
	String after_xpath_delete = "]/td[9]/button";
	
	public void validateInsertedNameAndDelete() {
		
		for(int i = 1; i <= 10; i++) {
			
			String actualName = driver.findElement(By.xpath(before_xpath + i + after_xpath)).getText();
			System.out.println(actualName);
			if(actualName.contains(insertedName)) {
				System.out.println("Inserted name exist!!");
				driver.findElement(By.xpath(before_xpath + i + after_xpath_delete)).click();
				break;
			}
			
		}
		
		
	}
	
	
}
