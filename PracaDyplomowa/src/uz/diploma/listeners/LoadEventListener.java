package uz.diploma.listeners;

import java.util.EventListener;

import uz.diploma.model.project.LoadEvent;

/**
 * @author Spider
 * 
 * Interface need to be implemented by classes that need to be notified
 * when project was loaded
 *
 */
public interface LoadEventListener extends EventListener {

	public void loadEventReceived(LoadEvent event);
}
