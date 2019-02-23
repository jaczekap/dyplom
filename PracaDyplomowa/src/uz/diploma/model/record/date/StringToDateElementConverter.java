package uz.diploma.model.record.date;

import java.io.Serializable;

public class StringToDateElementConverter implements Serializable {
	
	public int convertToCentury(String century) {
		return convertToNumber(century, "century");
	}
	
	public int convertToYear(String year) {
		return convertToNumber(year, "year");
	}
	
	public int convertToMonth(String month) {
		int convertedMonth = convertToNumber(month, "month");
		return convertedMonth;
	}
	
	public int convertToDay(String day) {
		int convertedDay = convertToNumber(day, "day");
		return convertedDay;
	}
	
	public int convertToHour(String time) {
		if(time == null || time.trim().equals(""))
			return -1;
		String[] splitted = time.trim().split(":");
		int convertedHour = convertToNumber(splitted[0], "hour");
		return convertedHour;
	}
	
	public int convertToMinute(String time) {
		if(time == null || time.trim().equals(""))
			return -1;
		String[] splitted = time.trim().split(":");
		try {
			int convertedMinute = convertToNumber(splitted[1], "minute");
			return convertedMinute;
		} catch(ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Minute must be given if hour is not empty");
		}
	}
	
	private int convertToNumber(String stringToConvert, String name) {
		if(stringToConvert == null || stringToConvert.trim().equals(""))
			return -1;
		try {
			return Integer.parseInt(stringToConvert);
		} catch(NumberFormatException e) {
			throw new IllegalArgumentException(name + " can contains only digits");
		}
}

}
