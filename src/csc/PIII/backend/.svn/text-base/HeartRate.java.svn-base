package csc.PIII.backend;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.text.format.DateFormat;

/**
 * A representation of the Heart Rate of a Patient
 * @author ZDesk
 *
 */
public class HeartRate implements Serializable {

	/**
	 * generated serialVersionUID
	 */
	private static final long serialVersionUID = 1339035477249720340L;
	
	/** the heart rate of the patient */
	private int heartRate;
	/** the time when the heart rate was checked */
	private Date timeChecked;
	
	/**
	 * Constructs a Heart Rate representation with the heart rate and time/date
	 * when it was checked.
	 * @param heartRate heart rate of the patient
	 * @param timeChecked time when check was done
	 */
	public HeartRate(int heartRate, Date timeChecked) {
		this.heartRate = heartRate;
		this.timeChecked = timeChecked;
	}

	/**
	 * Gets the heart rate
	 * @return heart reate
	 */
	public int getHeartRate() {
		return heartRate;
	}

	/**
	 * Sets the heart rate
	 * @param heartRate heart rate
	 */
	public void setHeartRate(int heartRate) {
		this.heartRate = heartRate;
	}

	/**
	 * Gets the time/date when check was done.
	 * @return time/date of check
	 */
	public Date getTimeChecked() {
		return timeChecked;
	}

	/**
	 * Sets the time/date when the HeartRate was checked.
	 * @param timeChecked
	 */
	public void setTimeChecked(Date timeChecked) {
		this.timeChecked = timeChecked;
	}
	
	/**
	 * String representation of the HeartRate
	 */
	@Override
	public String toString() {
		return "("+ DateFormat.format("yyyy-M-d H:m", timeChecked) +
				"), " + "HR: " + heartRate + "\n";
	}
	
	/**
	 * Returns the save string generated for the HeartRate, used for saving to
	 * file.
	 * @return
	 */
	public String saveString() {
		// date format to use for all dates/times
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.CANADA);
		String s = formatter.format(timeChecked) + ">>";
		s += heartRate + "\n";
		return s;
	}
}
