package uz.diploma.listeners;

import java.awt.event.ActionEvent;
import java.util.EventListener;

/**
 * @author Spider
 * 
 * Interface need to be implemented by classes that need to be notified
 * about events occurred on dialog window
 *
 */
public interface DialogViewListener extends EventListener {

	public void onSelectionChanged(ActionEvent event);
	public void onTextFieldChanged();
	public void onCancelButtonClicked();
	public void onAddButtonClicked();
}
