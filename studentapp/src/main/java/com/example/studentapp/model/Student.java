package com.example.studentapp.model;

import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String course;

    public Student() {}

    public Student(Long id, String name, String course) {
        this.id = id;
        this.name = name;
        this.course = course;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCourse() { return course; }
}