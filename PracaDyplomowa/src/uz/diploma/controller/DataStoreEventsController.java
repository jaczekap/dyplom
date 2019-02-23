package uz.diploma.controller;

import java.io.IOException;

import javax.swing.JFileChooser;

import uz.diploma.gui.window.WindowView;
import uz.diploma.listeners.DataStoreEventsListener;
import uz.diploma.model.project.DataStoreEventsManagerInterface;
import uz.diploma.tools.Validator;

/**
 * @author Spider
 * 
 * Class controls all events associated with data storing such as loading or saving project
 *
 */
public class DataStoreEventsController implements DataStoreEventsListener {
	
	private WindowView appWindow;
	private DataStoreEventsManagerInterface storeEventsManager;
	private String currentProjectPath;
	
	/**
	 * @param appWindow window view that contains menu items for loading and saving project
	 * @param storeEventsManager - object that manage loading and saving project process 
	 */
	public DataStoreEventsController(WindowView appWindow, DataStoreEventsManagerInterface storeEventsManager) {
		Validator.ensureIsNotNull(appWindow, "appWindow");
		this.appWindow = appWindow;
		Validator.ensureIsNotNull(storeEventsManager, "storeEventManager");
		this.storeEventsManager = storeEventsManager;
		this.appWindow.addListener(this);
	}

	@Override
	public void onOpen(String filePath) {
		Validator.ensureIsNotNull(filePath, "filePath");
		try {
			storeEventsManager.onOpen(filePath);
		} catch (ClassNotFoundException | IOException e) {
			appWindow.showInformationDialog("Unable to load project");
		}
		currentProjectPath = filePath;
		appWindow.setSaveMenuItemEnabled(iscurrentProjectPathSet());
	}

	@Override
	public void onSaveAs(String filePath) {
		Validator.ensureIsNotNull(filePath, "filePath");
		try {
			storeEventsManager.onSave(filePath);
		} catch (IOException e) {
			appWindow.showInformationDialog("Unable to save project");
		}
		currentProjectPath = filePath;
		appWindow.setSaveMenuItemEnabled(iscurrentProjectPathSet());
	}
	
	private boolean iscurrentProjectPathSet() {
		if(currentProjectPath != null) {
			return true;
		}
		return false;
	}

	@Override
	public void onSave() {
		try {
			storeEventsManager.onSave(currentProjectPath);
		} catch (IOException e) {
			appWindow.showInformationDialog("Unable to save project");
		}		
	}

}
