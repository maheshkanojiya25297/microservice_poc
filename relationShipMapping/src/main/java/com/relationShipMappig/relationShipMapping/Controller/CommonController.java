package com.relationShipMappig.relationShipMapping.Controller;

import com.relationShipMappig.relationShipMapping.DTO.AuthorDTO;
import com.relationShipMappig.relationShipMapping.DTO.CommentDTO;
import com.relationShipMappig.relationShipMapping.DTO.postDTO;
import com.relationShipMappig.relationShipMapping.model.Post;
import com.relationShipMappig.relationShipMapping.repository.CommentRepository;
import com.relationShipMappig.relationShipMapping.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/mapping")
public class CommonController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/get")
    private ResponseEntity<List<postDTO>> fetchPost() {
        log.info("fetchPost {}:");
        List<Post> post = postRepository.findAll();
        log.info("post: " + post);
        List<CommentDTO> comments = new ArrayList<>();
        List<AuthorDTO> authors = new ArrayList<>();

        List<postDTO> result = post.stream().map(post1 -> {
            post1.getComments().stream().map(comment -> {
                comments.add(CommentDTO.builder()
                        .commentText(comment.getCommentText())
                        .commentByAuther(
                                 AuthorDTO.builder()
                                .autherName(comment.getCommentByAuther()
                                .getAutherName())
                                .build()
                        )
                        .build());
                return comment;
            }).collect(Collectors.toList());

            return postDTO.builder()
                    .postDescription(post1.getPostDescription())
                    .postTitle(post1.getPostTitle())
                    .comments(comments)
                    .build();
        }).collect(Collectors.toList());
     return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/add/post")
    private ResponseEntity<Post> addPost(@RequestBody Post post) {
        log.info("addPost {} :");
        Post post1 = postRepository.save(post);
        log.info("post1:" + post1);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(post1);

    }
}
