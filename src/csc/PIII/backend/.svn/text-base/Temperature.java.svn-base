package csc.PIII.backend;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.text.format.DateFormat;

/**
 * The representation of a Patient's Temperature, along with the time/date
 * when it was checked.
 * @author Zaid
 *
 */
public class Temperature implements Serializable {
	
	/** Eclipse generated value */
	private static final long serialVersionUID = -736176273936893585L;
	
	/** The temperature in degrees celcius */
	private Double temp;
	/** The time/date when this temperature was checked*/
	private Date timeChecked;
	
	/**
	 * Constructs a Temperature that hold the temp. is degrees celcius, and the
	 * time when that temp. was checked.
	 * @param temp
	 * @param timeChecked
	 */
	public Temperature(Double temp, Date timeChecked) {
		this.temp = temp;
		this.timeChecked = timeChecked;
	}

	/**
	 * Gets the temperature.
	 * @return the temperature in degrees celcius
	 */
	public Double getTemp() {
		return temp;
	}

	/**
	 * Sets the temperature.
	 * @param temp temp in degrees celcius
	 */
	public void setTemp(Double temp) {
		this.temp = temp;
	}

	/**
	 * Gets the time/date when this temperature was checked.
	 * @return time/date of check
	 */
	public Date getTimeChecked() {
		return timeChecked;
	}

	/**
	 * Sets the time/date of when the temperature was checked.
	 * @param timeChecked
	 */
	public void setTimeChecked(Date timeChecked) {
		this.timeChecked = timeChecked;
	}
	
	/**
	 * Returns the string representatin of this Temperature.
	 */
	@Override
	public String toString() {
		return "("+ DateFormat.format("yyyy-M-d H:m", timeChecked) +
				"), " + temp + " degrees Celcius\n";
	}
	
	/**
	 * Returns the save string generated from this Temperature.
	 * @return the generated save string
	 */
	public String saveString() {
		// date format to use for all dates/times
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.CANADA);
		String s = formatter.format(timeChecked) + ">>";
		s += temp + "\n";
		return s;
	}
}
