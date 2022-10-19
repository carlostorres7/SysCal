package com.syscal.apisyscal.infraestructure.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

}
