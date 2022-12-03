package com.facebook.feed.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

@Service
public class FacebookServiceImp {
	
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
		createConnection().getOAuthOperations().exchangeForAccess(code, "http://localhost:8080/facebook", null).getAccessToken();
	}
	

}
