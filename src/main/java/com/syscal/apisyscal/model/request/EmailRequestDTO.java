package com.syscal.apisyscal.model.request;

import lombok.Data;

@Data
public class EmailRequestDTO {

    private String content;
    private String email;
    private String subject;

}
