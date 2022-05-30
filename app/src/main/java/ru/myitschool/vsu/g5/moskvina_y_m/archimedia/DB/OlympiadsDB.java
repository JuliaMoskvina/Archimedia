package ru.myitschool.vsu.g5.moskvina_y_m.archimedia.DB;

import java.io.Serializable;

public class OlympiadsDB implements Serializable {
    private long id;
    private String name;
    private String url;
    private long date;
    private String university;
    private String subject_name;


    public OlympiadsDB(long id, String name, String url, long date, String university, String subject_name) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.date = date;
        this.university = university;
        this.subject_name = subject_name;
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

    public String getSubject_name() {
        return subject_name;
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

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }
}
