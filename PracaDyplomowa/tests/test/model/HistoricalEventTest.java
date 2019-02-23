package test.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uz.diploma.model.record.date.PreciseDate;
import uz.diploma.model.record.element.HistoricalEvent;

public class HistoricalEventTest {
	
	HistoricalEvent testEvent;
	PreciseDate matchingDate;
	PreciseDate nonMatchingDate;

	@Before
	public void setUp() throws Exception {
		testEvent = new HistoricalEvent("Powstanie Spartakusa");
		testEvent.setEventDate(new PreciseDate("1", "71", null, null, null, true));
		testEvent.setDesciption("Ancient Rome");
		matchingDate = new PreciseDate("1", "71", null, null, null, true);
		nonMatchingDate = new PreciseDate("1", "72", null, null, null, true);
	}

	@Test
	public void matchesName_MatchingStringArgumentGiven_ShouldReturnTrue() {
		assertTrue(testEvent.matchesName("Powstanie Spartakusa"));
	}
	
	@Test
	public void matchesName_NonMatchingStringArgumentGiven_ShouldReturnFalse() {
		assertFalse(testEvent.matchesName("Zburzenie Bastylii"));
	}

	@Test
	public void matchesDescription_MatchingStringArgumentGiven_ShouldReturnTrue() {
		assertTrue(testEvent.matchesDescription("Ancient Rome"));
	}
	
	@Test
	public void matchesDescription_NonMatchingStringArgumentGiven_ShouldReturnFalse() {
		assertFalse(testEvent.matchesDescription("Ancient Greece"));
	}

	@Test
	public void matchesEventDate_MatchingDateGiven_ShouldReturnTrue() {
		assertTrue(testEvent.matchesEventDate(matchingDate));
	}
	
	@Test
	public void matchesEventDate_NonMatchingDateGiven_ShouldReturnFalse() {
		assertFalse(testEvent.matchesEventDate(nonMatchingDate));
	}

}
