package page_objects;

import command_providers_ModularFrmWrk.ActOn;
import command_providers_ModularFrmWrk.AssertThat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home extends NavigationBar{

    private final By HomevalueInputField = By.id("homeval");
    private final By DownpaymentInputField = By.id("downpayment");
    private final By DownpaymentTypeInDollar = By.xpath("//*[@id='calc']//input[@name='param[downpayment_type]'][@value='money']");
    private final By LoanamountInputField = By.id("loanamt");
    private final By InterestRateInputField = By.id("intrstsrate");
    private final By LoantermInputField = By.id("loanterm");
    private final By StartMonthDropDown = By.name("param[start_month]");
    private final By StartYearInputField = By.id("start_year");
    private final By PropertyTaxInputField = By.id("pptytax");
    private final By PmiInputField = By.id("pmi");
    private final By HomeInsInputField = By.id("hoi");
    private final By MonthlyhoaInputField = By.id("hoa");
    private final By LoanTypeDropDown = By.name("param[milserve]");
    private final By BuyOrRefDropDown= By.name("param[refiorbuy]");
    private final By CalculateButton = By.name("cal");

    private static final Logger LOGGER = LogManager.getLogger(Home.class);

    public  Home(WebDriver driver){
        super(driver);
    }

    public Home typeHomePrice(String value){
        LOGGER.debug("Typing Home Value: " +value);
        ActOn.element(driver,HomevalueInputField).setValue(value);
        return this;
    }

    public Home typeDownPayment(String value){
        LOGGER.debug("Typing Down Payment Value: " +value);
        ActOn.element(driver,DownpaymentInputField).setValue(value);
        return this;
    }

    public Home clickDownPaymentInDollar(){
        LOGGER.debug("Clicking on the $ radio button: ");
        ActOn.element(driver,DownpaymentTypeInDollar).click();
        return this;
    }

    public Home typeLoanAmount(String value){
        LOGGER.debug("Typing the Loan Amount: " +value);
        ActOn.element(driver,LoanamountInputField).setValue(value);
        return this;
    }

    public Home typeInterestRate(String value){
        LOGGER.debug("Typing the Interest Rate: " +value);
        ActOn.element(driver,InterestRateInputField).setValue(value);
        return this;
    }

    public Home typeLoanTermYears(String value){
        LOGGER.debug("Typing the Loan Year: " +value);
        ActOn.element(driver,LoantermInputField).setValue(value);
        return this;
    }

    public Home selectMonth(String month){
        LOGGER.debug("Select the start date Month: " +month);
        ActOn.element(driver,StartMonthDropDown).selectValue(month);
        return this;
    }

    public Home typeYear(String year){
        LOGGER.debug("Typing the Loan Start Year: " +year);
        ActOn.element(driver,StartYearInputField).setValue(year);
        return this;
    }

    public Home typePropertyTax(String value){
        LOGGER.debug("Typing the Property Tax: " +value);
        ActOn.element(driver,PropertyTaxInputField).setValue(value);
        return this;
    }

    public Home typePMI(String value){
        LOGGER.debug("Typing the PMI: " +value);
        ActOn.element(driver,PmiInputField).setValue(value);
        return this;
    }

    public Home typeHomeOwnerInsurance(String value){
        LOGGER.debug("Typing the Home Owner Insurance: " +value);
        ActOn.element(driver,HomeInsInputField).setValue(value);
        return this;
    }

    public Home typeMOnthlyHOA(String value){
        LOGGER.debug("Typing the Monthly HOA: " +value);
        ActOn.element(driver,MonthlyhoaInputField).setValue(value);
        return this;
    }

    public Home selectLoanType(String value){
        LOGGER.debug("Selecting Loan Type: " +value);
        ActOn.element(driver,LoanTypeDropDown).selectValue(value);
        return this;
    }

    public Home selectBuyOrRefinance(String value){
        LOGGER.debug("Selecting option : " +value);
        ActOn.element(driver,BuyOrRefDropDown).selectValue(value);
        return this;
    }

    public Home clickOnCalculateButton(){
        LOGGER.debug("Typing Home Value: ");
        ActOn.element(driver,CalculateButton).click();
        return this;
    }

    public Home validateTotalMonthlyPayment(String totalMonthlyPayment){
        LOGGER.debug("Validating Total Monthly Payment: " +totalMonthlyPayment);
        String formattedXpath = String.format("//h3[text()='$%s']",totalMonthlyPayment);
        AssertThat.elementAssertions(driver,By.xpath(formattedXpath)).elementIsDisplayed();
        return this;
    }
}
