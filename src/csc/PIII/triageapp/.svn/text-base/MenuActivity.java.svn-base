package csc.PIII.triageapp;

import java.io.FileNotFoundException;
import java.io.IOException;

import csc.PII.triageapp.R;
import csc.PIII.backend.Nurse;
import csc.PIII.backend.PatientManager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MenuActivity extends Activity {

	/** This activity's Nurse */
	Nurse nurse;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		grabNurseFromPrevIntent();
	}

	/**
	 * Get the serialized Nurse from the previous activity to pass to the
	 * next activity.
	 */
	private void grabNurseFromPrevIntent() {
		Intent intent = getIntent();
		nurse = (Nurse)intent.getSerializableExtra("nurse");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}
	
	private void showToast(String text) {
		CharSequence toastText = text;
		Context context = getApplicationContext();
		Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
	}
	
    public void gotoLookupActivity(View view) {
    	Intent intent = new Intent(this, LookupActivity.class);
    	intent.putExtra("user", nurse);
    	startActivity(intent);
    }
    
    public void gotoAddPatientActivity(View view) {
    	Intent intent = new Intent(this, AddPatientActivity.class);
    	intent.putExtra("nurse", nurse);
    	startActivity(intent);
    }
    
    public void gotoPriorityListActivity(View view) {
    	Intent intent = new Intent(this, PriorityListActivity.class);
    	intent.putExtra("nurse", nurse);
    	startActivity(intent);
    }
    
    public void gotoArrivalListActivity(View view) {
    	Intent intent = new Intent(this, ArrivalListActivity.class);
    	intent.putExtra("nurse", nurse);
    	startActivity(intent);
    }
    
    public void gotoUpdatePatientActivity(View view) {
    	Intent intent = new Intent(this, UpdatePatient.class);
    	intent.putExtra("nurse", nurse);
    	startActivity(intent);
    }
    
    public void savePatientsToFile(View view) {
    	PatientManager pmanager;
    	try {
			pmanager = new PatientManager(nurse.getAllPatients(),
					this.getApplicationContext().getFilesDir());
			pmanager.savePatientRecords(this.getApplicationContext());
    	} catch (FileNotFoundException e) {
    		e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	showToast("All Patients Saved.");
    }
}
