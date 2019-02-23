package uz.diploma.gui.window.panel.details.overviewtab;

import uz.diploma.listeners.TextFieldChangedListener;

/**
 * @author Spider
 * 
 * Interface that need to be implemented by summary panel view classes
 *
 */
public interface SummaryPanelInterface {

	public void setFieldValue(String fieldValue);
	public String getFieldValue();
	public void addTextChangedListener(TextFieldChangedListener listener);
	public void removeTextChangedListener(TextFieldChangedListener listener);
	public void textFieldChanged();
}
