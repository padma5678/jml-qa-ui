#/**
# * Copyright (c) 2020 JML Software Solutions Ltd
# */
 
#/**
# *
# * @author Padmaja.Sagi
# *
# */

@test
Feature: Gender scenarios

  Background:
    Given User login to Chronicle application as "admin"
    And User landed on Home page of Chronicle application

  Scenario Outline: User with gender write permissions can add new gender record
    Given I click on genders link
    And I am on the "Gender List" page
    When I add a gender with required data "<Gender Name>"
    And I select new gender row of "<Gender Name>"
    Then I can see gender information in the gender details page

    Examples:
      | Gender Name |
      | RandomStr   |

  Scenario: User can switch columns on gender list page
    Given I click on genders link
    And I am on the "Gender List" page
    When I switch "Gender Name" column on gender list page
    Then I cannot see "Gender Name" column on gender list page
    When I switch "Gender Name" column on gender list page
    Then I can see "Gender Name" column on gender list page

  Scenario: User with gender write permissions can modify existing gender
    Given I click on genders link
    And I am on the "Gender List" page
    And I select gender row of "Male"
    When I change gender name to "Male-Test" and save
    Then I should see gender name changed to "Male-Test" in gender details page
    And I change gender name to "Male" and save

  Scenario: User with gender write permissions can archive a gender record
    Given I click on genders link
    And I am on the "Gender List" page
    And I select gender row of "Other"
    When I click on archive gender
    Then I should not see gender "Other" in gender list page
    When I click on unarchive gender
    Then I should see gender "Other" in gender list page
