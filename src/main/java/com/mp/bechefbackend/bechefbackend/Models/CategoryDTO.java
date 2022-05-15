package com.mp.bechefbackend.bechefbackend.Models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "categories")
public class CategoryDTO implements Serializable{

    @Id
    @Column(length = 45)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
