package test.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import uz.diploma.controller.LeftPaneController;
import uz.diploma.gui.window.panel.record.RecordsPanel;
import uz.diploma.model.project.Project;
import uz.diploma.model.record.element.Record;

@RunWith(MockitoJUnitRunner.class)
public class LeftPaneControllerTest {
	
	@Mock
	List<Record> recordList;
	@Spy
	Project project;
	@Mock
	RecordsPanel leftPanel;
	@InjectMocks
	LeftPaneController controller;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void onSearchTextChanged_ShouldInvokeSetResultListMethod() {
		controller.onSearchTextChanged("ala");
		verify(leftPanel, atLeast(1)).setResultList(recordsAsList(project.getRecordsIterator()));
	}

	@Test
	public void recordMenuItemSelected_ShouldInvokeSetResultListMethod() {
		controller.recordMenuItemSelected();
		verify(leftPanel, atLeast(1)).setResultList(recordsAsList(project.getRecordsIterator()));
	}
	
	@Test
	public void recordMenuItemSelected_ShouldInvokeSetResultListSelectionMethod() {
		controller.recordMenuItemSelected();
		verify(leftPanel, atLeast(1)).setResultListSelection();
	}
	
	private List<Record> recordsAsList(Iterator<Record> iter) {
		List<Record> records = new ArrayList<Record>();
		iter.forEachRemaining(e -> records.add(e));
		return records;
	}

}
