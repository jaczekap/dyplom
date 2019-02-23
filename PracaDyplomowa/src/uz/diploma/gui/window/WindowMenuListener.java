package uz.diploma.gui.window;

import java.util.EventListener;

/**
 * @author Spider
 * 
 * Interface need to be implemented by classes that need to be notified
 * about events occurred on main application window
 *
 */
public interface WindowMenuListener extends EventListener {

	public void onRemoveRecordSelected();
	public void onAddRecordSelected();

}
