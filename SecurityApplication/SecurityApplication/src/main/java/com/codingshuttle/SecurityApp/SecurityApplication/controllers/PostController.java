package com.codingshuttle.SecurityApp.SecurityApplication.controllers;

import com.codingshuttle.SecurityApp.SecurityApplication.dto.PostDTO;
import com.codingshuttle.SecurityApp.SecurityApplication.entities.enums.Role;
import com.codingshuttle.SecurityApp.SecurityApplication.services.PostService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "posts")
//@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public List<PostDTO> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping(path = "/{postId}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN') OR hasAuthority('POST_VIEW')")
    @PreAuthorize("@postSecurity.isOwnerOfPost(#postId)")
    public PostDTO getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }

    @PostMapping
    public PostDTO createNewPost(@RequestBody PostDTO inputPost){
        return postService.createNewPost(inputPost);
    }

    @PutMapping(path = "{postId}")
    public PostDTO updatePostById(@RequestBody PostDTO inputPost, @PathVariable Long postId){
        return postService.updatePostById(inputPost, postId);
    }

}
