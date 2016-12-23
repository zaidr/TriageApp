package csc.PIII.triageapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import csc.PII.triageapp.R;
import csc.PIII.backend.User;

public class LookupActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lookup);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lookup, menu);
		return true;
	}

	private void showToast(String text) {
		CharSequence toastText = text;
		Context context = getApplicationContext();
		Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
	}
	
	public void displayPatient(View view){
		Intent intent = getIntent();
		User user = (User)intent.getSerializableExtra("user");
		
		EditText hcn = (EditText) findViewById(R.id.lookup_hcn);
		String healthCardNumber = hcn.getText().toString();
		
		if (!user.patientExists(healthCardNumber)){
			showToast("Please enter a valid health card number.");
		}
		else{
			TextView patient = (TextView) findViewById(R.id.display_patient);
			patient.setText(user.getPatient(healthCardNumber).fullString());
		}

	}

}