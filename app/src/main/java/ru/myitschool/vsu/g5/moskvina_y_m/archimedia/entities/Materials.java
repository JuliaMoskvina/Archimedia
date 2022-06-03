package ru.myitschool.vsu.g5.moskvina_y_m.archimedia.entities;

public class Materials {
    public String content;
    public String u_name;
    public String name;

    public Materials(String name, String content, String u_name) {
        this.u_name = u_name;
        this.name = name;
        this.content = content;
    }

    public String getU_name() {
        return u_name;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

