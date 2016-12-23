package csc.PIII.backend;

import java.io.Serializable;

/**
 * A representation of a Prescription that a Patient is to take.
 * @author Zaid
 *
 */
public class Prescription implements Serializable{

	/**
	 * Eclipse Generated value
	 */
	private static final long serialVersionUID = -8740487552529713599L;
	
	/** The name of the medication used */
	String medName;
	/** The instructions on how the medication is to be administered */
	String instructions;
	
	/**
	 * Constructs a Prescription with the name of the medication to be
	 * administered, and the instructions on how the medication is to be
	 * administered.
	 * @param medName name of medication to be used
	 * @param instructions instructions on how the medication is to be
	 *  administered.
	 */
	public Prescription(String medName, String instructions) {
		super();
		this.medName = medName;
		this.instructions = instructions;
	}
	
	/**
	 * Gets the name of the medication.
	 * @return name of medication
	 */
	public String getMedName() {
		return medName;
	}
	
	/**
	 * Gets the instructions on how the medication is to be administered.
	 * @return instructions about how to administer the medication
	 */
	public String getInstructions() {
		return instructions;
	}
	
	/**
	 * Generates the string representation of this Prescription used for
	 * saving.
	 * @return string used for saving
	 */
	public String saveString() {
		String s = medName + ">>";
		s += instructions + "\n";
		return s;
	}
}
