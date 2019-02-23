package uz.diploma.viewer.view;

/**
 * @author Spider
 * 
 * Interface that need to be implemented by classes that are dialog windows for adding tag on image
 *
 */
public interface AddTagDialogView {

	public abstract void addTagDialogListener(TagDialogListener listener);
	public abstract void removeTagDialogListener(TagDialogListener listener);
	
	public void setAllTextFields(String name, String description);
	public void textFieldChanged();
	public void cancelButtonClicked();
	public void addButtonClicked();
}
