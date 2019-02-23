package uz.diploma.controller.dialog.source;

import java.awt.Component;
import java.awt.Window;
import uz.diploma.controller.dialog.DialogViewController;
import uz.diploma.gui.dialog.DialogViewFactory;
import uz.diploma.gui.dialog.source.SourceDialogView;
import uz.diploma.model.project.Project;
import uz.diploma.model.record.element.Record;
import uz.diploma.model.record.source.SourceLocationFactory;

/**
 * @author Spider
 * 
 * Class that need to be extended by class that is controller for dialog window that allow to edit source locations  
 *
 */
public abstract class SourceDialogViewController extends DialogViewController {
	
	protected SourceDialogView dialog;
	protected SourceLocationFactory sourceFactory;
	
	public SourceDialogViewController(Project project, DialogViewFactory viewFactory,
			Record currentRecord, Component parentView) {
		super(project, viewFactory, currentRecord, parentView);
		this.dialog = (SourceDialogView) viewFactory.getDialogView();
		((Window) dialog).setLocationRelativeTo(parentView);
		dialog.addDialogViewListener(this);
		fillDialogData();
		sourceFactory = new SourceLocationFactory();
		((Component) dialog).setVisible(true);
	}

	public abstract void onRemoveButtonClicked();

	public abstract void onSelectSourceButtonClicked();

}
