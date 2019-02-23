package uz.diploma.model.record.source;

import java.io.Serializable;

public enum SourceType implements Serializable {
	EXTERNAL("external"), INTERNET("internet"), FILE("file");
	
	private String name;

	private SourceType(String name) {
		this.name = name;
	}
	

	@Override
	public String toString() {
		return name;
	}
}