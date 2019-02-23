package uz.diploma.controller.context;

import java.util.ArrayList;

import uz.diploma.gui.window.panel.details.contexttab.ContextTabView;
import uz.diploma.gui.window.panel.record.ResultListListener;
import uz.diploma.listeners.ContextTabListener;
import uz.diploma.model.record.element.HistoricalEvent;
import uz.diploma.model.record.element.HistoricalPeriod;
import uz.diploma.model.record.element.Record;
import uz.diploma.tools.Validator;

/**
 * @author Spider
 * 
 * Class is responsible for controlling data flow between model and context tab
 *
 */
public class ContextTabController implements ResultListListener, ContextTabListener {

	private ContextTabView contextTab;
	private Record currentRecord;
	
	
	public ContextTabController(ContextTabView contextTab) {
		Validator.ensureIsNotNull(contextTab, "contextTab");
		this.contextTab = contextTab;
		contextTab.addContextTabListener(this);
		contextTab.fillHistoricalPeriodList();
	}

	@Override
	public void onResultListItemSelected(Record selectedRecord) {
		Validator.ensureIsNotNull(selectedRecord, "selectedRecord");
		currentRecord = selectedRecord;
		contextTab.setCurrentPeriod(selectedRecord.getHistoricalPeriod());
	}

	@Override
	public void onPeriodChanged(HistoricalPeriod period) {
		if(currentRecord != null)
			currentRecord.setHistoricalPeriod(period);
	}

}
