package com.syscal.apisyscal.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private VehicleEntity vehicle;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "technical_id", referencedColumnName = "id")
    private UserEntity technical;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private ClientEntity client;

    private String description;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "status", referencedColumnName = "id")
    private CategoryEntity status;

    @Column(name = "createdbyid")
    private Integer createdbyid;

    @CreationTimestamp
    @Column(name = "createdat")
    private Date createdat;

    @UpdateTimestamp
    @Column(name = "updatedat")
    private Date updatedat;

}
