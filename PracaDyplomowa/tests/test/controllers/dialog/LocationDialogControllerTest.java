package test.controllers.dialog;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Component;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import uz.diploma.controller.dialog.element.LocationDialogController;
import uz.diploma.gui.dialog.element.AddingLocationDialog;
import uz.diploma.gui.dialog.element.LocationDialogFactory;
import uz.diploma.model.project.Project;
import uz.diploma.model.record.element.Record;

@RunWith(MockitoJUnitRunner.class)
public class LocationDialogControllerTest {

	@Mock
	LocationDialogFactory viewFactory;
	@Mock
	AddingLocationDialog dialog;
	@Mock
	Component parentView;
	@Spy
	Project project;
	@Mock
	Record currentRecord;
	LocationDialogController controller;

	@Before
	public void setUp() throws Exception {
		when(viewFactory.getDialogView()).thenReturn(dialog);
		when(dialog.getLocationFieldText()).thenReturn("abc");
		controller = new LocationDialogController(project, viewFactory, currentRecord, parentView);
	}

	@Test
	public void onnTextFieldChanged_ShouldInvokeGetElemetMethod() {
		controller.onTextFieldChanged();
		verify(dialog, atLeast(1)).getElement();
	}
	
	@Test
	public void onnTextFieldChanged_ShouldInvokeGetLocationFieldText() {
		controller.onTextFieldChanged();
		verify(dialog, atLeast(1)).getLocationFieldText();
	}

	@Test
	public void onCancelButtonClicked_ShouldInvokePositiveAnswerToQuestion() {
		controller.onCancelButtonClicked();
		verify(dialog, times(1)).positiveAnswerToQuestion("Are you sure?");
	}


}
