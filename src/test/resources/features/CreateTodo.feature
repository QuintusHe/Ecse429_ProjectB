Feature: Todo Creation
	This feature verifies the functionality of todo creation
	(As a student, I create a to do list for a new class I am taking, so I can manage course work.)

	Background:
		Given I have connected to the TodoManager Server
		And I have a course (project)

	Scenario: Student create a new todo with a specific title
		When I create a todo list
		Then I verify that a new todo list associated with the project is created with the title

	Scenario: Student create a new todo with a specific title and its description
		When I create a todo list
		Then I verify that a new todo list associated with the project is created with the title and the description

	Scenario: Student create a replicated todo
		Given I have a todo list
		When I create the a todo list with same title
		Then I shall be notified that the to-do has already existed