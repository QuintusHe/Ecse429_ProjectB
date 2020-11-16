Feature: Task Prioriy
	This feature verifies the functionality of task priority 
	(As a student, I want to adjust the priority of a task, to help better manage my time.)

Background:
	Given I am a student
	Given I have a course (project)
 
Scenario: Student changes the tasks (todo) priority from HIGH to LOW
	Given I have a HIGH priority task associated with the project
	When I change the task priority to LOW
	Then I verify that the task priority is LOW

Scenario: Student changes the tasks (todo) priority from MEDIUM to HIGH
	Given I have a MEDIUM priority task associated with the project
	When I change the task priority to HIGH
	Then I verify that the task priority is HIGH

Scenario: Student changes the priority for a non-esxiting task (todo)
	Given I have no task associated with the project
	When I change the task priority to MEDIUM
	Then I shall be notified that the task doesn't exist