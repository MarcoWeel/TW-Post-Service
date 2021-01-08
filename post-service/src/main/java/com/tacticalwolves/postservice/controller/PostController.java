package com.tacticalwolves.postservice.controller;

import com.tacticalwolves.postservice.entity.Post;
import com.tacticalwolves.postservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin("*")
public class PostController {
    @Autowired
    private PostService service;

    @RolesAllowed({"ADMIN", "MEMBER"})
    @PostMapping("")
    public Post addPost(@RequestBody Post post){return service.SavePost(post);}

    @GetMapping("/{Id}")
    public Post findPostById(@PathVariable int Id){return service.GetPostById(Id);}

    @GetMapping("")
    public List<Post> findAllPosts(){return service.GetPosts();}

    @RolesAllowed({"ADMIN", "MEMBER"})
    @PutMapping("")
    public Post updatePost(@RequestBody Post post){return service.UpdatePost(post);}

    @RolesAllowed({"ADMIN", "MEMBER"})
    @DeleteMapping("/{Id}")
    public String deletePost(@PathVariable int Id){return service.DeletePostById(Id);}
}
