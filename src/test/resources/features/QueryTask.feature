Feature: Task Query
	This feature verifies the functionality of task query
	(As a student, I query the incomplete tasks for a class I am taking, to help manage my time.)
	
Background:
	Given I have connected to the TodoManager Server
	And I have a course (project)
 
Scenario: Student query incomplete tasks (todo) for a class (project)
	Given I have an incomplete todo list associated with the project
	When I query for incomplete todo list tasks
	Then I verify that the right todo list is returned

Scenario: Student query non-existing tasks (todo) for a class (project)
	Given I have no todo list associated with the project
	When I query for incomplete todo list tasks
	Then I verify that exceptions is handled correctly

Scenario: Student query an complete task (todo) for a class (project)
	Given I have no incomplete todo list associated with the project
	When I query for incomplete todo list tasks
	Then I verify that exceptions is handled correctly