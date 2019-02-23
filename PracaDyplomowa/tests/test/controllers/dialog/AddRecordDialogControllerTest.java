package test.controllers.dialog;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import uz.diploma.controller.dialog.record.AddRecordDialogController;
import uz.diploma.gui.dialog.record.AddRecordDialog;
import uz.diploma.model.project.Project;

@RunWith(MockitoJUnitRunner.class)
public class AddRecordDialogControllerTest {
	
	@Mock
	AddRecordDialog dialog;
	@Mock
	Project project;
	@InjectMocks
	AddRecordDialogController controller;

	@Before
	public void setUp() throws Exception {
		when(dialog.getTitleFieldText()).thenReturn("abc");
		when(dialog.getAuthorFieldText()).thenReturn("abc");
	}

	@Test
	public void onCancelButtonClicked_ShouldInvokeDisposeMethod() {
		controller.onCancelButtonClicked();
		verify(dialog, times(1)).dispose();
	}

	@Test
	public void onnAddButtonClicked_ShouldInvokeGetTitleFieldTextMethod() {
		controller.onAddButtonClicked();
		verify(dialog, atLeast(1)).getTitleFieldText();
	}
	
	@Test
	public void onnAddButtonClicked_ShouldInvokeGetAuthorFieldTextMethod() {
		controller.onAddButtonClicked();
		verify(dialog, atLeast(1)).getAuthorFieldText();
	}
	
	@Test
	public void onnAddButtonClicked_ShouldInvokeGetPublicationFieldTextMethod() {
		controller.onAddButtonClicked();
		verify(dialog, atLeast(1)).getPublicationFieldText();
	}
	
	@Test
	public void onnAddButtonClicked_ShouldInvokeGetMediumTypeMethod() {
		controller.onAddButtonClicked();
		verify(dialog, atLeast(1)).getMediumType();
	}
	
	@Test
	public void onnAddButtonClicked_ShouldInvokeShowInformationDialogMethod() {
		when(dialog.getTitleFieldText()).thenReturn("");
		controller.onAddButtonClicked();
		verify(dialog, atLeast(1)).showInformationDialog("Title cannot be empty");
	}

}
