
package com.student_management.student_management.model;

import jakarta.persistence.*;
import java.util.Set;

/**
 * Represents a student in the system.
 */
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private int age;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses;

    /**
     * Retrieves the ID of the student.
     *
     * @return The ID of the student
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the student.
     *
     * @param id The ID of the student
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the student.
     *
     * @return The name of the student
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the student.
     *
     * @param name The name of the student
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the surname of the student.
     *
     * @return The surname of the student
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname of the student.
     *
     * @param surname The surname of the student
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Retrieves the email of the student.
     *
     * @return The email of the student
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the student.
     *
     * @param email The email of the student
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the age of the student.
     *
     * @return The age of the student
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the student.
     *
     * @param age The age of the student
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Retrieves the courses of the student.
     *
     * @return The courses of the student
     */
    public Set<Course> getCourses() {
        return courses;
    }

    /**
     * Sets the courses of the student.
     *
     * @param courses The courses of the student
     */
    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
