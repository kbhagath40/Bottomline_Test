Feature: Verifying the login functionality
  In order to log in to phptravels
  As a customer
  I want to Enter user credentials

  Scenario Outline: Test Authentication Section with valid and invalid Credentials
    Given I open "<browser>"
    When I navigate "login_url"
    And I enter "username" as "<UserName>"
    And I enter "password" as "<Password>"
    And I click on "login_button"
    When I click on "dropdown_menu"
    And I click on "edit_account_details"
    When I click on "contact/sub_accounts"
    And I type "first_name" as "<FirstName>"
    And I click on "savechanges_button"
    And I refresh the page
    Then Verify the "<ActualResult>" is equal to "<ExpectedResult>"

    Examples: 
      | browser | UserName                | Password      | DropDownOptions      | FirstName | ActualResult | ExpectedResult |
      | Mozilla | rinormaloku@hotmail.com | qapassword111 | Edit Account Details | Master    | first_name   | Master         |
