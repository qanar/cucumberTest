Feature: Handle emails
  As a user
  I want to keep a list of email addresses

  Background:
    Given there are no emails

  @ES-1
  Scenario: Get a list of emails
    Given jefke@test.be is saved
    And jos@test.be is saved
    When the user requests a list of all emails
    Then the response status is 200
    And the response contains 2 emails

  @ES-1
  Scenario: Get a list of no emails
    When the user requests a list of all emails
    Then the response status is 200
    And the response contains 0 emails