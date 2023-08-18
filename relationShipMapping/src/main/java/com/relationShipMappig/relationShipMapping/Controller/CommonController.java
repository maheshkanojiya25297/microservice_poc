package com.relationShipMappig.relationShipMapping.Controller;

import com.relationShipMappig.relationShipMapping.model.Comment;
import com.relationShipMappig.relationShipMapping.model.Post;
import com.relationShipMappig.relationShipMapping.repository.CommentRepository;
import com.relationShipMappig.relationShipMapping.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/mapping")
public class CommonController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/get")
    private ResponseEntity<List<Post>> fetchPost() {
        log.info("fetchPost {}:");
        List<Post> post = postRepository.findAll();
        log.info("post: " + post);
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }

    @PostMapping("/add/post")
    private ResponseEntity<Post> addPost(@RequestBody Post post) {
        log.info("addPost {} :");
        Post post1 = postRepository.save(post);
        log.info("post1:" + post1);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(post1);

    }
}
