package uz.diploma.gui.window.panel.record;

import java.util.EventListener;

import uz.diploma.model.record.element.Record;

/**
 * @author Spider
 * 
 * Interface need to be implemented by classes that need to be notified
 * about record selection change event
 *
 */
public interface ResultListListener extends EventListener {
	
	public void onResultListItemSelected(Record selectedRecord);

}
