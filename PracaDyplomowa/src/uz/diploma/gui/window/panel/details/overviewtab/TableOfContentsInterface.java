package uz.diploma.gui.window.panel.details.overviewtab;

import uz.diploma.listeners.TextFieldChangedListener;

/**
 * @author Spider
 * 
 * Interface that need to be implemented by table of contents view classes
 *
 */
public interface TableOfContentsInterface {
	
	public void setFieldValue(String fieldValue);
	public String getFieldValue();
	public void addTextChangedListener(TextFieldChangedListener listener);
	public void removeTextListener(TextFieldChangedListener listener);
	public void textFieldChanged();

}
