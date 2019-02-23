package uz.diploma.model.record.source;



public class ExternalSource extends SourceLocation {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2506851816773654472L;

	public ExternalSource(String source) {
		super(source);
		isOpenable = false;
		sourceType = SourceType.EXTERNAL;
	}

}
