package com.example.goodfellows;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class NewsFeedPanel extends Activity {

	ArrayList<NewsFeedPanelPost> NFPArrayList;
	ListView NFPListView;
	NewFeedPanelAdapter NFPAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_feed_panel);
		
		NFPArrayList = new ArrayList<NewsFeedPanelPost>();
		
		NewsFeedPanelPost post = new NewsFeedPanelPost();
		
		post.likeCounter = "12";
		post.postAddedPictures = "added 1 pic";
		post.postStatus = "Accident";
		post.postLocation = "Islamabad";
		post.postTag = "#Accident";
		post.profileName = "abc";
		post.spamCounter = "1";
		post.profilePic = R.drawable.profile_pics;
		post.problemPic = R.drawable.problem;
		post.postProblemTagPic = R.drawable.traffic_sign;
		
		NFPArrayList.add(post);
		NFPArrayList.add(post);
		NFPArrayList.add(post);
		
		NFPListView  =(ListView)findViewById(R.id.listNewsFeedPanel);
		NFPAdapter = new NewFeedPanelAdapter(this, NFPArrayList);
		NFPListView.setAdapter(NFPAdapter);
		
	}

}
