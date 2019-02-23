package uz.diploma.listeners;

import java.util.EventListener;

/**
 * @author Spider
 * 
 * Interface need to be implemented by classes that need to be notified
 * about data storing operations events
 *
 */
public interface DataStoreEventsListener extends EventListener {

	public void onOpen(String filePath);
	public void onSaveAs(String filePath);
	public void onSave();
}
