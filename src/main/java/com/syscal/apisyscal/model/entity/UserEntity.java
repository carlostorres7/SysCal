package com.syscal.apisyscal.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    private String username;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn( name = "rol_id", referencedColumnName = "id")
    private RolEntity rol;

}
