package uz.diploma.controller.dialog;

import java.awt.Component;
import uz.diploma.gui.dialog.DialogViewFactory;
import uz.diploma.listeners.DialogViewListener;
import uz.diploma.model.project.Project;
import uz.diploma.model.record.element.Record;
import uz.diploma.tools.Validator;

/**
 * @author Spider
 *
 * Class that need to be extended by class that is controller for dialog window
 *
 */
public abstract class DialogViewController implements DialogViewListener {

	protected Project project;
	protected DialogViewFactory viewFactory;
	protected Record currentRecord;
	protected Component parentView;

	
	/**
	 * @param project - project which data are manipulated
	 * @param viewFactory - factory that returns dialog view
	 * @param currentRecord - currently selected record
	 * @param parentView - dialog window is positioned relatively to this component
	 */
	public DialogViewController(Project project, DialogViewFactory viewFactory, Record currentRecord, Component parentView) {
		Validator.ensureIsNotNull(project, "project");
		Validator.ensureIsNotNull(viewFactory, "viewFactory");
		Validator.ensureIsNotNull(currentRecord, "currentRecord");
		Validator.ensureIsNotNull(parentView, "parentView");
		this.project = project;
		this.viewFactory = viewFactory;
		this.currentRecord = currentRecord;
		this.parentView = parentView;
	}

	public abstract void fillDialogData();

}