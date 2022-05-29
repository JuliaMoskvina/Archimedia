package ru.myitschool.vsu.g5.moskvina_y_m.archimedia.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Olympiads {
    public int id;
    public String name;
    public String url;
    public long date;
    public String university;
    public int subj_id;
    public List<Materials> materials;

    public Olympiads(int id, String name, String url, long date, String university, int subj_id, List<Materials> materials) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.date = date;
        this.university = university;
        this.subj_id = subj_id;
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

    public Date getDate() {

        return new Date(date);
    }

    public String getUniversity() {
        return university;
    }

    public int getSubj_id() {
        return subj_id;
    }

    public List<Materials> getMaterials() {
        return materials;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDate(Date date) {

        this.date = date.getTime();
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setSubj_id(int subj_id) {
        this.subj_id = subj_id;
    }

    public void setMaterials(List<Materials> materials) {
        this.materials = materials;
    }
}
