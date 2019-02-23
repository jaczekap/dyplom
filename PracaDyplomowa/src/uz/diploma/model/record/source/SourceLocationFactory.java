package uz.diploma.model.record.source;

import uz.diploma.tools.Validator;

public class SourceLocationFactory {
	
	public SourceLocation getSource(SourceType sourceType, String source) {
		Validator.ensureIsNotNull(sourceType, "sourceType");
		if(sourceType == SourceType.EXTERNAL)
			return new ExternalSource(source);
		else if(sourceType == SourceType.INTERNET)
			return new InternetSource(source);
		else if(sourceType == SourceType.FILE)
			return new FileSource(source);
		else
			throw new IllegalArgumentException(sourceType.name() + ": unknown source type");
	}

}
