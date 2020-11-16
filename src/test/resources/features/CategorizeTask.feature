Feature: Task Categorization
	As a student, I want to adjust the priority of a task, to help better manage my time.
	(As a student, I categorize tasks as HIGH, MEDIUM or LOW priority, so I can better manage my time.)

	Background:
		Given I have connected to the TodoManager Server
		And I have a course (project)

	Scenario: Student adjusts a task as LOW priority From DEFAULT priority
		Given I have a task with a priority of DEFAULT
		When I adjust the task as LOW priority
		Then I verify that the task's priority is LOW

	Scenario: Student adjusts a task as HIGH priority From LOW priority
		Given I have a task with a priority of LOW
		When I adjust the task as HIGH priority
		Then I verify that the task's priority is HIGH

	Scenario: Student adjusts a non-existing task as HIGH priority
		Given I have a task with a priority of DEFAULT
		And I delete the task
		When I adjust the task as HIGH priority
		Then I shall be notified that the task doesn't exist