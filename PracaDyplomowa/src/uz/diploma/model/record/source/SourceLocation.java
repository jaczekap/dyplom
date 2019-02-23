package uz.diploma.model.record.source;

import java.io.Serializable;
import uz.diploma.model.record.element.RecordElement;
import uz.diploma.tools.Validator;

/**
 * @author Spider
 * 
 * Class represents physical location of the associated to record source.
 * Source can be path in local file system, 
 * Internet resource or string describing location of the source
 * This class is abstract and should be extended by any class that represents concrete type of source location   
 *
 */
public abstract class SourceLocation implements Serializable, RecordElement {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8607851219838600944L;
	protected SourceType sourceType;
	protected String source;
	protected boolean isOpenable;
	
	/**
	 * @param sourceType - type of the source (file, Internet or external)
	 * @param source - source in string representation
	 * @throws EmptyStringException 
	 */
	public SourceLocation(String source) {
		Validator.ensureIsNotNull(source, "source");
		Validator.ensureExists(source, "source");
		this.source = source;
	}
	
	public String getSource() {
		return source;
	};

	public SourceType getSourceType() {
		return sourceType;
	}
	
	public boolean isOpenable() {
		return isOpenable;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(this == obj)
			return true;
		if(getClass() != obj.getClass())
			return false;
		SourceLocation otherSource = (SourceLocation) obj;
		if(otherSource.sourceType.equals(this.sourceType) && otherSource.toString().trim().equals(this.toString().trim()))
			return true;
		return false;
	}

	@Override
	public String toString() {
		return source;
	}
	
	

}
