package step_definitions;

import command_providers_ModularFrmWrk.ActOn;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import page_objects.Home;
import page_objects.RealApr;
import utilities.ReadConfigFiles;

import java.util.List;
import java.util.Map;

public class MortgageSteps {
    private final Logger LOGGER = LogManager.getLogger(this.getClass().getName());
    WebDriver driver = Hooks.driver;

    @Given("user is in mortgage calculator home page")
    public void navigateToMortgageCalculatorHomePage(){
        ActOn.browser(driver).openBrowser(ReadConfigFiles.getPropertyValues("MortgageAppURL"));
        LOGGER.info(" Landed on the mortgage Calculator Home Page");
    }

    @And("user navigate to Real Apr page")
    public void navigateToRealAprPage(){
        new Home(driver)
                .mouseHoverToRates()
                .navigateToRealApr();
        LOGGER.info("Navigated to Real Apr Page");

    }

    @When("user click on calculate button upon entering the data")
    public void clickOnCalculateButtonUponEnteringData(DataTable table){
        List<Map<String,String>> data = table.asMaps(String.class,String.class);
        for (Map<String,String> cells: data) {
            new RealApr(driver)
                    .typeHomePrice(cells.get("HomePrice"))
                    .typeDownPayment(cells.get("DownPayment"))
                    .typeInterestRate(cells.get("InterestRate"))
                    .clickOnCalculateButton();

        }
        LOGGER.info("Real APR Rate is calculated upon entering the data");
    }

    @Then("^the real apr rate is \"(.+?)\"$")
    public void validateRealAprRate(String realApr){
        new RealApr(driver)
                .validateRealAprRate(realApr);
        LOGGER.info("Real APR Rate is Validate");
    }
}
