package test.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import uz.diploma.controller.context.ContextTabController;
import uz.diploma.controller.quotation.QuotationTabController;
import uz.diploma.gui.window.panel.details.contexttab.ContextTab;
import uz.diploma.gui.window.panel.details.quotationtab.QuotationTabPanel;
import uz.diploma.gui.window.panel.details.quotationtab.QuotationTabView;
import uz.diploma.model.project.Project;
import uz.diploma.model.record.element.HistoricalPeriod;
import uz.diploma.model.record.element.Quotation;
import uz.diploma.model.record.element.Record;
import uz.diploma.model.record.element.Reference;

@RunWith(MockitoJUnitRunner.class)
public class QuotationTabControllerTest {

	
	@Spy
	Record currentRecord = new Record("Title");
	@Mock
	QuotationTabPanel quotationTab;
	@Mock
	Project project;
	@InjectMocks
	QuotationTabController controller;
	
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void onResultListItemSelected_NotNullRecordGiven_ShouldInvokeUpdateReferenceListMethod() {
		List<Reference> references = new ArrayList<Reference>();
		currentRecord.getReferencesIterator().forEachRemaining(reference -> references.add(reference));
		
		controller.onResultListItemSelected(currentRecord);
		verify(quotationTab, times(1)).updateReferenceList(references);
	}
	
	@Test
	public void onResultListItemSelected_NotNullRecordGiven_ShouldInvokeUpdateQuotationsListMethod() {
		List<Quotation> quotations = new ArrayList<Quotation>();
		currentRecord.getQuotationsIterator().forEachRemaining(quotation -> quotations.add(quotation));
		
		controller.onResultListItemSelected(currentRecord);
		verify(quotationTab, times(1)).updateQuotationsList(quotations);
	}

}
