package com.reporta.report_service.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtUser {

    private Integer id;
    private String email;
    private String tipoUser;

}
