package test.controllers;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import uz.diploma.controller.overview.OverviewTabController;
import uz.diploma.gui.window.panel.details.overviewtab.OverviewTab;
import uz.diploma.model.project.Project;
import uz.diploma.model.record.element.Author;
import uz.diploma.model.record.element.MediumType;
import uz.diploma.model.record.element.Record;

@RunWith(MockitoJUnitRunner.class)
public class OverviewTabControllerTest {
	
	@Mock
	OverviewTab overviewTabPanel;
	@Mock
	Project project;
	@InjectMocks
	OverviewTabController controller;
	Record record;
	
	@Before
	public void setUp() throws Exception {
		record = new Record("test");
		record.setAuthor(new Author("test"));
		record.setMedium(MediumType.BOOK);
		record.setPublishingYear("test");
		record.setPublicationData("test");
	}

	@Test
	public void onResultListItemSelected_NotNullRecordGiven_ShouldInvokeSetAuthorFieldMethod() {
		controller.onResultListItemSelected(record);
		
		verify(overviewTabPanel, times(1)).setAuthorField("test");
	}
	
	@Test
	public void onResultListItemSelected_NotNullRecordGiven_ShouldInvokeSetPublicationDateFieldMethod() {
		controller.onResultListItemSelected(record);
		
		verify(overviewTabPanel, times(1)).setPublicationDateField("test");
	}
	
	@Test
	public void onResultListItemSelected_NotNullRecordGiven_ShouldInvokeSetPublishedFieldMethod() {
		controller.onResultListItemSelected(record);
		
		verify(overviewTabPanel, times(1)).setPublishedField("test");
	}
	
	@Test
	public void onResultListItemSelected_NotNullRecordGiven_ShouldInvokeSetTitleFieldMethod() {
		controller.onResultListItemSelected(record);
		
		
		verify(overviewTabPanel, times(1)).setTitleField("test");
	}

}
