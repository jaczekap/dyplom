package uz.diploma.model.record.element;

import java.io.Serializable;

public enum HistoricalPeriod implements Serializable, RecordElement {

	ANTIQUITY("Antiquity"), MIDDLE_AGES("Middle Ages"), MODERN_AGES("Modern Ages"), CONTEMPORANEITY("Contamporaneity");
	
	private String name;

	private HistoricalPeriod(String name) {
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
