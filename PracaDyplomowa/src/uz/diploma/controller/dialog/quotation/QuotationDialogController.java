package uz.diploma.controller.dialog.quotation;

import java.awt.Component;
import java.awt.Window;
import java.util.EventListener;

import javax.swing.JDialog;
import javax.swing.SwingUtilities;

import uz.diploma.gui.dialog.quotation.QuotationDialogView;
import uz.diploma.model.record.element.Quotation;
import uz.diploma.model.record.element.Record;
import uz.diploma.tools.Validator;

/**
 * @author Spider
 * 
 * Class is responsible for controlling dialog window for adding quotation 
 * Manipulates model's data by adding quotation entities
 *
 */
public class QuotationDialogController implements EventListener {
	
	private Record currentRecord;
	private QuotationDialogView dialog;
	private String quoteLocation;
	private String quote;

	/**
	 * @param dialog - dialog window view
	 * @param currentRecord - currently selected record
	 * @param parentView - dialog window is positioned relatively to this component
	 */
	public QuotationDialogController(QuotationDialogView dialog, Record currentRecord, Component parentView) {
		Validator.ensureIsNotNull(dialog, "dialog");
		Validator.ensureIsNotNull(currentRecord, "currentRecord");
		Validator.ensureIsNotNull(parentView, "parentView");
		this.currentRecord = currentRecord;
		this.dialog = dialog;
		dialog.addDialogViewListener(this);
		((Window) dialog).setLocationRelativeTo(SwingUtilities.getWindowAncestor(parentView));
		((Component) dialog).setVisible(true);
	}

	public void onCancelButtonClicked() {
		if(!dialog.positiveAnswerToQuestion("Are you sure?"))
			return;
		
		((JDialog)dialog).dispose();
	}

	public void onAddButtonClicked() {
		if(!dialog.positiveAnswerToQuestion("Add this Quote?"))
			return;
		
		quoteLocation = dialog.getLocationText();
		quote = dialog.getQuote();
		
		Quotation quotation = new Quotation(quoteLocation, quote);
		
		currentRecord.addQuotation(quotation);
		
		((JDialog)dialog).dispose();
	}

}
