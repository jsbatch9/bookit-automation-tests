Feature: User Registriation with API

 
Scenario: permission verification
	Given I logged BookIT api as a student
	When I try to register new user
	Then system should return only teachers can register message
@temp	
Scenario: post with teacher account
	Given I logged BookIT api as a teacher
	When I try to register new user
	Then I should get has been added to db message
