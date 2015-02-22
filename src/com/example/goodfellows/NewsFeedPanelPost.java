package com.example.goodfellows;

public class NewsFeedPanelPost {

	public String  profileName, postAddedPictures, postTag, postStatus,
	postLocation, likeCounter, spamCounter;
	int profilePic , problemPic, postProblemTagPic;
	
	public NewsFeedPanelPost(){
		profileName = postAddedPictures = postTag = postStatus =
		postLocation = likeCounter = spamCounter = null;
		profilePic =  problemPic =  postProblemTagPic = 0;
	}
}
