package com.example.application.data.entity;
import javax.persistence.Entity;

import com.example.application.data.AbstractEntity;
import java.time.LocalDate;

@Entity
public class Rating extends AbstractEntity{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
