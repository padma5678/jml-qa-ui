#/**
# * Copyright (c) 2020 JML Software Solutions Ltd
# */
 
#/**
# *
# * @author Padmaja.Sagi
# *
# */

@UserModule
Feature: User List scenarios

  Background:
    Given Consultant login to Chronicle application as "consultant"
    And Consultant landed on Home page of Chronicle application

  @testcase1
  Scenario Outline: User with user write permissions can add new ethnicity subgroup
    Given I click on users link
    And I am on the "User List" page
    When I add a user with required fields "<Unique Reference Id>","<Other Ref>", "<First Name>", "<Surname>", "<Email>", "<Gender>", "<Ethnic Subgroup>", "<Force>", "<Rank>", "<User Name>", "<Password>"
    Then I select user row of "<First Name>"
    And I can see user information in the user details page

    Examples:
      | Unique Reference Id | Other Ref | First Name | Surname  | Email   | Gender | Ethnic Subgroup | Force    | Rank  | User Name | Password   |
      | 228-Test-S3         | OthRef1   | Kevin      | Peterson | autogen | Male   | White - Irish   | Midshire | Staff | kp        | Kevin1234$ |

  @testcase2
  Scenario: User can switch columns on user list page
    Given I click on users link
    And I am on the "User List" page
    When I switch on "Surname" column on user list page
    Then I cannot see "Surname" column on user list page
    When I switch "Surname" column on user list page
    Then I can see "Surname" column on user list page
