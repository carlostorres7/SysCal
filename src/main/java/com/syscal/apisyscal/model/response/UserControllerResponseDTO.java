package com.syscal.apisyscal.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserControllerResponseDTO {

    private Integer id;
    private String username;
    private String name;
    private String email;
    @JsonProperty("rol_id")
    private Integer rolId;
    @JsonProperty("rol_name")
    private String RolName;


}
