package uz.diploma.controller.context;

import uz.diploma.gui.dialog.DialogViewFactory;
import uz.diploma.gui.dialog.element.AddingEventDialog;
import uz.diploma.gui.window.panel.details.contexttab.FilterTextFieldListener;
import uz.diploma.gui.window.panel.details.contexttab.panel.PanelView;
import uz.diploma.gui.window.panel.record.ResultListListener;
import uz.diploma.listeners.LoadEventListener;
import uz.diploma.listeners.PanelViewListener;
import uz.diploma.model.project.LoadEvent;
import uz.diploma.model.project.Project;
import uz.diploma.model.record.element.Record;
import uz.diploma.tools.Validator;

/**
 * @author Spider
 * 
 * Class that need to be extended by class that is controller for each particular data panel in context tab  
 * Examples of this classes are controllers for character panel, historical events panel etc.
 *
 */
public abstract class PanelViewController implements ResultListListener, LoadEventListener, PanelViewListener, FilterTextFieldListener {

	protected PanelView panel;
	protected Project project;
	protected DialogViewFactory viewFactory;
	protected Record currentRecord;

	/**
	 * @param panel view that is being controlled
	 * @param project project which data are shown in panel view 
	 */
	public PanelViewController(PanelView panel, Project project) {
		Validator.ensureIsNotNull(panel, "panel");
		Validator.ensureIsNotNull(project, "project");
		this.panel = panel;
		this.project = project;
		panel.addPanelViewListener(this);
	}

	@Override
	public void onResultListItemSelected(Record selectedRecord) {
		Validator.ensureIsNotNull(selectedRecord, "selectedRecord");
		panel.onNewRecord(selectedRecord);
		currentRecord = selectedRecord;
	}
	
	@Override
	public void loadEventReceived(LoadEvent event) {
		Validator.ensureIsNotNull(event, "event");
		project = event.getProject();
	}
}