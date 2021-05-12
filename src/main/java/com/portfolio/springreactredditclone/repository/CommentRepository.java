package com.portfolio.springreactredditclone.repository;

import com.portfolio.springreactredditclone.model.Comment;
import com.portfolio.springreactredditclone.model.Post;
import com.portfolio.springreactredditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
