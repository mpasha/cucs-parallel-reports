package com.ntuc.income.up.pages;

import com.ntuc.income.up.utilities.ReusableLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    ReusableLibrary rs;
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.rs = new ReusableLibrary(driver);
        this.driver = driver;
    }

    /********************Login Form**********************/

    @FindBy(how = How.XPATH, using = "//*[@data-test-id='login/email']")
    public static WebElement txtEmailAddress;

    @FindBy(how = How.XPATH, using = "//*[@data-test-id='login/password']")
    public static WebElement txtPassword;

    @FindBy(how = How.XPATH, using = "//*[@data-test-id='login/submit']")
    public static WebElement btnSubmitLogin;

    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Register Today!')]")
    public static WebElement lnkRegisterToday;

    /********************Login Form Error Messages**********************/

    @FindBy(how = How.XPATH, using = "//*[@data-test-id='login/email/error']")
    public static WebElement lblEmailAddressError;

    @FindBy(how = How.XPATH, using = "//*[@data-test-id='login/password/error']")
    public static WebElement lblPasswordError;

    @FindBy(how= How.XPATH, using="//input[@data-test-id='login/global/error']")
    public static WebElement lblGlobalError;


    /****************************************/
    /*             Functions                */

    /****************************************/

    public void fillUpLoginForm(String email, String password) {
        rs.pageSync();
        rs.sendKeys(txtEmailAddress, email);
        rs.sendKeys(txtPassword, email);
        rs.click(btnSubmitLogin);
    }

    public boolean waitForOtpModel(){
        rs.WaitForElementToLoad(driver, CommonPage.txtOtp0);
        return rs.isElemVisible(CommonPage.txtOtp0);
    }
}
