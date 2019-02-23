package uz.diploma.model.record.source;

import java.net.URI;

public class InternetSource extends SourceLocation {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -897332640636116182L;
	private URI sourceLocationAsURI;
	private static final String DEFAULT_PROTOCOL = "http://";

	public InternetSource(String source) {
		super(source);
		isOpenable = true;
		sourceType = SourceType.INTERNET;
		sourceLocationAsURI = createCorrectURI(source);
		this.source = sourceLocationAsURI.toString();
	}
	
	public URI getSourceLocationAsURI() {
		return sourceLocationAsURI;
	}



	/**
	 * Checks if given string is valid URL and if not modify it to form that enable 
	 * to create URL object. Finally URL object is returned.  
	 * @param webAddress - web address in string representation
	 * @return URL object based on given web address
	 */
	private URI createCorrectURI(String webAddress) {
		if(webAddress.startsWith(DEFAULT_PROTOCOL) || webAddress.startsWith("https://"))
			return URI.create(webAddress);
		else
			return URI.create(DEFAULT_PROTOCOL + webAddress);
	}

}
