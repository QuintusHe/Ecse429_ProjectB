Feature: Todo Creation
	This feature verifies the functionality of todo creation
	(As a student, I create a to do list for a new class I am taking, so I can manage course work.)

	Background:
		Given I have connected to the TodoManager Server
		And I have a course (project)

	Scenario: Student create a new todo with a specific title
		When I create a todo list with a title
		Then I verify that a new todo list is created with the title

	Scenario: Student create a new todo with a specific title and its description
		When I create a todo list with a title and description
		Then I verify that a new todo list is created with the title and the description

	Scenario: Student create a todo with empty title
		Given I have a todo list associated with the project
		When I create the a todo list with empty title
		Then I shall be notified that the creation of to-do is failed