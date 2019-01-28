package com.ntuc.income.up.pages;

import com.ntuc.income.up.utilities.ReusableLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.stream.IntStream;

import static com.ntuc.income.up.pages.CommonPage.*;

public class RegisterPage {

    private ReusableLibrary rs;
    private WebDriver driver;
    public RegisterPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
        this.rs = new ReusableLibrary(driver);
        this.driver = driver;
    }

    /********************Registration Form**********************/

    @FindBy(how= How.XPATH, using="//div[@id='identityType']")
    public static WebElement drpIdentityType;

    @FindBy(how= How.XPATH, using="//li[@data-test-id='idtype/nric']")
    public static WebElement liIdTypeNric;

    @FindBy(how= How.XPATH, using="//li[@data-test-id='idtype/fin']")
    public static WebElement liIdTypeFin;

    @FindBy(how= How.XPATH, using="//li[@data-test-id='idtype/passport']")
    public static WebElement liIdTypePassport;

    @FindBy(how= How.XPATH, using="//label[contains(text(), 'NRIC/FIN No.')]")
    public static WebElement lblIdTypeNricOrFin;

    @FindBy(how= How.XPATH, using="//label[contains(text(), 'Passport No.')]")
    public static WebElement lblIdTypePassport;

    @FindBy(how= How.XPATH, using="//label[contains(text(), 'Date of Birth')]")
    public static WebElement lblDateOfBirth;

    @FindBy(how= How.XPATH, using="//input[@data-test-id='register/id']")
    public static WebElement txtIdType;

    @FindBy(how= How.XPATH, using="//input[@data-test-id='register/dob']")
    public static WebElement txtDateOfBirth;

    @FindBy(how= How.XPATH, using="//input[@data-test-id='register/termAndCond']")
    public static WebElement chkTermsAndConditions;

    @FindBy(how= How.XPATH, using="//button[@type='submit']")
    public static WebElement btnRegisterSubmit;

    /********************Email Verify Registration Form**********************/

    @FindBy(how= How.XPATH, using="//input[@data-test-id='register/verify/email']")
    public static WebElement txtRegisterEmail;

    @FindBy(how= How.XPATH, using="//input[@data-test-id='register/verify/password']")
    public static WebElement txtRegisterPassword;

    @FindBy(how= How.XPATH, using="//input[@data-test-id='register/verify/passwordConfirm']")
    public static WebElement txtRegisterConfirmPassword;

    @FindBy(how= How.XPATH, using="//input[@data-test-id='register/verify/emailChange']")
    public static WebElement chkRegisterEmailChange;

    /********************Registration Form Error Messages**********************/

    @FindBy(how= How.XPATH, using="//div[contains(text(), 'Please enter a valid NRIC/FIN.')]")
    public static WebElement lblValidNricOrFinError;

    @FindBy(how= How.XPATH, using="//div[contains(text(), 'Please provide your Passport No. for verification.')]")
    public static WebElement lblValidPassportError;

    @FindBy(how= How.XPATH, using="//div[contains(text(), 'Please enter a valid DOB.')]")
    public static WebElement lblValidDateOfBirthError;

    @FindBy(how= How.XPATH, using="//div[contains(text(), 'Please enter a valid password')]")
    public static WebElement lblValidPasswordError;

    @FindBy(how= How.XPATH, using="//div[contains(text(), 'Passwords must match.')]")
    public static WebElement lblPasswordMustMatchError;

    @FindBy(how= How.XPATH, using="//p[contains(text(), 'Password must be a minimum of 8 characters and contain one uppercase and one lowercase letter.')]")
    public static WebElement lblPasswordCriteria;

    @FindBy(how= How.XPATH, using="//div[contains(text(), '* This is a required field.')]")
    public static WebElement lblTermsAndCondRequiredError;

    @FindBy(how= How.XPATH, using="//input[@data-test-id='register/global/error']")
    public static WebElement lblGlobalError;

    /********************Email Verification Success**********************/

    @FindBy(how= How.XPATH, using="//div[contains(text(), 'Verify your email address')]")
    public static WebElement lblVerifyEmailAdressPopUp;

    @FindBy(how= How.XPATH, using="//input[@data-test-id='emailModal/button']")
    public static WebElement btnVerifyEmailPopupOk;

    /****************************************/
    /*             Functions                */
    /****************************************/

    public void selectIdentityType(String idType){
        rs.pageSync();
        rs.click(drpIdentityType);
        rs.webpageState();
        rs.WaitForElementToLoad(driver, liIdTypeNric);
        switch (idType){
            case "NRIC":
                rs.click(liIdTypeNric);
            break;
            case "FIN": rs.click(liIdTypeFin);
            break;
            case "PASSPORT": rs.click(liIdTypePassport);
        }
    }

    public void enterIdType(String idTypeText){
        rs.sendKeys(txtIdType, idTypeText);
    }

    public void enterDateOfBirth(String dob){
        rs.sendKeys(txtDateOfBirth, dob);
    }

    public void clickTermsAndConds(){
        rs.click(chkTermsAndConditions);
    }

    public void submitNext(){
        rs.click(btnRegisterSubmit);
    }

    public void fillRegisterForm(String idType, String idTypeText, String dob){
        selectIdentityType(idType);
        enterIdType(idTypeText);
        enterDateOfBirth(dob);
        clickTermsAndConds();
        submitNext();
    }

    public void fillEmailVerifyForm(String password, String confirmPassword){
        clearElement(txtRegisterPassword);
        rs.sendKeys(txtRegisterPassword, password);
        clearElement(txtRegisterConfirmPassword);
        rs.sendKeys(txtRegisterConfirmPassword, confirmPassword);
    }

    public void fillEmailVerifyForm(String email, String password, String confirmPassword){
        rs.click(chkRegisterEmailChange);
        rs.sendKeys(txtRegisterEmail, email);
        rs.sendKeys(txtRegisterPassword, password);
        rs.sendKeys(txtRegisterConfirmPassword, confirmPassword);
    }

    public boolean verifyEmailPopUpDisplayed(){
        rs.WaitForElementToLoad(driver, lblVerifyEmailAdressPopUp);
        return rs.isElemVisible(lblVerifyEmailAdressPopUp);
    }

    public void clearElement(WebElement wb){
        rs.clear(wb);
    }








}
