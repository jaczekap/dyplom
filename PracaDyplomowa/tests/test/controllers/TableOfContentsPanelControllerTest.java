package test.controllers;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import uz.diploma.controller.overview.TableOfContentsPanelController;
import uz.diploma.gui.window.panel.details.overviewtab.OverviewTableOfContents;
import uz.diploma.model.record.element.Record;

@RunWith(MockitoJUnitRunner.class)
public class TableOfContentsPanelControllerTest {

	
	@Mock
	Record record;
	@Mock
	OverviewTableOfContents tableOfContents;
	@InjectMocks
	TableOfContentsPanelController controller;
	

	@Before
	public void setUp() throws Exception {
		controller.onResultListItemSelected(record);
	}

	@Test
	public void onResultListItemSelected_NotNullRecordGiven_ShouldInvokeSetFieldValueMethod() {
		verify(tableOfContents, times(1)).setFieldValue(null);
	}

	@Test
	public void onTextFieldChanged_ShouldInvokeSetTableOfContentsMethod() {
		controller.onTextFieldChanged();
		verify(record, times(1)).setTableOfContents(null);
	}

}
