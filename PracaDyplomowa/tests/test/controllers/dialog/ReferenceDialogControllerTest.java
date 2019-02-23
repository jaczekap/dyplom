package test.controllers.dialog;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.awt.Component;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import uz.diploma.controller.dialog.quotation.ReferenceDialogController;
import uz.diploma.gui.dialog.quotation.AddReferenceDialog;
import uz.diploma.model.project.Project;
import uz.diploma.model.record.element.Record;

@RunWith(MockitoJUnitRunner.class)
public class ReferenceDialogControllerTest {

	@Mock
	AddReferenceDialog dialog;
	@Mock
	Record record;
	@Mock
	Component parentView;
	@Spy
	Project project;
	@InjectMocks
	ReferenceDialogController controller;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void onCancelButtonClicked_ShouldInvokePositiveAnswerToQuestionMethod() {
		controller.onCancelButtonClicked();
		verify(dialog, times(1)).positiveAnswerToQuestion("Are you sure?");
	}

	@Test
	public void onnAddButtonClicked_ShouldInvokePositiveAnswerToQuestionMethod() {
		controller.onAddButtonClicked();
		verify(dialog, times(1)).positiveAnswerToQuestion("Add this reference?");
	}

}
