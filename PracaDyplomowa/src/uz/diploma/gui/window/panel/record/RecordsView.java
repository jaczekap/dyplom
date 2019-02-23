package uz.diploma.gui.window.panel.record;

import java.util.List;
import uz.diploma.listeners.SearchTextListener;
import uz.diploma.model.record.element.Record;

/**
 * @author Spider
 * 
 * Interface that need to be implemented for view classes that present left panel of the application window
 *
 */
public interface RecordsView {
	
	public void addResultListSelectedListener(ResultListListener listener);
	public void removeResultListSelectedListener(ResultListListener listener);
	
	public void addSearchTextListener(SearchTextListener listener);
	public void removeSearchTextListener(SearchTextListener listener);
	
	public void setResultList(List<Record> resultList);
	public void setResultListSelection();
	
	public void resultListItemSelected(Record r);
	
	public void searchTextChanged(String textFromField);

}
