package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    CommentRepository commentRepository;
    PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);

        return mapToDto(newComment);
    }

    public CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setBody(comment.getBody());
        commentDto.setEmail(comment.getEmail());
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        return commentDto;
    }

    public Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        return comment;
    }
}
