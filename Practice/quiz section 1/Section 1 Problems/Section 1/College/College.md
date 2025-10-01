## Description

You are responsible for implementing a new person identification system in University District. Since the University of Washington is adjacent to U-District, this new system will have to account for several different types of people.

## Requirements

In particular, you will be implementing the following four classes with their respective functionality:

- Person.java
  - `getName()` — return the name of the person
  - `getEmail()` — return the email of the person
  - `getAge()` — return the age of the person
  - Constructor: takes the name, email, and age

- Professor.java
  - `getName()` — return the name of the professor
  - `getEmail()` — return the email of the professor
  - `getAge()` — return the age of the professor
  - `getSalary()` — return the salary of the professor
  - `addClass()` — adds a class to the professor's schedule
  - `getSchedule()` — return a String that lists the classes the professor is teaching this quarter in the format:
    - "Teach <class 1>\nTeach <class 2>\n...Teach <class n>\n"
    - The order should follow the order the classes were added
  - Constructor: takes the name, email, age, and salary

- Student.java
  - `getName()` — return the name of the student
  - `getEmail()` — return the email of the student
  - `getAge()` — return the age of the student
  - `getYear()` — return the class standing of the student
  - `addClass()` — adds a class to the student's schedule
  - `getSchedule()` — return a String that lists the classes the student is taking this quarter in the format:
    - "Study <class 1>\nStudy <class 2>\n...Study <class n>\n"
    - The order should follow the order the classes were added
  - Constructor: takes the name, email, age, and class standing

- TeachingAssistant.java
  - `getName()` — return the name of the TA
  - `getEmail()` — return the email of the TA
  - `getAge()` — return the age of the TA
  - `getYear()` — return the class standing of the TA
  - `getSalary()` — return the salary of the TA
  - `addRegisteredClass()` — adds a class that the TA is taking to the schedule
  - `addTAClass()` — adds a class that the TA is a TA for
  - `getSchedule()` — return a String that lists the classes the TA is teaching and taking this quarter in the format:
    - "Teach <class 1>\nTeach <class 2>\n...Study <class n>\n"
    - The classes the TA is teaching should appear first
    - The order should follow the order the classes were added
  - Constructor: takes the name, email, age, class standing, and salary

# Implementation notes

- Aim to avoid redundancy using inheritance
- The provided class headers do not necessarily correspond to the best implementation and may be changed as appropriate