package uz.diploma.gui.window.panel.details.contexttab;

import uz.diploma.listeners.ContextTabListener;
import uz.diploma.model.record.element.HistoricalPeriod;

/**
 * @author Spider
 * 
 * Interface that need to be implemented by context tab view classes
 *
 */
public interface ContextTabView {

	public void addContextTabListener(ContextTabListener listener);
	public void removeContextTabListener(ContextTabListener listener);
	
	public void addFilterListener(FilterTextFieldListener listener);
	public void removeFilterTabListener(FilterTextFieldListener listener);
	
	public void fillHistoricalPeriodList();
	public void setCurrentPeriod(HistoricalPeriod period);
	public void periodChanged(HistoricalPeriod period);
	public void searchedTextChanged();
}
