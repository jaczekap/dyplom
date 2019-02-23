package uz.diploma.listeners;

import java.util.EventListener;

/**
 * @author Spider
 * 
 * Interface need to be implemented by classes that need to be notified
 * about events occurred on panel
 *
 */
public interface PanelViewListener extends EventListener {

	public void onAddButtonClicked();
	public void onRemoveButtonClicked();
	public void onFocusGained();
}
