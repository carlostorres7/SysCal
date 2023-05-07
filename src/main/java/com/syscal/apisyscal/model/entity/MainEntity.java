package com.syscal.apisyscal.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Data
@Table(name = "mains")
@AllArgsConstructor
@NoArgsConstructor
public class MainEntity {

    @Id private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;

    @Column(name = "title")
    private String title;

    @Column(name = "icon")
    private String icon;

    @Column(name = "hidden")
    private Boolean hidden;

}
