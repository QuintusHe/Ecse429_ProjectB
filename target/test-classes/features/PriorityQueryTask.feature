Feature: Priority Task Query
	This feature verifies the functionality of priority task query
	(As a student, I query all incomplete HIGH priority tasks from all my classes, to identity my short-term goals.)

Background:
	Given I have connected to the TodoManager Server
	And I have a course (project)
 
Scenario: Student query all HIGH incomplete tasks (todo) for a class (project)
	Given I have a HIGH incomplete todo list associated with the project
	When I query for HIGH incomplete todo list tasks
	Then I verify that the HIGH priority todo list is returned
	
Scenario: Student query all LOW complete task (todo) for a class (project)
	Given I have a LOW incomplete todo list associated with the project
	When I query for LOW incomplete todo list tasks
	Then I verify that the LOW priority todo list is returned

Scenario: Student query non-existing tasks (todo) for a class (project)
	Given I have no todo list associated with the project
	When I query for non-exist HIGH incomplete todo list tasks
	Then I verify that exceptions is handled correctly
