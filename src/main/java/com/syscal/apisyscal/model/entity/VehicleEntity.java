package com.syscal.apisyscal.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name="vehicles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String brand;

    private String reference;

    private String placa;

    private String color;

    @Column(name = "createdbyid")
    private Integer createdbyid;

    @CreationTimestamp
    @Column(name = "createdat")
    private Date createdat;

    @UpdateTimestamp
    @Column(name = "updatedat")
    private Date updatedat;

}
