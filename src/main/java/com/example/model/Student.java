package com.example.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String firstName;
    private String lastName;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "university_id")
    private University university;
    
    @ManyToMany(mappedBy = "students")
    private List<Course> courses = new ArrayList<>();
    
    
    
    
    
    
    // Setters getters ----
    
    public Student() { }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public University getUniversity() { return university; }
    public void setUniversity(University university) { this.university = university; }
    
    public List<Course> getCourses() { return courses; }
    public void setCourses(List<Course> courses) { this.courses = courses; }
}
