package test.controllers;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import uz.diploma.controller.overview.SummaryPanelController;
import uz.diploma.gui.window.panel.details.overviewtab.OverviewSummary;
import uz.diploma.model.record.element.Record;

@RunWith(MockitoJUnitRunner.class)
public class SummaryPanelControllerTest {
	
	@Mock
	Record record;
	@Mock
	OverviewSummary summary;
	@InjectMocks
	SummaryPanelController controller;
	

	@Before
	public void setUp() throws Exception {
		controller.onResultListItemSelected(record);
	}

	@Test
	public void onResultListItemSelected_NotNullRecordGiven_ShouldInvokeSetFieldValueMethod() {
		verify(summary, times(1)).setFieldValue(null);
	}

	@Test
	public void onTextFieldChanged_ShouldInvokeSetDescriptionMethod() {
		controller.onTextFieldChanged();
		verify(record, times(1)).setDescription(null);
	}

}
