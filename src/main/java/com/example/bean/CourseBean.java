package com.example.bean;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Course;
import com.example.model.Student;
import com.example.model.University;
import com.example.service.CourseService;
import com.example.service.StudentService;
import com.example.service.UniversityService;



@Named
@ViewScoped
public class CourseBean implements Serializable {
    @Inject
    private CourseService courseService;
    @Inject
    private UniversityService universityService;
    @Inject
    private StudentService studentService;
    
    private List<Course> courses;
    private Course course;
    private Integer id;
    private Integer selectedUniversityId;
    private List<Student> availableStudents = new ArrayList<>();
    private List<Integer> selectedStudentIds = new ArrayList<>();
    
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public Integer getSelectedUniversityId() {
        if (selectedUniversityId == null && getCourse().getUniversity() != null) {
            selectedUniversityId = course.getUniversity().getId();
        }
        return selectedUniversityId;
    }
    
    public void setSelectedUniversityId(Integer selectedUniversityId) {
        this.selectedUniversityId = selectedUniversityId;
    }
    
    public List<Integer> getSelectedStudentIds() {
        if (selectedStudentIds.isEmpty() && id != null && id != 0) {
            for (Student s : getCourse().getStudents()) {
                selectedStudentIds.add(s.getId());
            }
        }
        return selectedStudentIds;
    }
    public void setSelectedStudentIds(List<Integer> selectedStudentIds) {
        this.selectedStudentIds = selectedStudentIds;
    }
    
    
    

    public List<Course> getCourses() {
        if (courses == null) {
            courses = courseService.findAll();
        }
        return courses;
    }
    

    public Course getCourse() {
        if (course == null) {
            if (id != null && id != 0) {
                course = courseService.findById(id);
            } else {
                course = new Course();
            }
        }
        return course;
    }
    
    
    public List<University> getUniversities() {
        return universityService.findAll();
    }
    
    public List<Student> getAvailableStudents() {
        if (selectedUniversityId != null) {
            availableStudents = studentService.findByUniversityId(selectedUniversityId);
        } else {
            availableStudents = new ArrayList<>();
        }
        return availableStudents;
    }
    

    public void changeUniversity() {
        if (selectedUniversityId != null) {
            selectedStudentIds.clear();
            availableStudents = studentService.findByUniversityId(selectedUniversityId);
            getCourse().setStudents(new ArrayList<>());
        }
    }
    
    public String saveCourse() {
        if (selectedUniversityId != null) {
            University uni = universityService.findById(selectedUniversityId);
            course.setUniversity(uni);
        }
        List<Student> newStudents = new ArrayList<>();
        if (selectedStudentIds != null) {
            for (Student s : getAvailableStudents()) {
                if (selectedStudentIds.contains(s.getId())) {
                    newStudents.add(s);
                }
            }
        }
        course.setStudents(newStudents);
        
        if (course.getId() == 0) {
            courseService.create(course);
        } else {
            courseService.update(course);
        }
        return "courses?faces-redirect=true";
    }
    
    public String deleteCourse() {
        if (course != null) {
            courseService.delete(course.getId());
        }
        return "courses?faces-redirect=true";
    }
}
