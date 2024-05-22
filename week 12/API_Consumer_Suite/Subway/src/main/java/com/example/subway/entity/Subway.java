package com.example.subway.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "subways")
public class Subway {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;
    private Long averageVisitorsPerMonths;

    public Subway(String name, String description, Long averageVisitorsPerMonths) {
        this.name = name;
        this.description = description;
        this.averageVisitorsPerMonths = averageVisitorsPerMonths;
    }

    public Subway() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Long getAverageVisitorsPerMonths() {
        return averageVisitorsPerMonths;
    }

    public void setAverageVisitorsPerMonths(Long averageVisitorsPerMonths) {
        this.averageVisitorsPerMonths = averageVisitorsPerMonths;
    }

    @Override
    public String toString() {
        return "Subway{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", averageVisitorsPerMonths=" + averageVisitorsPerMonths +
                '}';
    }
}
