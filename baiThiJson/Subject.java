package baiThiJson;

import java.util.List;

public class Subject {
    private String name;
    private List<Integer> scores;

    public Subject(String name, List<Integer> scores) {
        this.name = name;
        this.scores = scores;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public void setScores(List<Integer> scores) {
        this.scores = scores;
    }
}
