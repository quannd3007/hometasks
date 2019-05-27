@Web
Feature: Login page test cases

  Background:
    Given I navigate to the Login page
    Then I should be on Login Page

  Scenario Outline: Login with incorrect information: <case>
    When I enter my account to login as email is <Email> and password is <Password>
    Then I should see the error message for <error>
    Examples:
      | case                 | Email                  | Password | error         |
      | Email blank          |                        | abc123   | emailblank    |
      | Password blank       | quanndd3cntt@gmail.com |          | passwordblank |
      | Incorrect password   | quanndd3cntt@gmail.com | 123456   | incorrectpass |
      | Invalid email format | 123@@gmail.com         | abc123   | invalidem     |

  Scenario: Login with correct account
    When I login to account with
      | Email                  | Password |
      | quanndd3cntt@gmail.com | abc123   |
    Then I should login successfully