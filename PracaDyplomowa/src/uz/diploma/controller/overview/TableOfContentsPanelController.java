package uz.diploma.controller.overview;

import uz.diploma.gui.window.panel.details.overviewtab.SummaryPanelInterface;
import uz.diploma.gui.window.panel.details.overviewtab.TableOfContentsInterface;
import uz.diploma.gui.window.panel.record.ResultListListener;
import uz.diploma.listeners.TextFieldChangedListener;
import uz.diploma.model.record.element.Record;
import uz.diploma.tools.Validator;

/**
 * @author Spider
 * 
 * Class is responsible for controlling data flow between model and table of contents view
 *
 */
public class TableOfContentsPanelController implements TextFieldChangedListener, ResultListListener {

	private Record record = null;
	private TableOfContentsInterface tableOfContents;

	public TableOfContentsPanelController(TableOfContentsInterface tableOfContentsPanel) {
		Validator.ensureIsNotNull(tableOfContentsPanel, "tableOfContents");
		this.tableOfContents = tableOfContentsPanel;
		tableOfContentsPanel.addTextChangedListener(this);
	}
	
	@Override
	public void onResultListItemSelected(Record selectedRecord) {
		Validator.ensureIsNotNull(selectedRecord, "selectedRecord");
		record = selectedRecord;
		tableOfContents.setFieldValue(record.getTableOfContents());
	}

	@Override
	public void onTextFieldChanged() {
		if(record != null)
		record.setTableOfContents(tableOfContents.getFieldValue());
	}

}
