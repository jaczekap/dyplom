package uz.diploma.controller.dialog.record;

import java.util.EventListener;

/**
 * @author Spider
 * 
 * Interface need to be implemented by classes that need to be notified
 * about events occurred on adding record dialog window
 *
 */
public interface AddRecordListener extends EventListener {

	public void onCancelButtonClicked();
	public void onAddButtonClicked();

}
