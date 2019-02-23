package testData;

import uz.diploma.model.project.Project;
import uz.diploma.model.record.date.PreciseDate;
import uz.diploma.model.record.date.RangeDate;
import uz.diploma.model.record.element.Author;
import uz.diploma.model.record.element.HistoricalCharacter;
import uz.diploma.model.record.element.HistoricalEvent;
import uz.diploma.model.record.element.HistoricalPeriod;
import uz.diploma.model.record.element.KeyWord;
import uz.diploma.model.record.element.Location;
import uz.diploma.model.record.element.MediumType;
import uz.diploma.model.record.element.Quotation;
import uz.diploma.model.record.element.Record;
import uz.diploma.model.record.element.Reference;
import uz.diploma.model.record.source.SourceLocationFactory;
import uz.diploma.model.record.source.SourceType;

public class DataTestFiller {
	
	Project project = new Project();
	Record record = new Record("Historia Grecji");
	Record record1 = new Record("Historia Mezopotamii");
	Record record2 = new Record("TW Lech Wa³êsa");
	Record record3 = new Record("Akropol");
	Record record4 = new Record("Henryk IV");
	Record record5 = new Record("Rzymianie");
	SourceLocationFactory factory = new SourceLocationFactory();
	
	public DataTestFiller() {
		createTestRecords();
	}
	
