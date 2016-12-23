package csc.PIII.triageapp;

import java.util.List;

import csc.PII.triageapp.R;
import csc.PIII.backend.Nurse;
import csc.PIII.backend.Patient;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;

public class ArrivalListActivity extends Activity {
	
	/** Nurse that this activity uses */
	Nurse nurse;
	
	/**
	 * On creation, it shows the list of Patients by arrival time.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_arrival_list);
		
		Intent intent = getIntent();
		nurse = (Nurse)intent.getSerializableExtra("nurse");
		List<Patient> patients = nurse.getPriorityList();
		
		ListView listView = (ListView) findViewById(R.id.listViewArrival);
		 
	    //Row layout defined by Android: android.R.layout.simple_list_item_1
	    listView.setAdapter(new ArrayAdapter<Patient>(this,
	    		android.R.layout.simple_list_item_1, patients));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.arrival_list, menu);
		return true;
	}

}
