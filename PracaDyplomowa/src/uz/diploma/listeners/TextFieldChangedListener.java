package uz.diploma.listeners;

import java.util.EventListener;

/**
 * @author Spider
 * 
 * Interface need to be implemented by classes that need to be notified
 * about changes in text field
 *
 */
public interface TextFieldChangedListener extends EventListener {
	
	public void onTextFieldChanged();
}
