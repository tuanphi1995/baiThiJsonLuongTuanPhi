package baiThiJson;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagement {
    private static StudentData studentData = JsonUtils.loadData();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Display all students");
            System.out.println("2. Add a student");
            System.out.println("3. Edit student information");
            System.out.println("4. Search for a student by name or ID");
            System.out.println("5. Exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    displayAllStudents();
                    break;
                case "2":
                    addStudent();
                    break;
                case "3":
                    editStudent();
                    break;
                case "4":
                    searchStudent();
                    break;
                case "5":
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayAllStudents() {
        for (Student student : studentData.getStudents()) {
            System.out.println("ID: " + student.getId() + ", Name: " + student.getName());
            for (Subject subject : student.getSubjects()) {
                System.out.println("  Subject: " + subject.getName() + ", Scores: " + subject.getScores());
            }
        }
    }

    private static void addStudent() {
        Student newStudent = new Student();
        newStudent.setId(studentData.getStudents().size() + 1);

        System.out.print("Enter student's name: ");
        newStudent.setName(scanner.nextLine());

        List<Subject> subjects = new ArrayList<>();
        while (true) {
            System.out.print("Enter subject name (or 'done' to finish): ");
            String subjectName = scanner.nextLine();
            if (subjectName.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter scores separated by commas: ");
            String[] scoresInput = scanner.nextLine().split(",");
            List<Integer> scores = new ArrayList<>();
            for (String score : scoresInput) {
                scores.add(Integer.parseInt(score.trim()));
            }

            Subject subject = new Subject(subjectName, scores);
            subjects.add(subject);
        }
        newStudent.setSubjects(subjects);

        studentData.getStudents().add(newStudent);
        JsonUtils.saveData(studentData);
        System.out.println("Student added successfully!");
    }

    private static void editStudent() {
        System.out.print("Enter student ID to edit: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        Student student = studentData.getStudents().stream()
                .filter(s -> s.getId() == studentId)
                .findFirst()
                .orElse(null);

        if (student != null) {
            System.out.print("Enter new name for " + student.getName() + " (or press Enter to keep current): ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                student.setName(newName);
            }

            for (Subject subject : student.getSubjects()) {
                System.out.println("Editing scores for " + subject.getName());
                System.out.print("Enter new scores for " + subject.getName() + " (current: " + subject.getScores() + ", or press Enter to keep current): ");
                String newScores = scanner.nextLine();
                if (!newScores.isEmpty()) {
                    String[] scoresInput = newScores.split(",");
                    List<Integer> scores = new ArrayList<>();
                    for (String score : scoresInput) {
                        scores.add(Integer.parseInt(score.trim()));
                    }
                    subject.setScores(scores);
                }
            }

            JsonUtils.saveData(studentData);
            System.out.println("Student information updated successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void searchStudent() {
        System.out.print("Enter student name or ID to search: ");
        String searchTerm = scanner.nextLine();

        Student student = null;
        if (searchTerm.matches("\\d+")) {
            int studentId = Integer.parseInt(searchTerm);
            student = studentData.getStudents().stream()
                    .filter(s -> s.getId() == studentId)
                    .findFirst()
                    .orElse(null);
        } else {
            String studentName = searchTerm.toLowerCase();
            student = studentData.getStudents().stream()
                    .filter(s -> s.getName().toLowerCase().equals(studentName))
                    .findFirst()
                    .orElse(null);
        }

        if (student != null) {
            System.out.println("ID: " + student.getId() + ", Name: " + student.getName());
            for (Subject subject : student.getSubjects()) {
                System.out.println("  Subject: " + subject.getName() + ", Scores: " + subject.getScores());
            }
        } else {
            System.out.println("Student not found!");
        }
    }
}
