package uz.diploma.gui.dialog.quotation;

import java.util.List;

import uz.diploma.controller.dialog.quotation.ReferenceDialogController;
import uz.diploma.model.record.element.Record;

/**
 * @author Spider
 * 
 * Interface that need to be implemented for view classes that present add reference window 
 *
 */
public interface ReferenceDialogView {

	public void addDialogViewListener(ReferenceDialogController listener);
	public void removeDialogViewListener(ReferenceDialogController listener);
	public void cancelButtonClicked();
	public void addButtonClicked();
	public void setSelectionComboBox(List<Record> elementsList);
	public String getLocationText();
	public String getNotes();
	public Record getSelectedRecord();
	public void showInformationDialog(String message);
	public int showQuestionDialog(String question);
	public boolean positiveAnswerToQuestion(String question);
	
}
