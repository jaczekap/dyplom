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
import uz.diploma.gui.dialog.element.AddingEventDialog;
import uz.diploma.gui.dialog.element.AddingLocationDialog;
import uz.diploma.gui.window.panel.details.contexttab.panel.PanelView;
import uz.diploma.model.project.Project;
import uz.diploma.model.record.element.HistoricalCharacter;
import uz.diploma.model.record.element.HistoricalEvent;
import uz.diploma.model.record.element.Location;
import uz.diploma.model.record.element.Record;
import uz.diploma.model.record.element.RecordElement;
import uz.diploma.tools.Validator;

/**
 * @author Spider
 * 
 * Class is responsible for controlling dialog window for adding geographical location 
 * Manipulates model's data by adding, removing geographical location entities
 *
 */
public class LocationDialogController extends ElementDialogViewController {
	private String enteredLocation;
	private Location currentLocation;

	public LocationDialogController(Project project, DialogViewFactory viewFactory, Record currentRecord, Component parentView) {
		super(project, viewFactory, currentRecord, parentView);
	}

	@Override
	public void fillDialogData() {
		List<RecordElement> locationsList = recordsAsList(project.getRecordsIterator()).stream().flatMap(record -> getLocations(record).stream()).distinct().collect(Collectors.toList());
		dialog.setSelectionComboBox(locationsList);
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
		if(enteredLocation.equals("")) {
			dialog.showInformationDialog("Location cannot be empty");
			return;
		}
		if(currentLocation.getLocation().trim().equals(enteredLocation)) {
				dialog.showInformationDialog("Location already exists");
		} else {
			if(!dialog.positiveAnswerToQuestion("Add new location?"))
				return;
			
			Location newLocation = new Location(enteredLocation);
			currentRecord.addLocation(newLocation);
		}
		((JDialog)dialog).dispose();
	}

	private void getEventData() {
		currentLocation = (Location) dialog.getElement();
		enteredLocation = ((AddingLocationDialog)dialog).getLocationFieldText().trim();
	}
	
	private List<Record> recordsAsList(Iterator<Record> iter) {
		List<Record> records = new ArrayList<Record>();
		iter.forEachRemaining(e -> records.add(e));
		return records;
	}
	
	private List<Location> getLocations(Record record) {
		List<Location> locations = new ArrayList<Location>();
		record.getLocationsIterator().forEachRemaining(location -> locations.add(location));
		return locations;
	}
}
