package uz.diploma.gui.window;

import uz.diploma.listeners.DataStoreEventsListener;

/**
 * @author Spider
 * 
 * Interface that need to be implemented by concrete class of application window
 *
 */
public interface WindowView {

	public void addListener(DataStoreEventsListener listener);
	public void removeListener(DataStoreEventsListener listener);
	public void addMenuListener(WindowMenuListener listener);
	public void removeMenuListener(WindowMenuListener listener);
	public void open();
	public void saveAs();
	public void save();
	public void setSaveMenuItemEnabled(boolean isEnabled);
	public void removeRecordSelected();
	public void addRecordSelected();
	public void showInformationDialog(String message);
}
