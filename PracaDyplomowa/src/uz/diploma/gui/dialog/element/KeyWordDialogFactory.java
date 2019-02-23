package uz.diploma.gui.dialog.element;

import uz.diploma.gui.dialog.DialogView;
import uz.diploma.gui.dialog.DialogViewFactory;

/**
 * @author Spider
 * 
 * Class provides method that returns view for adding keyword
 *
 */
public class KeyWordDialogFactory extends DialogViewFactory {

	@Override
	public DialogView getDialogView() {
		return new AddingKeyWordDialog();
	}

}
