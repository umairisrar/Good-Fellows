package com.example.goodfellows;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CreatePost extends Activity implements OnClickListener {

	EditText postStatus, postTags;
	Button post, hashTags, camera, addPic;
	CheckBox postCheck;
	String currentUser = "";
	
	private String[] arraySpinner;
	Spinner spinner;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_post);
		/*currentUser = "";
		Bundle gotBasket = getIntent().getExtras();
		currentUser = gotBasket.getString("user");*/
		
		this.arraySpinner = new String[] {
	            "Accident", "Traffic"
	        };
		spinner = (Spinner) findViewById(R.id.spCPHashTags);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	            android.R.layout.select_dialog_item, arraySpinner);
		spinner.setAdapter(adapter);
		
		postStatus = (EditText) findViewById(R.id.etPostStatus);
		postTags = (EditText) findViewById(R.id.etCPPostTags);
		post = (Button) findViewById(R.id.btCPPost);
		hashTags = (Button) findViewById(R.id.btCPHashTag);
		camera = (Button) findViewById(R.id.btCPCamera);
		//addPic = (Button) findViewById(R.id.btCPCamera);
		postCheck = (CheckBox)findViewById(R.id.cbPostAsProblem);
		post.setOnClickListener(this);
		hashTags.setOnClickListener(this);
		camera.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.btCPPost){
			if(postTags.getText().toString().length() == 0 ){
				// notify user
				Toast toast = Toast.makeText(this, "fill required fields",Toast.LENGTH_LONG);
				toast.show();
			} else{
				boolean status = true;
				try{
				GFDBHelper gfDB = new GFDBHelper(CreatePost.this);
		        gfDB.openConnection();
		        String place = "", problem = "";
		        if(postCheck.isChecked()){
		        	problem = "Problem";
		        }else{
		        	place = "Place";
		        }
		        gfDB.registerPost("", currentUser, "100", "text", place, problem, "", "", "", "1");
		        gfDB.closeConnection();
				} catch(Exception e){
					status = false;
				}
			}
		} else if(v.getId() == R.id.btCPHashTag){
			postTags.setText(postTags.getText().toString() + "#Accident");
		} else if(v.getId() == R.id.btCPCamera){
			
		} else if(v.getId() == R.id.btCPPost){
			
			
		}
	}

}
