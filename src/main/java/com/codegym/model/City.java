package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name ="city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameCity;

    @Min(0)
    private Long areaCity;

    @Min(0)
    private Long population;

    @Min(0)
    private Long GDP;

    private String description;
    @ManyToOne
    @JoinColumn(name ="country_id")

    private Country country;


    public City() {
    }

    public City(String nameCity, @Min(0) Long areaCity,@Min(0) Long population, @Min(0) Long GDP, String description, Country country) {
        this.nameCity = nameCity;
        this.areaCity = areaCity;


        this.population = population;
        this.GDP = GDP;
        this.description = description;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public Long getAreaCity() {
        return areaCity;
    }

    public void setAreaCity(Long areaCity) {
        this.areaCity = areaCity;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Long getGDP() {
        return GDP;
    }

    public void setGDP(Long GDP) {
        this.GDP = GDP;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
