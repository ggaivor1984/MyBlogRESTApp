package com.springboot.blog.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@ApiModel(description = "Post data container")
@Data
public class PostDto {

    @ApiModelProperty(value = "Blog Post id")
    private long id;

    @ApiModelProperty(value = "Blog Post title")
    @NotEmpty
    @Size(min = 2, message = "Post title should contain at least 2 characters")
    private String title;

    @ApiModelProperty(value = "Blog Post description")
    @NotEmpty
    @Size(min = 10, message = "Post description should contain at least 10 characters")
    private String description;

    @ApiModelProperty(value = "Blog Post content(body)")
    @NotEmpty
    private String content;

    @ApiModelProperty(value = "Exact Blog Post comments")
    private Set<CommentDto> comments;
}
