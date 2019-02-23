package uz.diploma.gui.dialog;

import java.awt.event.ActionEvent;
import java.util.List;

import uz.diploma.listeners.DialogViewListener;
import uz.diploma.model.record.element.RecordElement;

/**
 * @author Spider
 * 
 * Interface that need to be implemented for view classes that present dialog window 
 *
 */
public interface WindowDialogView extends DialogView {

	public void addDialogViewListener(DialogViewListener listener);
	public void removeDialogViewListener(DialogViewListener listener);
	
	public void setSelectionComboBox(List<RecordElement> elementsList);
	public void setAllTextFields();
	public void comboBoxSelectionChanged(ActionEvent event);
	public void textFieldChanged();
	public void cancelButtonClicked();
	public void addButtonClicked();
	public RecordElement getElement();
	public void showInformationDialog(String message);
	public int showQuestionDialog(String question);
	public boolean positiveAnswerToQuestion(String question);

}