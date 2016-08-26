package com.tradeshift;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Simple POJO to contain task attributes
 */

@XmlRootElement(name = "task")
public class Task {

    private String name;
    private String attributes;

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
}
