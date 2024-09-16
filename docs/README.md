# Bong User Guide

![Ui](https://samuelthen.github.io/ip/Ui.png)

Bong is a desktop app that serves as a Personal Assistant Chatbot that helps a person to keep track of various things.

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.

2. Download the latest `.jar` file from [here](https://github.com/).

3. Copy the file to the folder you want to use as the _home folder_ for your Bong chatbot.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar Bong.jar` command to run the application.<br>

5. Type the command in the text box and press Enter to execute it. 
   Some example commands you can try:

    * `list` : Lists all tasks.

    * `deadline Physics Homework /by 31 Dec 2024 1500` : Adds a deadline named `Physics Homework` to the task list.

    * `delete 3` : Deletes the 3rd task shown in the current list.

    * `clear` : Deletes all contacts.

    * `bye` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

### Listing all tasks : `list`

Shows a list of all tasks in the Bong chatbot. It is

Format: `list`

* The list is sorted according to the urgency of the tasks

### Adding a to-do : `todo`

Adds a to-do into the task list. 

Format: `todo TASK`

* No date and time is associated with to-do tasks

Examples:
*  `todo Buy groceries` Adds `Buy groceries` into the task list

```
Got it. I've added this task:
[T][] Buy groceries
Now you have 2 tasks in the list.
```

### Adding a deadline : `deadline`

Adds a task with a deadline into the task list.

Format: `deadline TASK /by DATE/DATE-TIME`

* If time is not specified for a date, it is assumed to be 2359 of the specified date

Examples:
*  `deadline CS3230 Assignment /by 20 Sep 2024 1500` Adds `CS3230 Assignment` into the task list with a deadline on 20 Sep 2024 1500
*  `deadline SEP application /by 29 Sep 2024` Adds `SEP application` into the task list with a deadline on 20 Sep 2024 2359

```
Got it. I've added this task:
[D][] SEP application (by: 29/9/2024 2359)
Now you have 3 tasks in the list.
```

### Adding an event : `event`

Adds an event into the task list.

Format: `event TASK /from DATE/DATE-TIME /to DATE/DATE-TIME`

* If an event time is not specified for a date, it is assumed to start at 0000 of the specified `from` date and end at 2359 of the specified `to` date

Examples:
*  `event Dinner /from 20 Sep 2024 1800 /to 20 Sep 2024 2200` Add `Dinner` into the task list from 20 Sep 2024 1800 to 20 Sep 2024 2200
*  `event Hackathon /from 29 Sep 2024 to 30 Sep 2024` Add `Hackathon` into the task list from 29 Sep 2024 0000 to 30 Sep 2024 2359

```
Got it. I've added this task:
[E][] Hackathon (from: 29/9/2024 0000 to: 20/9/2024 2359)
Now you have 4 tasks in the list.
```

### Mark a task as done : `mark`

Marks the specified task as done from the task list.

Format: `mark INDEX`

* Marks the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `mark 2` marks the 2nd task as done in the task list.
* `find Hackathon` followed by `mark 4` marks the task with index 4 as done in the results of the `find` command.

```
Nice! I've marked this task as done:
[E][X] Hackathon (from: 29/9/2024 0000 to: 20/9/2024 2359)
```

### Unmark a task as done : `unmark`

Unmarks the specified task as done from the task list.

Format: `unmark INDEX`

* Unmarks the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `unmark 2` unmarks the 2nd task as done in the task list.
* `find Hackathon` followed by `unmark 4` unmarks the task with index 4 as done in the results of the `find` command.

```
OK, I've marked this task as not done yet:
[E][] Hackathon (by: 29/9/2024 0000 to: 20/9/2024 2359)
```

### Locating tasks by name: `find`

Finds tasks whose names contain the given keyword.

Format: `find KEYWORD`

* The search is case-insensitive. e.g. `presentation` will match `Presentation`
* Only the name is searched.
* Only partial words will still be matched e.g. `CS` will match `CS2103T Homework`

Examples:
* `find CS` returns `CS2103T Homework` and `CS2109S Problem Set`

```
Here are the matching tasks in your list:
3. [D][] CS2103T Homework (by: 29/9/2024 2359)
7. [D][] CS2109S Problem Set (by: 30/9/2024 2359)
```

### Deleting a task : `delete`

Deletes the specified task from the task list.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd task in the task list.
* `find Hackathon` followed by `delete 4` deletes the task with index 4 in the results of the `find` command.

```
Noted. I've removed this task:
[E][] Hackathon (by: 29/9/2024 0000 to: 20/9/2024 2359)
Now you have 3 tasks in the list.
```

### Exiting the program : `bye`

Exits the program.

Format: `bye`

### Saving the data

Bong chatbot data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: <br>
What date and date-time format are accepted? <br>
**A**: <br>
Date-Time Formats:
1. `d/M/yyyy HHmm`. Example: "31/12/2023 2359"
2. `d-M-yyyy HHmm`. Example: "31-12-2023 2359"
3. `dd MMM yyyy HHmm`. Example: "31 Dec 2023 2359"
4. `yyyy-MM-dd HHmm`. Example: "2023-12-31 2359"
5. `MMM dd yyyy HHmm`. Example: "Dec 31 2023 2359"

Date-Only Formats:
1. `d/M/yyyy`. Example: "31/12/2023"
2. `d-M-yyyy`. Example: "31-12-2023"
3. `d MMM yyyy`. Example: "31 Dec 2023"
4. `yyyy-MM-dd`. Example: "2023-12-31"
5. `MMM dd yyyy`. Example: "Dec 31 2023"

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action           | Format, Examples                                                                                                         |
|------------------|--------------------------------------------------------------------------------------------------------------------------|
| **List Tasks**   | `list`<br>e.g., `list`                                                                                                   |
| **Add To-do**    | `todo TASK`<br>e.g., `todo Buy groceries`                                                                                |
| **Add Deadline** | `deadline TASK /by DATE/DATE-TIME`<br>e.g., `deadline CS3230 Assignment /by 20 Sep 2024 1500`                            |
| **Add Event**    | `event TASK /from DATE/DATE-TIME /to DATE/DATE-TIME`<br>e.g., `event Dinner /from 20 Sep 2024 1800 /to 20 Sep 2024 2200` |
| **Mark Task**    | `mark INDEX`<br>e.g., `mark 2`                                                                                           |
| **Unmark Task**  | `unmark INDEX`<br>e.g., `unmark 2`                                                                                       |
| **Find Task**    | `find KEYWORD`<br>e.g., `find CS`                                                                                        |
| **Delete Task**  | `delete INDEX`<br>e.g., `delete 3`                                                                                       |
| **Exit**         | `bye`                                                                                                                    |
