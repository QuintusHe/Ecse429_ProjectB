Feature: Edit Task Description
	This feature verifies the functionality of editing task description
	(As a student, I want to change a task description, to better represent the work to do. )
	
Background:
	Given I am a student
	Given I have a course (project)
 
Scenario: Student edit the description of a task with description
	Given I have a task with description
	When I changed the description of the task
	Then I verify that the task's description is changed
	
Scenario: Student edit the description of a task with no description
	Given I have a task without description
	When I changed the empty description of the task
	Then I verify that the empty task's description is changed

Scenario: Student edit the description of a non-existing task
	Given I have no task
	When I changed the description of the non-exist task
	Then I verify that description editing exceptions is handled correctly