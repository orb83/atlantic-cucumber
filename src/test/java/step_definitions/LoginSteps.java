package step_definitions;

import command_providers_ModularFrmWrk.ActOn;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import utilities.ReadConfigFiles;


import java.util.List;
import java.util.Map;

public class LoginSteps {

    private static final By FullName = By.id("name");
    private static final By Password = By.id("password");
    private static final By Login = By.id("login");
    private static final By Logout = By.id("logout");
    private static final By InvalidPassword = By.xpath("//*[@id='pageLogin']/form//div[text()='Password is invalid']");
    private  Logger LOGGER = LogManager.getLogger(this.getClass().getName());

    WebDriver driver = Hooks.driver;

    @Given("^a user is on the login page$")
    public void navigateToLoginPage(){
       // ActOn.browser(driver).openBrowser(ReadConfigFiles.getPropertyValues("TestAppURL"));
        ActOn.browser(driver).openBrowser("https://example.testproject.io/web");
        LOGGER.info("User is in the Login Page");
    }
    //Main Action

    @When("^user enters username \"(.+?)\" and password \"(.+?)\"$")
    public void enterUserCredentials(String username, String password){
        ActOn.element(driver,FullName).setValue(username);
        ActOn.element(driver,Password).setValue(password);
        LOGGER.info("User has entered credentials");
    }

    //Additional Action
    @And("^click on login button$")
    public void clickOnLogin(){
        ActOn.element(driver,Login).click();
        LOGGER.info("User Clicked on the Login Button");
    }

    @When("user click on login button upon entering credentials")
    public void userClickOnLoginButtonEnteringCredentials(DataTable table){
        List<Map<String,String>> data = table.asMaps(String.class,String.class);
        for (Map<String,String>cells: data) {
            ActOn.element(driver,FullName).setValue(cells.get("username"));
            ActOn.element(driver,Password).setValue(cells.get("password"));
            LOGGER.info("User has entered credentials");

            ActOn.element(driver,Login).click();
            LOGGER.info("User clicked on login button");
        }
    }

    //Assertion
    @Then("^user is navigated to home page$")
    public void validateUserIsLoggedInSuccessfully(){
        boolean logoutDisplayed = driver.findElement(Logout).isDisplayed();
        Assert.assertTrue("Logout button is not displayed",logoutDisplayed);
        LOGGER.info("User is in the home Page");

    }

    @Then("^user is failed to login$")
    public void validateUserIsFailedToLogin(){
        boolean invalidPassword = driver.findElement(InvalidPassword).isDisplayed();
        Assert.assertTrue("Invalid Password is not displayed",invalidPassword);
        LOGGER.info("User is still on the login Page");

    }
}
