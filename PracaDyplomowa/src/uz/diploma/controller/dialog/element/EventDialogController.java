package uz.diploma.controller.dialog.element;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.joda.time.IllegalFieldValueException;

import uz.diploma.gui.dialog.DialogViewFactory;
import uz.diploma.gui.dialog.element.AddingElementDialog;
import uz.diploma.gui.dialog.element.AddingEventDialog;
import uz.diploma.gui.window.panel.details.contexttab.panel.PanelView;
import uz.diploma.model.project.Project;
import uz.diploma.model.record.date.Date;
import uz.diploma.model.record.date.PreciseDate;
import uz.diploma.model.record.date.RangeDate;
import uz.diploma.model.record.element.HistoricalEvent;
import uz.diploma.model.record.element.Record;
import uz.diploma.model.record.element.RecordElement;
import uz.diploma.tools.Validator;

/**
 * @author Spider
 * 
 * Class is responsible for controlling dialog window for adding historical event 
 * Manipulates model's data by adding and editing historical event entities
 *
 */
public class EventDialogController extends ElementDialogViewController {
	
	private String enteredEventName;
	private String enteredEventDescription;
	private Date enteredDate;
	private HistoricalEvent currentEvent;

	public EventDialogController(Project project, DialogViewFactory viewFactory, Record currentRecord, Component parentView) {
		super(project, viewFactory, currentRecord, parentView);
	}

	@Override
	public void fillDialogData() {
		List<RecordElement> eventsList = recordsAsList(project.getRecordsIterator()).stream().flatMap(record -> getEvents(record).stream()).distinct().collect(Collectors.toList());
		dialog.setSelectionComboBox(eventsList);
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
		try {
		getEventData();
		
		if(enteredEventName.trim().equals("")) {
			dialog.showInformationDialog("Event name cannot be empty");
			return;
		}
		
		if(currentEvent.matchesName(enteredEventName)) {
			if(currentEvent.matchesDescription(enteredEventDescription) && currentEvent.matchesEventDate(enteredDate)) {
				dialog.showInformationDialog("Event already exists");
			} else {
				if(!dialog.positiveAnswerToQuestion("Save changes to this event?"))
					return;
				
				updateEvent();
			}
		} else {
			if(!dialog.positiveAnswerToQuestion("Add new event?"))
				return;
			
			if(enteredDate instanceof RangeDate)
				if(!isStartDateBeforeEndDate()) {
					if(!dialog.positiveAnswerToQuestion("The end event date is before start event date. Continue anyway?"))
						return;
			}
			
			addEvent();
		}
		((JDialog)dialog).dispose();
		} catch(IllegalFieldValueException e) {
			dialog.showInformationDialog("Some date fileds values are out of range. Correct it and try again");	
		} catch(IllegalArgumentException e) {
			dialog.showInformationDialog("Century, year, month and day need to be a number. No letters or special characters are allowed. \nTime should be entered in format \"hh:mm\"");
			dialog.showInformationDialog(e.getMessage());
		}
	}

	private void addEvent() {
		HistoricalEvent newEvent = new HistoricalEvent(enteredEventName);
		newEvent.setDesciption(enteredEventDescription);
		newEvent.setEventDate(enteredDate);
		currentRecord.addEvent(newEvent);
	}

	private void updateEvent() {
		currentEvent.setName(enteredEventName);
		currentEvent.setDesciption(enteredEventDescription);
		currentEvent.setEventDate(enteredDate);
	}

	private void getEventData() {
		currentEvent = (HistoricalEvent) dialog.getElement();
		enteredEventName = ((AddingEventDialog)dialog).getNameFieldText().trim();
		enteredEventDescription = ((AddingEventDialog)dialog).getDescriptionAreaText().trim();
		enteredDate = getDate();
	}
	
	private boolean isStartDateBeforeEndDate() {
			RangeDate date = (RangeDate)enteredDate;
			return date.getStartDate().isBefore(date.getEndDate());
	}
	
	private Date getDate() { 
		if(((AddingEventDialog)dialog).isPreciseDateSelected()) {
			return new PreciseDate(((AddingEventDialog)dialog).getDate(), ((AddingEventDialog)dialog).isBeforeChristusButtonSelected());
		} else {
			String[] startDateElements = Arrays.copyOfRange(((AddingEventDialog)dialog).getDate(), 0, 5);
			String[] endDateElements = Arrays.copyOfRange(((AddingEventDialog)dialog).getDate(), 5, 10);
			PreciseDate startDate = new PreciseDate(startDateElements, ((AddingEventDialog)dialog).isBeforeChristusButtonSelected());
			PreciseDate endDate = new PreciseDate(endDateElements, ((AddingEventDialog)dialog).isBeforeChristusButtonSelected());
			return new RangeDate(startDate, endDate);
		}
	}
	
	private List<Record> recordsAsList(Iterator<Record> iter) {
		List<Record> records = new ArrayList<Record>();
		iter.forEachRemaining(e -> records.add(e));
		return records;
	}
	
	private List<HistoricalEvent> getEvents(Record record) {
		List<HistoricalEvent> events = new ArrayList<HistoricalEvent>();
		record.getEventsIterator().forEachRemaining(event -> events.add(event));
		return events;
	}
}
