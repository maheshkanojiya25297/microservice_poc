package com.relationShipMappig.relationShipMapping.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.relationShipMappig.relationShipMapping.DTO.AuthDetailsDTO;
import com.relationShipMappig.relationShipMapping.DTO.AuthorDTO;
import com.relationShipMappig.relationShipMapping.DTO.CommentDTO;
import com.relationShipMappig.relationShipMapping.DTO.postDTO;
import com.relationShipMappig.relationShipMapping.DTO.request.ServiceRequestBean;
import com.relationShipMappig.relationShipMapping.Service.AutherInfoService;
import com.relationShipMappig.relationShipMapping.Service.PostInfoService;
import com.relationShipMappig.relationShipMapping.model.Auther;
import com.relationShipMappig.relationShipMapping.model.AutherDetails;
import com.relationShipMappig.relationShipMapping.model.Comment;
import com.relationShipMappig.relationShipMapping.model.Post;
import com.relationShipMappig.relationShipMapping.repository.AutherDeatailsRepository;
import com.relationShipMappig.relationShipMapping.repository.AutherRepository;
import com.relationShipMappig.relationShipMapping.repository.CommentRepository;
import com.relationShipMappig.relationShipMapping.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @Autowired
    private AutherDeatailsRepository autherDeatailsRepository;

    @Autowired
    private AutherRepository autherRepository;

    @Autowired
    private PostInfoService postInfoService;


    @Autowired
    private AutherInfoService autherInfoService;


    @GetMapping("/get")
    private ResponseEntity<List<postDTO>> fetchPost() {
        log.info("fetchPost {}:");
        List<Post> post = postRepository.findAll();
        log.info("post: " + post);
        List<CommentDTO> comments = new ArrayList<>();
        List<AuthorDTO> authors = new ArrayList<>();
        List<AuthDetailsDTO> authorsDetails = new ArrayList<>();

        List<postDTO> result = post.stream().map(post1 -> {
            post1.getComments().stream().map(comment -> {
                comment.getCommentByAuther().getDetailsList().stream().map(autherDetails -> {
                    authorsDetails.add(AuthDetailsDTO.builder()
                            .id(autherDetails.getId())
                            .email(autherDetails.getEmail())
                            .contact(autherDetails.getContact())
                            .gender(autherDetails.getGender())
                            .createdBy(autherDetails.getCreatedBy())
                            .createdDate(autherDetails.getCreatedDate())
                            .lastModifiedBy(autherDetails.getLastModifiedBy())
                            .lastModifiedDate(autherDetails.getLastModifiedDate())
                            .build());
                    return autherDetails;
                }).collect(Collectors.toList());

                comments.add(CommentDTO
                        .builder()
                        .commentText(comment.getCommentText())
                        .commentByAuther(
                                AuthorDTO
                                        .builder()
                                        .autherLocation(comment.getCommentByAuther().getAutherLocation())
                                        .autherName(comment.getCommentByAuther().getAutherName())
                                        .detailsList(!authorsDetails.isEmpty()
                                                && authorsDetails != null ? authorsDetails : null)
                                        .build()
                        )
                        .build());
                return comment;
            }).collect(Collectors.toList());

            return postDTO.builder()
                    .postDescription(post1.getPostDescription())
                    .postTitle(post1.getPostTitle())
                    .comments(!comments.isEmpty() && comments != null ? comments : null)
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

    @PostMapping("/add/comment")
    private ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        log.info("addComment {} :");
        Comment response = commentRepository.save(comment);
        log.info("response:" + response);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @PostMapping("/add/auther")
    private ResponseEntity<Auther> addAuther(@RequestBody Auther auther) {
        log.info("addAuther {} :");
        Auther response = autherRepository.save(auther);
        log.info("response:" + response);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @PostMapping("/add/autherDeails")
    private ResponseEntity<AutherDetails> addAutherDetails(@RequestBody AutherDetails autherDetails) {
        log.info("addAutherDetails {} :");
        AutherDetails response = autherDeatailsRepository.save(autherDetails);
        log.info("response:" + response);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @GetMapping("/get/Post-info")
    private ResponseEntity<?> getPostInfo() {
        return ResponseEntity.ok(this.postInfoService.getPostInfo());
    }


    @GetMapping("/get/Post/info")
    private ResponseEntity<List<postDTO>> fetchAutherInfo() {
        List<Post> post = postRepository.findAll();
        log.info("post: " + post);
        List<CommentDTO> listOfComment = new ArrayList<>();
        List<AuthDetailsDTO> listOfAuthor = new ArrayList<>();

        List<postDTO> outPut = post.stream().map(fetchPostData -> {
                    fetchPostData.getComments().stream().map(fetchComment -> {
                                fetchComment.getCommentByAuther().getDetailsList().stream().map(
                                        fetchAutherDeatils -> {
                                            listOfAuthor.add(AuthDetailsDTO
                                                    .builder()
                                                    .id(fetchAutherDeatils.getId())
                                                    .email(fetchAutherDeatils.getEmail())
                                                    .gender(fetchAutherDeatils.getContact())
                                                    .lastModifiedDate(fetchAutherDeatils.getLastModifiedDate())
                                                    .lastModifiedBy(fetchAutherDeatils.getLastModifiedBy())
                                                    .createdDate(fetchAutherDeatils.getCreatedDate())
                                                    .createdBy(fetchAutherDeatils.getCreatedBy())
                                                    .contact(fetchAutherDeatils.getContact())
                                                    .build());

                                            return listOfAuthor;
                                        }
                                ).collect(Collectors.toList());
                                listOfComment
                                        .add(CommentDTO.builder()
                                                .commentText(fetchComment.getCommentText())
                                                .commentByAuther(AuthorDTO
                                                        .builder()
                                                        .autherName(fetchComment.getCommentByAuther().getAutherName())
                                                        .autherLocation(fetchComment.getCommentByAuther().getAutherLocation())
                                                        .detailsList(!listOfAuthor.isEmpty() && listOfAuthor != null ? listOfAuthor : null)
                                                        .build())
                                                .build());
                                return listOfComment;
                            }
                    ).collect(Collectors.toList());

                    return postDTO
                            .builder()
                            .postDescription(fetchPostData.getPostDescription())
                            .postTitle(fetchPostData.getPostTitle())
                            .comments(!listOfComment.isEmpty() && listOfComment != null ? listOfComment : null)
                            .build();
                }
        ).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.CREATED).body(outPut);
    }


    @GetMapping("/get/auther-info/multi")
    private ResponseEntity<?> getAutherInfo() {
        return ResponseEntity.ok(this.autherInfoService.getAutherInfo());
    }


    @GetMapping("/get/auther-info/single")
    private ResponseEntity<?> getSingleAutherInfo() {
        return ResponseEntity.ok(this.autherInfoService.getSingleAutherInfo());
    }


    @GetMapping("/get/auther-info/single-specific")
    private ResponseEntity<?> getSingleAutherInfoSpecific() {
        return ResponseEntity.ok(this.autherInfoService.getSingleAutherInfoSpecific());
    }

    @GetMapping("/get/auther-info/single-dto")
    private ResponseEntity<?> getSingleAutherInfoAll() {
        return ResponseEntity.ok(this.autherInfoService.getSingleAutherInfoAll());
    }


    @GetMapping("/get/auther-info/tupple")
    private ResponseEntity<?> getTupleData() {
        return ResponseEntity.ok(this.autherInfoService.getTupleData());
    }

    @PostMapping("/get/auther-info/tupple")
    private ResponseEntity<?> saveAutherData
            (@RequestBody ServiceRequestBean serviceRequestBean) throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.autherInfoService.saveAutherData(serviceRequestBean));

    }



}
