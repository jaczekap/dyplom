package uz.diploma.controller.dialog.element;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import uz.diploma.gui.dialog.DialogViewFactory;
import uz.diploma.gui.dialog.element.AddingKeyWordDialog;
import uz.diploma.gui.dialog.element.AddingLocationDialog;
import uz.diploma.gui.window.panel.details.contexttab.panel.PanelView;
import uz.diploma.model.project.Project;
import uz.diploma.model.record.element.KeyWord;
import uz.diploma.model.record.element.Location;
import uz.diploma.model.record.element.Record;
import uz.diploma.model.record.element.RecordElement;
import uz.diploma.tools.Validator;

/**
 * @author Spider
 * 
 * Class is responsible for controlling dialog window for adding keyword 
 * Manipulates model's data by adding and editing keyword entities
 *
 */
public class KeyWordDialogController extends ElementDialogViewController {
	private String enteredKeyWord;
	private KeyWord currentKeyWord;

	public KeyWordDialogController(Project project, DialogViewFactory viewFactory, Record currentRecord, Component parentView) {
		super(project, viewFactory, currentRecord, parentView);
	}

	@Override
	public void fillDialogData() {
		List<RecordElement> keyWordsList = recordsAsList(project.getRecordsIterator()).stream().flatMap(record -> getKeywords(record).stream()).distinct().collect(Collectors.toList());
		dialog.setSelectionComboBox(keyWordsList);
		dialog.setAllTextFields();
		getEventData();
	}

	@Override
	public void onSelectionChanged(ActionEvent event) {
		Validator.ensureIsNotNull(event, "event");
		dialog.setAllTextFields();
		getEventData();
	}

	@Override
	public void onTextFieldChanged() {
		getEventData();
	}

	@Override
	public void onCancelButtonClicked() {
		if(!dialog.positiveAnswerToQuestion("Are you sure?"))
			return;
		((JDialog)dialog).dispose();
	}

	@Override
	public void onAddButtonClicked() {
		getEventData();
		
		if(enteredKeyWord.trim().equals("")) {
			dialog.showInformationDialog("Keyword cannot be empty");
			return;
		}
		if(currentKeyWord.getKeyWord().trim().equals(enteredKeyWord)) {
				dialog.showInformationDialog("Keyword already exists");
		} else {
			if(!dialog.positiveAnswerToQuestion("Add new keyword?"))
				return;
			
			KeyWord newKeyWord = new KeyWord(enteredKeyWord);
			currentRecord.addKeyWord(newKeyWord);
		}
		((JDialog)dialog).dispose();
	}

	private void getEventData() {
		currentKeyWord = (KeyWord) dialog.getElement();
		enteredKeyWord = ((AddingKeyWordDialog)dialog).getKeyWordFieldText().trim();
	}
	
	private List<Record> recordsAsList(Iterator<Record> iter) {
		List<Record> records = new ArrayList<Record>();
		iter.forEachRemaining(e -> records.add(e));
		return records;
	}
	
	private List<KeyWord> getKeywords(Record record) {
		List<KeyWord> keywords = new ArrayList<KeyWord>();
		
		record.getKeyWordIterator().forEachRemaining(k -> keywords.add(k));
		return keywords;
	}
}
