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

import uz.diploma.controller.dialog.element.KeyWordDialogController;
import uz.diploma.gui.dialog.element.AddingKeyWordDialog;
import uz.diploma.gui.dialog.element.KeyWordDialogFactory;
import uz.diploma.model.project.Project;
import uz.diploma.model.record.element.Record;

@RunWith(MockitoJUnitRunner.class)
public class KeyWordDialogControllerTest {

	@Mock
	KeyWordDialogFactory viewFactory;
	@Mock
	AddingKeyWordDialog dialog;
	@Mock
	Component parentView;
	@Spy
	Project project;
	@Mock
	Record currentRecord;
	KeyWordDialogController controller;

	@Before
	public void setUp() throws Exception {
		when(viewFactory.getDialogView()).thenReturn(dialog);
		when(dialog.getKeyWordFieldText()).thenReturn("abc");
		controller = new KeyWordDialogController(project, viewFactory, currentRecord, parentView);
	}

	@Test
	public void onnTextFieldChanged_ShouldInvokeGetElemetMethod() {
		controller.onTextFieldChanged();
		verify(dialog, atLeast(1)).getElement();
	}
	
	@Test
	public void onnTextFieldChanged_ShouldInvokeGetKeyWordFieldText() {
		controller.onTextFieldChanged();
		verify(dialog, atLeast(1)).getKeyWordFieldText();
	}

	@Test
	public void onCancelButtonClicked_ShouldInvokePositiveAnswerToQuestion() {
		controller.onCancelButtonClicked();
		verify(dialog, times(1)).positiveAnswerToQuestion("Are you sure?");
	}

}
