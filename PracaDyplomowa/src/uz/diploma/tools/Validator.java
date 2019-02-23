package uz.diploma.tools;

public class Validator {

	public static void ensureIsNotNull(Object objectToTest, String objectName) {
			ensureIsNotNull(objectToTest, objectName, " cannot be null"); 
	}
	
	public static void ensureIsNotNull(Object objectToTest, String objectName, String message) {
		if(objectToTest == null) {
			throw new IllegalArgumentException(objectName + " " + message);
		}
	}
	
	public static boolean containsSpaceCharacter(String testString) {
		if(testString.contains(" "))
			return true;
		return false;
	}
	
	public static void ensureExists(String stringToTest, String name) {
		if(stringToTest.equals(""))
			throw new IllegalArgumentException(stringToTest + " cannot be empty");
	}
	
	public static void ensureCanBeParsedToInteger(String stringToTest) {
		try{
			Integer.parseInt(stringToTest);
		} catch(NumberFormatException e){
			throw new IllegalArgumentException(stringToTest + ": can only contains digits");
		}
	}
}
