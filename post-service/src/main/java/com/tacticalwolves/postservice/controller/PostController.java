package com.tacticalwolves.postservice.controller;

import com.tacticalwolves.postservice.entity.Post;
import com.tacticalwolves.postservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService service;

    @PostMapping("")
    public Post addPost(@RequestBody Post eventImage){return service.SavePost(eventImage);}

    @GetMapping("/{Id}")
    public Post findPostById(@PathVariable int Id){return service.GetPostById(Id);}

    @GetMapping("")
    public List<Post> findAllPosts(){return service.GetPosts();}

    @PutMapping("")
    public Post updatePost(@RequestBody Post post){return service.UpdatePost(post);}

    @DeleteMapping("/{Id}")
    public String deletePost(@PathVariable int Id){return service.DeletePostById(Id);}
}
