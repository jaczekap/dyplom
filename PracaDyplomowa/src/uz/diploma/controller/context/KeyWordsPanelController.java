package uz.diploma.controller.context;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import uz.diploma.controller.dialog.element.EventDialogController;
import uz.diploma.controller.dialog.element.KeyWordDialogController;
import uz.diploma.gui.dialog.element.EventDialogFactory;
import uz.diploma.gui.dialog.element.KeyWordDialogFactory;
import uz.diploma.gui.window.panel.details.contexttab.panel.PanelView;
import uz.diploma.gui.window.panel.record.ResultListListener;
import uz.diploma.listeners.LoadEventListener;
import uz.diploma.model.project.LoadEvent;
import uz.diploma.model.project.Project;
import uz.diploma.model.record.element.KeyWord;
import uz.diploma.model.record.element.Location;
import uz.diploma.model.record.element.Record;

/**
 * @author Spider
 *
 * Class is responsible for controlling data flow between model and keyword panel. 
 * Assures that correct data are presented and manipulate model by removing user selected keyword
 * 
 */
public class KeyWordsPanelController extends PanelViewController {

	

	public KeyWordsPanelController(PanelView panel, Project project) {
		super(panel, project);
		viewFactory = new KeyWordDialogFactory();
	}

	@Override
	public void onAddButtonClicked() {
		new KeyWordDialogController(project, viewFactory, currentRecord, (Component)panel);
	}

	@Override
	public void onFocusGained() {
		panel.onNewRecord(currentRecord);
	}

	@Override
	public void onRemoveButtonClicked() {
		if(panel.getSelection() != null) {
			if(panel.canRemoveElement("Remove this Keyword"))
			currentRecord.removeKeyWord((KeyWord)panel.getSelection());
			panel.onNewRecord(currentRecord);
		} else {
			panel.showItemNotSelectedDialog();
		}
	}

	@Override
	public void onSearchedTextChanged(String searchedText) {
		filterView(searchedText);
	}
	
	private void filterView(String searchedText) {
		panel.updateView(getKeywords().stream()
		.filter(c -> c.getKeyWord().toLowerCase().trim().contains(searchedText.toLowerCase().trim()))
		.collect(Collectors.toList()));
	}
	
	private List<KeyWord> getKeywords() {
		List<KeyWord> keywords = new ArrayList<KeyWord>();
		
		currentRecord.getKeyWordIterator().forEachRemaining(k -> keywords.add(k));
		return keywords;
	}

}
