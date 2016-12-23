package csc.PIII.backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A representation of a Nurse, who manages a record of patients.
 * @author Zaid
 *
 */
public class Nurse extends User implements Serializable{
	
	/** Eclipse generated serialization ID */
	private static final long serialVersionUID = 2409736082651491692L;
	
	/**
	 * Constructs a Nurse with username and password, and attempts to generate
	 * patient records using PatientManager.
	 * @param username the username of this Nurse
	 * @param password the password of this Nurse
	 */
	public Nurse(String username, String password) {
		super(username, password, User.USERTYPE_NURSE);
		patients = new HashMap<String, Patient>();
	}
	
	/**
	 * Constructs a Nurse with username and password, and takes the Map of
	 * Health Card Numbers to Patients provided in parameters as its record
	 * of patients.
	 * in the parameters to 
	 * @param username the username of the Nurse
	 * @param password the password of the Nurse
	 * @param patients the Map of Health Card Numbers to Patients that serves
	 * 	as a record of patients the Nurse manages. 
	 */
	public Nurse(String username, String password,
			Map<String, Patient> patients) {
		super(username, password, User.USERTYPE_NURSE);
		super.patients = patients;
	}
	
	/**
	 * Adds a Patient to the patient record. If the new patient has the same
	 * health card number as a previous patient in the records, the previous
	 * patient's records are overwritten.
	 * @param patient the patient to add to the patient records
	 */
	public void addPatient(Patient patient) {
		patients.put(patient.getHCN(), patient);
	}
	
	/**
	 * Generates and returns a List of patients based on their individual
	 * priorities. The list is ordered non-increasing, ordered highest priority
	 * to lowest priority.
	 * @return	list of patients based on their priority, non-decreasing,
	 * 			ordered highest priority to lowest priority.
	 */
	public List<Patient> getPriorityList() {
		List<Patient> patientList = new ArrayList<Patient>(patients.values());
		/*
		 * We want to sort the list of patients by each Patient's priority,
		 * so we must implement a Comparator that compares one Patient's priority
		 * with the another's. This ordering makes the ordering smallest to
		 * greatest priority.
		 */
		Collections.sort(patientList, new Comparator<Patient>() {
			@Override
			public int compare(final Patient p1, final Patient p2) {
				return p1.getPriority() - p2.getPriority();
			}
		});
		/*
		 * Since we want a list of non-decreasing priorities, highest priority
		 * first, we have to reverse the list.
		 */
		Collections.reverse(patientList);
		return notSeenByDocPatients(patientList);
	}
	
	/**
	 * Generates and returns a List of patients based on their individual
	 * arrival times. The list is ordered chronologically, earliest to latest. 
	 * @return 	list of patients ordered chronologically, earliest to latest,
	 * 			based on their arrival time.
	 */
	public List<Patient> getArrivalTimeList() {
		List<Patient> patientList = new ArrayList<Patient>(patients.values());
		/*
		 * We want to sort the list of patients by each Patient's arrivalTime,
		 * so we must implement a Comparator that compares one Patient's
		 * arrivalTime with the another's.
		 */
		Collections.sort(patientList, new Comparator<Patient>() {
			@Override
			public int compare(final Patient p1, final Patient p2) {
				return p1.getArrivalTime().compareTo(p2.getArrivalTime());
			}
		});
		return notSeenByDocPatients(patientList);
	}
	
	/**
	 * Generates a new list of only those patients that have not been seen
	 * by a doctor.
	 * @param patientList original full list of patients
	 * @return list of patients who have not been seen by a doctor
	 */
	private List<Patient> notSeenByDocPatients(List<Patient> patientList) {
		List<Patient> unseenPatientList = new ArrayList<Patient>();
		for (Patient p : patientList) {
			if (!p.isSeenByDoc()) {
				unseenPatientList.add(p);
			}
		}
		return unseenPatientList;
	}
	
	/**
	 * Updates the description of symptoms of the patient in question.
	 * @param patient the patient who's symptoms have changed
	 * @param symptoms the new description of symptoms
	 */
	public void updateSymptoms(Patient patient, String symptoms, 
			Date timeChecked) {
		patient.updateSymptoms(symptoms, timeChecked);
	}
	
	/**
	 * Updates the vital signs of the patient in question.
	 * @param patient the patient for who's vital signs have changed 
	 * @param vitals the vitals signs for the patient in question
	 */
	public void updateVitals(Patient patient, int sysBP, int diBP,
			int heartRate, double temp, Date timeChecked) {
		patient.updateVitals(sysBP, diBP, heartRate, temp, timeChecked);
	}
	
	/**
	 * Sets the "Seen by Doctor" indicator for patient to true
	 * @param patinet the patient who has been seen by a doctor
	 */
	public void seenByDoc(Patient patient, Date whenSeenByDoc) {
		patient.setSeenByDoc(true, whenSeenByDoc);
	}
}