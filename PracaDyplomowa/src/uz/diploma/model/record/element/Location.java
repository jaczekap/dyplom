package uz.diploma.model.record.element;

import java.io.Serializable;

import uz.diploma.tools.Validator;

/**
 * @author Spider
 * 
 * Class represent geographical location that might be associated with any record
 *
 */
public class Location implements Serializable, RecordElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3442317977592708939L;
	private String location;

	public Location(String location) {
		Validator.ensureIsNotNull(location, "location");
		Validator.ensureExists(location, "Location");
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		Validator.ensureExists(location, "Location");
		this.location = location;
	}

	@Override
	public String toString() {
		return location;
	}
	
	
	
}
