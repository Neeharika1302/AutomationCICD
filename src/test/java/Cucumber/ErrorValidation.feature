@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Login Error Validation
    Given I landed on Ecommerce page
    And Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed
    Examples:
      | name                    | password    |
      |pneeharika2000@gmail.com | Honey@      |

