package com.example.subwaycustomer.entity.dto;

public class SubwayDTO {
    private long id;

    private String name;

    private String description;

    private Long averageVisitorsPerMonths;

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
        return "SubwayDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", averageVisitorsPerMonths=" + averageVisitorsPerMonths +
                '}';
    }
}
