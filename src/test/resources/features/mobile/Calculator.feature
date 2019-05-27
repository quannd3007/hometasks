@Mobile
Feature: Verify Calculator functionalities

  Background:
    Given I click OK button to dismiss

  Scenario Outline: Adding function
    When <Number 1> plus <Number 2>
    Then The result should be <Result>
    Examples:
      | Number 1   | Number 2 | Result |
      | 1          | 2        | 3      |
