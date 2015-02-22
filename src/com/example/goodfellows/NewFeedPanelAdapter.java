package com.example.goodfellows;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class NewFeedPanelAdapter extends BaseAdapter{

	private Activity activity;
	ArrayList<NewsFeedPanelPost> NFPArrayListData;
    private static LayoutInflater inflater;
    
    public NewFeedPanelAdapter(Activity a, ArrayList<NewsFeedPanelPost> d){
    	activity = a;
    	NFPArrayListData = d;
    	inflater=null;
    	//inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	
    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return NFPArrayListData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View customView = convertView;
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		customView = inflater.inflate(R.layout.news_feed_panel_post, parent,false);
		TextView likePost = (TextView) customView.findViewById(R.id.tvLikePost);
		TextView spamPost = (TextView) customView.findViewById(R.id.tvSpamPost);
		TextView comments = (TextView) customView.findViewById(R.id.tvCommentOnPost);
		TextView profileName = (TextView) customView.findViewById(R.id.tvProfileName);
		TextView addedPostPics = (TextView) customView.findViewById(R.id.tvAddedPhotosNo);
		TextView postTags = (TextView) customView.findViewById(R.id.tvPostTags);
		TextView postLocation = (TextView) customView.findViewById(R.id.tvPostDefaultLocation);
        Button profilePic = (Button) customView.findViewById(R.id.btNFPPProfilePic);
        Button postTypeTag = (Button) customView.findViewById(R.id.btPostTypeTag);
        Button postProblem = (Button) customView.findViewById(R.id.btPostPics);
		
		
        NewsFeedPanelPost post = new NewsFeedPanelPost();
        post = NFPArrayListData.get(position);
        
        likePost.setText(post.likeCounter);
        spamPost.setText(post.spamCounter);
        comments.setText("1");
        profileName.setText(post.profileName);
        addedPostPics.setText(post.postAddedPictures);
        postTags.setText(post.postTag);
        postLocation.setText(post.postLocation);
        profilePic.setBackgroundResource(post.profilePic);
        profilePic.setText("");
        postProblem.setBackgroundResource(post.problemPic);
        postProblem.setText("");
        postTypeTag.setBackgroundResource(post.postProblemTagPic);
        postTypeTag.setText("");
        
        return customView;
	}
}
