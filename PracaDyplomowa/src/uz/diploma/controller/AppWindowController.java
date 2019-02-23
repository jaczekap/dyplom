package uz.diploma.controller;


import java.util.EventListener;

import javax.swing.event.EventListenerList;

import uz.diploma.controller.dialog.record.AddRecordDialogController;
import uz.diploma.gui.dialog.record.AddRecordDialog;
import uz.diploma.gui.window.WindowMenuListener;
import uz.diploma.gui.window.WindowView;
import uz.diploma.gui.window.panel.record.ResultListListener;
import uz.diploma.listeners.DataStoreEventsListener;
import uz.diploma.listeners.LoadEventListener;
import uz.diploma.listeners.RecordMenuListener;
import uz.diploma.model.project.LoadEvent;
import uz.diploma.model.project.Project;
import uz.diploma.model.record.element.Record;
import uz.diploma.tools.Validator;

/**
 * @author Spider
 * 
 * Class is responsible for controlling data flow between model and Main Window
 * including menu bar options
 */
public class AppWindowController implements WindowMenuListener, ResultListListener, LoadEventListener {
	
	private Project project;
	private WindowView appWindow;
	private Record currentRecord;
	private EventListenerList removeRecordListeners;

	/**
	 * @param project - current project
	 * @param appWindow - application window view
	 */
	public AppWindowController(Project project, WindowView appWindow) {
		removeRecordListeners = new EventListenerList();
		Validator.ensureIsNotNull(appWindow, "appWindow");
		this.appWindow = appWindow;
		Validator.ensureIsNotNull(project, "project");
		this.project = project;
		appWindow.addMenuListener(this);
	}

	@Override
	public void onRemoveRecordSelected() {
		if(currentRecord != null) {
			project.removeRecord(currentRecord);
			recordListChanged();
		}
	}

	@Override
	public void onResultListItemSelected(Record selectedRecord) {
		currentRecord = selectedRecord;
	}
	
	public void addListener(RecordMenuListener listener) {
		removeRecordListeners.add(RecordMenuListener.class, listener);
	}

	public void removeListener(RecordMenuListener listener) {
		removeRecordListeners.remove(RecordMenuListener.class, listener);
	}
	
	private void recordListChanged() {
		for(RecordMenuListener listener : removeRecordListeners.getListeners(RecordMenuListener.class)) {
			listener.recordMenuItemSelected(); 
		}
	}

	@Override
	public void loadEventReceived(LoadEvent event) {
		project = event.getProject();
	}

	@Override
	public void onAddRecordSelected() {
		new AddRecordDialogController(project, new AddRecordDialog());
		recordListChanged();
	}
}
