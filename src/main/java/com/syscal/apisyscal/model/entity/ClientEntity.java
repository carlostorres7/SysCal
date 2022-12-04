package com.syscal.apisyscal.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "clients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="pname")
    private String pname;

    @Column(name ="sname")
    private String sname;

    @Column(name ="plastname")
    private String plastname;

    @Column(name ="slastname")
    private String slastname;

    @Column(name ="cedula")
    private String cedula;

    @Column(name ="phone")
    private String phone;

    @Column(name ="email")
    private String email;

    @Column(name ="address")
    private String address;


}
