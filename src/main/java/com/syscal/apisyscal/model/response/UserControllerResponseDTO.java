package com.syscal.apisyscal.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserControllerResponseDTO {

    private Integer id;
    private String username;
    private String name;
    private String email;


}
