package uz.diploma.controller.dialog.element;

import java.awt.Component;
import java.awt.Window;
import javax.swing.SwingUtilities;

import uz.diploma.controller.dialog.DialogViewController;
import uz.diploma.gui.dialog.DialogViewFactory;
import uz.diploma.gui.dialog.element.AddingElementDialog;
import uz.diploma.model.project.Project;
import uz.diploma.model.record.element.Record;
import uz.diploma.tools.Validator;

/**
 * @author Spider
 * 
 * Class that need to be extended by class that is controller for dialog that allow to add record element  
 * Examples of this classes are controllers for character, historical events, location and keyword dialog window
 *
 */
public abstract class ElementDialogViewController extends DialogViewController {
	
	protected AddingElementDialog dialog;

	/**
	 * @param project - project which data are manipulated
	 * @param viewFactory - factory that returns dialog view
	 * @param currentRecord - currently selected record
	 * @param parentView - dialog window is positioned relatively to this component
	 */
	public ElementDialogViewController(Project project, DialogViewFactory viewFactory, Record currentRecord, Component parentView) {
		super(project, viewFactory, currentRecord, parentView);
		Validator.ensureIsNotNull(viewFactory, "viewFactory");
		this.dialog = (AddingElementDialog) viewFactory.getDialogView();
		((Window) dialog).setLocationRelativeTo(SwingUtilities.getWindowAncestor(parentView));
		dialog.addDialogViewListener(this);
		fillDialogData();
		((Component) dialog).setVisible(true);
	}

}
