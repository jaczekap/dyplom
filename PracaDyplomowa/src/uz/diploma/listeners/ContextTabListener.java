package uz.diploma.listeners;

import java.util.EventListener;

import uz.diploma.model.record.element.HistoricalPeriod;

/**
 * @author Spider
 * 
 * Interface need to be implemented by classes that need to be notified
 * about events occurred on context tab
 *
 */
public interface ContextTabListener extends EventListener {

	void onPeriodChanged(HistoricalPeriod period);

}
