package uz.diploma.controller.context;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import uz.diploma.controller.dialog.element.CharacterDialogController;
import uz.diploma.controller.dialog.element.EventDialogController;
import uz.diploma.gui.dialog.element.CharacterDialogFactory;
import uz.diploma.gui.dialog.element.EventDialogFactory;
import uz.diploma.gui.window.panel.details.contexttab.panel.PanelView;
import uz.diploma.gui.window.panel.record.ResultListListener;
import uz.diploma.listeners.LoadEventListener;
import uz.diploma.model.project.LoadEvent;
import uz.diploma.model.project.Project;
import uz.diploma.model.record.element.HistoricalCharacter;
import uz.diploma.model.record.element.HistoricalEvent;
import uz.diploma.model.record.element.Record;

/**
 * @author Spider
 * 
 * Class is responsible for controlling data flow between model and character panel. 
 * Assures that correct data are presented and manipulate model by removing user selected character  
 *
 */
public class CharactersPanelController extends PanelViewController {
	
	public CharactersPanelController(PanelView panel,
			Project project) {
		super(panel, project);
		viewFactory = new CharacterDialogFactory();
	}

	@Override
	public void onAddButtonClicked() {
		new CharacterDialogController(project, viewFactory, currentRecord, (Component)panel);
	}

	@Override
	public void onFocusGained() {
		panel.onNewRecord(currentRecord);
	}

	@Override
	public void onRemoveButtonClicked() {
		if(panel.getSelection() != null) {
			if (panel.canRemoveElement("Remove this Character?")) {
			   currentRecord.removeCharacter((HistoricalCharacter)panel.getSelection());
			   panel.onNewRecord(currentRecord);
			}
		} else {
			panel.showItemNotSelectedDialog();
		}
	}

	@Override
	public void onSearchedTextChanged(String searchedText) {
		filterView(searchedText);
	}
	
	private void filterView(String searchedText) {
		panel.updateView(getCharacters().stream()
			.filter(containSrearchedText(searchedText))
			.collect(Collectors.toList()));	
	}

	private Predicate<? super HistoricalCharacter> containSrearchedText(
			String searchedText) {
		return character -> character.getName().toLowerCase().trim()
				.contains(searchedText.toLowerCase().trim());
	}
	
	private List<HistoricalCharacter> getCharacters() {
		List<HistoricalCharacter> characters = new ArrayList<HistoricalCharacter>();
		currentRecord.getCharactersIterator().forEachRemaining(character -> characters.add(character));
		return characters;
	}

}
