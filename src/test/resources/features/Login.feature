Feature: Login

#  @login
#  Scenario Outline: Register a user 2
#    Given user launches the facebook
#    When user enters signup information "<FirstName>","<LastName>","<EmailOrMobile>","<SignUpPassword>"
#    And user clears sign up information
##    Then sign up info is cleared
#
#    Examples:
#      | FirstName | LastName | EmailOrMobile       | SignUpPassword |
#      | Test    | Shaik    | mehraj999@gmail.com | 92379889       |


#  @facebook_proto2
#  Scenario Outline: Register a user
#    Given user launches the facebook "<Feature>","<Scenario>"
#    When user enters signup information
#    And user clears sign up information
##    Then sign up info is cleared
#
#    Examples:
#      | Feature  | Scenario       |
#      | Register | SignUpUser_TC1 |
#      | Register | SignUpUser_TC2 |


  @PU-3
  Scenario Outline: Register a PH user
    Given user launches the unified portal with "<Feature>" and "<Scenario>"
    When user fills up the registration form and clicks on next
    And user enters the otp successfully
    And user fills up and submits the register verification form
    Then email verification link is sent to the user

    Examples:
      | Feature  | Scenario           |
      | Register | RegisterPHUser_TC1 |
