package uz.diploma.viewer.view;

import java.util.EventListener;

/**
 * @author Spider
 * 
 * Interface that need to be implemented by classes needed to be notified
 * about events occurred in ImageTagDialog window 
 *
 */
public interface TagDialogListener extends EventListener {

	public void onTextFieldChanged();
	public void onCancelButtonClicked();
	public void onAddButtonClicked();

}
