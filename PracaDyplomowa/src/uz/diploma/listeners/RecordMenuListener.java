package uz.diploma.listeners;

import java.util.EventListener;

/**
 * @author Spider
 * 
 * Interface need to be implemented by classes that need to be notified
 * about events occurred on record menu items
 *
 */
public interface RecordMenuListener extends EventListener {
	
	public void recordMenuItemSelected();

}
