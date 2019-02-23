package uz.diploma.model.record.element;

import java.io.Serializable;

import uz.diploma.tools.Validator;

/**
 * @author Spider
 * 
 * Class represents reference from one record to another.
 * Reference stores required informations such as record to or from reference is, 
 * specific location in referenced record (page number, volume, etc.)
 *
 */
public class Reference implements Serializable, RecordElement {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2357498900693593369L;
	private Record referenceRecord;
	private String note;
	private String location;
	private boolean isToReference;
	
	
	
	/**
	 * @param referenceRecord - referenced record
	 * @param note - notes describing reference 
	 * @param locations - location in referenced position
	 * @param isToReference - specifies if reference is to this record or from this record
	 */
	public Reference(Record referenceRecord, String note, String locations,
			boolean isToReference) {
		Validator.ensureIsNotNull(referenceRecord, "referenceRecord");
		Validator.ensureIsNotNull(note, "notes");
		Validator.ensureIsNotNull(locations, "locations");
		Validator.ensureIsNotNull(isToReference, "isToReference");
		this.referenceRecord = referenceRecord;
		this.note = note;
		this.location = locations;
		this.isToReference = isToReference;
	}
	public Record getReferenceRecord() {
		return referenceRecord;
	}
	public void setReferenceRecord(Record referenceRecord) {
		this.referenceRecord = referenceRecord;
	}
	public String getNotes() {
		return note;
	}
	public void setNotes(String notes) {
		this.note = notes;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public boolean isToReference() {
		return isToReference;
	}
	public void setToReference(boolean isToReference) {
		this.isToReference = isToReference;
	}
	@Override
	public String toString() {
		return referenceRecord.getTitle() + ", " + location + ", " + note;
	}
	
	

}
