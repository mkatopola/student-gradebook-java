import java.util.ArrayList;
import java.util.Scanner;

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
                input.nextLine(); // clear bad input
                continue;
            }

            choice = input.nextInt();
            input.nextLine(); // clear the leftover newline

            if (choice == 1) {
                System.out.println("Add Student feature coming soon...\n");
            }
            else if (choice == 2) {
                System.out.println("Add Grade feature coming soon...\n");
            }
            else if (choice == 3) {
                listStudents(students);
            }
            else if (choice == 4) {
                System.out.println("View Student feature coming soon...\n");
            }
            else if (choice == 5) {
                System.out.println("Class Average feature coming soon...\n");
            }
            else if (choice == 6) {
                System.out.println("Remove Student feature coming soon...\n");
            }
            else if (choice == 7) {
                System.out.println("Goodbye!");
                break;
            }
            else {
                System.out.println("Invalid choice. Please try again.\n");
            }
        }

        input.close();
    }

    // -------------------- Methods --------------------

    public static void showMenu() {
        System.out.println("===== Student Gradebook =====");
        System.out.println("1. Add Student");
        System.out.println("2. Add Grade to Student");
        System.out.println("3. List All Students");
        System.out.println("4. View Single Student");
        System.out.println("5. Class Average");
        System.out.println("6. Remove Student");
        System.out.println("7. Exit");
    }

    public static void listStudents(ArrayList<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No students in the gradebook yet.\n");
            return;
        }

        System.out.println("\n----- All Students -----");
        for (Student s : students) {
            s.display();
        }
        System.out.println();
    }
}