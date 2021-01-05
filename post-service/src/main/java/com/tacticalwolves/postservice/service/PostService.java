package com.tacticalwolves.postservice.service;

import com.tacticalwolves.postservice.entity.Post;
import com.tacticalwolves.postservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository repository;

    public Post SavePost(Post post){
        return repository.save(post);
    }

    public List<Post> GetPosts(){
        return repository.findAll();
    }

    public Post GetPostById(int Id){
        return repository.findById(Id).orElse(null);
    }

    public String DeletePostById(int Id){
        repository.deleteById(Id);
        return "Post Deleted";
    }

    public Post UpdatePost(Post post){
        Post ExistingPost = GetPostById(post.getId());
        ExistingPost.setTitle((post.getTitle()));
        ExistingPost.setDescription(post.getDescription());
        return repository.save(ExistingPost);
    }

}
