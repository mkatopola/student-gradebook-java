# Overview

As a software engineer focused on continuous growth, I built this project to strengthen my practical skills in Java, particularly around object-oriented design, collections, and file persistence. Working with real data structures and user interaction helped me move beyond basic syntax into writing cleaner, more maintainable console applications.

This is a console-based Student Gradebook application written in Java. It allows users to manage student records by adding students, recording grades, viewing individual and class performance, calculating averages and letter grades, removing students, and saving/loading data from text files. The program uses an interactive menu, supports multiple gradebook files, and provides clear feedback to the user.

I created this software to deepen my understanding of core Java concepts such as classes, methods, the Java Collections Framework (ArrayList), conditionals, loops, and file input/output, while building something practical and reusable.

[Software Demo Video](http://youtube.link.goes.here)

# Development Environment

- Visual Studio Code with the Extension Pack for Java
- Java Development Kit (JDK 17 or later)
- Integrated terminal in VS Code for compiling and running
- Git for version control

Programming language: Java  
Key library / feature used: `java.util.ArrayList` (Java Collections Framework) and standard file I/O (`java.io`)

# How to Compile and Run

1. Open a terminal in the project folder that contains `Student.java` and `Gradebook.java`.
2. Compile both source files:

   ```bash
   javac Student.java Gradebook.java
   ```

3. Run the program:

   ```bash
   java Gradebook
   ```

The program looks for and saves gradebook `.txt` files in the folder you run it from, so start it from the project folder to keep your data files together. For example, save a gradebook as `class.txt` and it will be created in that same folder.

# Useful Websites


- [Giraffe Academy - Java](https://www.giraffeacademy.com/programming-languages/java/)
- [W3Schools Java Tutorial](https://www.w3schools.com/java/)
- [Java ArrayList Tutorial - GeeksforGeeks](https://www.geeksforgeeks.org/arraylist-in-java/)
- [Baeldung - Java I/O](https://www.baeldung.com/java-io)

# Future Work

- Add the ability to edit an existing student’s name or ID
- Support weighted grades or grade categories (e.g. homework, exams)
- Allow sorting students by name, ID, or average
- Add search functionality by student name or ID
- Improve input validation and error messages
- Export data to CSV format for use in spreadsheets
