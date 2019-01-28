package com.ntuc.income.up.steps;

import com.ntuc.income.up.base.BaseUtil;
import com.ntuc.income.up.model.LoginOuterClass.Login;
import com.ntuc.income.up.pages.CommonPage;
import com.ntuc.income.up.pages.LoginPage;
import com.ntuc.income.up.utilities.Validate;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static com.ntuc.income.up.pages.LoginPage.lblGlobalError;
import static com.ntuc.income.up.steps.StpCommon.load_test_data;

public class StpLogin {
    private BaseUtil base;
    private LoginPage loginPage;
    private CommonPage commonPage;
    public Login login;
    private Validate validate;

    public StpLogin(BaseUtil base) {
        this.base = base;
        this.loginPage = new LoginPage(this.base.Driver);
        this.commonPage = new CommonPage(this.base.Driver);
        this.validate = new Validate();
    }

    @Given("^user is launched unified portal login form$")
    public void user_is_launched_unified_portal_login_form(){

    }

    @Given("^user launches the unified portal with login data \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_launches_the_unified_portal_with_and(String sheetName, String scenarioName) throws Throwable {
        login = (Login) load_test_data(sheetName, scenarioName, Login.getDefaultInstance()).get(0);
    }

    @Given("^user fills up the login form and clicks on next$")
    public void user_fills_up_the_login_form_and_clicks_on_next(){
        loginPage.fillUpLoginForm(login.getEmail(), login.getPassword());
    }

    @Then("^user is logged into the unified portal$")
    public void user_is_logged_into_the_unified_portal(){

    }

    @Then("^error message is displayed for invalid credentials$")
    public void error_message_is_displayed_for_invalid_credentials(){
        validate.equals(commonPage.verifyErrorMessage(lblGlobalError), Boolean.TRUE);
    }



}
