Feature: Bank login

  Scenario: Successful login with valid credentials
    Given I open the bank login page
    When I enter username "jsmith" and password "Demo123!"
    And I click the login button
    Then I should see the welcome page

  Scenario: Unsuccessful login with invalid credentials
    Given I open the bank login page
    When I enter username "user1" and password "wrong"
    And I click the login button
    Then I should see a login error message
