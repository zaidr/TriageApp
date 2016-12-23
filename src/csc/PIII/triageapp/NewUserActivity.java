package csc.PIII.triageapp;

import java.io.FileNotFoundException;
import java.io.IOException;

import csc.PII.triageapp.R;
import csc.PIII.backend.Login;
import csc.PIII.backend.User;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class NewUserActivity extends Activity {

	Login login;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_user);
		
		// Login object to be used for adding users/passwords
		try {
			login = new Login(this.getApplicationContext().getFilesDir());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_user, menu);
		return true;
	}
	
	private void showToast(String text) {
		CharSequence toastText = text;
		Context context = getApplicationContext();
		Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
	}
	
	public void createUser(View view) {
		// grab the username from the Username field
		EditText usernameEditT = (EditText) findViewById(R.id.usernameText);
		String username = usernameEditT.getText().toString();
		
		//grab the password from the password field
		EditText passwordEditT = (EditText) findViewById(R.id.passwordText);
		String password = passwordEditT.getText().toString();
		
		// grab the user type that was selected
		RadioGroup userGroup = (RadioGroup) findViewById(R.id.radioNurseOrPhys);
		RadioButton userTypeRadio = (RadioButton)
				findViewById(userGroup.getCheckedRadioButtonId());
		String userTypeText = userTypeRadio.getText().toString();
		
		// Scrubbing data when either of the two fields are left blank
		if (username.equals("") || password.equals("")) {
			showToast("Fill in both Username and Password");
		// Checking if the username entered already exists
		} else if (login.userExists(username)){
			showToast("This Username already exists");
		} else {
			
			if (userTypeText.equals("Nurse")) {
				login.addUser(username, password, User.USERTYPE_NURSE);
			} else if (userTypeText.equals("Physician")){
				login.addUser(username, password, User.USERTYPE_PHYS);
			}
			try {
				login.save(this.getApplicationContext());
			} catch (FileNotFoundException e) {
				showToast("file not found");
			}
			showToast("Sucessfully added user: " + username);
			// Taking the user back to the main activity
	    	startActivity(new Intent(this, MainActivity.class));
		}
    }

}
