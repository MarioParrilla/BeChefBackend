package com.mp.bechefbackend.bechefbackend.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class UserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 15, unique = true)
    private String username;

    @Column(length = 50, unique = true)
    private String email;

    @Column(length = 20)
    private String password;

    @Column()
    private Boolean isAdmin;

    @Column(nullable = false, length = 100)
    private String token;

    @Column(length = 100)
    private String description;

    @Column(length = 300)
    private String urlImg;

    public UserDTO() {
    }

    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserDTO(String username, String token, String description, String urlImg) {
        this.username = username;
        this.token = token;
        this.description = description;
    }

    public UserDTO(String username, String email, String password, Boolean isAdmin, String token, String description, String urlImg) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.token = token;
        this.description = description;
        this.urlImg = urlImg;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                ", token='" + token + '\'' +
                ", description='" + description + '\'' +
                ", urlImg='" + urlImg + '\'' +
                '}';
    }
}
