package uz.diploma.model.record.element;

import java.io.Serializable;

public enum MediumType implements Serializable, RecordElement {
	BOOK("Book"), AUDIO("Audio"), VIDEO("Video"), MAGAZINE("Magazine"), SOURCE_TEXT("Source text"), IMAGE("image");
	
	
	private String name;
	
	private MediumType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
