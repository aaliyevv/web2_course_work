package com.example.subway.entity.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;

public class SubwayDTO {
    private long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    @Length(min = 12)
    private String description;

    @PositiveOrZero(message = "Average visitors must be a positive number or zero")
    @NotNull(message = "Input required, must be Positive or Zero")
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
        return "Subway{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", averageVisitorsPerMonths=" + averageVisitorsPerMonths +
                '}';
    }
}
