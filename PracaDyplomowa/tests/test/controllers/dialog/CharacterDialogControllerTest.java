package test.controllers.dialog;

import static org.mockito.Mockito.*;

import java.awt.Component;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;




import uz.diploma.controller.dialog.element.CharacterDialogController;
import uz.diploma.gui.dialog.element.AddingCharacterDialog;
import uz.diploma.gui.dialog.element.CharacterDialogFactory;
import uz.diploma.model.project.Project;
import uz.diploma.model.record.element.Record;

@RunWith(MockitoJUnitRunner.class)
public class CharacterDialogControllerTest {
	
	@Mock
	CharacterDialogFactory viewFactory;
	@Mock
	AddingCharacterDialog dialog;
	@Mock
	Component parentView;
	@Spy
	Project project;
	@Mock
	Record currentRecord;
	CharacterDialogController controller;

	@Before
	public void setUp() throws Exception {
		when(viewFactory.getDialogView()).thenReturn(dialog);
		when(dialog.getNameFieldText()).thenReturn("abc");
		when(dialog.getDescriptionAreaText()).thenReturn("abc");
		controller = new CharacterDialogController(project, viewFactory, currentRecord, parentView);
	}

	@Test
	public void onnTextFieldChanged_ShouldInvokeGetElemetMethod() {
		controller.onTextFieldChanged();
		verify(dialog, atLeast(1)).getElement();
	}
	
	@Test
	public void onnTextFieldChanged_ShouldInvokeGetNameFieldText() {
		controller.onTextFieldChanged();
		verify(dialog, atLeast(1)).getNameFieldText();
	}
	
	@Test
	public void onnTextFieldChanged_ShouldInvokeGetSelectedPeriodMethod() {
		controller.onTextFieldChanged();
		verify(dialog, atLeast(1)).getSelectedPeriod();
	}
	
	@Test
	public void onnTextFieldChanged_ShouldInvokeGetDescriptionAreaTextMethod() {
		controller.onTextFieldChanged();
		verify(dialog, atLeast(1)).getDescriptionAreaText();
	}

	@Test
	public void onCancelButtonClicked_ShouldInvokePositiveAnswerToQuestion() {
		controller.onCancelButtonClicked();
		verify(dialog, times(1)).positiveAnswerToQuestion("Are you sure?");
	}

}
