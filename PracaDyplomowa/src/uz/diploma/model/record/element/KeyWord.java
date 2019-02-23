package uz.diploma.model.record.element;

import java.io.Serializable;

import uz.diploma.tools.Validator;

/**
 * @author Spider
 * 
 * Class represent keyword that might be associated with any record
 *
 */
public class KeyWord implements Serializable, RecordElement {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6176423340901896369L;
	private String keyWord;

	
	public KeyWord(String keyWord) {
		Validator.ensureIsNotNull(keyWord, "keyWord");
		Validator.ensureExists(keyWord, "Keyword");
		this.keyWord = keyWord;
	}

	

	public String getKeyWord() {
		return keyWord;
	}



	public void setKeyWord(String keyWord) {
		Validator.ensureExists(keyWord, "Keyword");
		this.keyWord = keyWord;
	}



	@Override
	public String toString() {
		return keyWord;
	}

}
