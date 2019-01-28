Feature: Login to UP

  @PU-3
  Scenario Outline: Login to UP through a registered email
    Given user launches the unified portal with login data "<Feature>" and "<Scenario>"
    When user fills up the login form and clicks on next
    And user enters the otp successfully
    #TODO::verify next page
    Then user is logged into the unified portal

    Examples:
      | Feature | Scenario      |
      | Login   | LoginToUP_TC1 |

  @PU-3
  Scenario Outline: Verify Re-send OTP is successful
    Given user launches the unified portal with "<Feature>" and "<Scenario>"
    When user fills up the login form and clicks on next
    And user enters invalid otp
    #TODO::verify invalid otp error message
#    Then invalid otp error message is displayed
    When user clicked on Resend Otp link
    And user enters the otp successfully
    #TODO::verify next page
    Then user is logged into the unified portal

    Examples:
      | Feature | Scenario      |
      | Login   | LoginToUP_TC1 |

  @PU-3
  Scenario Outline: User doesn't login with invalid credentials like <Scenario>
    Given user launches the unified portal with "<Feature>" and "<Scenario>"
    When user fills up the login form and clicks on next
    Then error message is displayed for invalid credentials

    Examples:
      | Feature | Scenario            |
      | Login   | EmailNotRegistered  |
      | Login   | PasswordIsIncorrect |
      | Login   | AccountNotActivated |

  @PU-3
  Scenario Outline: User has invalid mobile number in entity
    Given user launches the unified portal with "<Feature>" and "<Scenario>"
    When user fills up the login form and clicks on next
    And user clicks on Not This Number link
    Then user able to see the contact center number and email id

    Examples:
      | Feature | Scenario      |
      | Login   | LoginToUP_TC1 |

  @PU-2
  Scenario Outline: User account is locked after 5 Re-Send OTP attempts
    Given user launches the unified portal with "<Feature>" and "<Scenario>"
    When user fills up the login form and clicks on next
    And user clicked on Resend otp for 6 times
    #TODO::verify error message
    Then user account is locked for 30 minutes message is displayed

    Examples:
      | Feature | Scenario      |
      | Login   | AccountIsLockedFor30Min |
