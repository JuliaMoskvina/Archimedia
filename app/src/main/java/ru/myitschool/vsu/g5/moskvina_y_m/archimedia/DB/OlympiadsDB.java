package ru.myitschool.vsu.g5.moskvina_y_m.archimedia.DB;

import java.io.Serializable;

public class OlympiadsDB implements Serializable {
    private long id;
    private String name;
    private String url;
    private long date;
    private String university;
    private int subject_id;


    public OlympiadsDB(long id, String name, String url, long date, String university, int subject_id) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.date = date;
        this.university = university;
        this.subject_id = subject_id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public long getDate() {
        return date;
    }

    public String getUniversity() {
        return university;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }
}
