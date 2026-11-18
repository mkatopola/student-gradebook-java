import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// ============================================================
//  Student Gradebook
//  CSE 310 - Applied Programming (Module 2: Java)
//  Author: Moffat Katopola
// ============================================================
//  This program demonstrates:
//  - Variables, expressions, conditionals, loops
//  - Methods and classes
//  - ArrayList (Java Collection Framework)
//  - Reading from and writing to a file
// ============================================================

public class Gradebook {

    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        int choice = 0;

        // Main program loop
        while (true) {
            showMenu();
            System.out.print("Enter your choice: ");

            // Protect against non-numeric input
            if (!input.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.\n");
                input.nextLine();
                continue;
            }

            choice = input.nextInt();
            input.nextLine();   // clear the leftover newline

            if (choice == 1) {
                loadFromFile(students, input);
            }
            else if (choice == 2) {
                addStudent(students, input);
            }
            else if (choice == 3) {
                addGradeToStudent(students, input);
            }
            else if (choice == 4) {
                listStudentsSimple(students);
            }
            else if (choice == 5) {
                viewStudent(students, input);
            }
            else if (choice == 6) {
                classAverage(students);
            }
            else if (choice == 7) {
                removeStudent(students, input);
            }
            else if (choice == 8) {
                saveToFile(students, input);
            }
            else if (choice == 9) {
                System.out.println("Goodbye!");
                break;
            }
            else {
                System.out.println("Invalid choice. Please try again.\n");
            }
        }

