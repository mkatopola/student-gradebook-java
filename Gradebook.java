import java.util.ArrayList;
import java.util.Scanner;

public class Gradebook {
    public static void main(String[] args) {
        // Create a list that will hold Student objects
        ArrayList<Student> students = new ArrayList<>();

        // Add some sample students
        Student s1 = new Student("Alice Johnson", "S001");
        s1.addGrade(95.0);
        s1.addGrade(88.0);

        Student s2 = new Student("Bob Smith", "S002");
        s2.addGrade(72.5);
        s2.addGrade(81.0);
        s2.addGrade(79.0);

        students.add(s1);
        students.add(s2);

        // Display all students using a loop
        System.out.println("===== Student Gradebook =====");
        for (Student s : students) {
            s.display();
        }

        // Calculate class average (example of using the list)
        double total = 0.0;
        int count = 0;
        for (Student s : students) {
            if (!s.getGrades().isEmpty()) {
                total += s.getAverage();
                count++;
            }
        }

        if (count > 0) {
            System.out.printf("%nClass Average: %.2f%n", total / count);
        }
    }
}