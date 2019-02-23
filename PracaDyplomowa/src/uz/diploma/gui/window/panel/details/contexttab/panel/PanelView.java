package uz.diploma.gui.window.panel.details.contexttab.panel;

import java.util.List;

import uz.diploma.controller.context.PanelViewController;
import uz.diploma.model.record.element.Record;
import uz.diploma.model.record.element.RecordElement;

/**
 * @author Spider
 * 
 * Interface that need to be implemented for view classes that panel elements in context tab
 *
 */
public interface PanelView {
	
	public void addPanelViewListener(PanelViewController listener);
	public void removePanelViewListener(PanelViewController listener);

	public void onNewRecord(Record selectedRecord);
	public void addButtonClicked();
	public void focusGrabed();
	public void removeButtonClicked();
	public RecordElement getSelection();
	public int showRemoveConfirmationDialog(String message);
	public void showItemNotSelectedDialog();
	public boolean canRemoveElement(String message);
	public void updateView(List<RecordElement> characters);

}
