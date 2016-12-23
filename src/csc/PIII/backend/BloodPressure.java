package csc.PIII.backend;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.text.format.DateFormat;

/**
 * The representation of BloodPressure, along with the time when it was
 * checked.
 * @author Zaid
 *
 */
public class BloodPressure implements Serializable {

	/**
	 * generated serializableVersionUID
	 */
	private static final long serialVersionUID = 3132296135373257842L;
	
	/** The systolic and diastolic blood pressures */
	private int sysBP, diBP;
	/** the date when the blood pressure was checked */
	private Date timeChecked;
	
	/**
	 * Constructs a BloodPressure representation with systolic and diastolic
	 * blood pressure indicators, as well as the time when the blood pressure
	 * was checked.
	 * @param sysBP
	 * @param diBP
	 * @param timeChecked
	 */
	public BloodPressure(int sysBP, int diBP, Date timeChecked) {
		this.sysBP = sysBP;
		this.diBP = diBP;
		this.timeChecked = timeChecked;
	}

	/**
	 * Gets the systolic BP
	 * @return systolic BP
	 */
	public int getSysBP() {
		return sysBP;
	}

	/**
	 * Sets the systolic BP
	 * @param sysBP systolic BP
	 */
	public void setSysBP(int sysBP) {
		this.sysBP = sysBP;
	}

	/**
	 * Gets the diastolic BP
	 * @return diastolic BP
	 */
	public int getDiBP() {
		return diBP;
	}

	/**
	 * Sets the diastolic BP
	 * @param diBP diastolic BP
	 */
	public void setDiBP(int diBP) {
		this.diBP = diBP;
	}

	/**
	 * Gets the time/date when the blood pressure was checked.
	 * @return time/date when BP was checked
	 */
	public Date getTimeChecked() {
		return timeChecked;
	}

	/**
	 * Sets the time/date when the blood pressure was checked.
	 * @param timeChecked time/date when BP was checked
	 */
	public void setTimeChecked(Date timeChecked) {
		this.timeChecked = timeChecked;
	}
	
	/**
	 * Returns the String representation of the BloodPressure, along with the
	 * time/date when it was checked.
	 */
	@Override
	public String toString() {
		return "("+ DateFormat.format("yyyy-M-d H:m", timeChecked) + "), " 
				+ "Systolic BP: " + sysBP
				+ ", Diastolic BP: " + diBP + "\n";
	}
	
	/**
	 * Returns the generated save string used for saving to file.
	 * @return
	 */
	public String saveString() {
		// date format to use for all dates/times
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.CANADA);
		String s = formatter.format(timeChecked) + ">>";
		s += sysBP + ">>" + diBP + "\n";
		return s;
	}
}
