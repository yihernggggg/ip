# Flight User Guide

Flight is a command-line chatbot to help users manage their tasks efficiently.
It helps keep track of Todo, Deadline and Event tasks and supports persistent storage,
where tasks are automatically saved and loaded when the user starts the program.

## Quick Start
1. Ensure Java is installed (JDK 17).
2. Download the latest release from the [releases page](https://github.com/YOUR_USERNAME/ip/releases).
3. Open a terminal and navigate to the jar location.
4. Run the program with:
   ```
   java -jar ip.jar
   ```
5. Start interacting with Flight.
6. Look at [Features](#features) for the commands supported.

## Features

### Add Todo

Adds a simple todo task.

Command:
```
todo DESCRIPTION
```

Example:
```
todo read book
```

Expected Output:
```
____________________________________________________________
 Got it. I've added this task:
  [T][ ] read book
 Now you have 1 tasks in the list.
____________________________________________________________
```

### Add Deadline

Adds a deadline task that must be completed before a specific date/time.
Supported date formats are described in [Date Format](#date-format).

Command:
```
deadline DESCRIPTION /by DATE
```

Example:
```
deadline return book /by 2025-03-06
```

Expected Output:
```
____________________________________________________________
 Got it. I've added this task:
  [D][ ] return book (by: Mar 06 2025)
 Now you have 2 tasks in the list.
____________________________________________________________
```

Example with time:
```
deadline submit report /by 2025-03-06 1800
```

Expected Output:
```
____________________________________________________________
 Got it. I've added this task:
  [D][ ] submit report (by: Mar 06 2025 6:00PM)
 Now you have 3 tasks in the list.
____________________________________________________________
```

### Add Event

Adds an event task that happens during a specific time period.
Supported date formats are described in [Date Format](#date-format).

Command:
```
event DESCRIPTION /from DATE /to DATE
```

Example:
```
event group meeting /from 2025-03-06 1400 /to 2025-03-06 1600
```

Expected Output:
```
____________________________________________________________
 Got it. I've added this task:
  [E][ ] group meeting (from: Mar 06 2025 2:00PM to: Mar 06 2025 4:00PM)
 Now you have 4 tasks in the list.
____________________________________________________________
```

### List Tasks

Lists all the tasks in the current list.

Command:
```
list
```

Expected Output:
```
____________________________________________________________
 Here are your tasks:
  1. [T][ ] read book
  2. [D][ ] return book (by: Mar 06 2025)
  3. [D][ ] submit report (by: Mar 06 2025 6:00PM)
  4. [E][ ] group meeting (from: Mar 06 2025 2:00PM to: Mar 06 2025 4:00PM)
____________________________________________________________
```

### Mark Task

Marks a task as completed.

Command:
```
mark INDEX
```

Example:
```
mark 1
```

Expected Output:
```
____________________________________________________________
 Here are your tasks:
  1. [T][X] read book
  2. [D][ ] return book (by: Mar 06 2025)
  3. [D][ ] submit report (by: Mar 06 2025 6:00PM)
  4. [E][ ] group meeting (from: Mar 06 2025 2:00PM to: Mar 06 2025 4:00PM)
____________________________________________________________
```

### Unmark Task

Marks a task as not completed.

Command:
```
unmark INDEX
```

Example:
```
unmark 1
```

Expected Output:
```
____________________________________________________________
 Here are your tasks:
  1. [T][ ] read book
  2. [D][ ] return book (by: Mar 06 2025)
  3. [D][ ] submit report (by: Mar 06 2025 6:00PM)
  4. [E][ ] group meeting (from: Mar 06 2025 2:00PM to: Mar 06 2025 4:00PM)
____________________________________________________________
```

### Delete Task

Deletes a task from the task list.

Command:
```
delete INDEX
```

Example:
```
delete 1
```

Expected Output:
```
____________________________________________________________
 Got it. I've removed this task:
  [T][ ] read book
 Now you have 3 tasks in the list.
____________________________________________________________
```

### Find Task by Keyword

Finds tasks whose descriptions contain the given keyword. The search is case-insensitive.

Command:
```
find KEYWORD
```

Example:
```
find book
```

Expected Output:
```
____________________________________________________________
 Here are the matching tasks in your list:
  1. [D][ ] return book (by: Mar 06 2025)
____________________________________________________________
```

### Find Tasks on Date

Finds all deadlines and events that fall on a specific date.
For deadlines, the date must match the due date.
For events, the date must fall within the event's start and end range.

Command:
```
on DATE
```

Example:
```
on 2025-03-06
```

Expected Output:
```
____________________________________________________________
 Tasks on Mar 06 2025:
  1. [D][ ] return book (by: Mar 06 2025)
  2. [D][ ] submit report (by: Mar 06 2025 6:00PM)
  3. [E][ ] group meeting (from: Mar 06 2025 2:00PM to: Mar 06 2025 4:00PM)
____________________________________________________________
```

### Exit Program

Exits the program.

Command:
```
bye
```

Expected Output:
```
____________________________________________________________
 Bye. Hope to see you again soon!
____________________________________________________________
```

## Date Format

### Supported Input Formats

Dates and times can be entered in the following formats:

Date and Time:
- `yyyy-MM-dd HHmm` (e.g., `2025-03-06 1800`)

Date Only:
- `yyyy-MM-dd` (e.g., `2025-03-06`)

### Output Format

Dates and times are displayed in the following formats:
- Date only: `MMM dd yyyy` (e.g., Mar 06 2025)
- Date and time: `MMM dd yyyy h:mma` (e.g., Mar 06 2025 6:00PM)

## Command Summary

| Command          | Format                                  | Example                                                    |
|------------------|-----------------------------------------|------------------------------------------------------------|
| Add Todo         | `todo DESCRIPTION`                      | `todo read book`                                           |
| Add Deadline     | `deadline DESCRIPTION /by DATE`         | `deadline return book /by 2025-03-06`                      |
| Add Event        | `event DESCRIPTION /from DATE /to DATE` | `event meeting /from 2025-03-06 1400 /to 2025-03-06 1600` |
| List Tasks       | `list`                                  | `list`                                                     |
| Mark Task        | `mark INDEX`                            | `mark 1`                                                   |
| Unmark Task      | `unmark INDEX`                          | `unmark 1`                                                 |
| Delete Task      | `delete INDEX`                          | `delete 1`                                                 |
| Find by Keyword  | `find KEYWORD`                          | `find book`                                                |
| Find by Date     | `on DATE`                               | `on 2025-03-06`                                            |
| Exit Program     | `bye`                                   | `bye`                                                      |