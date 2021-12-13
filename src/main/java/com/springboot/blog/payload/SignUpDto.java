package com.springboot.blog.payload;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(description = "Sign up data container")
@Data
public class SignUpDto {
    private String name;
    private String username;
    private String email;
    private String password;
}
