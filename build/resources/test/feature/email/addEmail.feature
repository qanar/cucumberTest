Feature: Add email
  As a user
  I want to add email address to my list

  Background:
    Given there are no emails

  @ES-2
  Scenario: Insert one email
    Given jefke@test.be is saved
    When the user requests a list of all emails
    Then the response status is 200
    And the response contains 1 email
    And the response contains the text jefke@test.be

  @ES-3
  Scenario Outline: Insert an invalid email
    When <invalid_email> is saved
    Then the response status is 400
    And the response contains 0 emails

  Examples:
    | invalid_email |
    | invalid |
    | jefke@gmail |
    | @gmail.com |
    | test@@test.com |