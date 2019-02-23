package test.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uz.diploma.model.record.element.HistoricalCharacter;
import uz.diploma.model.record.element.HistoricalPeriod;

public class HistoricalCharacterTest {
	
	HistoricalCharacter testCharacter;

	@Before
	public void setUp() throws Exception {
		testCharacter = new HistoricalCharacter("Perykles");
		testCharacter.setHistoricalPeriod(HistoricalPeriod.ANTIQUITY);
		testCharacter.setDescription("Ancient Greece");
	}

	@Test
	public void matchesName_MatchingStringArgumentGiven_ShouldReturnTrue() {
		assertTrue(testCharacter.matchesName("Perykles"));
	}
	
	@Test
	public void matchesName_NonMatchingStringArgumentGiven_ShouldReturnFalse() {
		assertFalse(testCharacter.matchesName("Alkibiades"));
	}

	@Test
	public void matchesPeriod_MatchingPeriodIsGiven_ShouldReturnTrue() {
		assertTrue(testCharacter.matchesPeriod(HistoricalPeriod.ANTIQUITY));
	}
	
	@Test
	public void matchesPeriod_NonMatchingPeriodIsGiven_ShouldReturnFalse() {
		assertFalse(testCharacter.matchesPeriod(HistoricalPeriod.MIDDLE_AGES));
	}

	@Test
	public void matchesDescription_MatchingStringArgumentIsGiven_ShouldReturTrue() {
		assertTrue(testCharacter.matchesDescription("Ancient Greece"));
	}
	
	@Test
	public void matchesDescription_NonMatchingStringArgumentIsGiven_ShouldReturFalse() {
		assertFalse(testCharacter.matchesDescription("Ancient Rome"));
	}

}
