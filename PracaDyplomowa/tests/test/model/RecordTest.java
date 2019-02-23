package test.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uz.diploma.model.record.element.Author;
import uz.diploma.model.record.element.HistoricalCharacter;
import uz.diploma.model.record.element.HistoricalEvent;
import uz.diploma.model.record.element.HistoricalPeriod;
import uz.diploma.model.record.element.KeyWord;
import uz.diploma.model.record.element.Location;
import uz.diploma.model.record.element.MediumType;
import uz.diploma.model.record.element.Quotation;
import uz.diploma.model.record.element.Record;

public class RecordTest {
	
	private Record record;
	
	@Before
	public void setUp() {
		record = new Record("Rzymianie");
	}
	
	@Test
	public void isInKeyWord_ShouldReturnTrue() {
		record.addKeyWord(new KeyWord("Ala ma kota"));
		record.addKeyWord(new KeyWord("Alicja w krainie czarów"));
		String testString = "ala";
		
		assertTrue(record.isInKeyWord(testString));
	}

	@Test
	public void isPartOfAuthorName_ShouldReturnTrue() {
		record.setAuthor(new Author("Kazio Berbeluch"));
		String testString = "luch";
		
		assertTrue(record.isPartOfAuthorName(testString));
	}

	@Test
	public void isInTitle_ShouldReturnTrue() {
		String testString = "rzymianie";
		
		assertTrue(record.isInTitle(testString));
	}

	@Test
	public void isInTableOfContents_ShouldReturnTrue() {
		record.setTableOfContents("1. Wstêp 2. Pocz¹tki imperium 3. Upadek Imperium");
		String testString = "Wstêp";
		
		assertTrue(record.isInTableOfContents(testString));
	}

	@Test
	public void isInDescription_ShouldReturnTrue() {
		record.setDescription("Pozycja wskazuje na wyj¹tkowy udzia³ Semitów w");
		String testString = "pozycja wskazuje";
		
		assertTrue(record.isInDescription(testString));
	}

	@Test
	public void isPartOfMediumTypeName_ShouldReturnTrue() {
		record.setMedium(MediumType.BOOK);
		String testString = "book";
		
		assertTrue(record.isPartOfMediumTypeName(testString));
	}

	@Test
	public void isPartOfPeriodName_ShouldReturnTrue() {
		record.setHistoricalPeriod(HistoricalPeriod.MIDDLE_AGES);
		String testString = "ages";
		
		assertTrue(record.isPartOfPeriodName(testString));
	}

	@Test
	public void isInQuotation_ShouldReturnTrue() {
		record.addQuotation(new Quotation("20", "Wszyscy rycerze byli ju¿ gotowi do szturmu"));
		String testString = "byli ju¿";
		
		assertTrue(record.isInQuotation(testString));
	}

	@Test
	public void isPartOfCharacterName_ShouldReturnTrue() {
		HistoricalCharacter character = new HistoricalCharacter("Wilhelm Zdobywca");
		record.addCharacter(character);
		String testString = "elm zdob";
		
		assertTrue(record.isPartOfCharacterName(testString));
	}

	@Test
	public void isPartOfEventName_ShouldReturnTrue() {
		HistoricalEvent event = new HistoricalEvent("Rewolucja Francuska");
		record.addEvent(event);
		String testString = "francuska";
		
		assertTrue(record.isPartOfEventName(testString));
	}

	@Test
	public void isPartOfLocationNameShouldReturnTrue() {
		record.addLocation(new Location("Andaluzja"));
		String testString = "andaluzja";
		
		assertTrue(record.isPartOfLocationName(testString));
	}

}
