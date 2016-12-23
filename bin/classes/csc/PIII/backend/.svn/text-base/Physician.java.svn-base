package csc.PIII.backend;

import java.util.HashMap;
import java.util.Map;

public class Physician extends User {

	/**
	 * Eclipse generated value
	 */
	private static final long serialVersionUID = 6907175448570077245L;

	/**
	 * Constructs a Physician, who can look up Patient information, and add
	 * a Prescription to that Patient's records.
	 * @param username
	 * @param password
	 */
	public Physician(String username, String password) {
		super(username, password, User.USERTYPE_PHYS);
		patients = new HashMap<String, Patient>();
	}
	
	/**
	 * Constructs a Physician with the supplied Map of Patient records.
	 * @param username
	 * @param password
	 * @param patients
	 */
	public Physician(String username, String password,
			Map<String, Patient> patients) {
		super(username, password, User.USERTYPE_PHYS);
		super.patients = patients;
	}
}
