package csc.PIII.backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Vital Signs that a Patient has are represented in this class.
 * @author Zaid
 *
 */
public class VitalSigns implements Serializable {
	
	/** Eclipse Generated Value */
	private static final long serialVersionUID = -2671670583611876949L;
	
	/** The critical temperature to measure against for urgency */
	private final double CRIT_TEMP = 39.0;
	
	/** The critical systolic blood pressure */
	private final int CRIT_SYSBP = 140;
	
	/** The critical diastolic blood pressure */
	private final int CRIT_DIBP = 90;
	
	/** The critical low and high heart rates */
	private final int CRIT_HR_LOW = 50;
	private final int CRIT_HR_HIGH = 100;
	
	/** The systolic and diastolic blood pressure indicators */
	private List<BloodPressure> bloodPressure;
	
	/** The heart rate */
	private List<HeartRate> heartRate;
	
	/** The temperature in degrees celcius */
	private List<Temperature> temp;
	
	/**
	 * Constructs a VitalSigns instance, with corresponding systolic blood
	 * pressure, diastolic blood pressure, temperature and heart rate.
	 * @param sysBP	the systolic blood pressure
	 * @param diBP the diastolic blood pressure
	 * @param temp temperature in degrees celcius
	 * @param heartRate the heart rate
	 */
	public VitalSigns(int sysBP, int diBP, double temp, int heartRate, 
			Date timeChecked) {
		// setting the BloodPressure
		this.bloodPressure = new ArrayList<BloodPressure>();
		this.bloodPressure.add(new BloodPressure(sysBP, diBP, timeChecked));
		
		// setting the Temperature
		this.temp = new ArrayList<Temperature>();
		this.temp.add(new Temperature(temp, timeChecked));
		
		// setting the HeartRate
		this.heartRate = new ArrayList<HeartRate>();
		this.heartRate.add(new HeartRate(heartRate, timeChecked));
	}
	
	/**
	 * Constructs VitalSigns from a list of all BloodPressures, Temperatures
	 * and HeartRates checked over time
	 * @param bplist list of BloodPressures checked over time
	 * @param hrlist list of HeartRate checked over time
	 * @param templist list of Temperatures checked over time
	 */
	public VitalSigns(List<BloodPressure> bplist, List<HeartRate> hrlist,
			List<Temperature> templist) {
		this.bloodPressure = bplist;
		this.heartRate = hrlist;
		this.temp = templist;
		
	}

	/**
	 * Returns the String representation of the VitalSigns.
	 */
	@Override
	public String toString() {
		String s = "";
		// Adding the Blood Pressures
		s += "Blood Pressure:\n";
		for (BloodPressure bp : bloodPressure) {
			s += bp.toString(); 
		}
		// Adding the Temperatures
		s += "Temperature:\n";
		for (Temperature tp : temp) {
			s += tp.toString(); 
		}
		// Adding the Heart Rates
		s += "Heart Rate:\n";
		for (HeartRate hr : heartRate) {
			s += hr.toString();
		}
		return s;
	}
	
	/**
	 * Returns the generated save string used for saving to file.
	 * @return generated save string for saving to file
	 */
	public String saveString() {
		String s = "";
		
		// process all BloodPressure checks
		s += bloodPressure.size() + "\n";
		for (BloodPressure bp : bloodPressure) {
			s += bp.saveString();
		}
		
		//process all Temperature checks
		s += temp.size() + "\n";
		for (Temperature tmp : temp) {
			s += tmp.saveString();
		}
		
		//process all HeartRate checks
		s += heartRate.size() + "\n";
		for (HeartRate hr : heartRate) {
			s += hr.saveString();
		}
		
		return s;
	}

	/**
	 * Updates the BloodPressure to the newly checked values, along with
	 * the time when the blood pressure check was made.
	 * @param sysBP	systolic blood pressure indicator
	 * @param diBP diastolic blood pressure indicator
	 * @param timeChecked the time when the blood pressure was checked
	 */
	public void updateBP(int sysBP, int diBP, Date timeChecked) {
		this.bloodPressure.add(new BloodPressure(sysBP, diBP, timeChecked));
	}

	/**
	 * Updates the Temperature to the newly checked values, along with the
	 * time when the temperature check was made.
	 * @param temp the newly checked temperature
	 * @param timeChecked the time when the temperature was checked
	 */
	public void updateTemp(double temp, Date timeChecked) {
		this.temp.add(new Temperature(temp, timeChecked));
	}

	/**
	 * Updates the HeartRate to the newly checked values, along with the time
	 * when the heart rate check was made.
	 * @param heartRate
	 * @param timeChecked
	 */
	public void updateHeartRate(int heartRate, Date timeChecked) {
		this.heartRate.add(new HeartRate(heartRate, timeChecked));
	}
	
	/**
	 * Returns the most current Blood Pressure recorded.
	 * @return the most current blood pressure recorded
	 */
	public BloodPressure getCurrentBloodPressure() {
		return bloodPressure.get(bloodPressure.size() - 1);
	}
	
	/**
	 * Returns the most current HeartRate recorded.
	 * @return the most current HeartRate recorded
	 */
	public HeartRate getCurrentHeartRate() {
		return heartRate.get(heartRate.size() - 1);
	}
	
	/**
	 * Returns the most current Temperature recorded.
	 * @return the most current temperature recorded.
	 */
	public Temperature getCurrentTemperature() {
		return temp.get(temp.size() - 1);
	}
	
	/**
	 * Tells if heart rate is in critical rage. Used for calculating
	 * a Patient's urgency.
	 * @return 	true if HR >= 100 or HR <= 50
	 * 			false otherwise
	 */
	public boolean isHRCrit() {
		int hr = getCurrentHeartRate().getHeartRate();
		if (hr >= CRIT_HR_HIGH || hr <= CRIT_HR_LOW) {
			return true;
		}
		return false;
	}
	
	/**
	 * Tells if temperature is in critical range. Used for calculating
	 * a Patient's urgency.
	 * @return	true if temp >= CRITTEMP degrees celcius
	 * 			false otherwise
	 */
	public boolean isTempCrit() {
		double temperature = getCurrentTemperature().getTemp();
		if (temperature >= CRIT_TEMP) {
			return true;
		}
		return false;
	}
	
	/**
	 * Tells if blood pressure is in critical range. Used for calculating
	 * a Patient's urgency.
	 * @return	true if systolic blood pressure >= 140 or diastolic blood
	 * 			pressure >= 90
	 * 			false otherwise
	 */
	public boolean isBPCrit() {
		BloodPressure bp = getCurrentBloodPressure();
		int sysBP = bp.getSysBP();
		int diBP = bp.getDiBP();
		if (sysBP >= CRIT_SYSBP || diBP >= CRIT_DIBP) {
			return true;
		}
		return false;
	}
}
