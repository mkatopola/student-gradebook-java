import java.util.ArrayList;

public class Student {
    private String id;
    private String name;
    private ArrayList<Double> grades;

    // Constructor
    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.grades = new ArrayList<>();
    }

    // Add a grade
    public void addGrade(double grade) {
        grades.add(grade);
    }

    // Display student info
    public void display() {
        System.out.println("ID: " + id + " | Name: " + name + " | Grades: " + grades);
    }
}