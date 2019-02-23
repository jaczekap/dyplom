package uz.diploma.controller.context;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import uz.diploma.controller.dialog.element.EventDialogController;
import uz.diploma.controller.dialog.element.LocationDialogController;
import uz.diploma.gui.dialog.element.EventDialogFactory;
import uz.diploma.gui.dialog.element.LocationDialogFactory;
import uz.diploma.gui.window.panel.details.contexttab.panel.PanelView;
import uz.diploma.gui.window.panel.record.ResultListListener;
import uz.diploma.listeners.LoadEventListener;
import uz.diploma.model.project.LoadEvent;
import uz.diploma.model.project.Project;
import uz.diploma.model.record.element.HistoricalCharacter;
import uz.diploma.model.record.element.HistoricalEvent;
import uz.diploma.model.record.element.Location;
import uz.diploma.model.record.element.Record;

/**
 * @author Spider
 * 
 * Class is responsible for controlling data flow between model and location panel. 
 * Assures that correct data are presented and manipulate model by removing user selected location
 *
 */
public class LocationsPanelController extends PanelViewController {
	
	

	public LocationsPanelController(PanelView panel, Project project) {
		super(panel, project);
		viewFactory = new LocationDialogFactory();
	}

	@Override
	public void onAddButtonClicked() {
		new LocationDialogController(project, viewFactory, currentRecord, (Component)panel);
	}

	@Override
	public void onFocusGained() {
		panel.onNewRecord(currentRecord);
	}

	@Override
	public void onRemoveButtonClicked() {
		if(panel.getSelection() != null) {
			if(panel.canRemoveElement("Remove this Character"))
			currentRecord.removeLocation((Location)panel.getSelection());
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
		panel.updateView(getLocations().stream()
		.filter(c -> c.getLocation().toLowerCase().trim().contains(searchedText.toLowerCase().trim()))
		.collect(Collectors.toList()));
	}
	
	private List<Location> getLocations() {
		List<Location> locations = new ArrayList<Location>();
		currentRecord.getLocationsIterator().forEachRemaining(location -> locations.add(location));
		return locations;
	}

}
