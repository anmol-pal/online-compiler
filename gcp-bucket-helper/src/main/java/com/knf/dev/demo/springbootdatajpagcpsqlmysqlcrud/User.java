package com.knf.dev.demo.springbootdatajpagcpsqlmysqlcrud;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // private String firstName;
    // private String lastName;

    @Column(name = "file_blob_id")
    String file_blob_id;
    @Column(name = "user_id")
    String user_id;
    @Column(name = "language")
    String language;
    @Column(name = "extension")
    String extension;

    public User() {
        super();
    }

    public User(String file_blob_id, String user_id, String language, String extension) {
        this.file_blob_id = file_blob_id;
        this.user_id = user_id;
        this.language = language;
        this.extension = extension;
    }

    public String getFile_blob_id() {
        return file_blob_id;
    }

    public void setFile_blob_id(String file_blob_id) {
        this.file_blob_id = file_blob_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

}