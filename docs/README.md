# Flight User Guide

Flight is a lightweight CLI task manager written in Java. It supports three task types, namely Todos, Deadlines, and Events, with automatic file persistence so your data survives between sessions.

## Getting Started
1. Make sure you have JDK 17 installed.
2. Grab the latest JAR from the [releases page](https://github.com/YOUR_USERNAME/ip/releases).
3. Open a terminal, navigate to where the JAR is located, and run:
   ```
   java -jar ip.jar
   ```
4. You're ready to go! See [Features](#features) below for what Flight can do.

## Features

### Adding a Todo

Creates a basic task with no date attached.

**Usage:** `todo DESCRIPTION`

```
> todo read book
____________________________________________________________
 Got it. I've added this task:
  [T][ ] read book
 Now you have 1 tasks in the list.
____________________________________________________________
```

### Adding a Deadline

Creates a task with a due date. You can optionally include a time.
See [Date Format](#date-format) for accepted formats.

**Usage:** `deadline DESCRIPTION /by DATE`

```
> deadline return book /by 2025-03-06
____________________________________________________________
 Got it. I've added this task:
  [D][ ] return book (by: Mar 06 2025)
 Now you have 2 tasks in the list.
____________________________________________________________
```

With a time component:
```
> deadline submit report /by 2025-03-06 1800
____________________________________________________________
 Got it. I've added this task:
  [D][ ] submit report (by: Mar 06 2025 6:00PM)
 Now you have 3 tasks in the list.
____________________________________________________________
```

### Adding an Event

Creates a task that spans a time period, defined by a start and end date/time.
See [Date Format](#date-format) for accepted formats.

**Usage:** `event DESCRIPTION /from DATE /to DATE`

```
> event group meeting /from 2025-03-06 1400 /to 2025-03-06 1600
____________________________________________________________
 Got it. I've added this task:
  [E][ ] group meeting (from: Mar 06 2025 2:00PM to: Mar 06 2025 4:00PM)
 Now you have 4 tasks in the list.
____________________________________________________________
```

### Listing All Tasks

Shows every task currently in your list.

**Usage:** `list`

```
> list
____________________________________________________________
 Here are your tasks:
  1. [T][ ] read book
  2. [D][ ] return book (by: Mar 06 2025)
  3. [D][ ] submit report (by: Mar 06 2025 6:00PM)
  4. [E][ ] group meeting (from: Mar 06 2025 2:00PM to: Mar 06 2025 4:00PM)
____________________________________________________________
```

### Marking a Task as Done

Toggles a task's status to completed.

**Usage:** `mark INDEX`

```
> mark 1
____________________________________________________________
 Here are your tasks:
  1. [T][X] read book
  2. [D][ ] return book (by: Mar 06 2025)
  3. [D][ ] submit report (by: Mar 06 2025 6:00PM)
  4. [E][ ] group meeting (from: Mar 06 2025 2:00PM to: Mar 06 2025 4:00PM)
____________________________________________________________
```

### Unmarking a Task

Reverts a task's status back to not done.

**Usage:** `unmark INDEX`

```
> unmark 1
____________________________________________________________
 Here are your tasks:
  1. [T][ ] read book
  2. [D][ ] return book (by: Mar 06 2025)
  3. [D][ ] submit report (by: Mar 06 2025 6:00PM)
  4. [E][ ] group meeting (from: Mar 06 2025 2:00PM to: Mar 06 2025 4:00PM)
____________________________________________________________
```

### Deleting a Task

Removes a task from the list permanently.

**Usage:** `delete INDEX`

```
> delete 1
____________________________________________________________
 Got it. I've removed this task:
  [T][ ] read book
 Now you have 3 tasks in the list.
____________________________________________________________
```

### Finding Tasks by Keyword

Searches all task descriptions for a keyword match. The search is case-insensitive.

**Usage:** `find KEYWORD`

```
> find book
____________________________________________________________
 Here are the matching tasks in your list:
  1. [D][ ] return book (by: Mar 06 2025)
____________________________________________________________
```

### Finding Tasks on a Date

Retrieves all deadlines due on the given date, and all events whose date range includes it.

**Usage:** `on DATE`

```
> on 2025-03-06
____________________________________________________________
 Tasks on Mar 06 2025:
  1. [D][ ] return book (by: Mar 06 2025)
  2. [D][ ] submit report (by: Mar 06 2025 6:00PM)
  3. [E][ ] group meeting (from: Mar 06 2025 2:00PM to: Mar 06 2025 4:00PM)
____________________________________________________________
```

### Exiting Flight

Saves all tasks and closes the application.

**Usage:** `bye`

```
> bye
____________________________________________________________
 Bye. Hope to see you again soon!
____________________________________________________________
```

## Date Format

### Input

Flight accepts the following date formats:

| Format | Example | Description |
|---|---|---|
| `yyyy-MM-dd` | `2025-03-06` | Date only |
| `yyyy-MM-dd HHmm` | `2025-03-06 1800` | Date with 24-hour time |

### Display

Flight renders dates in a more readable format:
- Without time: `Mar 06 2025`
- With time: `Mar 06 2025 6:00PM`

## Command Summary

| Command | Format | Example |
|---|---|---|
| Todo | `todo <desc>` | `todo read book` |
| Deadline | `deadline <desc> /by <date>` | `deadline essay /by 2025-03-06` |
| Event | `event <desc> /from <date> /to <date>` | `event meeting /from 2025-03-06 1400 /to 2025-03-06 1600` |
| List | `list` | `list` |
| Mark | `mark <index>` | `mark 1` |
| Unmark | `unmark <index>` | `unmark 1` |
| Delete | `delete <index>` | `delete 1` |
| Find | `find <keyword>` | `find book` |
| Date search | `on <date>` | `on 2025-03-06` |
| Exit | `bye` | `bye` |