package com.portfolio.springreactredditclone.repository;

import com.portfolio.springreactredditclone.model.Post;
import com.portfolio.springreactredditclone.model.User;
import com.portfolio.springreactredditclone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIDDesc(Post post, User user);
}
