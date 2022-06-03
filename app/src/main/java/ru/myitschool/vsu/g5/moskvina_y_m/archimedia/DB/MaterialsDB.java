package ru.myitschool.vsu.g5.moskvina_y_m.archimedia.DB;

import java.io.Serializable;

public class MaterialsDB implements Serializable {
    private long id;
    private String name;
    private String content;
    private long olympiads_id;
    private String u_name;

    public MaterialsDB(long id, String name, String content, long olympiads_id, String u_name) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.olympiads_id = olympiads_id;
        this.u_name = u_name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public long getOlympiads_id() {
        return olympiads_id;
    }

    public String getU_name(){
        return u_name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setOlympiads_id(long olympiads_id) {
        this.olympiads_id = olympiads_id;
    }
    public void setU_name(String u_name){
        this.u_name = u_name;
    }
}
