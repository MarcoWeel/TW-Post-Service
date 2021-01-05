package com.tacticalwolves.postservice.repository;

import com.tacticalwolves.postservice.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
