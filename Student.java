import java.util.ArrayList;

public class Student {
    // --- Variables (fields) ---
    private String name;
    private String id;
    private ArrayList<Double> grades;

    // Constructor
    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.grades = new ArrayList<>();
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Double> getGrades() {
        return grades;
    }

    // Add a grade (with validation)
    public void addGrade(double grade) {
        if (grade >= 0.0 && grade <= 100.0) {
            grades.add(grade);
        } else {
            System.out.println("Invalid grade. Must be between 0 and 100.");
        }
    }

    // Calculate average using a loop and expression
    public double getAverage() {
        if (grades.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        for (double g : grades) {   
            sum += g;     
        }
        return sum / grades.size();
    }

    // Convert average to letter grade (conditionals)
    public String getLetterGrade() {
        double avg = getAverage();

        if (avg >= 90.0) {
            return "A";
        } else if (avg >= 80.0) {
            return "B";
        } else if (avg >= 70.0) {
            return "C";
        } else if (avg >= 60.0) {
            return "D";
        } else {
            return "F";
        }
    }

    // Nice display of the student
    public void display() {
        System.out.printf("ID: %s | Name: %s | Grades: %s | Average: %.2f (%s)%n",
                id, name, grades.toString(), getAverage(), getLetterGrade());
    }
    
        // Convert student data into a line for the file
    // Format: id,name,grade1,grade2,grade3,...
    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(",").append(name);

        for (double g : grades) {
            sb.append(",").append(g);
        }
        return sb.toString();
    }

    // Create a Student object from a line in the file
    public static Student fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length < 2) {
            return null;
        }

        Student student = new Student(parts[1], parts[0]); // name, id

        for (int i = 2; i < parts.length; i++) {
            try {
                student.addGrade(Double.parseDouble(parts[i]));
            } catch (NumberFormatException e) {
                // skip invalid grade
            }
        }
        return student;
    }
}