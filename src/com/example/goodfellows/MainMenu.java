
package com.example.goodfellows;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenu extends Activity implements OnClickListener {

	Button newsFeed, map, createPost, notification, profilePic;
	Button search, menuOptions;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		newsFeed = (Button) findViewById(R.id.btNewsFeed);
		map = (Button) findViewById(R.id.btMaps);
		createPost = (Button) findViewById(R.id.btCreatePost);
		notification = (Button) findViewById(R.id.btNotifications);
		profilePic = (Button) findViewById(R.id.btProfilePic);
		search = (Button) findViewById(R.id.btbtSearch);
		menuOptions = (Button) findViewById(R.id.btOptions);
		
		newsFeed.setOnClickListener(this);
		map.setOnClickListener(this);
		createPost.setOnClickListener(this);
		notification.setOnClickListener(this);
		profilePic.setOnClickListener(this);
		search.setOnClickListener(this);
		menuOptions.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.btNewsFeed){
			Intent intent = new Intent("com.example.goodfellows.NEWSFEEDPANEL");
			startActivity(intent);
		} else if(v.getId() == R.id.btMaps){
			
		} else if(v.getId() == R.id.btCreatePost){
			Intent intent = new Intent("com.example.goodfellows.CREATEPOST");
			startActivity(intent);
		}else if(v.getId() == R.id.btNotifications){
			
		}else if(v.getId() == R.id.btProfilePic){
			Intent intent = new Intent("com.example.goodfellows.VIEWPROFILE");
			startActivity(intent);
		}else if(v.getId() == R.id.btbtSearch){
			
		}else if(v.getId() == R.id.btOptions){
			
		}
	}

}
