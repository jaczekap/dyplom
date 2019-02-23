package uz.diploma.controller.quotation;

import java.awt.Component;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import uz.diploma.controller.dialog.quotation.QuotationDialogController;
import uz.diploma.controller.dialog.quotation.ReferenceDialogController;
import uz.diploma.gui.dialog.quotation.AddQuotationDialog;
import uz.diploma.gui.dialog.quotation.AddReferenceDialog;
import uz.diploma.gui.window.panel.details.quotationtab.QuotationTabView;
import uz.diploma.gui.window.panel.record.ResultListListener;
import uz.diploma.listeners.LoadEventListener;
import uz.diploma.model.project.LoadEvent;
import uz.diploma.model.project.Project;
import uz.diploma.model.record.element.HistoricalCharacter;
import uz.diploma.model.record.element.Quotation;
import uz.diploma.model.record.element.Record;
import uz.diploma.model.record.element.Reference;
import uz.diploma.tools.Validator;

/**
 * @author Spider
 * 
 * Class is responsible for controlling data flow between model and Quotation tab
 *
 */
public class QuotationTabController implements ResultListListener, LoadEventListener {
	
	private QuotationTabView quotationTab;
	private Project project;
	private Record currentRecord;

	public QuotationTabController(QuotationTabView quotationTab, Project project) {
		Validator.ensureIsNotNull(quotationTab, "quotationTab");
		Validator.ensureIsNotNull(project, "project");
		this.quotationTab = quotationTab;
		this.project = project;
		quotationTab.addListener(this);
	}



	public void onAddReferenceToButtonPressed() {
		new ReferenceDialogController(project, new AddReferenceDialog(), currentRecord, (Component)quotationTab);
	}



	@Override
	public void onResultListItemSelected(Record selectedRecord) {
		currentRecord = selectedRecord;
		quotationTab.updateReferenceList(getReferences(currentRecord));
		quotationTab.updateQuotationsList(getQuotations(currentRecord));
	}



	@Override
	public void loadEventReceived(LoadEvent event) {
		project = event.getProject();
	}



	public void onRemoveReferenceToButtonPressed() {
		findAndRemoveReference();
		quotationTab.updateReferenceList(getReferences(currentRecord));
	}



	public void onFocusGot() {
		quotationTab.updateReferenceList(getReferences(currentRecord));
		quotationTab.updateQuotationsList(getQuotations(currentRecord));
	}
	
	private void findAndRemoveReference() {
		Record referenceRecord = quotationTab.getReferenceRecord();
		Reference referenceForm = null;
		Reference referenceTo = null;
		
		for(Reference reference : getReferences(referenceRecord)) {
			if(reference.getLocation().equals(quotationTab.getReferenceLocation()) && reference.getNotes().equals(quotationTab.getReferenceNotes())) {
				referenceForm = reference;
			}
		}
		
		referenceRecord.removeReference(referenceForm);
		
		for(Reference reference : getReferences(currentRecord)) {
			if(reference.getLocation().equals(quotationTab.getReferenceLocation()) && reference.getNotes().equals(quotationTab.getReferenceNotes())) {
				referenceTo = reference;
			}
		}
		
		currentRecord.removeReference(referenceTo);
		
	}



	public void onRemoveQuotationButtonPressed() {
		Quotation quote = null;
		
		for(Quotation quotation : getQuotations(currentRecord)) {
			if(quotation.getLocation().equals(quotationTab.getQuotationLocation()) && quotation.getQuote().equals(quotationTab.getQuote())) {
				quote = quotation;
			}
		}
		
		currentRecord.removeQuotation(quote);
		quotationTab.updateQuotationsList(getQuotations(currentRecord));
	}



	public void onAddQuotationButtonPressed() {
		new QuotationDialogController(new AddQuotationDialog(), currentRecord, (Component)quotationTab);
	}

	private List<Reference> getReferences(Record record) {
		List<Reference> references = new ArrayList<Reference>();
		record.getReferencesIterator().forEachRemaining(reference -> references.add(reference));
		return references;
	}
	
	private List<Quotation> getQuotations(Record record) {
		List<Quotation> quotations = new ArrayList<Quotation>();
		record.getQuotationsIterator().forEachRemaining(quotation -> quotations.add(quotation));
		return quotations;
	}
}
