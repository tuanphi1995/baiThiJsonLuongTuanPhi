package baiThiJson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Tạo dữ liệu mẫu
        Student student1 = new Student(1, "Truong Gia Binh", Arrays.asList(
                new Subject("Math", Arrays.asList(10, 9, 8)),
                new Subject("Physics", Arrays.asList(7, 8, 9))
        ));

        Student student2 = new Student(2, "Le Truong Tung", Arrays.asList(
                new Subject("Chemistry", Arrays.asList(8, 9, 10)),
                new Subject("Biology", Arrays.asList(7, 7, 8))
        ));

        StudentData studentData = new StudentData(Arrays.asList(student1, student2));

        // Sử dụng Gson để chuyển đổi đối tượng thành JSON và ghi vào file
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("Student.json")) {
            gson.toJson(studentData, writer);
            System.out.println("Dữ liệu đã được ghi vào file Student.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
