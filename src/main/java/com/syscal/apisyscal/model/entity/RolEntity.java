package com.syscal.apisyscal.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
public class RolEntity {

    @Id private Integer id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "permissions",
            joinColumns =
                    { @JoinColumn(name = "rol_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "main_id", referencedColumnName = "id") })
    private List<MainEntity> mains;

}
