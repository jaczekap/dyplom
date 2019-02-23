package uz.diploma.gui.dialog.source;

import uz.diploma.gui.dialog.WindowDialogView;
import uz.diploma.model.record.source.SourceType;

/**
 * @author Spider
 * 
 * Interface that need to be implemented for view classes that present add source location window 
 *
 */
public interface SourceDialogView extends WindowDialogView {
	
	public void removeButtonClicked();
	public void setSourceType(SourceType currentType);
	public void selectSourceButtonClicked();

}
