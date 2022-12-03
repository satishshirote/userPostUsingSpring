package com.facebook.feed.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="userPost")
public class UserPost {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer userId;
	@Column(name="post")
	String post;
	public UserPost(String post) {
		super();
		this.post = post;
	}
	
	

}
