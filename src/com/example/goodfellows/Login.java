/**
 * 
 */
package com.example.goodfellows;

import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author Administrator
 *
 */
public class Login extends Activity implements OnClickListener{

	Button login, signup;
	EditText user, password;
	@Override                          
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		login = (Button) findViewById(R.id.btLogin);
		signup = (Button) findViewById(R.id.btSignUp);
		user = (EditText) findViewById(R.id.etUserName);
		password = (EditText) findViewById(R.id.etPassword);
		
		login.setOnClickListener(this);
		signup.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(v.getId() == R.id.btLogin){
			if(user.getText().toString().length() == 0 || password.getText().toString().length() == 0){
				// notify user
				Toast toast = Toast.makeText(this, "invalid user name & password",Toast.LENGTH_LONG);
				toast.show();
			}else {
				String status = "1";
				//String status = validateUser(user.getText().toString(), password.getText().toString());
				
				if(status.equals("")){

					Toast toast = Toast.makeText(this, "invalid user name & password",Toast.LENGTH_LONG);
					toast.show();
				} else {
					Toast toast = Toast.makeText(this, "successfully sign in",Toast.LENGTH_LONG);
					toast.show();
					Intent intent = new Intent("com.example.goodfellows.MAINMENU");
					Bundle basket = new Bundle();
					basket.putString("user", user.getText().toString());
					intent.putExtras(basket);
					startActivity(intent);
				}
			}
		} else if(v.getId() == R.id.btSignUp){
			Intent intent = new Intent("com.example.goodfellows.SIGNUP");
			startActivity(intent);
		}
	}
	
	public String validateUser(String userID, String password){
		
		String status = " ";
		try{
		GFDBHelper gfDB = new GFDBHelper(Login.this);
        gfDB.openConnection();
        gfDB.validateUser(userID , password);
        gfDB.closeConnection();
		} catch(Exception e){
			status = "";
		}
		return status;
		
	}
	
	public void loginUser(View view){
        // Get Email Edit View Value
		String usser = user.getText().toString();
        // Get Password Edit View Value
        String pass = password.getText().toString();
     // Instantiate Http Request Param Object
        RequestParams params = new RequestParams();
     // When Email Edit View and Password Edit View have values other than Null
        if(Utility.isNotNull(usser) && Utility.isNotNull(pass)){
            // When Email entered is Valid
            //if(Utility.validate(usser))
        	{
                // Put Http parameter username with value of Email Edit View control
                params.put("username", usser);
                // Put Http parameter password with value of Password Edit Value control
                params.put("password", password);
                // Invoke RESTful Web Service with Http parameters
                invokeWS(params);
            } 
            // When Email is invalid
            //else
            {
                //Toast.makeText(getApplicationContext(), "Please enter valid user pattern", Toast.LENGTH_LONG).show();
            }
        } else{
            Toast.makeText(getApplicationContext(), "Please fill the form, don't leave any field blank", Toast.LENGTH_LONG).show();
        }
	}
	
	public void invokeWS(RequestParams params){
	
		// Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://192.168.2.2:9999/useraccount/login/dologin",params ,new AsyncHttpResponseHandler() {
        	// When the response returned by REST has Http response code '200'
        	@Override
            public void onSuccess(String response) {
        		try {
                    // JSON Object
                   JSONObject obj = new JSONObject(response);
                // When the JSON response has status boolean value assigned with true
                   if(obj.getBoolean("status")){
                       Toast.makeText(getApplicationContext(), "You are successfully logged in!", Toast.LENGTH_LONG).show();
                       // Navigate to Home screen
                       navigatetoMainMenu();
                   } 
                   // Else display error message
                   else{
                       //errorMsg.setText(obj.getString("error_msg"));
                       Toast.makeText(getApplicationContext(), obj.getString("error_msg"), Toast.LENGTH_LONG).show();
                   } 
        		}catch (JSONException e) {
                       // TODO Auto-generated catch block
                       Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                       e.printStackTrace();
   
                }
        	}// On success Ends
        
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
	
	}//invokeWS
	
	public void navigatetoMainMenu(){
        Intent homeIntent = new Intent(getApplicationContext(),MainMenu.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }
	
	public void navigatetoRegisterActivity(View view){
        Intent loginIntent = new Intent(getApplicationContext(),SignUp.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(loginIntent);
    }
	
	
}
