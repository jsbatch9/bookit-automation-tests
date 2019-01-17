Feature: User Registriation with API

 @temp
Scenario: permission verification
	Given I logged BookIT api as a student
	When I try to register new user
	Then system should return only teachers can register message