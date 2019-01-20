package com.ntuc.income.up.pages;

import com.ntuc.income.up.utilities.ReusableLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

    ReusableLibrary rs;
    WebDriver driver;
    public RegisterPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
        this.rs = new ReusableLibrary(driver);
        this.driver = driver;
    }
    //NTUC Register
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


    //Otp POP-UP
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


    @FindBy(how= How.XPATH, using="//div[contains(text(), 'Activate With your OTP')]")
    public static WebElement lblOtpPopUp;

    //Register with Email Address Page

    @FindBy(how= How.XPATH, using="//input[@data-test-id='register/verify/email']")
    public static WebElement txtRegisterEmail;

    @FindBy(how= How.XPATH, using="//input[@data-test-id='register/verify/password']")
    public static WebElement txtRegisterPassword;

    @FindBy(how= How.XPATH, using="//input[@data-test-id='register/verify/passwordConfirm']")
    public static WebElement txtRegisterConfirmPassword;

    @FindBy(how= How.XPATH, using="//input[@data-test-id='register/verify/emailChange']")
    public static WebElement chkRegisterEmailChange;

    //Error Messages
    @FindBy(how= How.XPATH, using="//div[contains(text(), 'Please enter a valid NRIC/FIN.')]")
    public static WebElement lblValidNricOrFinError;

    @FindBy(how= How.XPATH, using="//div[contains(text(), 'Please enter a valid passport.')]")
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
    public static WebElement lblTermsAndCondRequired;

    //Success Flow
    @FindBy(how= How.XPATH, using="//div[contains(text(), 'Verify your email address')]")
    public static WebElement lblVerifyEmailAdressPopUp;

    @FindBy(how= How.XPATH, using="//input[@data-test-id='emailModal/button']")
    public static WebElement btnVerifyEmailPopupOk;

    //****************************************//
    //***************Functions****************//
    //****************************************//

    public void selectIdentityType(String idType){
        rs.pageSync();
        rs.click(drpIdentityType);
        rs.webpageState();
        switch (idType){
            case "NRIC":
                rs.WaitForElementToLoad(driver, liIdTypeNric);
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

    public void registerNext(){
        rs.click(btnRegisterSubmit);
    }

    public void fillRegisterForm(String idType, String idTypeText, String dob){
        selectIdentityType(idType);
        enterIdType(idTypeText);
        enterDateOfBirth(dob);
        clickTermsAndConds();
        registerNext();
    }

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

    public void fillEmailVerifyForm(String password, String confirmPassword){
        rs.sendKeys(txtRegisterPassword, password);
        rs.sendKeys(txtRegisterConfirmPassword, confirmPassword);
    }

    public void fillEmailVerifyForm(String email, String password, String confirmPassword){
        rs.click(chkRegisterEmailChange);
        rs.sendKeys(txtRegisterEmail, email);
        rs.sendKeys(txtRegisterPassword, password);
        rs.sendKeys(txtRegisterConfirmPassword, confirmPassword);
    }

    public boolean verifyEmailPopUpDisplayed(){
//        rs.webpageState();
        rs.WaitForElementToLoad(driver, lblVerifyEmailAdressPopUp);
        return rs.isElemVisible(lblVerifyEmailAdressPopUp);
    }














    //*******************Sample Facebook App ************************/
    @FindBy(how= How.XPATH, using="//input[@id='email']")
    public static WebElement txtUserName;

    @FindBy(how= How.XPATH, using="//input[@id='pass']")
    public static WebElement txtPassword;

    @FindBy(how= How.XPATH, using="//input[@value='Log In']")
    public static WebElement btnSubmit;

    @FindBy(how= How.XPATH, using="//input[@name='firstname']")
    public static WebElement txtFirstName;

    @FindBy(how= How.XPATH, using="//input[@name='lastname']")
    public static WebElement txtLastName;

    @FindBy(how= How.XPATH, using="//input[@name='reg_email__']")
    public static WebElement txtMobileOrEmail;

    @FindBy(how= How.XPATH, using="//input[@name='reg_passwd__']")
    public static WebElement txtSignupPassword;

    public void enterSignUpInfo(String firstName,String lastName,String emailOrMobile,String signUpPassword){
        rs.sendKeys(txtFirstName,firstName);
        rs.sendKeys(txtLastName,lastName);
        rs.sendKeys(txtMobileOrEmail,emailOrMobile);
        rs.sendKeys(txtSignupPassword,signUpPassword);
    }

    public void clearSignUpInfo(){
        txtFirstName.clear();
        txtLastName.clear();
        txtMobileOrEmail.clear();
        txtSignupPassword.clear();
    }

    public void waitForFacebookPage(WebDriver driver){
        rs.WaitForElementToLoad(driver,"//input[@name='firstname']");
    }
}
