package csc.PIII.backend;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import android.content.Context;


/**
 * Manages the loading from file and saving to file of the master Map of
 * Patients.
 * @author group_0718
 *
 */
public class PatientManager {
	
	/** The name of the file where the patient data is stored */
	private final String PATIENTDATA = "patientsDB.txt";
	
	/** a Map of Health Card Numbers to Patients */
	private Map<String, Patient> patients;
	
	/**
	 * Constructs a PatientManager by reading the patient records from file
	 * and constructing a Map of Health Card Numbers to Patients.
	 * @param dir File pointing to the directory of the file
	 * @throws IOException
	 */
	public PatientManager(File dir) throws IOException {
		File file = new File(dir, PATIENTDATA);
		if (file.exists()) {
	        loadPatientRecords(file.getPath());
	    } else {
	        file.createNewFile();
	        patients = new HashMap<String, Patient>();
	    }
	}
	
	/**
	 * Overloaded constructor to create the file if not created, but not
	 * read from it to populate the patients map, but use the one provided
	 * in the parameters. Used when Saving Only.
	 * @param dir File pointing to the directory of the file
	 * @param patients Map of Health Card Numbers to Patients
	 * @throws IOException 
	 */
	public PatientManager(Map<String, Patient> patients, File dir) 
			throws IOException {
		File file = new File(dir, PATIENTDATA);
		if (!file.exists()) {
	        file.createNewFile();
	    }
		this.patients = patients;
	}
	
	/**
	 * Returns the generated Map of Health Card Numbers to Patients
	 * @return Map of Health Card Numbers to Patients
	 */
	public Map<String, Patient> getPatients() {
		return patients;
	}
	
