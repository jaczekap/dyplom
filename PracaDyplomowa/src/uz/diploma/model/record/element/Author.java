package uz.diploma.model.record.element;

import java.io.Serializable;

import uz.diploma.tools.Validator;

/**
 * @author Spider
 * 
 * Class represents author of the record it belongs to
 *
 */
public class Author implements Serializable, RecordElement {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4592971779047859087L;
	private String name;

	public Author(String name) {
		Validator.ensureIsNotNull(name, "name");
		this.name = name;
	}

	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}



	@Override
	public String toString() {
		return name;
	}	
	
	

}
