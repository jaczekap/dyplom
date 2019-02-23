package uz.diploma.viewer.controller;

import java.util.EventListener;

import uz.diploma.viewer.view.ImageContainer;

/**
 * @author Spider
 * 
 * Class that need to be extended by class that is controller for image viewer
 *
 */
public interface ImageViewerViewController extends EventListener {

	public void onZoomFactorChanged(int zoomFactor);
	public void setImageContainer(ImageContainer container);
	public void onfitInWindowButtonClicked();
	public void onHideButtonStateChanged();
	public void onlistItemSelected();

}
