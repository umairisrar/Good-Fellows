package com.example.goodfellows;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ViewProfile extends Activity implements OnClickListener {

	Button editProfile;
	TextView dob, profession, maritalStatus, location;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_profile);
		
		editProfile = (Button)findViewById(R.id.btVPEdit);
		editProfile.setOnClickListener(this);
		
		dob = (TextView) findViewById(R.id.tvVPDOBInfo);
		profession = (TextView) findViewById(R.id.tvVPProfessionInfo);
		maritalStatus = (TextView) findViewById(R.id.tvVPMaritalStatusInfo);
		location = (TextView) findViewById(R.id.tvVPLocation);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.btVPEdit){
			
		}
	}

	
}
