package uz.diploma.model.record.date;

import java.io.Serializable;

import org.joda.time.DateTimeFieldType;
import org.joda.time.Interval;
import org.joda.time.Partial;
import org.joda.time.ReadableInstant;

import uz.diploma.tools.Validator;

/**
 * @author Spider
 * 
 * Class represents time period with start and end date. Can be associated with historical event.
 *
 */
public class RangeDate extends Date implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7000058044282806682L;
	private PreciseDate startDate;
	private PreciseDate endDate;
	
	public RangeDate(PreciseDate startDate, PreciseDate endDate) {
		Validator.ensureIsNotNull(startDate, "startYear");
		Validator.ensureIsNotNull(endDate, "endYear");
		this.startDate = startDate;
		this.endDate = endDate;
		this.isBeforeChristus = startDate.isBeforeChristus();
	}
	
	public PreciseDate getStartDate() {
		return startDate;
	}
	
	public void setStartDate(PreciseDate startDate) {
		this.startDate = startDate;
	}
	
	public PreciseDate getEndDate() {
		return endDate;
	}
	
	public void setEndDate(PreciseDate endDate) {
		this.endDate = endDate;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RangeDate other = (RangeDate) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	@Override
	public boolean isInDateRange(int year) {
		int startDateEra = this.startDate.isBeforeChristus() ? 0 : 1;
		int endDateEra = this.endDate.isBeforeChristus() ? 0 : 1;
		ReadableInstant startDate = this.startDate.getDate().toDateTime(null).withEra(startDateEra);
		ReadableInstant endDate = this.endDate.getDate().toDateTime(null).withEra(endDateEra);
		ReadableInstant dateToCheck = new Partial(DateTimeFieldType.year(), year).toDateTime(null).withEra(startDateEra);
		boolean isInDate = false;
		System.out.println(startDate.get(DateTimeFieldType.era()));
		
		try {
			Interval durationOfEvent = new Interval(startDate, endDate);
			System.out.println(durationOfEvent);
			isInDate = durationOfEvent.contains(dateToCheck);
		} catch (IllegalArgumentException e) {
			return false;
		}
		return isInDate;
	}
	
}
