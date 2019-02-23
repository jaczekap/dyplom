package uz.diploma.viewer.controller;

import java.awt.Component;
import java.util.EventListener;

import javax.swing.JDialog;

import uz.diploma.tools.Validator;
import uz.diploma.viewer.model.ImageTag;
import uz.diploma.viewer.model.TagRenderer;
import uz.diploma.viewer.view.AddTagDialog;
import uz.diploma.viewer.view.AddTagDialogView;
import uz.diploma.viewer.view.TagDialogListener;

/**
 * @author Spider
 * 
 * Class is responsible for controlling dialog window for adding tag to picture 
 * Manipulates model's by setting data to provided in constructor tag
 *
 */
public class AddTagController implements TagDialogListener {
	
	private AddTagDialogView dialog;
	private ImageTag currentTag;
	private String currentName;
	private String currentDescription;

	/**
	 * @param dialog - dialog window that display controls needed to set data on the tag
	 * @param currentTag - tag which data are to be set
	 */
	public AddTagController(AddTagDialogView dialog, ImageTag currentTag) {
		Validator.ensureIsNotNull(dialog, "dialog");
		Validator.ensureIsNotNull(currentTag, "currentTag");
		this.dialog = dialog;
		this.currentTag = currentTag;
		this.currentName = currentTag.getName();
		this.currentDescription = currentTag.getDescription();
		this.dialog.addTagDialogListener(this);
		this.dialog.setAllTextFields(currentName, currentDescription);
		((Component) dialog).setVisible(true);
	}

	@Override
	public void onTextFieldChanged() {
		getFieldValues();
	}

	@Override
	public void onCancelButtonClicked() {
		((JDialog) dialog).dispose();
	}

	@Override
	public void onAddButtonClicked() {
		getFieldValues();
		currentTag.setName(currentName);
		currentTag.setDescription(currentDescription);
		((JDialog) dialog).dispose();
	}
	
	private void getFieldValues() {
		currentName = ((AddTagDialog) dialog).getNameFieldText();
		currentDescription = ((AddTagDialog)dialog).getDescriptionAreaText();
	}

}
