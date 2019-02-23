package uz.diploma.model.record.element;

import java.io.Serializable;

import uz.diploma.model.record.date.Date;
import uz.diploma.tools.Validator;

/**
 * @author Spider
 * 
 * Class represent historical event that might be associated with any record
 *
 */
public class HistoricalEvent implements Serializable, RecordElement {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6343974853829266348L;
	private String name;
	private String description;
	private Date eventDate;
	
	public HistoricalEvent(String name) {
		Validator.ensureIsNotNull(name, "name");
		Validator.ensureExists(name, "Historical Event name");
		this.name = name;
		description = "";
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		Validator.ensureExists(name, "Historical Event name");
		this.name = name;
	}
	
	public String getDesciption() {
		return description;
	}
	
	public void setDesciption(String desciption) {
		this.description = desciption;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	@Override
	public String toString() {
		return name;
	}
	
	/**
	 * Tests if given string matches the name of the event
	 * @param testString - string to be tested
	 * @return - true if tested string matches this event name
	 */
	public boolean matchesName(String testString) {
		if(name.trim().equalsIgnoreCase(testString.trim()))
			return true;
		return false;
	}
	
	/**
	 * Tests if given string matches description of the event
	 * @param testString - string to be tested
	 * @return - true if tested string matches this event description
	 */
	public boolean matchesDescription(String testString) {
		if(description.trim().equalsIgnoreCase(testString.trim()))
			return true;
		return false;
	}
	
	/**
	 * Tests if given date matches this event date
	 * @param eventDate - date to be tested
	 * @return true if tested date matches this event date
	 */
	public boolean matchesEventDate(Date eventDate) {
		return this.eventDate.equals(eventDate);
	}
}
