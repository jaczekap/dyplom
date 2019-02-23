package uz.diploma.model.record.element;

import java.io.Serializable;

import uz.diploma.tools.Validator;

/**
 * @author Spider
 * 
 * Class represent historical character that might be associated with any record
 *
 */
public class HistoricalCharacter implements Serializable, RecordElement {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1046544708119669945L;
	private String name = "";
	private HistoricalPeriod historicalPeriod;
	private String description = "";
	
	public HistoricalCharacter(String name) {
		Validator.ensureIsNotNull(name, "name");
		Validator.ensureExists(name, "Character name");
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String characterName) {
		Validator.ensureExists(name, "Character name");
		this.name = characterName;
	}

	public HistoricalPeriod getHistoricalPeriod() {
		return historicalPeriod;
	}
	
	public void setHistoricalPeriod(HistoricalPeriod historicalPeriod) {
		this.historicalPeriod = historicalPeriod;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String desription) {
		this.description = desription;
	}

	@Override
	public String toString() {
		return name;
	}
	
	/**
	 * Tests if given string matches the name of the character
	 * @param testString - string to be tested
	 * @return - true if tested string matches this character name
	 */
	public boolean matchesName(String testString) {
		if(name.trim().equalsIgnoreCase(testString.trim()))
			return true;
		return false;
	}
	
	/**
	 * Tests if given string matches historical period
	 * @param testPeriod - string to be tested
	 * @return - true if tested string matches this character historical period
	 */
	public boolean matchesPeriod(HistoricalPeriod testPeriod) {
		if(historicalPeriod == null && testPeriod == null)
			return true;
		if(historicalPeriod != null )
			if(historicalPeriod.equals(testPeriod))
				return true;
		return false;
	}
	
	/**
	 * Tests if given string matches description of the character
	 * @param testString - string to be tested
	 * @return - true if tested string matches this character description
	 */
	public boolean matchesDescription(String testString) {
		if(description.trim().equalsIgnoreCase(testString.trim()))
			return true;
		return false;
	}
	
	/**
	 * Tests if given strings matches all fields (name, historical period and description)
	 * @param nameTest - string to test if matches character name
	 * @param periodTest - period to test if matches character historical period
	 * @param descriptionTest - string to test if matches character description
	 * @return true if all tested values matches 
	 */
	public boolean matchesEventData(String nameTest, HistoricalPeriod periodTest, String descriptionTest) {
		if(matchesName(nameTest) && matchesPeriod(periodTest) && matchesDescription(descriptionTest))
			return true;
		return false;
	}

}
