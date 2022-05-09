package com.mp.bechefbackend.bechefbackend.Models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "recipes")
public class RecipeDTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long id_autor;

    @Column(length = 50)
    private String name;

    @Column(length = 100)
    private String description;

    @Column(length = 20)
    private String category;

    @Column(length = 1000)
    private String steps;

    @Column(length = 300)
    private String urlImg;

    public Long getId_autor() {
        return id_autor;
    }

    public void setId_autor(Long id_autor) {
        this.id_autor = id_autor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    @Override
    public String toString() {
        return "RecipeDTO{" +
                "id=" + id +
                ", id_autor=" + id_autor +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", steps='" + steps + '\'' +
                '}';
    }
}
