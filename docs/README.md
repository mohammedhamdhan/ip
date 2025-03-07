# Orca Task Manager - User Guide

```
  ___    ____     ____      _
 / _ \  |  _ \   / ___|    / \
| | | | | |_) | | |       / _ \
| |_| | |  _ <  | |___   / ___ \
 \___/  |_| \_\  \____| /_/   \_\
```

Welcome to Orca, your friendly task management assistant! Orca helps you keep track of your todos, deadlines, and events with a simple command-line interface.

## Quick Start

1. Ensure you have Java installed on your computer
2. Download the latest version of Orca
3. Run the application using: `java -jar orca.jar`
4. Start managing your tasks with simple commands!

## Features

### 1. View All Tasks: `list`

Shows all your tasks in a numbered list.

```
list
```

Example output:

```
Your todo list:
1.[T][✓] read book
2.[D][✗] submit report (by: Monday 2pm)
3.[E][✗] team meeting (from: 3pm to: 5pm)
```

### 2. Add Tasks

#### a. Todo Tasks: `todo`

Add a simple task without any deadline.

```
todo read book
```

#### b. Deadline Tasks: `deadline`

Add a task with a specific deadline.

```
deadline submit report /by Monday 2pm
```

#### c. Event Tasks: `event`

Add a task with a start and end time.

```
event team meeting /from 3pm /to 5pm
```

### 3. Mark/Unmark Tasks

#### Mark as Done: `mark`

Mark a task as completed using its number.

```
mark 1
```

#### Mark as Not Done: `unmark`

Change a completed task back to not done.

```
unmark 1
```

### 4. Delete Tasks: `delete`

Remove a task from your list using its number.

```
delete 1
```

### 5. Find Tasks: `find`

Search for tasks containing specific keywords.

```
find report
```

Example output:

```
Here are the matching tasks in your list:
1.[D][✗] submit report (by: Monday 2pm)
2.[D][✓] write progress report (by: Friday 5pm)
```

### 6. Get Help: `help`

Display all available commands and their usage.

```
help
```

### 7. Exit: `bye`

Save your tasks and exit the application.

```
bye
```

## Command Format

- Words in `UPPER_CASE` are parameters to be supplied by you
  - e.g., in `todo DESCRIPTION`, `DESCRIPTION` is a parameter
- Items in square brackets are optional
  - e.g., `find [KEYWORD]`
- Parameters with `/` are command flags
  - e.g., `/by`, `/from`, `/to`

## Command Summary

| Command  | Format                                            | Example                                 |
| -------- | ------------------------------------------------- | --------------------------------------- |
| List     | `list`                                            | `list`                                  |
| Todo     | `todo DESCRIPTION`                                | `todo read book`                        |
| Deadline | `deadline DESCRIPTION /by TIME`                   | `deadline submit report /by Monday 2pm` |
| Event    | `event DESCRIPTION /from START_TIME /to END_TIME` | `event team meeting /from 3pm /to 5pm`  |
| Mark     | `mark TASK_NUMBER`                                | `mark 1`                                |
| Unmark   | `unmark TASK_NUMBER`                              | `unmark 1`                              |
| Delete   | `delete TASK_NUMBER`                              | `delete 1`                              |
| Find     | `find KEYWORD`                                    | `find report`                           |
| Help     | `help`                                            | `help`                                  |
| Exit     | `bye`                                             | `bye`                                   |

## Error Messages

Here are some common error messages and how to fix them:

1. "The description cannot be empty"

   - Make sure to include a description after the command
   - Example: `todo read book` instead of just `todo`

2. "Please provide a task number"

   - Include the task number for mark/unmark/delete commands
   - Use `list` to see task numbers

3. "Task index out of range"

   - The task number provided doesn't exist
   - Use `list` to see valid task numbers

4. "Invalid deadline format"

   - Use the correct format: `deadline DESCRIPTION /by TIME`
   - Example: `deadline submit report /by Monday 2pm`

5. "Invalid event format"
   - Use the correct format: `event DESCRIPTION /from START_TIME /to END_TIME`
   - Example: `event team meeting /from 3pm /to 5pm`
