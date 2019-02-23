package uz.diploma.gui.dialog.source;

import uz.diploma.gui.dialog.DialogView;
import uz.diploma.gui.dialog.DialogViewFactory;

/**
 * @author Spider
 * 
 * Class provides method that returns view for adding source location
 *
 */
public class SourceDialogFactory extends DialogViewFactory {

	@Override
	public DialogView getDialogView() {
		return new SourceDialog();
	}

}
