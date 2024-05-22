package com.student_management.student_management.model;

import jakarta.persistence.*;
import java.util.Set;

/**
 * Represents a course in the system.
 */
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students;

    /**
     * Retrieves the ID of the course.
     *
     * @return The ID of the course
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the course.
     *
     * @param id The ID of the course
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the title of the course.
     *
     * @return The title of the course
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the course.
     *
     * @param title The title of the course
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the description of the course.
     *
     * @return The description of the course
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the course.
     *
     * @param description The description of the course
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
