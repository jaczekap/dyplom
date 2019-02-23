package uz.diploma.gui.dialog.quotation;

import uz.diploma.controller.dialog.quotation.QuotationDialogController;

/**
 * @author Spider
 * 
 * Interface that need to be implemented for view classes that present add quotation window 
 *
 */
public interface QuotationDialogView {

	public void addDialogViewListener(QuotationDialogController listener);
	public void removeDialogViewListener(QuotationDialogController listener);
	public void cancelButtonClicked();
	public void addButtonClicked();
	public String getQuote();
	public String getLocationText();
	public void showInformationDialog(String message);
	public int showQuestionDialog(String question);
	public boolean positiveAnswerToQuestion(String question);
}
