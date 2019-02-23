package uz.diploma.model.record.element;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Stream;

import uz.diploma.model.record.source.SourceLocation;
import uz.diploma.tools.Validator;

public class Record implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3368054946573517175L;
	private String title;
	private String publishingDate;
	private String publicationInfo;
	private Author author;
	private String tableOfContents;
	private String summary;
	private List<KeyWord> keyWords;
	private MediumType medium;
	private List<Reference> referencesList;
	private HistoricalPeriod historicalPeriod;
	private List<Location> locations;
	private List<SourceLocation> sources;
	private List<Quotation> quotationsList;
	private List<HistoricalEvent> eventsList;
	private List<HistoricalCharacter> charactersList;
	
	public Record(String title) {
		Validator.ensureIsNotNull(title, "title");
		Validator.ensureExists(title, "title");
		this.title = title;
		keyWords = new ArrayList<KeyWord>();
		referencesList = new ArrayList<Reference>();
		locations = new ArrayList<Location>();
		quotationsList = new ArrayList<Quotation>();
		eventsList = new ArrayList<HistoricalEvent>();
		charactersList = new ArrayList<HistoricalCharacter>();
		sources = new ArrayList<SourceLocation>();
	}
	
	public boolean isInKeyWord(String searchedText) {
		return containsRegexOrCaseInsensitive(keyWords, KeyWord::getKeyWord, searchedText);
	}
	
	public boolean isPartOfAuthorName(String searchedText) {
		return containsRegexOrCaseInsensitive(author.getName(), searchedText);
	}
	
	public boolean isInTitle(String searchedText) {
		return containsRegexOrCaseInsensitive(title, searchedText);
	}
	
	public boolean isInTableOfContents(String searchedText) {
		return containsRegexOrCaseInsensitive(tableOfContents, searchedText);
	}
	
	public boolean isInDescription(String searchedText) {
		return containsRegexOrCaseInsensitive(summary, searchedText);
	}
	
	public boolean isPartOfMediumTypeName(String searchedText) {
		return containsRegexOrCaseInsensitive(medium.getName(), searchedText);
	}
	
	public boolean isPartOfPeriodName(String searchedText) {
		return containsRegexOrCaseInsensitive(historicalPeriod.getName(), searchedText);
	}
	
	public boolean isInQuotation(String searchedText) {
		return containsRegexOrCaseInsensitive(quotationsList, 
				Quotation::getQuote, searchedText);
	}
	
	public boolean isPartOfCharacterName(String searchedText) {
		return containsRegexOrCaseInsensitive(charactersList, HistoricalCharacter::getName, searchedText);
	}
	
	public boolean isPartOfEventName(String searchedText) {
		return containsRegexOrCaseInsensitive(eventsList, HistoricalEvent::getName, searchedText);
	}
	
	public boolean isPartOfLocationName(String searchedText) {
		return containsRegexOrCaseInsensitive(locations, Location::getLocation, searchedText);
	}
	
	public boolean isInDateRange(String date) {
		try {
			return eventsList.stream().map(HistoricalEvent::getEventDate).anyMatch(d -> d.isInDateRange(Integer.parseInt(date)));
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
	private boolean containsCaseInsensitive(String where, String what) {
		if(where != null)
			return where.toLowerCase().contains(what.toLowerCase());
		return false;
	}
	
	private <T> boolean containsCaseInsensitive(List<T> where, Function<T, String> mapper, String what) {
		return where.stream().map(mapper).anyMatch(s -> containsCaseInsensitive(s, what));
	}
	
	private boolean containsRegex(String where, String what) {
		if(where != null) {
			try {
				Pattern regex = Pattern.compile(what);
				Matcher matcher = regex.matcher(where);
				matcher.reset();
				return matcher.find();
			} catch(PatternSyntaxException e) {
				return false;
			}
		}
		return false;
	}
	
	private <T> boolean containsRegex(List<T> where, 
			Function<T, String> mapper, String what) {
		return where.stream().map(mapper)
				.anyMatch(s -> containsRegex(s, what));
	}
	
	private boolean containsRegexOrCaseInsensitive(String where, String what) {
		boolean caseInsensitiveMatcher = containsCaseInsensitive(where, what);
		boolean regexMatcher = containsRegex(where, what);
		return caseInsensitiveMatcher || regexMatcher;
	}
	
	private <T> boolean containsRegexOrCaseInsensitive(List<T> where, Function<T, String> mapper, String what) {
		return containsCaseInsensitive(where, mapper, what) || containsRegex(where, mapper, what);
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		Validator.ensureExists(title, "title");
		this.title = title;
	}
	
	public Author getAuthor() {
		return author;
	}
	
	public void setAuthor(Author author) {
		this.author = author;
	}
	
	public String getTableOfContents() {
		return tableOfContents;
	}
	
	public void setTableOfContents(String tableOfContents) {
		this.tableOfContents = tableOfContents;
	}
	
	public String getDescription() {
		return summary;
	}
	
	public void setDescription(String description) {
		this.summary = description;
	}
	
	public Iterator<KeyWord> getKeyWordIterator() {
		return keyWords.iterator();
	}
	
	public void addKeyWord(KeyWord keyWord) {
		keyWords.add(keyWord);
	}
	
	public void removeKeyWord(KeyWord keyWord) {
		keyWords.remove(keyWord);
	}
	
	public Iterator<HistoricalEvent> getEventsIterator() {
		return eventsList.iterator();
	}
	
	public void addEvent(HistoricalEvent event) {
		if(!eventsList.contains(event))
			eventsList.add(event);
	}
	
	public void removeEvent(HistoricalEvent event) {
		eventsList.remove(event);
	}
	
	public void addCharacter(HistoricalCharacter character) {
		charactersList.add(character);
	}
	
	public Iterator<HistoricalCharacter> getCharactersIterator() {
		return charactersList.iterator();
	}
	
	public void removeCharacter(HistoricalCharacter character) {
		charactersList.remove(character);
	}

	public String getPublishingYear() {
		return publishingDate;
	}

	public void setPublishingYear(String publishingYear) {
		this.publishingDate = publishingYear;
	}

	public MediumType getMedium() {
		return medium;
	}

	public void setMedium(MediumType medium) {
		this.medium = medium;
	}
	
	public Iterator<Reference> getReferencesIterator() {
		return referencesList.iterator();
	}

	public void addReference(Reference reference) {
		referencesList.add(reference);
	}
	
	public void removeReference(Reference reference) {
		referencesList.remove(reference);
	}

	public HistoricalPeriod getHistoricalPeriod() {
		return historicalPeriod;
	}

	public void setHistoricalPeriod(HistoricalPeriod historicalPeriod) {
		this.historicalPeriod = historicalPeriod;
	}
	
	public Iterator<Location> getLocationsIterator() {
		return locations.iterator();
	}

	public void addLocation(Location countryOrRegion) {
		this.locations.add(countryOrRegion);
	}
	
	public void removeLocation(Location countryOrRegion) {
		locations.remove(countryOrRegion);
	}
	
	public Iterator<SourceLocation> getSourceLocationIterator() {
		return sources.iterator();
	}

	public void addSource(SourceLocation source) {
		sources.add(source);
	}
	
	public void removeSource(SourceLocation source) {
		sources.remove(source);
	}
	
	public Iterator<Quotation> getQuotationsIterator() {
		return quotationsList.iterator();
	}

	public void addQuotation(Quotation quotation) {
		quotationsList.add(quotation);
	}	
	
	public void removeQuotation(Quotation quotation) {
		quotationsList.remove(quotation);
	}

	public String getPublicationData() {
		return publicationInfo;
	}

	public void setPublicationData(String publicationData) {
		this.publicationInfo = publicationData;
	}

	@Override
	public String toString() {
		return title + ", " + author.getName() + ", " + publishingDate;
	}
	
}
