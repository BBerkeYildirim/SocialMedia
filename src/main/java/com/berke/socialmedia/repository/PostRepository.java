package com.berke.socialmedia.repository;

import com.berke.socialmedia.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> getAllByProfileId(Long id);
}
