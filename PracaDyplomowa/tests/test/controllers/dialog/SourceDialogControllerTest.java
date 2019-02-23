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

import uz.diploma.controller.dialog.source.SourceDialogController;
import uz.diploma.gui.dialog.source.SourceDialog;
import uz.diploma.gui.dialog.source.SourceDialogFactory;
import uz.diploma.model.project.Project;
import uz.diploma.model.record.element.Record;
import uz.diploma.model.record.source.InternetSource;

@RunWith(MockitoJUnitRunner.class)
public class SourceDialogControllerTest {
	
	@Mock
	InternetSource source;
	@Mock
	SourceDialogFactory viewFactory;
	@Mock
	SourceDialog dialog;
	@Mock
	Component parentView;
	@Mock
	Project project;
	@Spy
	Record currentRecord = new Record("title");
	SourceDialogController controller;

	@Before
	public void setUp() throws Exception {
		when(viewFactory.getDialogView()).thenReturn(dialog);
		when(dialog.getElement()).thenReturn(source);
		when(dialog.getSourceFieldText()).thenReturn("abc");
		when(dialog.getSelectedRadioButtonActionCommand()).thenReturn(SourceDialog.EXTERNAL_BUTTON_ACTION);
		controller = new SourceDialogController(project, viewFactory, currentRecord, parentView);
	}

	@Test
	public void onRemoveButtonClicked_ShouldInvokeGetElementMethod() {
		controller.onRemoveButtonClicked();
		verify(dialog, atLeast(1)).getElement();
	}
	
	@Test
	public void onRemoveButtonClicked_ShouldInvokePositiveAnswerToQuestionMethod() {
		controller.onRemoveButtonClicked();
		verify(dialog, times(1)).positiveAnswerToQuestion("Remove this source location?");
	}
	
	@Test
	public void onRemoveButtonClicked_ShouldInvokeShowInformationDialogMethod() {
		when(dialog.getElement()).thenReturn(null);
		controller.onRemoveButtonClicked();
		verify(dialog, times(1)).showInformationDialog("Select item to remove first");
	}

	@Test
	public void onTextFieldChanged_ShouldInvokeGetElementMethod() {
		controller.onTextFieldChanged();
		verify(dialog, atLeast(1)).getElement();
	}
	
	@Test
	public void onTextFieldChanged_ShouldInvokeGetSourceFieldTextMethod() {
		controller.onTextFieldChanged();
		verify(dialog, atLeast(1)).getSourceFieldText();
	}
	
	@Test
	public void onTextFieldChanged_ShouldInvokeGetSelectedRadioButtonActionComandMethod() {
		controller.onTextFieldChanged();
		verify(dialog, atLeast(1)).getSelectedRadioButtonActionCommand();
	}

	@Test
	public void onCancelButtonClicked_ShouldInvokePositiveAnswerToQuestionMethod() {
		controller.onCancelButtonClicked();
		verify(dialog, times(1)).positiveAnswerToQuestion("Are you sure?");
	}

	@Test
	public void onAddButtonClicked_ShouldInvokeGetElementMethod() {
		controller.onAddButtonClicked();
		verify(dialog, atLeast(1)).getElement();
	}
	
	@Test
	public void onAddButtonClicked_ShouldInvokeGetSourceFieldTextMethod() {
		controller.onAddButtonClicked();
		verify(dialog, atLeast(1)).getSourceFieldText();
	}
	
	@Test
	public void onAddButtonClicked__ShouldInvokeGetSelectedRadioButtonActionComandMethod() {
		controller.onAddButtonClicked();
		verify(dialog, atLeast(1)).getSelectedRadioButtonActionCommand();
	}

}
