# 🐋 Orca Task Manager - User Guide

```
  ___    ____     ____      _
 / _ \  |  _ \   / ___|    / \
| | | | | |_) | | |       / _ \
| |_| | |  _ <  | |___   / ___ \
 \___/  |_| \_\  \____| /_/   \_\
```

Welcome to Orca, your friendly task management assistant! Orca helps you keep track of your todos, deadlines, and events with a simple command-line interface.

---

## 🚀 Quick Start

1. ✓ Ensure you have Java installed on your computer
2. ⬇️ Download the latest version of Orca
3. 🎯 Run the application using: `java -jar orca.jar`
4. 🎉 Start managing your tasks with simple commands!

---

## 📋 Features

### 1. 📝 View All Tasks: `list`

Shows all your tasks in a numbered list.

```
> list
----------------------------------------
Your todo list:

1.[T][✓] read book
2.[D][✗] submit report (by: Monday 2pm)
3.[E][✗] team meeting (from: 3pm to: 5pm)
----------------------------------------
```

### 2. ➕ Add Tasks

#### 📌 Todo Tasks: `todo`

Add a simple task without any deadline.

```
> todo read book
----------------------------------------
Awesome! I've added this task:

     [T][ ] read book

----------------------------------------
```

#### ⏰ Deadline Tasks: `deadline`

Add a task with a specific deadline.

```
> deadline submit report /by Monday 2pm
----------------------------------------
Awesome! I've added this task:

     [D][ ] submit report (by: Monday 2pm)

----------------------------------------
```

#### 📅 Event Tasks: `event`

Add a task with a start and end time.

```
> event team meeting /from 3pm /to 5pm
----------------------------------------
Awesome! I've added this task:

     [E][ ] team meeting (from: 3pm to: 5pm)

----------------------------------------
```

### 3. ✅ Mark/Unmark Tasks

#### ✔️ Mark as Done: `mark`

Mark a task as completed using its number.

```
> mark 1
----------------------------------------
Awesome! Congrats on finishing this task!

  [✓] read book

----------------------------------------
```

#### ❌ Mark as Not Done: `unmark`

Change a completed task back to not done.

```
> unmark 1
----------------------------------------
Okay, I have unmarked this task!

  [✗] read book

----------------------------------------
```

### 4. 🗑️ Delete Tasks: `delete`

Remove a task from your list using its number.

```
> delete 1
----------------------------------------
Noted. I've removed this task:

  [T][✓] read book

Now you have 2 tasks in the list.

----------------------------------------
```

### 5. 🔍 Find Tasks: `find`

Search for tasks containing specific keywords.

```
> find report
----------------------------------------
Here are the matching tasks in your list:

1.[D][✗] submit report (by: Monday 2pm)
2.[D][✓] write progress report (by: Friday 5pm)
----------------------------------------
```

### 6. ❓ Get Help: `help`

Display all available commands and their usage.

```
> help
----------------------------------------
Available Commands:
  list                            - Display all tasks in your list.
  mark <task number>              - Mark a task as done.
  unmark <task number>            - Mark a task as not done.
  todo <description>              - Add a new todo task.
  deadline <description> /by <time>  - Add a new deadline task.
  event <description> /from <start time> /to <end time> - Add a new event task.
  delete <task number>            - Delete a task.
  find <keyword>                  - Find tasks matching the keyword.
  help                            - Display this help message.
  bye                             - Exit the application.
----------------------------------------
```

### 7. 👋 Exit: `bye`

Save your tasks and exit the application.

```
> bye
----------------------------------------
Bye! Hope to see you again soon :)
----------------------------------------
```

---

## 💾 Data Saving

Orca automatically saves your tasks after every change you make. Here's what you need to know about data management:

### 📂 Save Location

- Tasks are saved in the `data/tasks.txt` file
- The `data` folder is created automatically in the same directory as the application

### 🔄 Auto-saving

- Every change (add, mark, unmark, delete) is saved immediately
- No manual save command needed
- Your tasks persist between sessions

