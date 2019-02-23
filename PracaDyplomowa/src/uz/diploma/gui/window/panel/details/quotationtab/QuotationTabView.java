package uz.diploma.gui.window.panel.details.quotationtab;

import java.util.List;

import uz.diploma.controller.quotation.QuotationTabController;
import uz.diploma.model.record.element.Quotation;
import uz.diploma.model.record.element.Record;
import uz.diploma.model.record.element.Reference;

/**
 * @author Spider
 * 
 * Interface that need to be implemented by quotation tab view classes
 *
 */
public interface QuotationTabView {
	
	public void addListener(QuotationTabController listener);
	public void removeListener(QuotationTabController listener);
	public void addReferenceToButtonPressed();
	public void removeReferenceToButtonPressed();
	public void addQuotationButtonPressed();
	public void removeQuotationButtonPressed();
	public void updateReferenceList(List<Reference> references);
	public void focusGot();
	public void updateQuotationsList(List<Quotation> quotations);
	public Record getReferenceRecord();
	public String getReferenceLocation();
	public String getReferenceNotes();
	public String getQuotationLocation();
	public String getQuote();

}
