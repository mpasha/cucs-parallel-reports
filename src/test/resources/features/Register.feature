Feature: Register for an UP account (PH)

  @PU-2
  Scenario Outline: Register a PH user
    Given user launches the unified portal with "<Feature>" and "<Scenario>"
    When user fills up the registration form and clicks on next
    And user enters the otp successfully
    And user fills up and submits the register verification form
    Then email verification link is sent to the user

    Examples:
      | Feature  | Scenario           |
      | Register | RegisterPHUser_TC1 |

#
#  @PU-2
#  Scenario Outline: Register a PH user
#    Given user launches the unified portal with "<Feature>" and "<Scenario>"
#    When user fills up the registration form and clicks on next
#    And user enters the otp successfully
#    And user fills up and submits the register verification form
#    Then email verification link is sent to the user
#
#    Examples:
#      | Feature  | Scenario           |
#      | Register | RegisterPHUser_TC1 |
#
#  @PU-2
#  Scenario Outline: Register a PH user
#    Given user launches the unified portal with "<Feature>" and "<Scenario>"
#    When user fills up the registration form and clicks on next
#    And user enters the otp successfully
#    And user fills up and submits the register verification form
#    Then email verification link is sent to the user
#
#    Examples:
#      | Feature  | Scenario           |
#      | Register | RegisterPHUser_TC1 |