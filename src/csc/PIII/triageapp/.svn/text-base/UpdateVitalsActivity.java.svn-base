package csc.PIII.triageapp;

import java.util.Date;

import csc.PII.triageapp.R;
import csc.PIII.backend.Nurse;
import csc.PIII.backend.Patient;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/*
 * @author c2brookt
 * The activity used to update a patient's vitals, including adding symptoms and 
 * if they have been seen by a doctor and when.
 */
public class UpdateVitalsActivity extends Activity {

	/** The Patient to whom the vitals will be updated */
	Patient foundPatient;
	Nurse nurse;
	
	EditText systolicBP;
	EditText diastolicBP;
	EditText heartRate;
	EditText temperature;
	EditText symptoms;
	CheckBox seen;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_vitals);
		
		systolicBP = (EditText) findViewById(R.id.sysEdit);
		diastolicBP = (EditText) findViewById(R.id.diEditText);
		heartRate = (EditText) findViewById(R.id.hrEditText);
		temperature = (EditText) findViewById(R.id.tEditText);
		symptoms = (EditText) findViewById(R.id.symEditText);
		seen = (CheckBox) findViewById(R.id.seenByDocCheck);
		
		Intent intent = getIntent();
		foundPatient = (Patient)intent.getSerializableExtra("foundPatient");
		nurse = (Nurse)intent.getSerializableExtra("nurse");
		fillField();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_vitals, menu);
		return true;
	}

	/**
	 * Helper method to fill the EditTexts found on this activity with previous
	 * values of the found patient's vital signs.
	 */
	private void fillField(){
		TextView Name = (TextView) findViewById(R.id.patientNameView);
		Name.setText(foundPatient.getName());
		
		systolicBP.setText(""+foundPatient.getVitals()
				.getCurrentBloodPressure().getSysBP());
		
		diastolicBP.setText(""+foundPatient.getVitals()
				.getCurrentBloodPressure().getDiBP());
		
		heartRate.setText(""+foundPatient.getVitals()
				.getCurrentHeartRate().getHeartRate());
		
		temperature.setText(""+foundPatient.getVitals()
				.getCurrentTemperature().getTemp());
		
		// setting the symptoms edittext to the previous symptoms
		symptoms.setText(""+foundPatient.getCurrentSymptoms().getDescripton());
		
		if (foundPatient.isSeenByDoc()){
			seen.setChecked(true);
		}
		else{
			seen.setChecked(false);
		}
	}
	
	/**
	 * The changed vital sign values are updated into the Patient in question,
	 * and the value is placed back into the map of Patients that reside in
	 * the Nurse.
	 * @param view
	 */
	public void saveChanges(View view){
		
		Date timeChecked = new Date();
		
		int sysBP = Integer.parseInt(systolicBP.getText().toString());
		int diBP = Integer.parseInt(diastolicBP.getText().toString());
		int hr = Integer.parseInt(heartRate.getText().toString());
		double temp = Double.parseDouble(temperature.getText().toString());
		String symp = symptoms.getText().toString();
		
		foundPatient.updateVitals(sysBP, diBP, hr, temp, timeChecked);
		foundPatient.updateSymptoms(symp, timeChecked);
		foundPatient.setSeenByDoc(seen.isChecked(), timeChecked);
		nurse.addPatient(foundPatient);
		Intent intent = new Intent (this, MenuActivity.class);
		intent.putExtra("foundPatient", foundPatient);
		intent.putExtra("nurse", nurse);
		startActivity(intent);
	}
}
