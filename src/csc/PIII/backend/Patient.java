package csc.PIII.backend;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.text.format.DateFormat;

/**
 * A representation of a Patient with a name, a unique Health Card Number,
 * and VitalSigns, as well as a list of Symptoms. All data is date stamped.
 * @author ZDesk
 *
 */
public class Patient implements Serializable {

	/** Eclipse generated value */
	private static final long serialVersionUID = -4786923647537079993L;
	
	/** The first and last name of the Patient */
	private String firstName, lastName;
	
	/** The health card number of the Patient */
	private String HCN;
	
	/** The date of birth of the Patient */
	private Date DOB;
	
	/** The time the Patient arrived at the ER */
	private Date arrivalTime;
	
	/** The vital signs of this patient */
	private VitalSigns vitals;
	
	/** The priority of this patient according to hospital policy */
	private int priority;
	
	/** The description of symptoms of the Patient */
	private List<Symptom> symptoms;
	
	/** The list of prescription for this Patient */
	private List<Prescription> prescriptions;

	/** Indicator that this Patient has been seen by a doctor. */
	private boolean seenByDoc;
	
	/** Date/Time when this Patient was seen by a doctor. */
	private Date whenSeenByDoc;
	
	/**
	 * Constructs a Patient with all the relevant information an initial
	 * check-up would look for.
	 * @param firstName the first name of the patient
	 * @param lastName the last name of the patient
	 * @param hCN the patient's Health Card Number
	 * @param dOB the date of birth of the patient
	 * @param vitals the initial vital signs of the patient
	 * @param arrivalTime the time/date the patient arrived in the ER
	 * @param symptoms the initial symptoms the patient is exhibiting
	 */
	public Patient(String firstName, String lastName, String hCN, Date dOB,
			VitalSigns vitals, Date arrivalTime, String symptoms) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.HCN = hCN;
		this.DOB = dOB;
		this.vitals = vitals;
		this.arrivalTime = arrivalTime;
		this.seenByDoc = false;
		this.symptoms = new ArrayList<Symptom>();
		this.symptoms.add(new Symptom(symptoms, arrivalTime));
		generatePriority();
		this.prescriptions = new ArrayList<Prescription>();
	}
	
	/**
	 * Constructs a Patient with all the relevant information that patient
	 * has, including information on multiple check-ups.
	 * @param firstName the first name of the patient
	 * @param lastName the last name of the patient
	 * @param hCN the patient's health card number
	 * @param dOB the patient's date of birth
	 * @param vitals the list of vital sings pertaining to each check-up
	 * @param arrivalTime the arrival time of the patient in the ER
	 * @param symptoms the list of symptoms the patient has exhibited
	 * 			throughout multiple check-ups.
	 */
	public Patient(String firstName, String lastName, String hCN, Date dOB,
			VitalSigns vitals, Date arrivalTime, List<Symptom> symptoms) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.HCN = hCN;
		this.DOB = dOB;
		this.vitals = vitals;
		this.arrivalTime = arrivalTime;
		this.seenByDoc = false;
		this.symptoms = symptoms;
		generatePriority();
		this.prescriptions = new ArrayList<Prescription>();
	}
	
	
	/**
	 * Returns the String representation of the Patient, with all elements
	 * of the patient.
	 * @return string representation of the every element of the patient
	 */
	public String fullString() {
		String s = "Name: " + firstName + " " + lastName + "\n";
		s += "Health Card Number: " + HCN + "\n";
		// Formatting the date to the current Locale
		s += "DOB: " + DateFormat.format("yyyy-MMM-d", DOB) + "\n";
		
		s += "Vital Signs: \n";
		// Only showing vitals for current visit to the ER
		s += vitals.toString();
		s += this.getCurrentSymptoms() + "\n";
		if (this.isSeenByDoc()) {
			s += "This Patient was seen by a doctor on: " +
					DateFormat.format("yyyy-MMM-d H:m a", whenSeenByDoc) + "\n";
		} else {
			s += "This Patient has not seen a doctor yet. \n";
		}
		return s;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName + "\n" +
				"DOB: " + DateFormat.format("yyyy-M-d", DOB) + "\n" +
				"Health Card Num: " + HCN;
	}
	
	/**
	 * The string to be saved that represents this patient.
	 * @return the save string representation of this patient
	 */
	public String saveString() {
		// date format to use for all dates/times
		Format formatter = new SimpleDateFormat("yyyy-M-d H:m:s",
				Locale.CANADA);
		
		String s = HCN + "\n" + firstName + "\n" + lastName + "\n";
		s += formatter.format(DOB) + "\n";
		s += formatter.format(arrivalTime) + "\n";
		if (this.isSeenByDoc()) {
			s += formatter.format(whenSeenByDoc) + "\n";
		} else {
			s += "FALSE\n";
		}
		s += vitals.saveString();
		
		//process all Symptom checks
		s += symptoms.size() + "\n";
		for (Symptom sym : symptoms) {
			s += sym.saveString();
		}
		
		//process all Symptom checks
		s += prescriptions.size() + "\n";
		for (Prescription pres : prescriptions) {
			s += pres.saveString();
		}
		
		return s;
	}
	
	/**
	 * Generates the priority of this Patient based off of the vital signs of
	 * this Patient.
	 */
	private void generatePriority() {
		int critPoints = 0;
		if (vitals.isBPCrit()) {
			critPoints++;
		}
		if (vitals.isHRCrit()) {
			critPoints++;
		}
		if (vitals.isTempCrit()) {
			critPoints++;
		}
		if (ageUnder2()) {
			critPoints++;
		}
		priority = critPoints;
	}
	
	/**
	 * Returns true if the patient is under 2 years of age, and false otherwise.
	 * @return 	true if patient is under 2 years of age
	 * 			false otherwise
	 */
	private boolean ageUnder2() {
		Calendar cal = Calendar.getInstance();
		// Setting the Calendar date to the current time/date
		cal.setTime(new Date());
		// Calculating a date that is two years prior from today
		cal.add(Calendar.YEAR, -2);
		Date twoYearsPrior = cal.getTime();
		
		/*
		 * if DOB is greater than (after) a date 2 years prior from today,
		 * then this Patient is under 2 years of age.
		 */
		if (DOB.compareTo(twoYearsPrior) > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns if this patient has been seen by a doctor.
	 * @return
	 */
	public boolean isSeenByDoc() {
		return seenByDoc;
	}

	/**
	 * Sets that this patient has been seen by a doctor.
	 * @param seenByDoc true or false of if the patient has been seen 
	 * 	by a doctor.
	 */
	public void setSeenByDoc(boolean seen, Date whenSeenByDoc) {
		this.seenByDoc = seen;
		this.whenSeenByDoc = whenSeenByDoc;
	}

	/**
	 * Gets the date of birth of this patient
	 * @return the DOB of this patient
	 */
	public Date getDOB() {
		return DOB;
	}

	/**
	 * Gets the time/date when this Patient arrived in the ER.
	 * @return the time/date of arrival of the patient
	 */
	public Date getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * Sets the arrival time/date of this Patient into the ER.
	 * @param arrivalTime
	 */
	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/**
	 * Gets the VitalSigns of this Patient
	 * @return the vital signs of this patient
	 */
	public VitalSigns getVitals() {
		return vitals;
	}

	/**
	 * Updates the VitalSigns of this patient with the current ones provided,
	 * retaining older values.
	 * @param sysBP the newly checked systolic blood pressure
	 * @param diBP the newly checked diastolic blood pressure
	 * @param heartRate the newly checked heart rate
	 * @param temp the newly checked temperature
	 * @param timeChecked the time/date when the check was done
	 */
	public void updateVitals(int sysBP, int diBP, int heartRate, double temp,
			Date timeChecked) {
		this.vitals.updateBP(sysBP, diBP, timeChecked);
		this.vitals.updateHeartRate(heartRate, timeChecked);
		this.vitals.updateTemp(temp, timeChecked);
		generatePriority();
	}

	/**
	 * Gets the health card number of this patient.
	 * @return the health card number of this patient
	 */
	public String getHCN() {
		return HCN;
	}

	/**
	 * Gets the priority of this patient, which is based off of the patient's
	 * vital signs, according to the hospital's policy. 
	 * @return the priority of this patient according to the hospital's policy
	 */
	public int getPriority() {
		return priority;
	}
	
	/**
	 * Gets the name of the patient, formatted with a space between first
	 * and last name. First name first, last name last.
	 * @return the name of the Patient
	 */
	public String getName() {
		String name = "" + firstName +" "+ lastName;
		return name;
	}
	
	/**
	 * Sets the patient's first and last name
	 * @param firstName first name of the patient
	 * @param lastName last name of the patient
	 */
	public void setName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * Gets the last recorded Symptoms of the patient
	 * @return the last recorded Symptom of this patient
	 */
	public Symptom getCurrentSymptoms() {
		return symptoms.get(symptoms.size() - 1);
	}
	
	/**
	 * Updates the Symptoms of the patient, retaining older values. Adds a
	 * timestamp for the time/date when the Symptoms were checked.
	 * @param symptoms the current symptoms of the patient
	 * @param timeChecked the time/date when the symptoms were checked
	 */
	public void updateSymptoms(String symptoms, Date timeChecked) {
		this.symptoms.add(new Symptom(symptoms, timeChecked));
	}
	
	/**
	 * Get the list of prescriptions for this Patient.
	 * @return the list of prescriptions for this Patient
	 */
	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}

	/**
	 * Adds a new Prescription to the list of prescriptions for this Patient.
	 * @param prescription the new prescription to add
	 */
	public void addPrescription(Prescription prescription) {
		this.prescriptions.add(prescription);
	}
}
