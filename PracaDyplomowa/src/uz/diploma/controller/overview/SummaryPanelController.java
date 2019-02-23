package uz.diploma.controller.overview;

import uz.diploma.gui.window.panel.details.overviewtab.OverviewSummary;
import uz.diploma.gui.window.panel.details.overviewtab.SummaryPanelInterface;
import uz.diploma.gui.window.panel.record.RecordsView;
import uz.diploma.gui.window.panel.record.ResultListListener;
import uz.diploma.listeners.TextFieldChangedListener;
import uz.diploma.model.record.element.Record;
import uz.diploma.tools.Validator;

/**
 * @author Spider
 * 
 * Class is responsible for controlling data flow between model and Summary panel view
 *
 */
public class SummaryPanelController implements TextFieldChangedListener, ResultListListener {
	
private SummaryPanelInterface summary;
private Record currentRecord;

	public SummaryPanelController(SummaryPanelInterface summary) {
		Validator.ensureIsNotNull(summary, "summary");
		this.summary = summary;
		summary.addTextChangedListener(this);
	}


	
	@Override
	public void onResultListItemSelected(Record selectedRecord) {
		Validator.ensureIsNotNull(selectedRecord, "selectedRecord");
		currentRecord = selectedRecord;
		summary.setFieldValue(currentRecord.getDescription());
	}

	@Override
	public void onTextFieldChanged() {
		if(currentRecord != null)
		currentRecord.setDescription(summary.getFieldValue());
	}

}
