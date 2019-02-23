package test.model;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import uz.diploma.model.record.date.PreciseDate;

public class PreciseDateTest {
	
	PreciseDate date_BC_1974_12_24_No_Time_Given;
	PreciseDate date_BC_1920;

	@Before
	public void setUp() throws Exception {
		date_BC_1974_12_24_No_Time_Given = new PreciseDate("20", "1974", "12", "24", null, true);
		date_BC_1920 = new PreciseDate("20", "1920", null, null, null, true);
	}

	@Test
	public void isBeforeThisDate_DateObjectPoitingOnDateBeforeThisDateIsGiven_ShouldReturnTrue() throws NumberFormatException, ParseException {
		assertTrue(date_BC_1920.isBefore(date_BC_1974_12_24_No_Time_Given));
	}
	
	@Test
	public void isBeforeThisDate_DateObjectPoitingOnDateAfterThisDateIsGiven_ShouldReturnFalse() throws NumberFormatException, ParseException {
		assertFalse(date_BC_1974_12_24_No_Time_Given.isBefore(date_BC_1920));
	}

}
