package com.example.bean;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

import com.example.model.Student;
import com.example.model.University;
import com.example.service.StudentService;
import com.example.service.UniversityService;

@Named
@ViewScoped
public class StudentBean implements Serializable  {
    @Inject
    private StudentService studentService;
    @Inject
    private UniversityService universityService;
    
    private List<Student> students;
    private Student student;
    private Integer id;
    private Integer selectedUniversityId;
    
    public List<Student> getStudents() {
        if (students == null) {
            students = studentService.findAll();
        }
        return students;
    }
    
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    
    
    
    
    
    
    public Student getStudent() {
        if (student == null) {
            if (id != null && id != 0) {
                student = studentService.findById(id);
            } else {
                student = new Student();
            }
        }
        return student;
    }


    
    public Integer getSelectedUniversityId() {
        if (selectedUniversityId == null && getStudent().getUniversity() != null) {
            selectedUniversityId = student.getUniversity().getId();
        }
        return selectedUniversityId;
    }


    public void setSelectedUniversityId(Integer selectedUniversityId) {
        this.selectedUniversityId = selectedUniversityId;
    }



    public List<University> getUniversities() {
        return universityService.findAll();
    }


    @Transactional
    public String saveStudent() {
        if (selectedUniversityId != null) {
            University uni = universityService.findById(selectedUniversityId);
            student.setUniversity(uni);
        }
        if (student.getId() == 0) {
            studentService.create(student);
        } else {
            studentService.update(student);
        }
        return "students?faces-redirect=true";
    }
    

    public String deleteStudent() {
        if (student != null) {
            studentService.delete(student.getId());
        }
        return "students?faces-redirect=true";
    }


    public String reloadStudent() {
        if (id != null && id != 0) {
            student = studentService.findById(id);
        }
        return null;
    }

}
