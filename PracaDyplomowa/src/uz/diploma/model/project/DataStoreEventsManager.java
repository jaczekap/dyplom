package uz.diploma.model.project;

import java.awt.Dialog;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.event.EventListenerList;

import uz.diploma.listeners.LoadEventListener;
import uz.diploma.tools.Validator;

/**
 * @author Spider
 * 
 * Class is responsible for performing data store operations and fire
 * event notifying listeners when project has been loaded
 *
 */
public class DataStoreEventsManager implements DataStoreEventsManagerInterface, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1626923180342295768L;
	private EventListenerList listeners;
	private Project currentProject;

	public DataStoreEventsManager(Project project) {
		Validator.ensureIsNotNull(project, "project");
		currentProject = project;
		listeners = new EventListenerList();
	}
	
	@Override
	public void addLoadEventListener(LoadEventListener listener) {
		listeners.add(LoadEventListener.class, listener);
	}
	
	@Override
	public void removeLoadEventListener(LoadEventListener listener) {
		listeners.remove(LoadEventListener.class, listener);
	}
	
	
	private void fireLoadEvent() {
		for(LoadEventListener listener : listeners.getListeners(LoadEventListener.class)) {
			listener.loadEventReceived(new LoadEvent(this, currentProject)); 
		}
	}
	
	private void save(String filePath) throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath));
			out.writeObject(currentProject);
			out.close();
	}
	
	private void load(String filePath) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath));
			currentProject = (Project)in.readObject();
			in.close();
	}

	@Override
	public void onOpen(String filePath) throws FileNotFoundException, ClassNotFoundException, IOException {
		Validator.ensureIsNotNull(filePath, "filePath");
		load(filePath);
		fireLoadEvent();
	}

	@Override
	public void onSave(String filePath) throws FileNotFoundException, IOException {
		Validator.ensureIsNotNull(filePath, "filePath");
		save(filePath);
		
	}

}
