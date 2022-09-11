package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory_Grid {

    //Do not allow to initialize this class from outside
    private  DriverFactory_Grid(){
        //Do- nothing----Empty Constructor
    }

    private static final DriverFactory_Grid instance = new DriverFactory_Grid();

    public static DriverFactory_Grid getInstance(){
        return instance;
    }
    // Thread local driver object for WebDriver
    ThreadLocal<WebDriver> driver = ThreadLocal.withInitial(
            () -> { //Using Lambda Expression
                String environment = System.getProperty("environment") == null ? "local" : System.getProperty("environment");
                String browser = System.getProperty("browser") == null ? "chrome" : System.getProperty("browser");
                URL gridUrl = null;

                try {
                    gridUrl = new URL(ReadConfigFiles.getPropertyValues("GridURL"));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                if (environment.equals("remote") && browser.equals("chrome")) {
                    ChromeOptions chromeOptions = new ChromeOptions();
                    return new RemoteWebDriver(gridUrl, chromeOptions);
                } else if (environment.equals("remote") && browser.equals("firefox")) {
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    return new RemoteWebDriver(gridUrl, firefoxOptions);
                } else if (environment.equals("remote") && browser.equals("edge")) {
                    EdgeOptions edgeOptions = new EdgeOptions();
                    return new RemoteWebDriver(gridUrl, edgeOptions);
                } else {
                    WebDriverManager.chromedriver().setup();
                    ;
                    // Can be replaced with other browser drivers
                    return new ChromeDriver();
                }
            } );

    // Call this method to get the driver object and launch the browser
    public  WebDriver getDriver(){
        return driver.get();
    }

    // Quits the driver and closes the browser
    public void removeDriver(){
        driver.get().quit();
        driver.remove();
    }
}
