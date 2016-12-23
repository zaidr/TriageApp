package csc.PIII.triageapp;

import java.io.IOException;

import csc.PII.triageapp.R;
import csc.PIII.backend.Login;
import csc.PIII.backend.Nurse;
import csc.PIII.backend.PatientManager;
import csc.PIII.backend.Physician;
import csc.PIII.backend.User;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	Login login;
	PatientManager pm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		try {
			login = new Login(getApplicationContext().getFilesDir());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	private void showToast(String text) {
		CharSequence toastText = text;
		Context context = getApplicationContext();
		Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
	}
	
	public void login(View view) {
		// grab the username from the Username field
		EditText usernameEditT = (EditText) findViewById(R.id.usernameText);
		String username = usernameEditT.getText().toString();
		
		//grab the password from the password field
		EditText passwordEditT = (EditText) findViewById(R.id.passwordText);
		String password = passwordEditT.getText().toString();
		
		if (login.validCredentials(username, password)) {
			try {
				pm = new PatientManager(getApplicationContext().getFilesDir());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/* Checks if user is a patient or a physician, and passes on
			 * correct user type into correct activity. */
			if (login.getUserType(username).equals(User.USERTYPE_NURSE)) {
				Intent intent = new Intent(this, MenuActivity.class);
				intent.putExtra("nurse", new Nurse(username, password,
						pm.getPatients()));
				startActivity(intent);
			} else if (login.getUserType(username).equals(User.USERTYPE_PHYS)){
				Intent intent = new Intent(this, PhysMenuActivity.class);
				intent.putExtra("phys", new Physician(username, password,
						pm.getPatients()));
				startActivity(intent);
			}
			
		} else {
			showToast("Wrong Username or Password - try again");
		}
    }

}