        input.close();
    }

    // ------------------------------------------------------------
    // Displays the main menu
    // ------------------------------------------------------------
    public static void showMenu() {
        System.out.println("===== Student Gradebook =====");
        System.out.println("1. Load Course Gradebook");
        System.out.println("2. Add Student");
        System.out.println("3. Add Grade to Student");
        System.out.println("4. List All Students");
        System.out.println("5. View Single Student");
        System.out.println("6. Class Average");
        System.out.println("7. Remove Student");
        System.out.println("8. Save Course Gradebook");
        System.out.println("9. Exit");
    }

    // ------------------------------------------------------------
    // Shows a simple list (ID : Name) – used by several options
    // ------------------------------------------------------------
    public static void listStudentsSimple(ArrayList<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No students in the gradebook yet.\n");
            return;
        }

        System.out.println("\n----- Students -----");
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            System.out.println((i + 1) + ". " + s.getId() + " : " + s.getName());
        }
        System.out.println();
    }

    // ------------------------------------------------------------
    // Adds a new student
    // ------------------------------------------------------------
    public static void addStudent(ArrayList<Student> students, Scanner input) {
        System.out.print("Enter student name: ");
        String name = input.nextLine().trim();

        System.out.print("Enter student ID: ");
        String id = input.nextLine().trim();

        if (name.isEmpty() || id.isEmpty()) {
            System.out.println("Name and ID cannot be empty.\n");
            return;
        }

        // Check for duplicate ID
        for (Student s : students) {
            if (s.getId().equalsIgnoreCase(id)) {
                System.out.println("A student with that ID already exists.\n");
                return;
            }
        }

        students.add(new Student(name, id));
        System.out.println("Student added successfully!\n");
    }

    // ------------------------------------------------------------
    // Adds a grade to an existing student
    // ------------------------------------------------------------
    public static void addGradeToStudent(ArrayList<Student> students, Scanner input) {
        if (students.isEmpty()) {
            System.out.println("No students available. Add a student first.\n");
            return;
        }

        listStudentsSimple(students);
        System.out.print("Enter the number of the student: ");

        if (!input.hasNextInt()) {
            System.out.println("Invalid input.\n");
            input.nextLine();
            return;
        }

        int index = input.nextInt();
        input.nextLine();

        if (index < 1 || index > students.size()) {
            System.out.println("Invalid student number.\n");
            return;
        }

        System.out.print("Enter grade (0-100): ");
        if (!input.hasNextDouble()) {
            System.out.println("Invalid grade.\n");
            input.nextLine();
            return;
        }

        double grade = input.nextDouble();
        input.nextLine();

        students.get(index - 1).addGrade(grade);
        System.out.println("Grade added successfully!\n");
    }

    // ------------------------------------------------------------
    // Shows simple list, then full details of the selected student
    // ------------------------------------------------------------
    public static void viewStudent(ArrayList<Student> students, Scanner input) {
        if (students.isEmpty()) {
            System.out.println("No students in the gradebook yet.\n");
            return;
        }

        listStudentsSimple(students);
        System.out.print("Enter the number of the student to view: ");

        if (!input.hasNextInt()) {
            System.out.println("Invalid input.\n");
            input.nextLine();
            return;
        }

        int index = input.nextInt();
        input.nextLine();

        if (index < 1 || index > students.size()) {
            System.out.println("Invalid student number.\n");
            return;
        }

        System.out.println("\n----- Student Details -----");
        students.get(index - 1).display();
        System.out.println();
    }

    // ------------------------------------------------------------
    // Calculates the average of all students who have grades
    // ------------------------------------------------------------
    public static void classAverage(ArrayList<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No students in the gradebook yet.\n");
            return;
        }

        double total = 0.0;
        int count = 0;

        for (Student s : students) {
            if (!s.getGrades().isEmpty()) {
                total += s.getAverage();
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No grades have been entered yet.\n");
        } else {
            System.out.printf("Class Average: %.2f%n\n", total / count);
        }
    }

    // ------------------------------------------------------------
    // Removes a student from the gradebook
    // ------------------------------------------------------------
    public static void removeStudent(ArrayList<Student> students, Scanner input) {
        if (students.isEmpty()) {
            System.out.println("No students to remove.\n");
            return;
        }

        listStudentsSimple(students);
        System.out.print("Enter the number of the student to remove: ");

        if (!input.hasNextInt()) {
            System.out.println("Invalid input.\n");
            input.nextLine();
            return;
        }

        int index = input.nextInt();
        input.nextLine();

        if (index < 1 || index > students.size()) {
            System.out.println("Invalid student number.\n");
            return;
        }

        Student removed = students.remove(index - 1);
        System.out.println("Removed: " + removed.getId() + " : " + removed.getName() + "\n");
    }

    // ------------------------------------------------------------
    // Saves all students to a file (user chooses the name)
    // Warns before overwriting an existing file
    // ------------------------------------------------------------
    public static void saveToFile(ArrayList<Student> students, Scanner input) {
        if (students.isEmpty()) {
            System.out.println("No students to save.\n");
            return;
        }

        System.out.print("Enter Course Code for the filename: ");
        String filename = input.nextLine().trim();

        if (filename.isEmpty()) {
            System.out.println("Filename cannot be empty.\n");
            return;
        }

        filename = filename + ".txt";

        File file = new File(filename);

        // Ask before overwriting
        if (file.exists()) {
            System.out.print("File \"" + filename + "\" already exists. Overwrite? (y/n): ");
            String answer = input.nextLine().trim();

            if (!answer.equalsIgnoreCase("y")) {
                System.out.println("Save cancelled.\n");
                return;
            }
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Student s : students) {
                writer.println(s.toFileString());
            }
            System.out.println("Data saved successfully to \"" + filename + "\".\n");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage() + "\n");
        }
    }

    // ------------------------------------------------------------
    // Lists all .txt files and lets the user choose which one to load
    // ------------------------------------------------------------
    public static void loadFromFile(ArrayList<Student> students, Scanner input) {
        File folder = new File(".");
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

        if (files == null || files.length == 0) {
            System.out.println("No .txt files found in the current directory.\n");
            return;
        }

        // Show available files
        System.out.println("\n----- Available Gradebook Files -----");
        for (int i = 0; i < files.length; i++) {
            System.out.println((i + 1) + ". " + files[i].getName());
        }

        System.out.print("\nEnter the number of the file to load: ");

        if (!input.hasNextInt()) {
            System.out.println("Invalid input.\n");
            input.nextLine();
            return;
        }

        int choice = input.nextInt();
        input.nextLine();

        if (choice < 1 || choice > files.length) {
            System.out.println("Invalid selection.\n");
            return;
        }

        File selectedFile = files[choice - 1];

        // Clear current list before loading new data
        students.clear();

        try (Scanner fileScanner = new Scanner(selectedFile)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (!line.isEmpty()) {
                    Student s = Student.fromFileString(line);
                    if (s != null) {
                        students.add(s);
                    }
                }
            }
            System.out.println("Successfully loaded " + students.size() +
                               " student(s) from \"" + selectedFile.getName() + "\".\n");
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage() + "\n");
        }
    }
}