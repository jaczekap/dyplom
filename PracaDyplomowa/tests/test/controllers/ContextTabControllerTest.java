package test.controllers;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import uz.diploma.controller.context.ContextTabController;
import uz.diploma.gui.window.panel.details.contexttab.ContextTab;
import uz.diploma.model.record.element.HistoricalPeriod;
import uz.diploma.model.record.element.Record;

@RunWith(MockitoJUnitRunner.class)
public class ContextTabControllerTest {
	
	
	@Mock
	ContextTab contextTab;
	@InjectMocks
	ContextTabController controller;
	private HistoricalPeriod period;
	private Record record;

	@Before
	public void setUp() {
		record = new Record("Test");
		period = HistoricalPeriod.MIDDLE_AGES;
		record.setHistoricalPeriod(period);
	}
	
	@Test
	public void onResultListItemSelected_NotNullRecordGiven_ShouldInvokeSetCurrentPeriodMethod() {
		controller.onResultListItemSelected(record);
		verify(contextTab, times(1)).setCurrentPeriod(record.getHistoricalPeriod());
	}

}
