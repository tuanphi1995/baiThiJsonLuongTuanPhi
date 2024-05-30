package baiThiJson;

import java.util.ArrayList;
import java.util.List;

public class StudentData {
    private List<Student> students;

    public StudentData() {
        // Constructor mặc định
        this.students = new ArrayList<>();
    }

    public StudentData(List<Student> students) {
        this.students = students;
    }

    // Getters và Setters
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