	public void createTestRecords() {
		Author autor = new Author("Kazio Berbeluch");
		record.setHistoricalPeriod(HistoricalPeriod.MIDDLE_AGES);
		record.setAuthor(autor);
		record.setPublishingYear("2007");
		record.addKeyWord(new KeyWord("walka o tron Anglii"));
		Location location = new Location("Grecja");
		record.addLocation(location);
		HistoricalCharacter character = new HistoricalCharacter("Perykles");
		character.setHistoricalPeriod(HistoricalPeriod.ANTIQUITY);
		record.addCharacter(character);
		record.setMedium(MediumType.BOOK);
		record.addSource(factory.getSource(SourceType.EXTERNAL, "Biblioteka Uniwersytecka"));
		record.addSource(factory.getSource(SourceType.FILE, "C:/Users/Spider/Downloads/Beginning Blender.pdf"));//new SourceLocation(SourceType.FILE, "C:/Users/Spider/Downloads/Beginning Blender.pdf"));
		record.addSource(factory.getSource(SourceType.INTERNET, "www.uz.zgora.pl"));
		record.addSource(factory.getSource(SourceType.FILE, "C:/Users/Spider/Desktop/Logo.jpg"));
		HistoricalEvent event = new HistoricalEvent("Bitwa pod Maratonem");
		event.setEventDate(new PreciseDate("5", "480", null, null, null, true));
		Quotation quotation = new Quotation("104", "192 Ateñczyków ruszy³o naprzeciw kilkutysiêcznej armii perskiej");
		record.addQuotation(quotation);
		record.setDescription("Pozycja pokazuje winnym œwietle ni¿ ogólnie dostêpne pozycje, rolê rodu Alkmeonidów w umacnianiu pozycji Aten wœród greckich Pañstw-Miast");
		record.setTableOfContents("1. Grecja w okresie dominacji Myken \n2. Dominacja militarna Sparty \n3. Ekspansja kultury greckiej w okresie podbojów Aleksandra Macedoñskiego");
		record.addEvent(event);
		
		Author autor1 = new Author("Zenek Skorbeszon");
		record1.setAuthor(autor1);
		record1.setPublishingYear("2009");
		record1.addKeyWord(new KeyWord("Walka o tron Anglii"));
		record1.addKeyWord(new KeyWord("pieriestrojka"));
		Location location1 = new Location("Mezopotamia");
		record1.addLocation(location1);
		record1.addLocation(new Location("Izrael"));
		HistoricalCharacter character1 = new HistoricalCharacter("Nabuchodonozor");
		character1.setHistoricalPeriod(HistoricalPeriod.CONTEMPORANEITY);
		record1.addCharacter(character1);
		record1.setHistoricalPeriod(HistoricalPeriod.MODERN_AGES);
		record1.setMedium(MediumType.MAGAZINE);
		record1.addSource(factory.getSource(SourceType.EXTERNAL, "Biblioteczka rega³ 2, pozycja 10"));
		record1.addSource(factory.getSource(SourceType.FILE, "C:/Users/Spider/Downloads/book.png"));
		record1.addSource(factory.getSource(SourceType.INTERNET, "wp.pl"));
		record1.addSource(factory.getSource(SourceType.FILE, "C:/Users/Spider/Desktop/Logo.jpg"));
		HistoricalEvent event1 = new HistoricalEvent("Kodeks Hamurabiego");
		PreciseDate startDate = new PreciseDate("20", "1950", "9", "12", null, true);
		PreciseDate endtDate = new PreciseDate("17", "1620", "12", "4", null, true);
		event1.setEventDate(new RangeDate(startDate, endtDate));
		Quotation quotation1 = new Quotation("324", "Okres nowobabiloñski to szybki rozwój nauk matematycznych. Znaleziono wiele tabliczek œwiadcz¹cych o nauce rozwi¹zywania równañ kwadratowych");
		record1.addQuotation(quotation1);
		record1.setDescription("Ksi¹¿ka opisuje historiê mezopotamii od okresu Sumerów do upadku pañstwa Nowobabiloñskiego. Szczególny nacisk k³adzie na aspekt kulturowy i ¿ycie codzienne w Asyrii");
		record1.setTableOfContents("1. Pierwsze osady sta³e w dorzeczu Eufratu i Tygrysu \n2. Wierzenia i administracja u Sumerów \n3. Wp³ywy ludów semickich na rozwój pañstwa babiloñskiego");
		record1.addEvent(event1);
		
		Author autor2 = new Author("Alojzy Szprot");
		record2.setHistoricalPeriod(HistoricalPeriod.CONTEMPORANEITY);
		record2.setAuthor(autor2);
		record2.setPublishingYear("2015");
		record2.addKeyWord(new KeyWord("TW Bolek"));
		Location location2 = new Location("Polska");
		record2.addLocation(location2);
		HistoricalCharacter character2 = new HistoricalCharacter("Lech Wa³êsa");
		character2.setHistoricalPeriod(HistoricalPeriod.CONTEMPORANEITY);
		record2.addCharacter(character2);
		record2.setMedium(MediumType.BOOK);
		HistoricalEvent event2 = new HistoricalEvent("Okr¹g³y stó³");
		event2.setEventDate(new PreciseDate("20", "1989", "8", "12", null, false));
		record2.addEvent(event2);
		
		Author autor3 = new Author("unknown author");
		record3.setHistoricalPeriod(HistoricalPeriod.ANTIQUITY);
		record3.setAuthor(autor3);
		record3.addKeyWord(new KeyWord("Akropol"));
		Location location3 = new Location("Ateny");
		record3.addLocation(location3);
		HistoricalCharacter character3 = new HistoricalCharacter("Aspazja");
		character3.setHistoricalPeriod(HistoricalPeriod.ANTIQUITY);
		record3.addCharacter(character3);
		record3.setMedium(MediumType.IMAGE);
		record3.addSource(factory.getSource(SourceType.FILE, "files/AkropolisAthens.jpg"));
		
		Author autor4 = new Author("Adam Kowalski");
		record4.setHistoricalPeriod(HistoricalPeriod.MIDDLE_AGES);
		record4.setAuthor(autor4);
		record4.setPublishingYear("1999");
		record4.setMedium(MediumType.BOOK);
		
		Author autor5 = new Author("Olaf Kazimierski");
		record5.setHistoricalPeriod(HistoricalPeriod.ANTIQUITY);
		record5.setAuthor(autor5);
		record5.setPublishingYear("2003");
		record5.setMedium(MediumType.BOOK);
		
		Reference ref = new Reference(record1, "Opis wojny Peloponeskiej rozszerza informacje zawarte w tej pozycji", "p. 39-42", true);
		Reference ref2 = new Reference(record1, "Opis wojny Peloponeskiej rozszerza informacje zawarte w tej pozycji", "p. 100-420", true);
		Reference ref1 = new Reference(record, "Opis wojny Peloponeskiej rozszerza informacje zawarte w tej pozycji", "p. 39-42", false);
		Reference ref3 = new Reference(record, "Opis wojny Peloponeskiej rozszerza informacje zawarte w tej pozycji", "p. 100-420", false);
		
		record.addReference(ref);
		record.addReference(ref2);
		record1.addReference(ref1);
		record1.addReference(ref3);
		
		
			project.addRecord(record);
			project.addRecord(record1);
			project.addRecord(record2);
			project.addRecord(record3);
			project.addRecord(record4);
			project.addRecord(record5);
	}

	public Project getProject() {
		return project;
	}

	public Record getRecord() {
		return record;
	}

	public Record getRecord1() {
		return record1;
	}
	
	
}
