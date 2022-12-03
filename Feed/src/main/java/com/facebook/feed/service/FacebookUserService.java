package com.facebook.feed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

import com.facebook.feed.entity.UserPost;
import com.facebook.feed.repository.UserPostRepo;

@Service
public class FacebookUserService {
	
	@Autowired
	Facebook facebook;
 
	@Autowired
	UserPostRepo userPostRepo;
	
	@Value("${facebook.clientid}")
	String clientId;
	
	@Value("${facebook.clientsecret}")
	String clientSecret;
	
	String accessToken;
	public FacebookConnectionFactory createConnection()
	{
		return new FacebookConnectionFactory(clientId,clientSecret);
	}
	
	public String generateFacebookAuthrizeUrl()
	{
		OAuth2Parameters param=new OAuth2Parameters();
		param.setRedirectUri("http://localhost:8080/facebook");
		param.setScope("email");
		return createConnection().getOAuthOperations().buildAuthenticateUrl(param);
	}
	
	public void generateFacebookAccessToken(String code)
	{
		accessToken=createConnection().getOAuthOperations().exchangeForAccess(code, "http://localhost:8080/facebook", null).getAccessToken();
	}
	
	public List<Post> getUserPost()
	{
		 PagedList<Post> feed = new FacebookTemplate(accessToken).feedOperations().getFeed();
		 
		 feed.stream().forEach(post -> {
			 UserPost userP=new UserPost(post.toString());
			 userPostRepo.save(userP);
		 });
		 
		 return feed;
	}
	

}
