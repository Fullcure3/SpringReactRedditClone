package com.portfolio.springreactredditclone.repository;

import com.portfolio.springreactredditclone.model.Post;
import com.portfolio.springreactredditclone.model.Subreddit;
import com.portfolio.springreactredditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);
}
