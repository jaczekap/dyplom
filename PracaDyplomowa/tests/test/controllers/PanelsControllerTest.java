package test.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import uz.diploma.controller.LeftPaneController;
import uz.diploma.gui.window.panel.details.RightPanel;
import uz.diploma.gui.window.panel.record.RecordsPanel;
import uz.diploma.model.project.LoadEvent;
import uz.diploma.model.project.Project;
import uz.diploma.model.record.element.Record;

@RunWith(MockitoJUnitRunner.class)
public class PanelsControllerTest {
	
	@InjectMocks
	LoadEvent loadEvent;
	@Spy
	Project project;
	@Mock
	RecordsPanel leftPanel;
	@Mock
	RightPanel rightPanel;
	@InjectMocks
	LeftPaneController controller;

	@Test
	public void loadEventReceived_NotNullEventGiven_ShouldInvokeSetResultListMethod() {
		controller.loadEventReceived(loadEvent);
		verify(leftPanel, atLeast(1)).setResultList(recordsAsList(project.getRecordsIterator()));
	}
	
	@Test
	public void loadEventReceived_NotNullEventGiven_ShouldInvokeSetResultListSelectionMethod() {
		controller.loadEventReceived(loadEvent);
		verify(leftPanel, atLeast(1)).setResultListSelection();
	}

	@Test
	public void onSearchTextChanged_StringGiven_ShouldInvokeSetResultListMethod() {
		controller.onSearchTextChanged("test");
		verify(leftPanel, atLeast(1)).setResultList(recordsAsList(project.getRecordsIterator()));
	}
	
	private List<Record> recordsAsList(Iterator<Record> iter) {
		List<Record> records = new ArrayList<Record>();
		iter.forEachRemaining(e -> records.add(e));
		return records;
	}

}
