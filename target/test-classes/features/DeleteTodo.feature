Feature: Delete Todo
	This feature verifies the functionality of todo deletion
	(As a student, I remove a to do list for a class which I am no longer taking, to declutter my schedule.)
	
Background:
	Given The system is running
	Given I have a course (project)
 
Scenario: Student delete a todo list with tasks in it
	Given I have a todo list with tasks for the course
	When I delete the todo list with tasks
	Then I verify that the todo list with tasks is removed

Scenario: Student delete a todo list with no tasks in it
	Given I have an empty todo list for the course
	When I delete the empty todo list
	Then I verify that the empty todo list is removed

Scenario: Student delete a non-existing todo list
	Given I have no todo list
	When I delete the todo list
	Then I verify that non-exsit todo list exceptions is handled correctly