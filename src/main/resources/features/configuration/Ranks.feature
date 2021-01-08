#/**
# * Copyright (c) 2020 JML Software Solutions Ltd
# */
 
#/**
# *
# * @author Padmaja.Sagi
# *
# */

@test
Feature: Rank scenarios

  Background:
    Given User login to Chronicle application as "admin"
    And User landed on Home page of Chronicle application

#  Scenario Outline: User with rank write permissions can add new rank record
#    Given I click on ranks link
#    And I am on the "Rank List" page
#    When I add a rank with required data "<rankName>" and "<rankCategory>"
#    And I select newly added rank row of "<rankName>"
#    Then I can see below rank information in the rank details page
#
#    Examples:
#      | rankName  | rankCategory |
#      | RandomStr | CIVILIAN     |
#
#  Scenario: User can switch column on rank list page
#    Given I click on ranks link
#    And I am on the "Rank List" page
#    When I switch on "Rank Category" column on rank list page
#    Then I can see "Rank Category" column on rank list page
#    When I switch off "Rank Category" column on rank list page
#    Then I cannot see "Rank Category" column on rank list page
#
#  Scenario: User with rank write permissions can modify existing rank
#    Given I click on ranks link
#    And I am on the "Rank List" page
#    And I select gender row of "Staff"
#    When I change rank to "Staff-Test" and "OFFICER" and save
#    Then I should see rank changed to "Staff-Test" and "OFFICER" in rank details page
#    And I change rank to "Staff" and "CIVILIAN" and save

  Scenario: Navigate through rank Pages
    Given I click on ranks link
    And I am on the "Rank List" page
    And I should see the default page size is "10"
    When I navigate to "Next page"
    Then I am on the next page starting with page count "11"
    And I should see the default page size is "10"
#
#  Scenario: Navigate Backward on rank Pages
#    Given I click on ranks link
#    And I am on the "Rank List" page
#    And I navigate "Forward"
#    And  I am on the next page starting with page count "11"
#    When I navigate "Backward"
#    Then I am on the previous page starting with page count "1"
#    And I should see the default page size is "10"