package com.ntuc.income.up.steps;

import com.ntuc.income.up.base.BaseUtil;
import com.ntuc.income.up.model.RegisterOuterClass;
import com.ntuc.income.up.model.RegisterOuterClass.Register;
import com.ntuc.income.up.pages.CommonPage;
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

import static com.ntuc.income.up.pages.CommonPage.lnkContactEmailPopup;
import static com.ntuc.income.up.pages.CommonPage.lnkMobileNumberPopup;
import static com.ntuc.income.up.pages.RegisterPage.*;
import static com.ntuc.income.up.steps.StpCommon.load_test_data;

public class StpRegister {
    private BaseUtil base;
    private RegisterPage registerPage;
    private CommonPage commonPage;
    public Register register;
    private Validate validate;

    public StpRegister(BaseUtil base) {
        this.base = base;
        this.registerPage = new RegisterPage(this.base.Driver);
        this.commonPage = new CommonPage(this.base.Driver);
        this.validate = new Validate();

    }

    @Given("^user is launched unified portal registration form$")
    public void user_is_launched_unified_portal_registration_form() {

    }

    @Given("^user launches the unified portal with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_launches_the_unified_portal_with_and(String sheetName, String scenarioName) throws Throwable {
        register = (Register) load_test_data(sheetName, scenarioName, Register.getDefaultInstance()).get(0);
    }

    @When("^user fills up the registration form and clicks on next$")
    public void user_fills_up_the_registration_form_and_clicks_on_next() throws Throwable {
        registerPage.fillRegisterForm(register.getIdentityTypeList(), register.getIdentityType(), register.getDateOfBirth());
    }

    @When("^user enters the otp successfully$")
    public void user_enters_the_otp_successfully() throws Throwable {
        commonPage.enterOtp();
        commonPage.clickLogin();

    }

    @When("^user enters invalid otp$")
    public void user_enters_invalid_otp() throws Throwable {
        commonPage.enterOtp();
        commonPage.clickLogin();
    }

    @Then("^invalid otp error message is displayed$")
    public void invalid_otp_error_message_is_displayed() {

    }

    @When("^user clicked on Resend Otp link$")
    public void user_clicked_on_Resend_Otp_link() throws Throwable {
        commonPage.clickResendOtpPin();
    }

    @Then("^user is successfully navigated to the email verification form$")
    public void user_is_successfully_navigated_to_the_email_verification_form() throws Throwable {
        validate.equals(txtRegisterPassword.isDisplayed(), Boolean.TRUE);
    }


    @When("^user fills up and submits the register email verification form$")
    public void user_fills_up_and_submits_the_register_email_verification_form() throws Throwable {
        registerPage.fillEmailVerifyForm(register.getPassword(), register.getConfirmPassword());
        registerPage.submitNext();
    }

    @Then("^email verification link is sent to the user$")
    public void email_verification_link_is_sent_to_the_user() throws Throwable {
        validate.equals(registerPage.verifyEmailPopUpDisplayed(), Boolean.TRUE);
    }

    @Then("^Error message is shown to the user$")
    public void NRIC_is_already_registered_error_message_is_displayed() throws Throwable {
        validate.equals(commonPage.verifyErrorMessage(lblGlobalError), Boolean.TRUE);
    }

    @When("^user enters invalid \"([^\"]*)\" with \"([^\"]*)\"$")
    public void user_enters_invalid_NRIC_or_FIN(String IdType, String Id) {
        registerPage.selectIdentityType(IdType);
        registerPage.enterIdType(Id);
        registerPage.submitNext();
    }

    @Then("^error message is displayed for invalid nric or fin$")
    public void error_message_is_displayed_for_invalid_nric_or_fin() {
        validate.equals(commonPage.verifyErrorMessage(lblValidNricOrFinError), Boolean.TRUE);
    }

    @When("^user enters invalid dob as \"([^\"]*)\"$")
    public void user_enters_invalid_DOB(String dob) {
        registerPage.enterDateOfBirth(dob);
        registerPage.submitNext();
    }

    @Then("^error message is displayed for invalid dob$")
    public void error_message_is_displayed_for_invalid_dob() {
        validate.equals(commonPage.verifyErrorMessage(lblValidDateOfBirthError), Boolean.TRUE);
    }

    @When("^user is clicked on Next$")
    public void user_is_clicked_on_Next() {
        registerPage.submitNext();
    }

    @Then("^error message is displayed to agree terms and conditions$")
    public void error_message_is_displayed_to_agree_terms_and_conditions() {
        validate.equals(commonPage.verifyErrorMessage(lblTermsAndCondRequiredError), Boolean.TRUE);
    }

    @When("^user selects \"([^\"]*)\" but does not enter the passport$")
    public void user_selects_passport_but_does_not_enter_the_passport(String idType) {
        registerPage.clearElement(txtIdType);
        registerPage.selectIdentityType(idType);
        registerPage.submitNext();
    }

    @Then("^error message is displayed to provide passport number for verification$")
    public void error_message_is_displayed_to_enter_valid_passport() {
        validate.equals(commonPage.verifyErrorMessage(lblValidPassportError), Boolean.TRUE);
    }

    @When("^user clicks on Not This Number link$")
    public void user_clicks_on_wrong_mobile_number_link() {
        commonPage.clickNotThisNumber();
    }

    @Then("^user able to see the contact center number and email id$")
    public void user_able_to_see_the_contact_center_number_and_email_id() {
        validate.equals(commonPage.verifyErrorMessage(lnkMobileNumberPopup), Boolean.TRUE);
        validate.equals(commonPage.verifyErrorMessage(lnkContactEmailPopup), Boolean.TRUE);
    }

    @When("^user enters the \"([^\"]*)\" password and \"([^\"]*)\" confirm password$")
    public void user_enters_the_password(String password, String confirmPassword) {
        registerPage.fillEmailVerifyForm(password, confirmPassword);
        registerPage.submitNext();
    }

    @Then("^please enter a valid password error message is shown$")
    public void please_enter_a_valid_password_error_message_is_shown() {
        validate.equals(commonPage.verifyErrorMessage(lblValidPasswordError), Boolean.TRUE);
    }

    @Then("^passwords must match error message is shown$")
    public void passwords_must_match_error_message_is_shown() {
        validate.equals(commonPage.verifyErrorMessage(lblPasswordMustMatchError), Boolean.TRUE);
    }

    @When("^user clicked on Resend otp for 6 times$")
    public void user_clicked_on_Resend_otp_for_6_times() {
        commonPage.resendOtpSixTimes();
    }

    @Then("^user account is locked for 30 minutes message is displayed$")
    public void user_account_is_locked_for_30_minutes_message_is_displayed() {
//        validate.equals(registerPage.verifyErrorMessage(), Boolean.TRUE);
    }
}
