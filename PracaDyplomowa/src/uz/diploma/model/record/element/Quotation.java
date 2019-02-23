package uz.diploma.model.record.element;

import java.io.Serializable;

import uz.diploma.tools.Validator;

/**
 * @author Spider
 * 
 * Class represent quotation that might be associated with any record
 *
 */
public class Quotation implements Serializable, RecordElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8627468857886330685L;
	private String location;
	String quote;
	
	/**
	 * @param location - location of the quotation (number or range of pages, volume etc.)
	 * @param quote - quotation
	 */
	public Quotation(String location, String quote) {
		Validator.ensureIsNotNull(location, "location");
		Validator.ensureIsNotNull(quote, "quote");
		this.location = location;
		this.quote = quote;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getQuote() {
		return quote;
	}
	
	public void setQuote(String quote) {
		this.quote = quote;
	}

	@Override
	public String toString() {
		return location + ", " + quote;
	}
	
	
}
