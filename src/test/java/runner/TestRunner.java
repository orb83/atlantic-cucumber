package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = "src/test/resources/Features",
        glue = {"step_definitions"},
       // plugin = {"pretty", "summary","html:target/CucumberReports/reports.html"}
        plugin = {"pretty","summary","json:target/cucumber-reports/cucumber-html-reports/report.json",
                "me.jvt.cucumber.report.PrettyReports:target/cucumber-reports"},
        snippets=CucumberOptions.SnippetType.CAMELCASE
        //publish = true
       // tags = "@positive_test"
       // tags = "@LoginTest"
        //tags = "@positive_test or @negative_test"
       // tags = "@positive_test and @negative_test"    (result =0)
        //tags = "@LoginTest and @positive_test"
)
public class TestRunner {

}
