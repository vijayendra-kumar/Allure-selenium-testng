package tests;

import java.time.Duration;

import org.codehaus.plexus.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;

public class SampleTest {
private	Logger logger;
private WebDriver driver;


@BeforeClass
public void SetUp() {
	
	driver = new ChromeDriver();
}

@Test
@Feature("Sample Feature")
@Description("Test Case to open Google and Verify the title.")
public void testGoolgeSearch() {
	openHomePage();
	searchFor("Selenium");
	verifyTitle("Selenium - Google Search");
}
@Step("Open Google")
private void openHomePage() {
	driver.get("https://www.google.com");
}
@Step("Search for : {query}")
private void searchFor(String query) {
	driver.findElement(By.name("q")).sendKeys(query);
	driver.findElement(By.name("q")).submit();
}

@Step("Verify title is :{expectedTitle}")
private void verifyTitle(String expectedTitle) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	//WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("your CSS selector")));
	wait.until(ExpectedConditions.titleContains(expectedTitle));
	String actualTitle = driver.getTitle();
	//logger.debug(actualTitle);
	System.out.print("################ tittle : "+actualTitle);
	Assert.assertEquals(actualTitle,expectedTitle);
}

@AfterClass
private void tearDown() {
	if(driver != null) {
		//driver.quit();
	}
}
}