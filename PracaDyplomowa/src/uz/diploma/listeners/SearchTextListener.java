package uz.diploma.listeners;

import java.util.EventListener;

/**
 * @author Spider
 * 
 * Interface need to be implemented by classes that need to be notified
 * about changes in search field text
 *
 */
public interface SearchTextListener extends EventListener {

	public void onSearchTextChanged(String textFromField);
	
}
