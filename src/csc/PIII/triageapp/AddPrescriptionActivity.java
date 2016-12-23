package csc.PIII.triageapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import csc.PII.triageapp.R;
import csc.PIII.backend.Physician;
import csc.PIII.backend.Prescription;

public class AddPrescriptionActivity extends Activity {
	private Physician physician;
	private String medName, instructions;
	private String hcn;
	private EditText medNameEdit, instructionsEdit, hcnEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_prescription);
		
		Intent intent = getIntent();
		physician = (Physician)intent.getSerializableExtra("physician");
		
		medNameEdit = (EditText) findViewById(R.id.med_name_in);
		instructionsEdit = (EditText) findViewById(R.id.instruction_input);
		hcnEdit = (EditText) findViewById(R.id.hcn_input);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_prescription, menu);
		return true;
	}
	
	public void addPrescription(View view){
		if (isValidInput()){
			hcn = hcnEdit.getText().toString();
			medName = medNameEdit.getText().toString();
			instructions = instructionsEdit.getText().toString();
			
			Prescription prescription = new Prescription(medName, instructions);
			physician.getPatient(hcn).addPrescription(prescription);
			
			Intent intent = new Intent(this, PhysMenuActivity.class);
	    	intent.putExtra("phys", physician);
	    	startActivity(intent);
		}
	}
	
	private boolean isValidInput() {
		if (medNameEdit.getText().length() == 0
				|| instructionsEdit.getText().length() == 0){
			showToast("Missing Data. Fill in all fields.");
			return false;
		}
		else if (!physician.patientExists(hcnEdit.toString())){
			showToast("A patient with this Health Card Number does not exist.");
			return false;
		}
		return true;
	}
	
	private void showToast(String text) {
		CharSequence toastText = text;
		Context context = getApplicationContext();
		Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
	}
}
