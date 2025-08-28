package stepsDefinitions;



import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class LoginSteps {
    protected static WebDriver driver = null;
    protected static WebDriverWait wait = null;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("I open the bank login page")
    public void openLoginPage() {
        driver.get("https://185.199.31.30:8443/bank/login");

        // Wait and accept cookie banner if it appears
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Adjust this selector to your actual cookie accept button
            wait.until(ExpectedConditions.elementToBeClickable(By.id("cookie-accept"))).click();
        } catch (Exception e) {
            System.out.println("Cookie banner not found or already accepted.");
        }
    }

    @When("I enter username {string} and password {string}")
    public void enterCredentials(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @When("I click the login button")
    public void clickLogin() {
        driver.findElement(By.id("submit")).click();
    }

    @Then("I should see the welcome page")
    public void verifyWelcomePage() {
        String title = driver.getTitle();
        assertTrue("Expecting welcome in title", title.contains("Welcome"));
    }

    @Then("I should see a login error message")
    public void verifyErrorMessage() {
        String error = driver.findElement(By.id("loginError")).getText();
        assertTrue("Error should be displayed", error.contains("Invalid credentials"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