### 📊 Data Format

Each task type is saved with a specific format:

| Type     | Format                             |
| -------- | ---------------------------------- |
| Todo     | `T\|isDone\|description`           |
| Deadline | `D\|isDone\|description\|deadline` |
| Event    | `E\|isDone\|description\|from\|to` |

### 🔧 Data Recovery

If the data file is corrupted, Orca will:

- ⚠️ Skip corrupted entries
- ✅ Load valid entries
- 📢 Show warning messages for corrupted data

---

## 📝 Command Format

| Symbol       | Meaning                         |
| ------------ | ------------------------------- |
| `UPPER_CASE` | Parameter to be supplied by you |
| `/flag`      | Command flag                    |

Examples:

- In `todo DESCRIPTION`, replace `DESCRIPTION` with your task
- Use `/by`, `/from`, `/to` to specify times

---

## 🎯 Command Summary

| Command     | Format                                            | Example                                 |
| ----------- | ------------------------------------------------- | --------------------------------------- |
| 📝 List     | `list`                                            | `list`                                  |
| 📌 Todo     | `todo DESCRIPTION`                                | `todo read book`                        |
| ⏰ Deadline | `deadline DESCRIPTION /by TIME`                   | `deadline submit report /by Monday 2pm` |
| 📅 Event    | `event DESCRIPTION /from START_TIME /to END_TIME` | `event team meeting /from 3pm /to 5pm`  |
| ✔️ Mark     | `mark TASK_NUMBER`                                | `mark 1`                                |
| ❌ Unmark   | `unmark TASK_NUMBER`                              | `unmark 1`                              |
| 🗑️ Delete   | `delete TASK_NUMBER`                              | `delete 1`                              |
| 🔍 Find     | `find KEYWORD`                                    | `find report`                           |
| ❓ Help     | `help`                                            | `help`                                  |
| 👋 Exit     | `bye`                                             | `bye`                                   |

---

## ⚠️ Error Messages

Here are some common error messages and how to fix them:

### 📝 Empty Description

> "The description cannot be empty"

- ✅ Include a description after the command
- Example: `todo read book` instead of just `todo`

### 🔢 Missing Task Number

> "Please provide a task number"

- ✅ Include the task number for mark/unmark/delete commands
- Use `list` to see task numbers

### 📊 Invalid Index

> "Task index out of range"

- ✅ The task number provided doesn't exist
- Use `list` to see valid task numbers

### ⏰ Deadline Format

> "Invalid deadline format"

- ✅ Use the correct format: `deadline DESCRIPTION /by TIME`
- Example: `deadline submit report /by Monday 2pm`

### 📅 Event Format

> "Invalid event format"

- ✅ Use the correct format: `event DESCRIPTION /from START_TIME /to END_TIME`
- Example: `event team meeting /from 3pm /to 5pm`

---

## ❓ FAQ

### 🔄 Program Behavior

**Q: What happens if I close the program without using `bye`?**

- ✅ Your tasks are saved automatically after every change, so no data will be lost.

### ✏️ Task Management

**Q: Can I edit a task after creating it?**

- 🔄 Currently, you need to delete the task and create a new one with the correct information.

### ⏰ Time Formats

**Q: What time formats are accepted?**

- ✅ Orca accepts flexible time formats:
  ```
  ✓ "Monday 2pm"
  ✓ "tomorrow 3:30pm"
  ✓ "23/03/2024 14:00"
  ```

### 📊 Task Limits

**Q: Is there a limit to how many tasks I can add?**

- ✅ No, you can add as many tasks as you need.

### 💾 Data Portability

**Q: Can I move my tasks to another computer?**

- ✅ Yes, just copy the `data/tasks.txt` file to the same location on the new computer.

### 📝 Task Descriptions

**Q: Can I use the same task description multiple times?**

- ✅ Yes, you can have multiple tasks with the same description.

### 🔄 Task Type Conversion

**Q: How do I change a todo to a deadline or event?**

- 🔄 Delete the todo task and create a new deadline or event task with the same description.
