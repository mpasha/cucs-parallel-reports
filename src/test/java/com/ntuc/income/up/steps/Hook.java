package com.ntuc.income.up.steps;

import com.ntuc.income.up.base.BaseUtil;
import com.ntuc.income.up.utilities.ReusableLibrary;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class Hook extends BaseUtil {

    private BaseUtil base;
    public Hook(BaseUtil base) {
        this.base = base;
    }


    /**
     * @description: To be executed before every scenario block
     * @author: Arunava
     */
    @Before
    public void setup() {
        System.out.println("Start Browser");
        String appUrl = "http://localhost:3000/register/";
        String env = "LOCAL";
        String remoteUrl = "";
        String browser = "Chrome";//System.getProperty("Browser");

        if (env.equalsIgnoreCase("LOCAL"))
            setUpLocal(browser, appUrl);
        else
            setUpRemote(browser, remoteUrl, appUrl);
    }

    public void setUpLocal(String browser, String url) {
        if ("IE".equalsIgnoreCase(browser)) {
            InternetExplorerDriverManager.getInstance().setup();
            InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
            internetExplorerOptions.ignoreZoomSettings();
            internetExplorerOptions.introduceFlakinessByIgnoringSecurityDomains();
            internetExplorerOptions.requireWindowFocus();
            base.Driver = new InternetExplorerDriver(internetExplorerOptions);
        } else if ("FIREFOX".equalsIgnoreCase(browser)) {
            FirefoxDriverManager.getInstance().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            if ("Y".equalsIgnoreCase(System.getenv("HEADLESS"))) {
                firefoxOptions.addArguments("--headless");
            }
            base.Driver = new FirefoxDriver(firefoxOptions);
        } else if ("CHROME".equalsIgnoreCase(browser)) {
            ChromeDriverManager.getInstance().setup();
            ChromeOptions options = new ChromeOptions();
            if ("Y".equalsIgnoreCase(System.getenv("HEADLESS"))) {
                options.addArguments("--headless");
                options.addArguments("--disable-gpu");
            }
            base.Driver = new ChromeDriver(options);
        }
        initialiseDriver(url);
    }

    public void setUpRemote(String browser, String remoteUrl, String appUrl) {

        switch (browser) {
            case "IE":
                InternetExplorerDriverManager.getInstance().setup();
//                driver = new InternetExplorerDriver();
                break;
            case "FIREFOX":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if ("Y".equalsIgnoreCase(System.getenv("HEADLESS"))) {
                    firefoxOptions.addArguments("--headless");
                    firefoxOptions.addArguments("--disable-gpu");
                }
                DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                desiredCapabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
//                    driver = new RemoteWebDriver(new URL(URL), desiredCapabilities);
                break;
            case "CHROME":
            default:
                ChromeOptions options = new ChromeOptions();
                if ("Y".equalsIgnoreCase(System.getenv("HEADLESS"))) {
                    options.addArguments("--headless");
                    options.addArguments("--disable-gpu");
                }
//                    desiredCapabilities = DesiredCapabilities.chrome();
//                    desiredCapabilities.setVersion(System.getenv("BROWSER_VERSION"));
//                    driver = new RemoteWebDriver(new URL(URL), options);
                break;
        }
//        initialiseDriver(appUrl);
    }

    public void initialiseDriver(String url) {
        base.Driver.get(url);
        base.Driver.manage().window().maximize();
        base.Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        ReusableLibrary rs = new ReusableLibrary(base.Driver);
        rs.pageSync();
        rs.validateHttpPageError(url);
    }

    /**
     * @description: To be executed after every scenario block
     * @author: Arunava
     */
    @After(order = 0)
    public void tearDown() {
        base.Driver.quit();
    }

    @After(order = 1)
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            ReusableLibrary rs = new ReusableLibrary(base.Driver);
            rs.captureScreenshot(scenario);
        }
    }
}
