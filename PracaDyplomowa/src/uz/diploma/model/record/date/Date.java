package uz.diploma.model.record.date;

import java.io.Serializable;

import uz.diploma.tools.Validator;

/**
 * @author Spider
 * 
 * Class that need to be implemented by concrete classes that represent date
 *
 */
public abstract class Date implements Serializable {
	
	protected boolean isBeforeChristus;

	public boolean isBeforeChristus() {
		return isBeforeChristus;
	}

	/**
	 * @param isBeforeChristus - set if date is BC or AD
	 */
	public void setBeforeChristus(boolean isBeforeChristus) {
		Validator.ensureIsNotNull(isBeforeChristus, "isBeforeChristus");
		this.isBeforeChristus = isBeforeChristus;
	}
	
	/**
	 * Tests if given year in string representation matches this date
	 * @param year - string to be tested
	 * @return true if parameter matches this date year
	 */
	public abstract boolean isInDateRange(int year);
	
}
