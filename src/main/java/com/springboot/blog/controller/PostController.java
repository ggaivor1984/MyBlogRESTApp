package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostDtoV2;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;

import static com.springboot.blog.utils.AppConstants.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Api(value = "CRUD API for blog posts")
@RestController
@RequestMapping
public class PostController {
    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @ApiOperation("Create API for blog posts")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/v1/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @ApiOperation("API to get all blog posts")
    @GetMapping("/api/v1/posts")
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIR, required = false) String sortDir
    ) {
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    @ApiOperation("API to get blog post by its id")
    @GetMapping("/api/v1/posts/{id}")
    public ResponseEntity<PostDto> getPostByIdV1(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

//    @GetMapping("/api/v2/posts/{id}")
//    public ResponseEntity<PostDtoV2> getPostByIdV2(@PathVariable(name = "id") long id) {
//        PostDto postDto = postService.getPostById(id);
//        PostDtoV2 postDtoV2 = new PostDtoV2();
//        postDtoV2.setId(postDto.getId());
//        postDtoV2.setTitle(postDto.getTitle());
//        postDtoV2.setDescription(postDto.getDescription());
//        postDtoV2.setContent(postDto.getContent());
//        postDtoV2.setComments(postDto.getComments());
//        List<String> tags = new ArrayList<>();
//        tags.add("Java");
//        tags.add("JWT");
//        tags.add("MyBlog");
//        postDtoV2.setTags(tags);
//
//        return ResponseEntity.ok(postDtoV2);
//    }

//    @GetMapping(value = "/api/posts/{id}", params = "version=1")
//    public ResponseEntity<PostDto> getPostByIdV1(@PathVariable(name = "id") long id) {
//        return ResponseEntity.ok(postService.getPostById(id));
//    }
//
//    @GetMapping(value = "/api/posts/{id}", params = "version=2")
//    public ResponseEntity<PostDtoV2> getPostByIdV2(@PathVariable(name = "id") long id) {
//        PostDto postDto = postService.getPostById(id);
//        PostDtoV2 postDtoV2 = new PostDtoV2();
//        postDtoV2.setId(postDto.getId());
//        postDtoV2.setTitle(postDto.getTitle());
//        postDtoV2.setDescription(postDto.getDescription());
//        postDtoV2.setContent(postDto.getContent());
//        postDtoV2.setComments(postDto.getComments());
//        List<String> tags = new ArrayList<>();
//        tags.add("Java");
//        tags.add("JWT");
//        tags.add("MyBlog");
//        postDtoV2.setTags(tags);
//
//        return ResponseEntity.ok(postDtoV2);
//    }

//    @GetMapping(value = "/api/posts/{id}", headers = "X-API-VERSION=1")
//    public ResponseEntity<PostDto> getPostByIdV1(@PathVariable(name = "id") long id) {
//        return ResponseEntity.ok(postService.getPostById(id));
//    }
//
//    @GetMapping(value = "/api/posts/{id}", headers = "X-API-VERSION=2")
//    public ResponseEntity<PostDtoV2> getPostByIdV2(@PathVariable(name = "id") long id) {
//        PostDto postDto = postService.getPostById(id);
//        PostDtoV2 postDtoV2 = new PostDtoV2();
//        postDtoV2.setId(postDto.getId());
//        postDtoV2.setTitle(postDto.getTitle());
//        postDtoV2.setDescription(postDto.getDescription());
//        postDtoV2.setContent(postDto.getContent());
//        postDtoV2.setComments(postDto.getComments());
//        List<String> tags = new ArrayList<>();
//        tags.add("Java");
//        tags.add("JWT");
//        tags.add("MyBlog");
//        postDtoV2.setTags(tags);
//
//        return ResponseEntity.ok(postDtoV2);
//    }

//    @GetMapping(value = "/api/posts/{id}", produces = "application/vnd.gaivor.v1+json")
//    public ResponseEntity<PostDto> getPostByIdV1(@PathVariable(name = "id") long id) {
//        return ResponseEntity.ok(postService.getPostById(id));
//    }
//
//    @GetMapping(value = "/api/posts/{id}", produces = "application/vnd.gaivor.v2+json")
//    public ResponseEntity<PostDtoV2> getPostByIdV2(@PathVariable(name = "id") long id) {
//        PostDto postDto = postService.getPostById(id);
//        PostDtoV2 postDtoV2 = new PostDtoV2();
//        postDtoV2.setId(postDto.getId());
//        postDtoV2.setTitle(postDto.getTitle());
//        postDtoV2.setDescription(postDto.getDescription());
//        postDtoV2.setContent(postDto.getContent());
//        postDtoV2.setComments(postDto.getComments());
//        List<String> tags = new ArrayList<>();
//        tags.add("Java");
//        tags.add("JWT");
//        tags.add("MyBlog");
//        postDtoV2.setTags(tags);
//
//        return ResponseEntity.ok(postDtoV2);
//    }

    @ApiOperation("Update API for blog posts")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/api/v1/posts/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") long id) {
        PostDto postResponse = postService.updatePost(postDto, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @ApiOperation("Delete API for blog posts(by id)")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/api/v1/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post with id " + id + " was successfully deleted from DB", HttpStatus.OK);
    }
}
