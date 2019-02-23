package uz.diploma.controller.dialog.record;

import uz.diploma.gui.dialog.record.AddRecordDialog;
import uz.diploma.model.project.Project;
import uz.diploma.model.record.element.Author;
import uz.diploma.model.record.element.MediumType;
import uz.diploma.model.record.element.Record;
import uz.diploma.tools.Validator;

/**
 * @author Spider
 *
 * Class is responsible for controlling dialog window for adding record 
 * Manipulates model's data by adding record entities
 *
 */
public class AddRecordDialogController implements AddRecordListener {
	
	private Project project;
	private AddRecordDialog dialog;
	
	/**
	 * @param project - project that record will be add to
	 * @param dialog - dialog window view
	 */
	public AddRecordDialogController(Project project, AddRecordDialog dialog) {
		Validator.ensureIsNotNull(project, "project");
		Validator.ensureIsNotNull(dialog, "dialog");
		this.project = project;
		this.dialog = dialog;
		dialog.addListener(this);
		dialog.setSelectionComboBox();
		dialog.setSelectedMediumType();
		dialog.setVisible(true);
	}

	@Override
	public void onCancelButtonClicked() {
		dialog.dispose();
	}

	@Override
	public void onAddButtonClicked() {
		if(dialog.getTitleFieldText().trim().equals("")) {
			dialog.showInformationDialog("Title cannot be empty");
			return;
		}
		addRecord();
		dialog.dispose();
	}
	
	private void addRecord() {
		String title = dialog.getTitleFieldText();
		Author author = new Author(dialog.getAuthorFieldText());
		String publicationDate = dialog.getPublicationFieldText();
		MediumType medium = dialog.getMediumType();
		
		Record recordToAdd = new Record(title);
		recordToAdd.setAuthor(author);
		recordToAdd.setPublicationData(publicationDate);
		recordToAdd.setMedium(medium);
		
		project.addRecord(recordToAdd);
	}

}
