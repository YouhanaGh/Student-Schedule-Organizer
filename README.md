
=======
# Project-Team-9(Student Schedule Organizer)
![WhatsApp Image 2025-03-27 at 12 43 04 AM](https://github.com/user-attachments/assets/8b298527-8422-4fbe-8793-abe86e1f6af2)
## Git UserNames
 - Swati Mehta        = SwatiMehta14
 - Jatin Vig          = JatinVig-q2mj8
 - Muhammad Jahanzaib = Muhammad4179
 - Youhana Ghandali   = YouhanaGh 
## Overview

The Student Scheduler is a software tool designed to assist students at the University of New Brunswick (UNB) in organizing and managing their daily responsibilities related to each class. The existing To-Do list and calendar features provided by D2L lack essential functionalities that can significantly improve student productivity. This tool aims to bridge that gap by offering a more comprehensive and user-friendly scheduling experience.

## Primary Features:

Event and Assignment Management: Users can input assignments, tasks, and events into the scheduler.
Automated Scheduling: The tool generates a schedule indicating when assignments must be completed.
TO-DO List: Provides a list view of incomplete assignments and tasks.
Multiple Viewing Modes:
Calendar View: Displays the current and upcoming weeks with scheduled assignments.
List View: Shows a detailed breakdown of all user-entered tasks in order of urgency.
Graphical User Interface (GUI): A simple and intuitive interface for entering and managing tasks.
Data Storage: Assignments and tasks are saved in CSV and TXT files for future reference.

## Task class:
The `Task` class includes a constructor to create a new task for the student schedule, it takes in a name, due date, priority(low, medium and high) and a description with a default status("Pending"), completed status(false), and a unique static id. It also includes getters and setters for all the variables and a `toString` method to print out a task.

## TaskManager :
The `TaskManager` class is responsible for managing a list of tasks efficiently. It provides methods to add, remove, sort, and display tasks, ensuring a streamlined approach to task management. The addTask method allows users to add new tasks to the list, while the removeTask method removes tasks based on their unique ID. Tasks can be sorted in two ways: by due date using the sortTasksByDate method, which arranges tasks in ascending order, and by priority using the sortTasksByPriority method, which ranks tasks from High to Low importance. The displayTasks method outputs all tasks to the console, helping users keep track of their workload. By managing and organizing tasks effectively, this class plays a crucial role in enhancing productivity within the Student Scheduler.


## FileHandler:
The `FileHandler` class is responsible for handling file operations related to saving and loading task data. It provides methods to store task details in a CSV file and retrieve them for further processing. This class ensures persistence by maintaining tasks between different executions of the program. It provides functionality for saving and loading tasks to and from a CSV file. The `saveTasks` method writes tasks to tasks.csv, including a header and task details. The `loadTasks` method reads the file, recreates the tasks, and returns them as a list, keeping data available between runs.


## PreferencesManager:

The `PreferencesManager` class is responsible for managing user preferences and storing them in a properties file. It provides methods to save, load, and retrieve user-specific settings, ensuring a personalized experience. Preferences such as sorting order and theme selection are stored in a preferences.txt file. The savePreferences method writes user settings to the file, while the loadPreferences method retrieves and applies stored settings. This ensures that users do not have to reconfigure their preferences each time they use the scheduler.

### TaskTest Class

The `TaskTest` class is a JUnit test suite designed to validate the functionality of the `Task` class. It includes tests for:

- Creating tasks with correct initial values.
- Marking tasks as completed and verifying status changes.
- Ensuring task IDs auto-increment correctly.
- Setting and getting task properties (due date, priority, status, name, description).

These tests ensure that the `Task` class correctly manages individual task data, handles status changes, and maintains the integrity of task properties.

## TaskManagerTest:
TaskManagerTest Class
The TaskManagerTest class is a JUnit test suite designed to validate the functionality of the TaskManager class. It ensures the reliability and correctness of the task management operations by testing various scenarios, including:
Adding Tasks, 
Removing Tasks, 
Sorting Tasks, 
  By Date and
  By Priority (High > Medium > Low),
Displaying Tasks and
Retrieving Tasks

### FileHandlerTest Class

The `FileHandlerTest` class is a JUnit test suite designed to validate the functionality of the `FileHandler` class. It includes tests for:

- Saving tasks to a CSV file and loading them back, ensuring data integrity.
- Handling empty files when loading tasks.
- Properly managing scenarios where the task file does not exist.

These tests ensure that `FileHandler` correctly manages task persistence and handles various file-related scenarios.

### PreferencesManagerTest Class

The `PreferencesManagerTest` class is a JUnit test suite designed to validate the functionality of the `PreferencesManager` class. It includes tests for:

- Creating preference files and handling default preferences.
- Setting, retrieving, and managing custom preferences.
- Handling missing preferences and providing default values.
- Managing sort and theme preferences specifically.
- Ensuring preference persistence across multiple class instances.
- Properly handling empty preference files.
- Accurately storing and retrieving multiple preferences.

These tests ensure that `PreferencesManager` correctly manages user preferences, maintains data integrity across sessions, and handles various preference-related scenarios.


### How to run the Program
- Navigate to the Project Directory
- Compile the Java Files :javac *.java
- Run the Main Class : java TaskManagerFullGUI

## Run Junit Test Cases :
'From Terminal': 
- Prerequisites

- Java JDK 8  installed
- JUnit Platform Console Standalone JAR placed in your project directory.
- Compile All Java Files using command (javac -cp .:junit-platform-console-standalone-1.10.0.jar *.java)
- For Windows (javac -cp .;junit-platform-console-standalone-1.10.0.jar *.java)
- Run All Test Cases
    


