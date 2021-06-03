Feature: As a user I should able to Register, Login  and List all the resources.

  Background: Set the base URI
    Given the base URI is "https://reqres.in/"

  Scenario: Register user
    Given the api end point is "/api/register"
    And the user credentials are
      | eve.holt@reqres.in | cityslicka |
    When the request is made with post method
    Then the response code should be 200


  Scenario: User Login
    Given the api end point is "/api/login"
    And the user credentials are
      | eve.holt@reqres.in | cityslicka |
    When the request is made with post method
    Then the response code should be 200

  Scenario: List the resources
    Given the api end point is "/api/unknown"
    When the request is made with get method
    Then the response code should be 200
    And response should contain id for first resource