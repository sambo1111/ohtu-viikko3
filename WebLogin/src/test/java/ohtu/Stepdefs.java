package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Stepdefs {
    WebDriver driver = new ChromeDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();          
    } 
    
    @Given("^command new user is selected$")
    public void signup_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();          
    }
    
    @Given("user with username \"([^\"]*)\" with password \"([^\"]*)\" is successfully created")
    public void user_is_created(String name, String password) throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();
        registerWith(name,password,password);
    }
    
    @Given("user with username \"([^\"]*)\" and password \"([^\"]*)\" is tried to be created")
    public void bad_user_is_tried_created(String name, String password) throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();
        registerWith(name,password,password);
    }

    @When("^username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_and_password_are_given(String username, String password) throws Throwable {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    }

    @Then("^system will respond \"([^\"]*)\"$")
    public void system_will_respond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }
    
    @When("^username \"([^\"]*)\" and password \"([^\"]*)\" are entered")
    public void username_and_password_are_entered(String username, String password) {
        logInWith(username, password);
    }
    
    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    
    @When("^nonexistent username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void nonexistent_username_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    
    @When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered$")
    public void valid_username_and_password_and_matching_confirmation_password_are_entered(String username, String password) throws Throwable {
        registerWith(username, password, password);
    }
    
    @When("^too short username \"([^\"]*)\" and valid password \"([^\"]*)\" and matching confirmation are given$")
    public void too_short_username_and_valid_password_and_confirmation_given(String username, String password) throws Throwable {
        registerWith(username, password, password);
    }
    
    @When("^already taken username \"([^\"]*)\" and valid password \"([^\"]*)\" are given$")
    public void already_taken_username_and_valid_password_given(String username, String password) throws Throwable {
        registerWith(username, password, password);
    }
    
    @When("^valid username \"([^\"]*)\" and password \"([^\"]*)\" and mismatched password confirmation \"([^\"]*)\" are given$")
    public void valid_username_and_mismatched_passwords_given(String username, String password, String confirmation) throws Throwable {
        registerWith(username, password, confirmation);
    }
    
    @When("^valid username \"([^\"]*)\" and too short password \"([^\"]*)\" are given$")
    public void valid_username_and_too_short_password_given(String username, String password) throws Throwable {
        registerWith(username, password, password);
    }


    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    
    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }
    
    @Then("^a new user is created$")
    public void new_user_is_created() throws Throwable {
        pageHasContent("Welcome to Ohtu Application!");
    }
    
    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }
    
    @Then("^user is not created and error \"username is already taken\" is reported$")
    public void user_is_not_created_and_error_already_taken_given() throws Throwable {
        pageHasContent("username is already taken");
    }
    
    @Then("^user is not created and error \"password and password confirmation do not match\" is reported$")
    public void user_is_not_created_and_error_confirmation_mismatch_given() throws Throwable {
        pageHasContent("password and password confirmation do not match");
    }
    
    @Then("^user is not created and error \"username should have at least 3 characters\" is reported$")
    public void user_not_created_and_error_too_short_name_given() throws Throwable {
        pageHasContent("username should have at least 3 characters");
    } 
    
    @Then("^user is not created and error \"password should have at least 8 characters\" is reported$")
    public void user_not_created_and_error_too_short_password_given() throws Throwable {
        pageHasContent("password should have at least 8 characters");
    } 
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    } 
    
    private void registerWith(String username, String password, String password_confirmation) throws Exception {
        
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password_confirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
}
