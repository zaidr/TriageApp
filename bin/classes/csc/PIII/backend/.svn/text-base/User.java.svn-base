package csc.PIII.backend;

import java.io.Serializable;
import java.util.Map;

/**
 * A User of the application, who can log-in.
 * @author Zaid
 *
 */
public class User implements Serializable {
	
	/** Eclipse generated value */
	private static final long serialVersionUID = 6357698726630370760L;
	
	/***/
	public static final String USERTYPE_NURSE = "nurse";
	public static final String USERTYPE_PHYS = "phys";
	
	/** The username and password this user uses to login */
	private String username, password;
	
	/** The type of User this User is - Nurse or Physician */
	private String userType;
	
	/** Map of all Health Card Numbers to Patients */
	protected Map<String, Patient> patients;
	
	/**
	 * Assigns username and password to their respective strings. 
	 * @param username
	 * @param password
	 */
	public User(String username, String password, String userType) {
		this.username = username;
		this.password = password;
		this.userType = userType;
	}

	/**
	 * Returns the username of this user.
	 * @return username of this user
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Returns the password of this user.
	 * @return password of the user
	 */
	public String getPassword() {
		return password;
	}
	
	public String getUserType() {
		return userType;
	}
	
	/**
	 * Tells if a patient exists in the patient records with the unique
	 * health card number HCN.
	 * @param HCN the health card number to be searched
	 * @return true if a patient exists with the HCN
	 * 	false otherwise
	 */
	public boolean patientExists(String HCN) {
		return patients.containsKey(HCN);
	}
	
	/**
	 * Returns the entire patient record.
	 * @return the Map of Health Card Numbers to Patients
	 */
	public Map<String, Patient> getAllPatients() {
		return this.patients;
	}
	
	/**
	 * Gets the patient from the map of HCN->Patients who has the specific, 
	 * unique Health Card Number. 
	 * @param HCN the health card number of the patient
	 * @return 	the patient with the health card number provided, or null if no
	 * 			patient with that health card number is found in the patient
	 * 			record.
	 */
	public Patient getPatient(String HCN) {
		return patients.get(HCN);
	}
}
