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
@Table(name = "permissions")
@AllArgsConstructor
@NoArgsConstructor
public class PermissionEntity {

    @Id private Integer id;

    @Column(name = "rol_id")
    private Integer rolId;

    @Column(name = "main_id")
    private Integer mainId;
}
