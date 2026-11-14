import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Gradebook {
    private static final String DATA_FILE = "gradebook_data.txt";

    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        // Load existing data when the program starts
        loadFromFile(students);

        int choice = 0;

        while (true) {
            showMenu();
            System.out.print("Enter your choice: ");

            if (!input.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.\n");
                input.nextLine();
                continue;
            }

            choice = input.nextInt();
            input.nextLine();

            if (choice == 1) {
                addStudent(students, input);
            } else if (choice == 2) {
                addGradeToStudent(students, input);
            } else if (choice == 3) {
                listStudentsSimple(students);
            } else if (choice == 4) {
                viewStudent(students, input);
            } else if (choice == 5) {
                classAverage(students);
            } else if (choice == 6) {
                removeStudent(students, input);
            } else if (choice == 7) {
                saveToFile(students);
                System.out.println("Data saved successfully.");
            } else if (choice == 8) {
                saveToFile(students);
                System.out.println("Data saved. Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.\n");
            }
        }

        input.close();
    }

    // -------------------- Menu --------------------

    public static void showMenu() {
        System.out.println("===== Student Gradebook =====");
        System.out.println("1. Add Student");
        System.out.println("2. Add Grade to Student");
        System.out.println("3. List All Students");
        System.out.println("4. View Single Student");
        System.out.println("5. Class Average");
        System.out.println("6. Remove Student");
        System.out.println("7. Save to File");
        System.out.println("8. Exit");
    }

    // -------------------- Core Features --------------------

    public static void listStudents(ArrayList<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No students in the gradebook yet.\n");
            return;
        }

        System.out.println("\n----- All Students -----");
        for (int i = 0; i < students.size(); i++) {
            System.out.print((i + 1) + ". ");
            students.get(i).display();
        }
        System.out.println();
    }

    public static void addStudent(ArrayList<Student> students, Scanner input) {
        System.out.print("Enter student name: ");
        String name = input.nextLine().trim();

        System.out.print("Enter student ID: ");
        String id = input.nextLine().trim();

        if (name.isEmpty() || id.isEmpty()) {
            System.out.println("Name and ID cannot be empty.\n");
            return;
        }

        for (Student s : students) {
            if (s.getId().equalsIgnoreCase(id)) {
                System.out.println("A student with that ID already exists.\n");
                return;
            }
        }

        students.add(new Student(name, id));
        System.out.println("Student added successfully!\n");
    }

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

    // Simple list showing only ID and Name (used by options 2 and 3)
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

    public static void viewStudent(ArrayList<Student> students, Scanner input) {
        if (students.isEmpty()) {
            System.out.println("No students in the gradebook yet.\n");
            return;
        }

        listStudents(students);
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

    public static void removeStudent(ArrayList<Student> students, Scanner input) {
        if (students.isEmpty()) {
            System.out.println("No students to remove.\n");
            return;
        }

        listStudents(students);
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
        System.out.println("Removed: " + removed.getName() + " (" + removed.getId() + ")\n");
    }

    // -------------------- File Operations --------------------

    public static void saveToFile(ArrayList<Student> students) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (Student s : students) {
                writer.println(s.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public static void loadFromFile(ArrayList<Student> students) {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return; // No file yet – that's fine
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (!line.isEmpty()) {
                    Student s = Student.fromFileString(line);
                    if (s != null) {
                        students.add(s);
                    }
                }
            }
            System.out.println("Loaded " + students.size() + " student(s) from file.\n");
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}