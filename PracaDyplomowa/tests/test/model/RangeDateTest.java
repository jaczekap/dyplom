package test.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uz.diploma.model.record.date.PreciseDate;
import uz.diploma.model.record.date.RangeDate;

public class RangeDateTest {
	
	PreciseDate date_BC_1974_12_24_No_Time_Given;
	PreciseDate date_BC_1920;
	PreciseDate date_AD_1974_12_24_No_Time_Given;
	PreciseDate date_AD_1920;
	RangeDate date_1970_To_1920_BC;
	RangeDate date_1920_To_1970_AD;

	@Before
	public void setUp() throws Exception {
		date_BC_1974_12_24_No_Time_Given = new PreciseDate("20", "1974", "12", "24", null, true);
		date_BC_1920 = new PreciseDate("20", "1920", null, null, null, true);
		date_AD_1974_12_24_No_Time_Given = new PreciseDate("20", "1974", "12", "24", null, false);
		date_AD_1920 = new PreciseDate("20", "1920", null, null, null, false);
		date_1970_To_1920_BC = new RangeDate(date_BC_1974_12_24_No_Time_Given, date_BC_1920);
		date_1920_To_1970_AD = new RangeDate(date_AD_1920, date_AD_1974_12_24_No_Time_Given);
	}

	@Test
	public void isInDateRange_DateObjectPointingOnBCDateAndArgumentIsInDateRange_ShouldReturnTrue() {
		assertTrue(date_1970_To_1920_BC.isInDateRange(1950));
	}
	
	@Test
	public void isInDateRange_DateObjectPointingOnBCDateAndArgumentIsNotInDateRange_ShouldReturnFalse() {
		assertFalse(date_1970_To_1920_BC.isInDateRange(1980));
	}
	
	@Test
	public void isInDateRange_DateObjectPointingOnADDateAndArgumentIsInDateRange_ShouldReturnTrue() {
		assertTrue(date_1920_To_1970_AD.isInDateRange(1930));
	}
	
	@Test
	public void isInDateRange_DateObjectPointingOnADDateAndArgumentIsNotInDateRange_ShouldReturnFalse() {
		assertFalse(date_1920_To_1970_AD.isInDateRange(190));
	}

}
