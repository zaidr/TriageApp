package csc.PIII.triageapp;

import csc.PII.triageapp.R;
import csc.PIII.backend.Nurse;
import csc.PIII.backend.Patient;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UpdatePatient extends Activity {

	/**
	 * @author Peter 
	 * @author Zaid
	 */
	Nurse nurse;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_patient);
		
		Intent intent = getIntent();
		nurse = (Nurse)intent.getSerializableExtra("nurse");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_patient, menu);
		return true;
	}
	
	public void findPatient(View view){	
		/**
		 * Finds  the Patient indicated by the corresponding Health Card Number
		 * and populates EditTexts with corresponding values. 
		 */
		TextView ErrorMessage = (TextView) findViewById(R.id.ErrorMessage);
		ErrorMessage.setVisibility(View.INVISIBLE);
		
		Patient foundPatient;
		EditText patientHCN = (EditText) findViewById(R.id.EnterHealthText);
		String HCNum = patientHCN.getText().toString();
		
		if (nurse.patientExists(HCNum)){
			foundPatient = nurse.getPatient(HCNum);
			Intent intent = new Intent (this, UpdateVitalsActivity.class);
			intent.putExtra("foundPatient", foundPatient);
			intent.putExtra("nurse", nurse);
			startActivity(intent);
		}
		else{
			ErrorMessage.setVisibility(View.VISIBLE);
		}
		
	}
}
