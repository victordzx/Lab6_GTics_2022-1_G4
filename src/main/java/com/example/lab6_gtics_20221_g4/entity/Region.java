package com.example.lab6_gtics_20221_g4.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "regions")
public class Region {
    @Id
    @Column(name = "region_id", nullable = false, precision = 22)
    private BigDecimal id;

    @Column(name = "region_name", length = 25)
    private String regionName;

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    //TODO Reverse Engineering! Migrate other columns to the entity
}