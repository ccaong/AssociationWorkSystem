package com.example.gqsystem.bean;

/**
 * @author : devel
 * @date : 2020/3/4 14:50
 * @desc :
 */
public class HomePageBean {

    private String id;

    private String name;

    private int unRead;

    public HomePageBean() {
    }

    public HomePageBean(String id, String name, int unRead) {
        this.id = id;
        this.name = name;
        this.unRead = unRead;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnRead() {
        return unRead;
    }

    public void setUnRead(int unRead) {
        this.unRead = unRead;
    }
}
