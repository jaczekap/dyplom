package test.controllers;

import static org.mockito.Mockito.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import uz.diploma.controller.DataStoreEventsController;
import uz.diploma.gui.window.AppWindow;
import uz.diploma.model.project.DataStoreEventsManager;


@RunWith(MockitoJUnitRunner.class)
public class DataStoreEventsControllerTest {
	
	
	String testString;
	
	@Mock
	AppWindow appWindow;
	@Mock
	DataStoreEventsManager manager;
	@InjectMocks
	DataStoreEventsController controller;
	

	@Before
	public void setUp() throws Exception {
		testString = "testString";
	}

	@Test
	public void onOpen_ValidPathAsStringGiven_ShouldInvokeSetSaveMenuItemEnabledMethod() {
		controller.onOpen(testString);
		
		verify(appWindow, times(1)).setSaveMenuItemEnabled(true);
	}
	
	@Test
	public void onOpen_ValidPathAsStringGiven_ShouldInvokeOnOpenMethod() throws FileNotFoundException, ClassNotFoundException, IOException {
		controller.onOpen(testString);
		
		verify(manager, times(1)).onOpen(testString);
	}

	@Test
	public void onSave_ValidPathAsStringGiven_ShouldInvokeSetSaveMenuItemEnabledMethod() {
		controller.onSaveAs(testString);
		
		verify(appWindow, times(1)).setSaveMenuItemEnabled(true);
	}
	
	@Test
	public void onSave_ValidPathAsStringGiven_ShouldInvokeOSaveMethod() throws FileNotFoundException, IOException {
		controller.onSaveAs(testString);
		
		verify(manager, times(1)).onSave(testString);
	}

}
