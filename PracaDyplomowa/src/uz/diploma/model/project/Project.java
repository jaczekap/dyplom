package uz.diploma.model.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.event.EventListenerList;

import uz.diploma.model.record.element.Author;
import uz.diploma.model.record.element.HistoricalCharacter;
import uz.diploma.model.record.element.HistoricalEvent;
import uz.diploma.model.record.element.HistoricalPeriod;
import uz.diploma.model.record.element.Record;
import uz.diploma.tools.Validator;

/**
 * @author Spider
 * 
 * Class that represents application project. Projects store all informations about individual elements of database
 * in list holding all records. 
 *
 */
public class Project implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5751859839874471403L;
	private List<Record> records;
	
	public Project() {
		records = new ArrayList<Record>();
	}
	
	public Iterator<Record> getRecordsIterator() {
		return records.iterator();
	}

	public void addRecord(Record record) {
		if(!records.contains(record))
			records.add(record);
	}
	
	public void removeRecord(Record record) {
		records.remove(record);
	}
	
	/**
	 * Searches through all records to find if table of contents field contains desired string 
	 * @param stringToSearch - string to be found
	 * @return list of records where table of contents contains desired string
	 */
	public List<Record> findRecordsByTableOfContents(String stringToSearch) {
		return findRecords(r -> r.isInTableOfContents(stringToSearch));
	}
	
	/**
	 * Searches through all records to find if description field contains desired string 
	 * @param stringToSearch - string to be found
	 * @return list of records where description contains desired string
	 */
	public List<Record> findRecordsByDescription(String stringToSearch) {
		return findRecords(r -> r.isInDescription(stringToSearch));
	}
	
	/**
	 * Searches through all records to find if record contains keyword or it's part matching desired string 
	 * @param stringToSearch - string to be found
	 * @return list of records where at least one keyword contains desired string
	 */
	public List<Record> findRecordsByKeyWord(String stringToSearch) {
		return findRecords(r -> r.isInKeyWord(stringToSearch));
	}
	
	/**
	 * Searches through all records to find if medium type (book, magazine, video etc.) field contains desired string 
	 * @param stringToSearch - string to be found
	 * @return list of records where medium type contains desired string
	 */
	public List<Record> findRecordsByType(String stringToSearch) {
		return findRecords(r -> r.isPartOfMediumTypeName(stringToSearch));
	}
	
	/**
	 * Searches through all records to find if historical period field contains desired string 
	 * @param stringToSearch - string to be found
	 * @return list of records where historical period contains desired string
	 */
	public List<Record> findRecordsByPeriod(String stringToSearch) {
		return findRecords(r -> r.isPartOfPeriodName(stringToSearch));
	}
	
	/**
	 * Searches through all records to find if title field contains desired string 
	 * @param stringToSearch - string to be found
	 * @return list of records where title contains desired string
	 */
	public List<Record> findRecordsByTitle(String stringToSearch) {
		return findRecords(r -> r.isInTitle(stringToSearch));
	}
	
	/**
	 * Searches through all records to find if record contains historical event or it's part matching desired string 
	 * @param stringToSearch - string to be found
	 * @return list of records where at least one historical event contains desired string
	 */
	public List<Record> findRecordsByEvent(String stringToSearch) {
		return findRecords(r -> r.isPartOfEventName(stringToSearch));
	}
	
	/**
	 * Searches through all records to find if author field contains desired string 
	 * @param stringToSearch - string to be found
	 * @return list of records where author contains desired string
	 */
	public List<Record> findRecordsByAuthor(String stringToSearch) {
		return findRecords(r -> r.isPartOfAuthorName(stringToSearch));
	}
	
	/**
	 * Searches through all records to find if record contains geographical location or it's part matching desired string 
	 * @param stringToSearch - string to be found
	 * @return list of records where at least one geographical location contains desired string
	 */
	public List<Record> findRecordsByLocation(String stringToSearch) {
		return findRecords(r -> r.isPartOfLocationName(stringToSearch));
	}
	
	/**
	 * Searches through all records to find if record contains historical character or it's part matching desired string 
	 * @param stringToSearch - string to be found
	 * @return list of records where at least one historical character contains desired string
	 */
	public List<Record> findRecordsByCharacter(String stringToSearch) {
		return findRecords(r -> r.isPartOfCharacterName(stringToSearch));
	}
	
	/**
	 * Searches through all records to find if record contains quotation or it's part matching desired string 
	 * @param stringToSearch - string to be found
	 * @return list of records where at least one quotation contains desired string
	 */
	public List<Record> findRecordsByQuotation(String stringToSearch) {
		return findRecords(r -> r.isInQuotation(stringToSearch));
	}
	
	/**
	 * Searches through all records to find if record contains date matching desired string 
	 * @param stringToSearch - string to be found
	 * @return list of records where date contains desired string
	 */
	public List<Record> findRecordsByDate(String stringToSearch) {
		return findRecords(r -> r.isInDateRange(stringToSearch));
	}
	
	public List<Record> findAll(String stringToSearch) {
		List<Record> foundRecords = findRecordsByAuthor(stringToSearch);
		foundRecords.addAll(findRecordsByCharacter(stringToSearch));
		foundRecords.addAll(findRecordsByDescription(stringToSearch));
		foundRecords.addAll(findRecordsByEvent(stringToSearch));
		foundRecords.addAll(findRecordsByKeyWord(stringToSearch));
		foundRecords.addAll(findRecordsByLocation(stringToSearch));
		foundRecords.addAll(findRecordsByPeriod(stringToSearch));
		foundRecords.addAll(findRecordsByQuotation(stringToSearch));
		foundRecords.addAll(findRecordsByTableOfContents(stringToSearch));
		foundRecords.addAll(findRecordsByTitle(stringToSearch));
		foundRecords.addAll(findRecordsByType(stringToSearch));
		foundRecords.addAll(findRecordsByDate(stringToSearch));
		return foundRecords.stream().distinct().collect(Collectors.toList());
	}
	
	private List<Record> findRecords(Predicate<Record> filter) {
		Validator.ensureIsNotNull(filter, "filter");
		return records.stream().filter(filter).distinct().collect(Collectors.toList());
	}
}
