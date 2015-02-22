package com.example.goodfellows;

import java.util.Calendar;

import com.loopj.android.http.*;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class SignUp extends Activity implements OnClickListener {

	static AsyncHttpClient client = new AsyncHttpClient();
    // Email Edit View Object
    EditText userID, userName, password, profession;
    RadioGroup radioGroup;
    String DOB, locationLat, locationLong, maritalStatus;
    Button signin, defaultLocation;
    // Password Edit View Object
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up);
		DOB = locationLat = locationLong = "";
		userName = (EditText)findViewById(R.id.etSUProfileName);
		password = (EditText)findViewById(R.id.etSUPassword);
		userID = (EditText)findViewById(R.id.etSUPhoneNumber);
		profession = (EditText)findViewById(R.id.etSUProfession);
		//dateOfBirth = (DatePicker) findViewById(R.id.etSUDOB);
		radioGroup = (RadioGroup) findViewById(R.id.etSUMaritalStatus);
		
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {	
                // find which radio button is selected
                if(checkedId == R.id.rSingle) {
                   maritalStatus = "0";
                } else if(checkedId == R.id.rMarried) {
                   maritalStatus = "1";
                } 
            }
        });
		
        signin = (Button) findViewById(R.id.btSUlogin);
        signin.setOnClickListener(this);
        defaultLocation = (Button) findViewById(R.id.btSUDefaultLocation);
        defaultLocation.setOnClickListener(this);
        setCurrentDate();
        
	}
	
	private void setCurrentDate(){
		final Calendar calendar = Calendar.getInstance();
		 int year = calendar.get(Calendar.YEAR);
		 int month = calendar.get(Calendar.MONTH);
		 int day = calendar.get(Calendar.DAY_OF_MONTH);
		 setDefaultValue();
		 //dateOfBirth.init(year, month, day, null);
	}
	
	public void registerUser(){
        // Get NAme ET control value
        String userName = this.userName.getText().toString();
        String userID = this.userID.getText().toString();
        String password = this.password.getText().toString();
        String marital = this.maritalStatus;
        String profession = this.profession.getText().toString();
        String dob = this.DOB;
        String locLat = this.locationLat;
        String locLong = this.locationLong;
        
        // Instantiate Http Request Param Object
        RequestParams params = new RequestParams();
        // When Name Edit View, Email Edit View and Password Edit View have values other than Null
        if(Utility.isNotNull(userName) && Utility.isNotNull(userID) && Utility.isNotNull(password)
        	&& Utility.isNotNull(marital) && Utility.isNotNull(profession)){
            // When Email entered is Valid
           // if(Utility.validate(userID))
        	{
                // Put Http parameter name with value of Name Edit View control
            	params.put("userID", userID);
            	params.put("userName", userName);
                params.put("password", password);
                params.put("marital", marital);
                params.put("profession", profession);
                params.put("dob", dob);
                params.put("locLat", locLat);
                params.put("locLong", locLong);
                
                // Invoke RESTful Web Service with Http parameters
                invokeWS(params);
            }
            // When Email is invalid
            //else
        	{
             //   Toast.makeText(getApplicationContext(), "Please enter valid username", Toast.LENGTH_LONG).show();
            }
        } 
        // When any of the Edit View control left blank
        else{
            Toast.makeText(getApplicationContext(), "Please fill the form, don't leave any field blank", Toast.LENGTH_LONG).show();
        }
    }
	
	private void setDefaultValue(){
		locationLat = "33.655885";
		locationLong = "locationLong";
	}
	
	public void invokeWS(RequestParams params){
        // Show Progress Dialog 
        //prgDialog.show();
        // Make RESTful webservice call using AsyncHttpClient object
        client.get("http://192.168.1.20:8080/useraccount/fellow/signup",params ,new AsyncHttpResponseHandler() {
            // When the response returned by REST has Http response code '200'
             @Override
             public void onSuccess(String response) {
                // Hide Progress Dialog
                 //prgDialog.hide();
                 try {
                          // JSON Object
                         JSONObject obj = new JSONObject(response);
                         // When the JSON response has status boolean value assigned with true
                         if(obj.getBoolean("status")){
                        	 Toast.makeText(getApplicationContext(), "registered!", Toast.LENGTH_LONG).show();
                             // Set Default Values for Edit View controls
                             setDefaultValues();
                             //Entries in client DB
                             boolean status = insertUserInClient(userID.getText().toString(), userName.getText().toString(), password.getText().toString()
                            		 , maritalStatus, profession.getText().toString(), DOB, locationLat, locationLong);
                             if(status == true){
                            	// Display successfully registered message using Toast
                                 Toast.makeText(getApplicationContext(), "You are successfully registered!", Toast.LENGTH_LONG).show();
                             }
                         } 
                         // Else display error message
                         else{
                             //errorMsg.setText(obj.getString("error_msg"));
                             Toast.makeText(getApplicationContext(), obj.getString("error_msg"), Toast.LENGTH_LONG).show();
                         }
                 } catch (JSONException e) {
                     // TODO Auto-generated catch block
                     Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                     e.printStackTrace();
 
                 }
             }
             // When the response returned by REST has Http response code other than '200'
             @Override
             public void onFailure(int statusCode, Throwable error,
                 String content) {
                 // Hide Progress Dialog
                 //prgDialog.hide();
                 // When Http response code is '404'
                 if(statusCode == 404){
                     Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                 } 
                 // When Http response code is '500'
                 else if(statusCode == 500){
                     Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                 } 
                 // When Http response code other than 404, 500
                 else{
                     Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
                 }
             }
         });
    }
	
	public void navigatetoLoginActivity(View view){
        Intent loginIntent = new Intent(getApplicationContext(),Login.class);
        // Clears History of Activity
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(loginIntent);
    }
	
	public void setDefaultValues(){
        userID.setText("");
        userName.setText("");
        password.setText("");
        maritalStatus = "";
        profession.setText("");
        DOB = locationLat = locationLong = "";
        
    }
	
	public boolean insertUserInClient(String userID, String userName, String password, String maritalStatus, String profession, String dob
			, String locLat, String locLong){
		
		boolean status = true;
		try{
		GFDBHelper gfDB = new GFDBHelper(SignUp.this);
        gfDB.openConnection();
        gfDB.registerUser(userID, userName, password);
        //gfDB.registerFellow(userID, userName, locLat, locLong, profession, maritalStatus, "", "", "", "");
        gfDB.closeConnection();
		} catch(Exception e){
			status = false;
		}
		return status;
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(v.getId()== R.id.btSUlogin)
		{
			if(userID.getText().toString().length() == 0 || userName.getText().toString().length() == 0
					|| profession.getText().toString().length() == 0 || password.getText().toString().length() == 0
					|| locationLat.length() == 0 || locationLong.length() == 0){
				// notify user
				Toast toast = Toast.makeText(this, "fill required fields",Toast.LENGTH_LONG);
				toast.show();
			} else{
				boolean status = true;
				//status = insertUserInClient(this.userID.getText().toString(), this.userName.getText().toString(), this.password.getText().toString()
		         //  		 , this.maritalStatus, this.profession.getText().toString(), "", locationLat, locationLong);
				if(status = true){
					Toast toast = Toast.makeText(this, "successfully sign up",Toast.LENGTH_LONG);
					toast.show();
				} else {
					Toast toast = Toast.makeText(this, "invalid user name password",Toast.LENGTH_LONG);
					toast.show();
				}
			}
			//registerUser();
	        
	        
		} else if(v.getId()== R.id.btSUDefaultLocation)
		{
			Maps getLocation = new Maps();
			getLocation.getCoordinates();
		}
		
	}

	
}
