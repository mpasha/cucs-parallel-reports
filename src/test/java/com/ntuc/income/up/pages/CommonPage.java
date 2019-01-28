package com.ntuc.income.up.pages;

import com.ntuc.income.up.utilities.ReusableLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.stream.IntStream;

public class CommonPage {

    private ReusableLibrary rs;
    private WebDriver driver;
    public CommonPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
        this.rs = new ReusableLibrary(driver);
        this.driver = driver;
    }

    /********************OTP Model**********************/

    @FindBy(how= How.XPATH, using="//input[@data-test-id='otp/0']")
    public static WebElement txtOtp0;

    @FindBy(how= How.XPATH, using="//input[@data-test-id='otp/1']")
    public static WebElement txtOtp1;

    @FindBy(how= How.XPATH, using="//input[@data-test-id='otp/2']")
    public static WebElement txtOtp2;

    @FindBy(how= How.XPATH, using="//input[@data-test-id='otp/3']")
    public static WebElement txtOtp3;

    @FindBy(how= How.XPATH, using="//input[@data-test-id='otp/4']")
    public static WebElement txtOtp4;

    @FindBy(how= How.XPATH, using="//input[@data-test-id='otp/5']")
    public static WebElement txtOtp5;

    @FindBy(how= How.XPATH, using="//div[@data-test-id='otpModal/resend']")
    public static WebElement lnkResendOtp;

    @FindBy(how= How.XPATH, using="//button[@data-test-id='otpModal/login']")
    public static WebElement btnLogin;

    @FindBy(how= How.XPATH, using="//div[@data-test-id='otpModal/wrongNo']")
    public static WebElement lnkWrongNumber;

    @FindBy(how= How.XPATH, using="//div[contains(text(), '+95 6788 1122')]")
    public static WebElement lnkMobileNumberPopup;

    @FindBy(how= How.XPATH, using="//div[contains(text(), 'csquey@income.com.sg')]")
    public static WebElement lnkContactEmailPopup;

    /********************OTP Model**********************/

    public void enterOtp(){
        rs.sendKeys(txtOtp0, "1");
        rs.sendKeys(txtOtp1, "1");
        rs.sendKeys(txtOtp2, "1");
        rs.sendKeys(txtOtp3, "1");
        rs.sendKeys(txtOtp4, "1");
        rs.sendKeys(txtOtp5, "1");
    }

    public void clickLogin(){
        rs.click(btnLogin);
    }

    public void clickNotThisNumber(){
        rs.click(lnkWrongNumber);
    }

    public void clickResendOtpPin(){
        rs.click(lnkResendOtp);
    }

    public void resendOtpSixTimes(){
        IntStream.range(1,6).forEach(index->{
            enterOtp();
            clickResendOtpPin();
            try {
                Thread.sleep(20001);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
    }

    public boolean verifyErrorMessage(WebElement element){
        rs.WaitForElementToLoad(driver, element);
        return rs.isElemVisible(element);
    }
}
