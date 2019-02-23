package uz.diploma.controller.context;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import uz.diploma.controller.dialog.element.EventDialogController;
import uz.diploma.gui.dialog.element.AddingEventDialog;
import uz.diploma.gui.dialog.element.EventDialogFactory;
import uz.diploma.gui.window.panel.details.contexttab.panel.EventsPanel;
import uz.diploma.gui.window.panel.details.contexttab.panel.PanelView;
import uz.diploma.gui.window.panel.record.ResultListListener;
import uz.diploma.listeners.LoadEventListener;
import uz.diploma.model.project.LoadEvent;
import uz.diploma.model.project.Project;
import uz.diploma.model.record.element.HistoricalEvent;
import uz.diploma.model.record.element.Record;

/**
 * @author Spider
 *
 * Class is responsible for controlling data flow between model and events panel. 
 * Assures that correct data are presented and manipulate model by removing user selected event
 * 
 */
public class EventsPanelController extends PanelViewController {

	public EventsPanelController(PanelView panel, Project project) {
		super(panel, project);
		viewFactory = new EventDialogFactory();
	}

	@Override
	public void onAddButtonClicked() {
		new EventDialogController(project, viewFactory, currentRecord, (Component)panel);
	}

	@Override
	public void onFocusGained() {
		panel.onNewRecord(currentRecord);
	}

	@Override
	public void onRemoveButtonClicked() {
		if(panel.getSelection() != null) {
			if(panel.canRemoveElement("Remove this Event?"))
			currentRecord.removeEvent((HistoricalEvent)panel.getSelection());
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
		panel.updateView(getEvents().stream()
		.filter(c -> c.getName().toLowerCase().trim().contains(searchedText.toLowerCase().trim()))
		.collect(Collectors.toList()));
	}
	
	private List<HistoricalEvent> getEvents() {
		List<HistoricalEvent> events = new ArrayList<HistoricalEvent>();
		currentRecord.getEventsIterator().forEachRemaining(event -> events.add(event));
		return events;
	}
}
