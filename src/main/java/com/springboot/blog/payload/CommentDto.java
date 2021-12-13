package com.springboot.blog.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ApiModel(description = "Blog post comments information")
@Data
public class CommentDto {

    @ApiModelProperty(value = "Comment id")
    private long id;

    @ApiModelProperty(value = "Name of the comment author")
    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    @ApiModelProperty(value = "Email of the comment author")
    @NotEmpty(message = "Email address can't be null or empty")
    @Email(message = "email address must be appropriate")
    private String email;

    @ApiModelProperty(value = "Body of a comment")
    @NotEmpty
    @Size(min = 10, message = "comment body must contain at least 10 characters")
    private String body;
}
