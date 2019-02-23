package uz.diploma.viewer.view;

import java.awt.event.MouseEvent;
import java.util.List;

import uz.diploma.viewer.controller.ImageViewerViewController;
import uz.diploma.viewer.model.ImageTag;

/**
 * @author Spider
 * 
 * Interface that need to be implemented by concrete classes that are main window of image view
 *
 */
public interface ImageView {

	public void addDialogViewListener(ImageViewerViewController listener);
	public void removeDialogViewListener(ImageViewerViewController listener);
	
	public void zoomFactorChanged();
	public void setTagList(List<ImageTag> tags);
	public boolean isEditModeEnabled();
	public void fitInWindowButtonClicked();
	public boolean isHideButtonOn();
	public void hideButtonStateChanged();
	public ImageTag getSelectedTag();
	public void listItemSelected();
	public void showTagDescription(MouseEvent e, String description);
}
