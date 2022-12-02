package com.syscal.apisyscal.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.syscal.apisyscal.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private Integer id;
    private String username;
    private String name;
    private String email;
    @JsonProperty("rol_id")
    private Integer rolId;
    @JsonProperty("rol_name")
    private String RolName;

    public UserResponseDTO(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.username = userEntity.getUsername();
        this.name = userEntity.getName();
        this.rolId = userEntity.getRol().getId();
        this.RolName = userEntity.getRol().getName();
    }

}
