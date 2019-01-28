Feature: Register for an UP account (PH)

  @PU-2
  Scenario Outline: Register a PH user with NRIC or FIN or Passport
    Given user launches the unified portal with "<Feature>" and "<Scenario>"
    When user fills up the registration form and clicks on next
    And user enters the otp successfully
    And user fills up and submits the register email verification form
    Then email verification link is sent to the user

    Examples:
      | Feature  | Scenario           |
      | Register | RegisterPHUser_TC1 |
      | Register | RegisterPHUser_TC2 |
      | Register | RegisterPHUser_TC3 |

  @PU-2
  Scenario Outline: Verify error message for <Scenario>
    Given user launches the unified portal with "<Feature>" and "<Scenario>"
    When user fills up the registration form and clicks on next
    #TODO::verify error message
    And Error message is shown to the user

    Examples:
      | Feature  | Scenario              |
      | Register | AlreadyRegisteredNRIC |
      | Register | IncorrectDOB          |

  @PU-2 
  Scenario Outline: Field Level Verification on Registration Form
    Given user is launched unified portal registration form
    When user enters invalid "NRIC" with "<NRIC>"
    Then error message is displayed for invalid nric or fin
    When user enters invalid "FIN" with "<FIN>"
    Then error message is displayed for invalid nric or fin
    When user selects "PASSPORT" but does not enter the passport
    Then error message is displayed to provide passport number for verification
    When user enters invalid dob as "<DOB>"
    Then error message is displayed for invalid dob
    When user is clicked on Next
    Then error message is displayed to agree terms and conditions

    Examples:
      | NRIC     | FIN      | DOB    |
      | G1234567 | R8372827 | 280890 |

  @PU-2
  Scenario Outline: User has invalid mobile number in entity
    Given user launches the unified portal with "<Feature>" and "<Scenario>"
    When user fills up the registration form and clicks on next
    And user clicks on Not This Number link
    Then user able to see the contact center number and email id

    Examples:
      | Feature  | Scenario           |
      | Register | RegisterPHUser_TC1 |

  @PU-2
  Scenario Outline: Verify Re-send OTP is successful
    Given user launches the unified portal with "<Feature>" and "<Scenario>"
    When user fills up the registration form and clicks on next
    And user enters invalid otp
    #TODO::verify invalid otp error message
#    Then invalid otp error message is displayed
    When user clicked on Resend Otp link
    And user enters the otp successfully
    #TODO::verify next page
    Then user is successfully navigated to the email verification form

    Examples:
      | Feature  | Scenario           |
      | Register | RegisterPHUser_TC1 |

  @PU-2
  Scenario Outline: User account is locked after 5 Re-Send OTP attempts
    Given user launches the unified portal with "<Feature>" and "<Scenario>"
    When user fills up the registration form and clicks on next
    And user clicked on Resend otp for 6 times
    #TODO::verify error message
    Then user account is locked for 30 minutes message is displayed

    Examples:
      | Feature  | Scenario           |
      | Register | RegisterPHUser_TC1 |

  @PU-2
  Scenario Outline: User enters existing email id
    Given user launches the unified portal with "<Feature>" and "<Scenario>"
    When user fills up the registration form and clicks on next
    And user enters the otp successfully
    And user enters existing email id
    Then Error message is shown to the user

    Examples:
      | Feature  | Scenario           |
      | Register | RegisterPHUser_TC1 |

  @PU-2
  Scenario: Verify invalid passwords and password mismatch
    Given user is launched unified portal registration form
    When user fills up the registration form and clicks on next
    And user enters the otp successfully
    And user enters the "INVALID1" password and "INVALID1" confirm password
    Then please enter a valid password error message is shown
    And user enters the "invalid2" password and "invalid2" confirm password
    Then please enter a valid password error message is shown
    And user enters the "Invalid" password and "Invalid" confirm password
    Then please enter a valid password error message is shown
    And user enters the "Password" password and "Password1" confirm password
    Then passwords must match error message is shown