	/**
	 * Saves all Patients from 'patients' map to file.
	 * @param patients Map of HCN to Patients
	 */
	public void savePatientRecords(Context context)
			throws FileNotFoundException {
		
		// creating the outputStream to write to file
		FileOutputStream output = context.openFileOutput(PATIENTDATA, 
				Context.MODE_PRIVATE);
		for (String HCN : patients.keySet()) {
			Patient patient = patients.get(HCN);
			try {
				output.write(patient.saveString().getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads all existing Patients from file, and constructs a Map of
	 * HCN keys to Patient values to pass on.
	 * File Structure:
	 * firstName(One String)
	 * lastName (One String)
	 * DOB (A formatted Date)
	 * ArrivalTime (A formatted Date)
	 * Date/Time when seen by doc (a formatted Date, or "FALSE" if not seen)
	 * #of BP's checked (1, 2, 3 ...)
	 * [ ...
	 * (Date when BP checked),SysBP,DiBP
	 * ... ]
	 * #of Temps Checked (1, 2, 3 ...)
	 * [ ...
	 * (Date when Temp Checked),Temp
	 * ... ]
	 * #of HeartRates checked (1, 2, 3 ...)
	 * [ ...
	 * (Date when HR Checked),HR
	 * ... ]
	 * #of Symptoms
	 * [ ...
	* (Date when Symptoms Checked),Symptoms
	* ... ]
	* #of Prescriptions
	* [ ...
	* Name of Medication,Instructions
	* ... ]
	 * @param filePath the path to the file to read from
	 * @return Map of Health Card Numbers to Patients
	 * @throws FileNotFoundException
	 */
	public void loadPatientRecords (String filePath) 
			throws FileNotFoundException {
		patients = new HashMap<String,Patient>();
		Scanner scanner = new Scanner(new FileInputStream(filePath));
		
        // iterating through the entire file to read all patients
		while(scanner.hasNextLine()) {
			// health card number
	        String HCN = scanner.nextLine().toString();
	        // first name of the patient
	        String firstName = scanner.nextLine().toString();
	        // last name of the patient
	        String lastName= scanner.nextLine().toString();
	        // DOB of the patient
	        String DOB= scanner.nextLine().toString();
	        Date theDOB = new Date();
			try {
				theDOB = new SimpleDateFormat("yyyy-M-d H:m:s", 
						Locale.CANADA).parse(DOB);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// arrival time of the Patient
	        String arrivalTime = scanner.nextLine().toString();
	        Date theArrivalTime = new Date();
			try {
				theArrivalTime = new SimpleDateFormat("yyyy-M-d H:m:s",
						Locale.CANADA).parse(arrivalTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// if the Patient has been seen by a doctor
	        String seenByDocString = scanner.nextLine().toString();
	        Date seenByDocTime = new Date();
			if (!seenByDocString.equals("FALSE")) {
				try {
					seenByDocTime = new SimpleDateFormat("yyyy-M-d H:m:s",
							Locale.CANADA).parse(seenByDocString);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			// Reads all recorded blood pressures from file
	        int num1= Integer.parseInt(scanner.nextLine().toString());
	        List <BloodPressure> bpList;
	        bpList = new ArrayList<BloodPressure>();
	        
	        for (int i =0; i< num1; i++){
	        	String[] bpInfo = scanner.nextLine().split(">>");
	        	String bpDate = bpInfo[0];
	        	Date theBpDate = new Date();
				try {
					theBpDate = new SimpleDateFormat("yyyy-M-d H:m:s",
							Locale.CANADA).parse(bpDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	int bpSys =Integer.parseInt(bpInfo[1]);
	        	int bpDi = Integer.parseInt(bpInfo[2]);
	        	BloodPressure BP = new BloodPressure(bpSys,bpDi,theBpDate);
	        	bpList.add(BP);
	        }
	        
	        // Reads all recorded temperatures from file
	        int num2= Integer.parseInt(scanner.nextLine().toString());
	        List <Temperature> tempList;
	        tempList = new ArrayList<Temperature>();
	       
	        for (int i =0; i< num2; i++){
		        String[] tempInfo = scanner.nextLine().split(">>");
		       	String tempDate = tempInfo[0];
		       	Date theTempDate = new Date();
				try {
					theTempDate = new SimpleDateFormat("yyyy-M-d H:m:s",
							Locale.CANADA).parse(tempDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		       	double tempNum =Double.parseDouble(tempInfo[1]);
		       	Temperature temp = new Temperature(tempNum, theTempDate);
		        tempList.add(temp);
	        }
	        
	        // Reads all recorded heart rates from file
	        int num3= Integer.parseInt(scanner.nextLine().toString());
	        List <HeartRate> hrList;
	        hrList = new ArrayList<HeartRate>();
	        
	        for (int i =0; i< num3; i++){
		        String[] heartInfo = scanner.nextLine().split(">>");
		       	String heartDate = heartInfo[0];
		       	Date theHeartDate = new Date();
				try {
					theHeartDate = new SimpleDateFormat("yyyy-M-d H:m:s",
							Locale.CANADA).parse(heartDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		       	int hrNum =Integer.parseInt(heartInfo[1]);
		       	HeartRate heartRate = new HeartRate(hrNum, theHeartDate);
		        hrList.add(heartRate);
	        } 
	        
	        // Reads all recorded symptoms from file
	        int num4= Integer.parseInt(scanner.nextLine().toString());
	        List <Symptom> symptomList;
	        symptomList = new ArrayList<Symptom>();
	        
	        for (int i =0; i< num4; i++){
	        	String[] symptomInfo = scanner.nextLine().split(">>");
		       	String symptomDate = symptomInfo[0];
		       	Date theHeartDate = new Date();
				try {
					theHeartDate = new SimpleDateFormat("yyyy-M-d H:m:s",
							Locale.CANADA).parse(symptomDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		       	String symptomDes =symptomInfo[1];
		       	Symptom symptom = new Symptom(symptomDes, theHeartDate);
		       	symptomList.add(symptom);
	        }
	        
	        // Creating the patient to be added to the patient map
	        VitalSigns vitalsigns = new VitalSigns(bpList,hrList,tempList);
	        Patient newValue = new Patient(firstName, lastName, HCN, theDOB,
	        		vitalsigns, theArrivalTime, symptomList);
	        // adding date/time if patient was seen by a doctor
	        if (seenByDocString.equals("FALSE")) {
	        	newValue.setSeenByDoc(true, seenByDocTime);
	        }
	        
	        // Reads all recorded prescriptions from file
	        int num5= Integer.parseInt(scanner.nextLine().toString());
	        if(num5>0){
	        	List <Prescription> presList;
    	        presList = new ArrayList<Prescription>();
    	       
	        	for (int i =0; i< num5; i++){
	        		String[] presInfo = scanner.nextLine().split(">>");
	        		String medName = presInfo[0];
	        		String instructions = presInfo[1];
	        		Prescription prescription = new Prescription(medName,
	        				instructions);
	        		presList.add(prescription);
	        	}
	        	
	        	for (Prescription meds:presList){
	        		newValue.addPrescription(meds);
	        	}
	        }
	        patients.put(newValue.getHCN(), newValue);
	        
        }
        scanner.close();
	}
}

