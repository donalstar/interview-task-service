package com.tradeshift.model;

import com.sun.istack.NotNull;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Simple POJO to contain task attributes
 */

@XmlRootElement(name = "task")
public class Task {

    private int id;

    @NotNull
    private String name;

    private String attributes;
    private int status;
    private int assignedUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(int assignedUser) {
        this.assignedUser = assignedUser;
    }
}
