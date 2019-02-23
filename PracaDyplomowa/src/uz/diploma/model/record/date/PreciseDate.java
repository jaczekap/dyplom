package uz.diploma.model.record.date;

import java.io.Serializable;
import java.util.LinkedHashMap;

import org.joda.time.DateTimeFieldType;
import org.joda.time.Partial;
import org.joda.time.ReadableInstant;

/**
 * @author Spider
 * 
 * Class represents precise moment in time. Precise date can be very accurate
 * including century, year, month, day and time (hours and minutes) 
 *
 */
public class PreciseDate extends Date implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5933942436223296130L;
	private StringToDateElementConverter converter;
	private Partial date;
	
	private PreciseDate(boolean isBeforeChristus) {
		this.isBeforeChristus = isBeforeChristus; 
		converter = new StringToDateElementConverter();
	}
	
	public PreciseDate(String[] dateElements, boolean isBeforeChristus) {
		this(isBeforeChristus);
		date = convertToDate(dateElements[0], dateElements[1], dateElements[2], dateElements[3], dateElements[4], isBeforeChristus);
	}
	
	public PreciseDate(String century, String year, String month, String day, String time, boolean isBeforeChristus) {
		this(isBeforeChristus);
		date = convertToDate(century, year, month, day, time, isBeforeChristus);
	}
	
	public PreciseDate(int century, int year, int month, int day, int hour, int minute, boolean isBeforeChristus) {
		this(isBeforeChristus);
		date = convertToDate(century, year, month, day, hour, minute, isBeforeChristus);
	}
	
	public PreciseDate(int[] dateElements, boolean isBeforeChristus) {
		this(isBeforeChristus);
		date = convertToDate(dateElements[0], dateElements[1], dateElements[2], dateElements[3], dateElements[4], dateElements[5], isBeforeChristus);
	}

	public String getCenturyAsString() {
		if(date.isSupported(DateTimeFieldType.centuryOfEra()))
			return date.toString("C");
		return "";
	}

	public String getYearAsString() {
		if(date.isSupported(DateTimeFieldType.year()))
			return date.toString("y");
		return "";
	}

	public String getMonthAsString() {
		if(date.isSupported(DateTimeFieldType.monthOfYear()))
			return date.toString("MM");
		return "";
	}

	public String getDayAsString() {
		if(date.isSupported(DateTimeFieldType.dayOfMonth()))
			return date.toString("dd");
		return "";
	}

	public String getTime() {
		if(date.isSupported(DateTimeFieldType.hourOfDay()))
			return date.toString("HH:mm");
		return "";
	}

	@Override
	public boolean isInDateRange(int year) {
		if(date.isSupported(DateTimeFieldType.year()))
			if(date.get(DateTimeFieldType.year()) == year)
				return true;
		return false;
	}
	
	public boolean isBefore(PreciseDate dateToCompare) {
		ReadableInstant thisDate = date.toDateTime(null);
		ReadableInstant otherDate = dateToCompare.getDate().toDateTime(null);
		
		return thisDate.isBefore(otherDate);
	}

	public int getCentury() {
		return date.get(DateTimeFieldType.centuryOfEra());
	}

	public int getYear() {
		return date.get(DateTimeFieldType.year());
	}

	public int getMonth() {
		return date.get(DateTimeFieldType.monthOfYear());
	}

	public int getDay() {
		return date.get(DateTimeFieldType.dayOfMonth());
	}

	public int getHour() {
		return date.get(DateTimeFieldType.hourOfDay());
	}

	public int getMinute() {
		return date.get(DateTimeFieldType.minuteOfHour());
	}
	
	public Partial getDate() {
		return date;
	}
	
	private Partial convertToDate(int century, int year, int month, int day, int hour, int minute, boolean isBeforeChristus) {
		int era = isBeforeChristus ? 0 : 1;
		
		return convertToDate(year, era, century, month, day, hour, minute);
	}
	
	private Partial convertToDate(String century, String year, String month, String day, String time, boolean isBeforeChristus) {
		int years = converter.convertToYear(year);
		int era = isBeforeChristus ? 0 : 1;
		int cen = calculateCentury(century, years);
		int mon = converter.convertToMonth(month);
		int days = converter.convertToDay(day);
		int hour = converter.convertToHour(time);
		int minute = converter.convertToMinute(time);
		
		return convertToDate(era, cen, years, mon, days, hour, minute);
	}

	private Partial convertToDate(int era, int century, int years, int month, int day, int hour, int minute) {
		int numberOfTimeElements = 0;
		
		LinkedHashMap<DateTimeFieldType, Integer> tempDateFields = new LinkedHashMap<DateTimeFieldType, Integer>();
		tempDateFields.put(DateTimeFieldType.era(), era);
		tempDateFields.put(DateTimeFieldType.centuryOfEra(), century);
		tempDateFields.put(DateTimeFieldType.year(), years);
		tempDateFields.put(DateTimeFieldType.monthOfYear(), month);
		tempDateFields.put(DateTimeFieldType.dayOfMonth(), day);
		tempDateFields.put(DateTimeFieldType.hourOfDay(), hour);
		tempDateFields.put(DateTimeFieldType.minuteOfHour(), minute);
		
		LinkedHashMap<DateTimeFieldType, Integer> dateFields = new LinkedHashMap<DateTimeFieldType, Integer>();
		
		for(DateTimeFieldType fieldType : tempDateFields.keySet()) {
			if(tempDateFields.get(fieldType) > -1) {
				dateFields.put(fieldType, tempDateFields.get(fieldType));
				numberOfTimeElements++;
			}
		}
		
		DateTimeFieldType[] fieldTypes = new DateTimeFieldType[numberOfTimeElements];
		int[] fieldValues = new int[numberOfTimeElements];
		
		int counter = 0;
		for(DateTimeFieldType fieldType : dateFields.keySet()) {
			fieldTypes[counter] = fieldType;
			fieldValues[counter] = dateFields.get(fieldType);
			counter++;
		}
		return new Partial(fieldTypes, fieldValues);
	}
	
	private int calculateCentury(String century, int years) {
		if(years != -1)
			return ((years - 1) / 100) + 1;
		return converter.convertToCentury(century);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PreciseDate other = (PreciseDate) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}
	
	

}
