package com.example.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class University implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String name;
    
    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    private List<Student> students = new ArrayList<>();
    
    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    private List<Course> courses = new ArrayList<>();
    
    
    
    
    
    // Setters getters ----
    
    public University() { }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public List<Student> getStudents() { return students; }
    public void setStudents(List<Student> students) { this.students = students; }
    
    public List<Course> getCourses() { return courses; }
    public void setCourses(List<Course> courses) { this.courses = courses; }
}
