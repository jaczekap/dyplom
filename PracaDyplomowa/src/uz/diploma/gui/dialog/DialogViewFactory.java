package uz.diploma.gui.dialog;

/**
 * @author Spider
 * 
 * Class that need to be extended by factory class that returns concrete dialog window
 *
 */
public abstract class DialogViewFactory {
	
	private DialogView dialog;
	
	public abstract DialogView getDialogView();

}
