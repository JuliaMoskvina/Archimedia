package ru.myitschool.vsu.g5.moskvina_y_m.archimedia.entities;

public class Materials {

    public String name;
    public String content;

    public Materials( String name, String content) {

        this.name = name;
        this.content = content;
    }



    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
