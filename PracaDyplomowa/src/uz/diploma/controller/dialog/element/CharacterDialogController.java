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
import uz.diploma.gui.dialog.element.AddingCharacterDialog;
import uz.diploma.gui.dialog.element.AddingEventDialog;
import uz.diploma.gui.window.panel.details.contexttab.panel.PanelView;
import uz.diploma.model.project.Project;
import uz.diploma.model.record.element.HistoricalCharacter;
import uz.diploma.model.record.element.HistoricalEvent;
import uz.diploma.model.record.element.HistoricalPeriod;
import uz.diploma.model.record.element.Record;
import uz.diploma.model.record.element.RecordElement;
import uz.diploma.tools.Validator;

/**
 * @author Spider
 * 
 * Class is responsible for controlling dialog window for adding character 
 * Manipulates model's data by adding and editing characters entities  
 *
 */
public class CharacterDialogController extends ElementDialogViewController {

	private String enteredCharacterName;
	private HistoricalPeriod selectedPeriod;
	private String enteredCharacterDescription;
	private HistoricalCharacter currentCharacter;

	public CharacterDialogController(Project project, DialogViewFactory viewFactory, Record currentRecord, Component parentView) {
		super(project, viewFactory, currentRecord, parentView);
	}

	@Override
	public void fillDialogData() {
		List<RecordElement> charactersList = recordsAsList(project.getRecordsIterator()).stream()
				.flatMap(record -> getCharacters(record).stream()).distinct().collect(Collectors.toList());
		dialog.setSelectionComboBox(charactersList);
		dialog.setAllTextFields();
		getEventData();
	}

	@Override
	public void onSelectionChanged(ActionEvent event) {
		Validator.ensureIsNotNull(event, "event");
		if(event.getActionCommand() == AddingCharacterDialog.SELECT_COMBOBOX_ACTION) {
			dialog.setAllTextFields();
		}
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
		
		if(enteredCharacterName.trim().equals("")) {
			dialog.showInformationDialog("Character name cannot be empty");
			return;
		}
		
		if(currentCharacter.matchesName(enteredCharacterName)) {
			if(currentCharacter.matchesPeriod(selectedPeriod) && currentCharacter.matchesDescription(enteredCharacterDescription)) {
					dialog.showInformationDialog("Character already exists");			
			} else { 
				if(!dialog.positiveAnswerToQuestion("Save changes to this character?"))
					return;
				updateCharacter();
			}
		} else {
			if(!dialog.positiveAnswerToQuestion("Add new character?"))
				return;
			addCharacter();
		}
		((JDialog)dialog).dispose();
	}

	private void addCharacter() {
		HistoricalCharacter newCharacter = new HistoricalCharacter(enteredCharacterName);
		newCharacter.setHistoricalPeriod(selectedPeriod);
		newCharacter.setDescription(enteredCharacterDescription);
		currentRecord.addCharacter(newCharacter);
	}

	private void getEventData() {
		currentCharacter = (HistoricalCharacter) dialog.getElement();
		enteredCharacterName = ((AddingCharacterDialog)dialog).getNameFieldText().trim();
		selectedPeriod = ((AddingCharacterDialog)dialog).getSelectedPeriod();
		enteredCharacterDescription = ((AddingCharacterDialog)dialog).getDescriptionAreaText().trim();
	}
	
	private void updateCharacter() {
		currentCharacter.setName(enteredCharacterName);
		currentCharacter.setHistoricalPeriod(selectedPeriod);
		currentCharacter.setDescription(enteredCharacterDescription);
	}
	
	private List<Record> recordsAsList(Iterator<Record> iter) {
		List<Record> records = new ArrayList<Record>();
		iter.forEachRemaining(e -> records.add(e));
		return records;
	}

	private List<HistoricalCharacter> getCharacters(Record record) {
		List<HistoricalCharacter> characters = new ArrayList<HistoricalCharacter>();
		record.getCharactersIterator().forEachRemaining(character -> characters.add(character));
		return characters;
	}
}
