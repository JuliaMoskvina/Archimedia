package ru.myitschool.vsu.g5.moskvina_y_m.archimedia.entities;

import java.util.List;

public class Olympiads {
    public int id;
    public String name;
    public String url;
    public String date;
    public String university;
    public List <Subjects> subjects;
    public List <Materials> materials;

    public Olympiads(int id, String name, String url, String date, String university, List<Subjects> subjects, List<Materials> materials) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.date = date;
        this.university = university;
        this.subjects = subjects;
        this.materials = materials;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getDate() {
        return date;
    }

    public String getUniversity() {
        return university;
    }

    public List<Subjects> getSubjects() {
        return subjects;
    }

    public List<Materials> getMaterials() {
        return materials;
    }
}
