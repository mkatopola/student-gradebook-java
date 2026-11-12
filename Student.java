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

    // Calculate average
    public double getAverage() {
        if (grades.isEmpty()) {
            return 0.0;
        }

        double sum = 0;
        for (double g : grades) {
            sum += g;
        }
        return sum / grades.size();
    }

    // Get letter grade based on average
    public String getLetterGrade() {
        double avg = getAverage();

        if (avg >= 90) {
            return "A";
        } else if (avg >= 80) {
            return "B";
        } else if (avg >= 70) {
            return "C";
        } else if (avg >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    // Display student info
    public void display() {
        System.out.println("ID: " + id 
            + " | Name: " + name 
            + " | Grades: " + grades 
            + " | Average: " + getAverage() 
            + " | Letter: " + getLetterGrade());
    }
}