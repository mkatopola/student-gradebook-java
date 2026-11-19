import java.util.ArrayList;

// ============================================================
//  Student class
//  Represents one student with a name, ID, and list of grades
// ============================================================
public class Student {

    // --- Variables ---
    private String name;
    private String id;
    private ArrayList<Double> grades;

    // Constructor – creates a new student
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

    // Add a grade (only accepts values between 0 and 100).
    // Returns true when the grade was added and false when it was rejected.
    public boolean addGrade(double grade) {
        if (grade >= 0.0 && grade <= 100.0) {
            grades.add(grade);
            return true;
        }
        return false;
    }

    // Calculate the average of all grades
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

    // Convert the average into a letter grade
    // Returns "N/A" when the student has no grades yet
    public String getLetterGrade() {
        if (grades.isEmpty()) {
            return "N/A";
        }

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

    // Display full details of the student
    public void display() {
        if (grades.isEmpty()) {
            System.out.printf("ID: %s | Name: %s | Grades: [] | Average: N/A%n",
                    id, name);
        } else {
            System.out.printf("ID: %s | Name: %s | Grades: %s | Average: %.2f (%s)%n",
                    id, name, grades.toString(), getAverage(), getLetterGrade());
        }
    }

    // Convert student data into a line for saving to a file.
    // Format: id|name|grade1|grade2|grade3,...
    // A pipe is used instead of a comma so names like "Katopola, Moffat" stay intact.
    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append("|").append(name);

        for (double g : grades) {
            sb.append("|").append(g);
        }
        return sb.toString();
    }

    // Create a Student object from a line in the file.
    // New files use the pipe format; older comma-separated files are still accepted.
    public static Student fromFileString(String line) {
        String delimiter = line.contains("|") ? "\\|" : ",";
        String[] parts = line.split(delimiter, -1);
        if (parts.length < 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
            return null;
        }

        Student student = new Student(parts[1], parts[0]); // name, id

        for (int i = 2; i < parts.length; i++) {
            if (parts[i].isEmpty()) {
                continue;
            }
            try {
                student.addGrade(Double.parseDouble(parts[i]));
            } catch (NumberFormatException e) {
                // skip invalid numbers
            }
        }
        return student;
    }
}