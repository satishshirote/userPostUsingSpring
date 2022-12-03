package com.facebook.feed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facebook.feed.entity.UserPost;

@Repository
public interface UserPostRepo extends JpaRepository<UserPost, Integer>{

}
