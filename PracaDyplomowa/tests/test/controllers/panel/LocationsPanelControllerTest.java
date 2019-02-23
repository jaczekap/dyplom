package test.controllers.panel;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import uz.diploma.controller.context.CharactersPanelController;
import uz.diploma.gui.window.panel.details.contexttab.panel.LocationsPanel;
import uz.diploma.model.project.Project;
import uz.diploma.model.record.element.Record;

@RunWith(MockitoJUnitRunner.class)
public class LocationsPanelControllerTest {

	@Mock
	LocationsPanel panel;
	@Mock
	Project project;
	@Mock
	Record currentRecord;
	@InjectMocks
	CharactersPanelController controller;
	

	@Before
	public void setUp() throws Exception {
	
	}

	@Test
	public void onFocusGained_ShouldInvokeOnNewRecordMethod() {
		controller.onFocusGained();
		verify(panel, times(1)).onNewRecord(null);
	}

}
