package uz.diploma.viewer.model;

import java.awt.Point;
import java.io.Serializable;

import uz.diploma.model.record.element.RecordElement;

/**
 * @author Spider
 * 
 * Class represents graphical mark applied on the image 
 *
 */
public class ImageTag implements Serializable, RecordElement {

	
	private static final long serialVersionUID = -8898561705434452773L;
	private double relativePositionX;
	private double relativePositionY;
	private String name;
	private String description;
	
	/**
	 * @param relativePositionX - x coordinate of the mark position on the image (not on the canvas) presented in percent
	 * @param relativePositionY - y coordinate of the mark position on the image (not on the canvas) presented in percent
	 */
	public ImageTag(double relativePositionX, double relativePositionY) {
		this.name = "";
		this.description = "";
		this.relativePositionX = relativePositionX;
		this.relativePositionY = relativePositionY;
	}
	
	/**
	 * Translates tag coordinates to absolute values relatively to canvas
	 * @param width - width of the image
	 * @param height - height of the image
	 * @return translated coordinates as Point object
	 */
	public Point computeAbsoluteTagPosition(int width, int height) {
		int x = (int)(relativePositionX * width);
		int y = (int)(relativePositionY * height);
		return new Point(x, y);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getRelativePositionX() {
		return relativePositionX;
	}

	public double getRelativePositionY() {
		return relativePositionY;
	}

	@Override
	public String toString() {
		return name;
	}
}
