package com.facebook.feed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Post;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.facebook.feed.service.FacebookUserService;

@RestController
public class FacebookUser {
	
	@Autowired
	FacebookUserService facebookUserService;
	


	
	@GetMapping("/authUrl")
	public String generateFacebbokAuthUrl()
	{
		return facebookUserService.generateFacebookAuthrizeUrl();
	}
	
	@GetMapping("/facebook")
	public void accessToken(@RequestParam("code") String code)
	{
		facebookUserService.generateFacebookAccessToken(code);
	}
	
	@GetMapping("/userPost")
	public List<Post> getUserPost()
	{
		return facebookUserService.getUserPost();
	}

}
