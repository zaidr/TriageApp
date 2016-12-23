package csc.PIII.triageapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import csc.PII.triageapp.R;
import csc.PIII.backend.Nurse;
import csc.PIII.backend.Patient;
import csc.PIII.backend.VitalSigns;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * Android activity used to add new Patients to the Patient records.
 * @author Zaid
 *
 */
public class AddPatientActivity extends Activity {

	/** The Nurse that will be worked on in this Activity */
	private Nurse nurse;
	
	/** The Health Card Number of the new Patient */
	private String HCNum;
	
	/** The name of the new Patient */
	private String firstName, lastName;
	
	/** The DOB of the new Patient */
	private int DOBday, DOBmonth, DOByear;
	
	/** The vitals of the new Patient */
	private int sysBP, diBP, heartRate;
	private double temp;
	
	/** The symptoms of the new Patient */
	private String symptoms;
	
	/** The date and time of the Patient's arrival */
	private int arrMin, arrHour, arrDay, arrMonth, arrYear;
	
	/** The EditTexts used in this Activity */
	EditText HCNEdit, firstNameEdit, lastNameEdit;
	EditText sysBPEdit, diBPEdit, tempEdit, heartRateEdit, symptomsEdit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_patient);
		
		Intent intent = getIntent();
		nurse = (Nurse)intent.getSerializableExtra("nurse");
		
		HCNEdit = (EditText) findViewById(R.id.HCNText);
		firstNameEdit = (EditText) findViewById(R.id.firstNameEdit);
		lastNameEdit = (EditText) findViewById(R.id.lastNameEdit);
		sysBPEdit = (EditText) findViewById(R.id.sysBPEdit);
		diBPEdit = (EditText) findViewById(R.id.diBPEdit);
		tempEdit = (EditText) findViewById(R.id.tempEdit);
		heartRateEdit = (EditText) findViewById(R.id.heartRateEdit);
		symptomsEdit = (EditText) findViewById(R.id.symptomsEdit);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_patient, menu);
		return true;
	}
	
	/**
	 * Checks to see if all the input fields have been entered.
	 * @return 	true if all input field have been entered
	 * 			false otherwise
	 */
	private boolean allFieldsEntered() {
		if (HCNEdit.getText().length() == 0
				|| firstNameEdit.getText().length() == 0
				|| lastNameEdit.getText().length() == 0
				|| sysBPEdit.getText().length() == 0
				|| diBPEdit.getText().length() == 0
				|| tempEdit.getText().length() == 0
				|| heartRateEdit.getText().length() == 0
				|| symptomsEdit.getText().length() == 0) {
			showToast("Missing Data. Fill in all fields.");
			return false;
		}
		return true;
	}

	/**
	 * After scrubbing entered data, creates a new Patient and enters it into
	 * the Patient records.
	 * @param view
	 */
	public void addPatient(View view) {
		/* Grab the data from the fields */
		if (allFieldsEntered()) {
			HCNum = HCNEdit.getText().toString();
			firstName = firstNameEdit.getText().toString();
			lastName = lastNameEdit.getText().toString();
			sysBP = Integer.parseInt(sysBPEdit.getText().toString());
			diBP = Integer.parseInt(diBPEdit.getText().toString());
			temp = Double.parseDouble(tempEdit.getText().toString());
			heartRate = Integer.parseInt(heartRateEdit.getText().toString());
			symptoms = symptomsEdit.getText().toString();
			
			DatePicker DOBDateP = (DatePicker)findViewById(R.id.DOBDatePicker);
			DOBday = DOBDateP.getDayOfMonth();
			DOBmonth = DOBDateP.getMonth();
			DOByear = DOBDateP.getYear();
			// Parsing the DOB date
			String dobString = "" + DOBday + "-" + DOBmonth + "-" + DOByear;
			Date dobDate = null;
			try {
				dobDate = new SimpleDateFormat("dd-MM-yyyy",
						Locale.CANADA).parse(dobString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			TimePicker arrTime = 
					(TimePicker)findViewById(R.id.arrivalTimePicker);
			arrHour = arrTime.getCurrentHour();
			arrMin = arrTime.getCurrentMinute();
			
			DatePicker arrDateP = 
					(DatePicker)findViewById(R.id.arrivalDatePicker);
			arrDay = arrDateP.getDayOfMonth();
			arrMonth = arrDateP.getMonth();
			arrYear = arrDateP.getYear();
			// Parsing the arrival date and time
			String arrString = "" + arrDay + "-" + arrMonth + "-" + arrYear;
			arrString += " " + arrHour + ":" + arrMin;
			Date arrDate = null;
			try {
				arrDate = new SimpleDateFormat("dd-MM-yyyy H:m",
						Locale.CANADA).parse(arrString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/* making sure data entered is valid */
			if (isValidInput()) {
				/* creating new patient */
				VitalSigns vitals = new VitalSigns(sysBP, diBP, temp, heartRate,
						arrDate);
				Patient patient = new Patient(firstName, lastName, HCNum,
						dobDate, vitals, arrDate, symptoms);
				nurse.addPatient(patient);
				
				/* passing to next activity */
				Intent intent = new Intent(this, MenuActivity.class);
		    	intent.putExtra("nurse", nurse);
		    	startActivity(intent);
			}
		}
	}
	
	/**
	 * Shows a "Toast" with a specified message to screen.
	 * @param text the text to show on screen
	 */
	private void showToast(String text) {
		CharSequence toastText = text;
		Context context = getApplicationContext();
		Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * Checks entered data for valid input.
	 */
	private boolean isValidInput() {
		// accounting for if Patient already exists with entered HCN
		if (nurse.patientExists(HCNum)) {
			showToast("A patient with this health card number already exists");
			return false;
		}
		/* allowing only alphabetic characters and space for
		 * firstName and lastName 
		 */
		String onlyAlphabetRegex = "^([a-zA-Z]+ +)*[a-zA-Z]+$";
		if (firstName == null || !firstName.matches(onlyAlphabetRegex)) {
			showToast("Incorrent First Name");
			return false;
		}
		if (lastName == null || !lastName.matches(onlyAlphabetRegex)) {
			showToast("Incorrect Last Name");
			return false;
		}
		return true;
	}
}
