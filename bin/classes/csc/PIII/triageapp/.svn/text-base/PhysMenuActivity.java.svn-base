package csc.PIII.triageapp;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import csc.PII.triageapp.R;
import csc.PIII.backend.PatientManager;
import csc.PIII.backend.Physician;

public class PhysMenuActivity extends Activity {
	private Physician phys;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phys_menu);
		
		grabPhysFromPrevIntent();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.phys_menu, menu);
		return true;
	}
	
	private void grabPhysFromPrevIntent() {
		Intent intent = getIntent();
		phys = (Physician)intent.getSerializableExtra("phys");
	}
	
	private void showToast(String text) {
		CharSequence toastText = text;
		Context context = getApplicationContext();
		Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
	}
	
    public void gotoLookupActivity(View view) {
    	Intent intent = new Intent(this, LookupActivity.class);
    	intent.putExtra("user", phys);
    	startActivity(intent);
    }
    
    public void savePatientsToFile(View view) {
    	PatientManager pmanager;
    	try {
			pmanager = new PatientManager(phys.getAllPatients(),
					this.getApplicationContext().getFilesDir());
			pmanager.savePatientRecords(this.getApplicationContext());
    	} catch (FileNotFoundException e) {
    		e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	showToast("All Patients Saved.");
    }
    
    public void gotoAddPrescriptionActivity(View view) {
    	Intent intent = new Intent(this, AddPrescriptionActivity.class);
    	intent.putExtra("physician", phys);
    	startActivity(intent);
    }

}
