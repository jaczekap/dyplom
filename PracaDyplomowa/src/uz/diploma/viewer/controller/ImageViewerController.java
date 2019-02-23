package uz.diploma.viewer.controller;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import uz.diploma.model.record.source.FileSource;
import uz.diploma.model.record.source.SourceLocation;
import uz.diploma.tools.Validator;
import uz.diploma.viewer.model.ImageData;
import uz.diploma.viewer.model.ImageTag;
import uz.diploma.viewer.model.TagRenderer;
import uz.diploma.viewer.tools.ImageToolKit;
import uz.diploma.viewer.view.AddTagDialog;
import uz.diploma.viewer.view.ImageContainerListener;
import uz.diploma.viewer.view.ImageContainer;
import uz.diploma.viewer.view.ImageView;
import uz.diploma.viewer.view.ImageViewer;

/**
 * @author Spider
 * 
 * Class is responsible for controlling image viewer window, especially for
 * controlling displaying, zooming and rendering tags
 *
 */
public class ImageViewerController implements ImageViewerViewController, ImageContainerListener {

	private ImageView imageViewer;
	private Image imageToDisplay;
	private FileSource imageSource;
	private ImageData imageRenderedArea;
	private Boolean fitInWindow;
	private double zoomFactor;
	private TagRenderer renderer;
	private ImageContainer container;
	private List<ImageTag> tags;
	private Graphics2D graphic;
	private ImageTag selectedTag;
	
	/**
	 * @param imageViewer - image viewer window
	 * @param imageSource - path to the image that will be displayed in image viewer 
	 */
	public ImageViewerController(ImageView imageViewer, FileSource imageSource) {
		Validator.ensureIsNotNull(imageViewer, "imageViewer");
		Validator.ensureIsNotNull(imageSource, "imageSource");
		renderer = new TagRenderer();
		zoomFactor = 1.0f;
		fitInWindow = true;
		this.imageSource = imageSource;
		tags = imageSource.getTags();
		this.imageToDisplay = new ImageIcon(imageSource.getSource()).getImage();
		this.imageViewer = imageViewer;
		imageViewer.addDialogViewListener(this);
		container.addListener(this);
		imageViewer.setTagList(tags);
	}


	@Override
	public void onZoomFactorChanged(int zoomFactor) {
		setFitInWindow(false);
		setZoomFactor(zoomFactor / 100.0f);
	}


	@Override
	public void onGraphicsChanged(Graphics2D g) {
		graphic = g;
		if(fitInWindow == false)
			imageRenderedArea = ImageToolKit.drawScaledImage(imageToDisplay, container, zoomFactor, g);
		else {
			imageRenderedArea = ImageToolKit.drawFittedImage(imageToDisplay, container, g);
		}
		if(!imageViewer.isHideButtonOn())
			renderer.renderTag(imageRenderedArea, tags, selectedTag, g);
	}
	
	private void setFitInWindow(boolean fitInWindow) {
		this.fitInWindow = fitInWindow;
	}
	
	private void setZoomFactor(double zoomFactor) {
		this.zoomFactor = zoomFactor;
		container.repaint();
	}
	
	@Override
	public void setImageContainer(ImageContainer container) {
		this.container = container;
	}


	@Override
	public void onMouseClicked(MouseEvent event) {
		selectedTag = renderer.getSelectedTag(tags, event.getX(), event.getY(), imageRenderedArea);
		
		if(imageViewer.isHideButtonOn())
			return;
		
		if (!imageViewer.isEditModeEnabled())
			if(selectedTag != null) {
				((ImageViewer)imageViewer).setSelectedTagOnList(selectedTag);
				imageViewer.showTagDescription(event, selectedTag.getDescription());
				return;
			}
		
		if(renderer.isInImageArea(event.getX(), event.getY(), imageRenderedArea) && imageViewer.isEditModeEnabled()) {
			if(renderer.getSelectedTag(tags, event.getX(), event.getY(), imageRenderedArea) == null) {
				double relativeX = ImageToolKit.computeRelativeCoordinate(event.getX(), imageRenderedArea.getImageWidth(), imageRenderedArea.getImageTopLeftX());
				double relativeY = ImageToolKit.computeRelativeCoordinate(event.getY(), imageRenderedArea.getImageHeight(), imageRenderedArea.getImageTopLeftY());
				ImageTag currentTag = new ImageTag(relativeX, relativeY);
				new AddTagController(new AddTagDialog(), currentTag);
				if(!currentTag.getName().equals(""))
					tags.add(currentTag);
			} else {
				new AddTagController(new AddTagDialog(), selectedTag);
			}
		}
		imageViewer.setTagList(tags);
		((ImageViewer)imageViewer).setSelectedTagOnList(selectedTag);
		container.repaint();
	}


	@Override
	public void onfitInWindowButtonClicked() {
		fitInWindow = true;
		container.setBounds(0, 0, 1, 1);
		container.setPreferredSize(new Dimension(1, 1));
		imageRenderedArea = ImageToolKit.drawFittedImage(imageToDisplay, container, graphic);
	}


	@Override
	public void onHideButtonStateChanged() {
		if(!imageViewer.isHideButtonOn())
			renderer.renderTag(imageRenderedArea, tags, selectedTag, graphic);
		container.repaint();
	}


	@Override
	public void onlistItemSelected() {
		selectedTag = imageViewer.getSelectedTag();
		renderer.renderTag(imageRenderedArea, tags, selectedTag, graphic);
		container.repaint();
	}
	
}
