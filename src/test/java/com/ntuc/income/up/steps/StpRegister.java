package com.ntuc.income.up.steps;

import com.ntuc.income.up.base.BaseUtil;
import com.ntuc.income.up.model.RegisterOuterClass;
import com.ntuc.income.up.pages.RegisterPage;
import com.ntuc.income.up.utilities.EnvironmentData;
import com.ntuc.income.up.utilities.ProtoMapper;
import com.ntuc.income.up.utilities.Validate;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.bitbucket.iamkenos.cissnei.rest.Client;

import java.util.List;
import java.util.Map;

public class StpRegister {
    private BaseUtil base;
    private RegisterPage registerPage;
    public RegisterOuterClass.Register register;
    private Validate validate;

    public StpRegister(BaseUtil base) {
        this.base = base;
        registerPage = new RegisterPage(this.base.Driver);
        this.validate = new Validate();
    }

    @Given("^user launches the unified portal with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_launches_the_unified_portal_with_and(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        List<Map<String, String>> excelMap = new EnvironmentData(arg1).getRowsData(arg2);
        register = (RegisterOuterClass.Register) ProtoMapper.transposeAndCast(excelMap, RegisterOuterClass.Register.getDefaultInstance()).get(0);
    }

    @When("^user fills up the registration form and clicks on next$")
    public void user_fills_up_the_registration_form_and_clicks_on_next() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        registerPage.fillRegisterForm(register.getIdentityTypeList(), register.getIdentityType(), register.getDateOfBirth());
    }

    @When("^user enters the otp successfully$")
    public void user_enters_the_otp_successfully() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        registerPage.enterOtp();
        registerPage.clickLogin();

    }

    @When("^user fills up and submits the register verification form$")
    public void user_fills_up_and_submits_the_register_verification_form() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        registerPage.fillEmailVerifyForm(register.getPassword(), register.getConfirmPassword());
        registerPage.registerNext();
    }

    @Then("^email verification link is sent to the user$")
    public void email_verification_link_is_sent_to_the_user() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        validate.equals(registerPage.verifyEmailPopUpDisplayed(), Boolean.TRUE);
    }























    /*@Given("^user launches the facebook$")
    public void user_launches_the_facebook() throws Throwable {
//        new EnvironmentData("Register");
        registerPage.waitForFacebookPage(this.base.Driver);
    }

    @Given("^user launches the facebook \"(.*?)\",\"(.*?)\"$")
    public void user_launches_the_facebook(String sheetName, String scenarioName) throws Throwable {
        List<Map<String, String>> excelMap = new EnvironmentData(sheetName).getRowsData(scenarioName);
        register = (RegisterOuterClass.Register) ProtoMapper.transposeAndCast(excelMap, RegisterOuterClass.Register.getDefaultInstance()).get(0);
    }

    @When("^user enters signup information \"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\"$")
    public void user_enters_signup_information(String firstName, String lastName, String emailOrMobile, String signUpPassword) throws Throwable {
        registerPage.waitForFacebookPage(this.base.Driver);
        registerPage.enterSignUpInfo(firstName, lastName, emailOrMobile, signUpPassword);
    }

    @When("^user clears sign up information$")
    public void user_clears_sign_up_information() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        registerPage.clearSignUpInfo();
    }
//    @Then("^sign up info is cleared$")
//    public void sign_up_info_is_cleared() throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }

    @When("^user enters signup information$")
    public void user_enters_signup_information() throws Throwable {
//        registerPage.waitForFacebookPage(driver);
//        RegisterPage.enterSignUpInfo(hashMap.get("FirstName"), hashMap.get("LastName"),hashMap.get("EmailOrMobile"),hashMap.get("SignUpPassword"));

        registerPage.enterSignUpInfo(register.getFirstName(), register.getLastName(), register.getEmailOrMobile(), register.getSignUpPassword());

    }*/

}
