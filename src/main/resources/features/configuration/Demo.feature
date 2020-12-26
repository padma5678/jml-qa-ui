@Demo
Feature: User List scenarios

  Background:
    Given Consultant login to Chronicle application as "consultant"
    And Consultant landed on Home page of Chronicle application

  Scenario Outline: User with user write permissions can add new ethnicity subgroup
    Given I click on users link
    And I am on the "User List" page
    When I add a user with required fields "<Unique Reference Id>","<Other Ref>", "<First Name>", "<Surname>", "<Email>", "<Gender>", "<Ethnic Subgroup>", "<Force>", "<Rank>", "<User Name>", "<Password>"
    Then I select user row of "Kevin"
    And I can see user information in the user details page
    
    Examples:
    | Unique Reference Id | Other Ref | First Name | Surname  | Email         | Gender | Ethnic Subgroup | Force    | Rank  | User Name | Password   |
    | 228-Test-S3         | TestRef1  | Kevin      | Peterson | test1@jml.com | Male   | White - Irish   | Midshire | Staff | kp        | kevin1234$ |