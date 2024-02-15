
@tag
Feature: Error Validation
  I want to use this template for my feature file

  
  @tag2
  Scenario Outline: Validation of error
    Given I landed on Ecommerce page
    When Logged in with username <name> and password <password>
    Then error "Incorrec email or password." is displayed

    Examples: 
      | name                 | password     |
      | punekar435@gmail.com | Punekar@435  |