package stepDefinitions;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginStepDefinition {
	
	public LoginStepDefinition() {
		logger = Logger.getLogger(LoginStepDefinition.class);
	}

	WebDriver driver;
	protected Logger logger;

	@Given("^user is already on Login Page$")
	public void user_already_on_login_page() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\AUTOMATION\\AUTOMATION-LABS\\All Lab Projects\\CucumberSeleniumFramework\\src\\main\\resources\\config\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("disable-infobars");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.freecrm.com/index.html");
	}

	@When("^title of login page is Free CRM$")
	public void title_of_login_page_is_free_CRM() {
		String title = driver.getTitle();
		logger.info("LoginPage Title : " +title);
		//System.out.println(title);
		Assert.assertEquals("#1 Free CRM software in the cloud for sales and service", title);
	}

	// Reg Exp:
	// 1. \"([^\"]*)\"
	// 2. \"(.*)\"

	@Then("^user enters username and password$")
	public void user_enters_username_and_password() {
		driver.findElement(By.name("username")).sendKeys("naveenk");
		driver.findElement(By.name("password")).sendKeys("test@123");
	}

	@Then("^user clicks on login button$")
	public void user_clicks_on_login_button() {
		WebElement loginBtn = driver.findElement(By.xpath("//input[@type='submit']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", loginBtn);
	}

	@Then("^user is on home page$")
	public void user_is_on_hopme_page() {
		String title = driver.getTitle();
		logger.info("Home Page title :" + title);
		//System.out.println("Home Page title ::" + title);
		Assert.assertEquals("CRMPRO", title);
	}

	@Then("^user moves to new contact page$")
	public void user_moves_to_new_contact_page() {
		driver.switchTo().frame("mainpanel");
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'Contacts')]"))).build().perform();
		driver.findElement(By.xpath("//a[contains(text(),'New Contact')]")).click();

	}

	@Then("^user enters contact details \"(.*)\" and \"(.*)\" and \"(.*)\"$")
	public void user_enters_contacts_details(String firstname, String lastname, String position) {
		driver.findElement(By.id("first_name")).sendKeys(firstname);
		driver.findElement(By.id("surname")).sendKeys(lastname);
		driver.findElement(By.id("company_position")).sendKeys(position);
		driver.findElement(By.xpath("//input[@type='submit' and @value='Save']")).click();
	}

	@Then("^Close the browser$")
	public void close_the_browser() {
		driver.quit();
	}

}
