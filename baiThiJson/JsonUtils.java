package baiThiJson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonUtils {
    private static final String FILE_PATH = "Student.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static StudentData loadData() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            return gson.fromJson(reader, StudentData.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new StudentData();
        }
    }

    public static void saveData(StudentData studentData) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(studentData, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
