Feature: Delete Todo
	This feature verifies the functionality of todo deletion
	(As a student, I remove a to do list for a class which I am no longer taking, to declutter my schedule.)
	
Background:
	Given I am a student
	Given I have a course (project)
 
Scenario: Student delete a todo list
	Given I have a todo list
	When I delete the todo list
	Then I verify that the todo list is removed

Scenario: Student delete a non-existing todo list
	Given I have no todo list
	When I delete the todo list
	Then I verify that exceptions is handled correctly