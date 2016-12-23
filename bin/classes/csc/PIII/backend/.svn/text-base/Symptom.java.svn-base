package csc.PIII.backend;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A repersentation of Symptoms of a Patient.
 * @author Zaid
 *
 */
public class Symptom implements Serializable {

	/** Eclipse generated value */
	private static final long serialVersionUID = -7468299404787381366L;
	
	/** The description of the symptoms to be recorded */
	String description;
	/** The time/date when the symptoms were recorded */
	Date timeChecked;
	
	/**
	 * Constructs a Symptom with the description of the symptoms and the
	 * time/date when the symptoms were recorded.
	 * @param descripton description of symptoms
	 * @param timeChecked time/date when symptoms were recorded
	 */
	public Symptom(String descripton, Date timeChecked) {
		this.description = descripton;
		this.timeChecked = timeChecked;
	}

	/**
	 * Gets the description of the symptoms.
	 * @return descripton of symptoms
	 */
	public String getDescripton() {
		return description;
	}

	/**
	 * Gets the time/date the symptoms were checked.
	 * @return the time/date when symptoms were checked.
	 */
	public Date getTimeChecked() {
		return timeChecked;
	}

	@Override
	public String toString() {
		return "" + timeChecked + ", Symptoms: " + description;
	}
	
	/**
	 * Returns the formatted string use for saving.
	 * @return formatted save string
	 */
	public String saveString() {
		// date format to use for all dates/times
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.CANADA);
		String s = formatter.format(timeChecked) + ">>";
		s += description + "\n";
		return s;
	}
	
}
