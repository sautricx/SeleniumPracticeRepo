#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Purchase the order from Ecommerce Website.
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce web page

  @tag2
  Scenario Outline: Positive test of submitting the order
    Given I login with <username>  and <password> 
    When I add product <productName> to cart
    And Checkout <productName> and submit.
    Then "THANKYOU FOR THE ORDER." message is dispalyed on ConfirmationPage

    Examples: 
      | username             | password            | productName       |
      | sausejneh@gmail.com  | LWpass11$		       | ADIDAS ORIGINAL   |
