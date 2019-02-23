package uz.diploma.viewer.model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;

import uz.diploma.tools.Validator;

/**
 * @author Spider
 * 
 * Class is container for image data including size, height, width,
 * position relatively to canvas and current zoom factor
 *
 */
public class ImageData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6669963787615648634L;
	private int imageTopLeftX;
	private int imageTopLeftY;
	private int imageBottomRightX;
	private int imageBottomRightY;
	
	private Point imageTopLeft;
	private Point imageBottomRight;
	
	private int imageWidth;
	private int imageHeight;
	
	private Dimension imageSize;
	private Rectangle imageArea;
	
	private double scaleFactor;
	
	/**
	 * @param imageArea - rectangle that contains image 
	 * @param scaleFactor - current zoom factor
	 */
	public ImageData(Rectangle imageArea, double scaleFactor) {
		Validator.ensureIsNotNull(imageArea, "imageArea");
		Validator.ensureIsNotNull(scaleFactor, "scaleFactor");
		this.imageArea = imageArea;
		this.imageWidth = (int)imageArea.getWidth();
		this.imageHeight = (int)imageArea.getHeight();
		this.imageTopLeftX = (int)imageArea.getX();
		this.imageTopLeftY = (int)imageArea.getY();
		this.imageBottomRightX = (int)(imageArea.getWidth() + imageArea.getX());
		this.imageBottomRightY = (int)(imageArea.getHeight() + imageArea.getY());
		this.imageTopLeft = imageArea.getLocation();
		this.imageBottomRight = new Point(imageBottomRightX, imageBottomRightY);
		this.imageSize = imageArea.getSize();
		this.scaleFactor = scaleFactor;
	}

	public int getImageTopLeftX() {
		return imageTopLeftX;
	}

	public void setImageTopLeftX(int imageTopLeftX) {
		this.imageTopLeftX = imageTopLeftX;
	}

	public int getImageTopLeftY() {
		return imageTopLeftY;
	}

	public void setImageTopLeftY(int imageTopLeftY) {
		this.imageTopLeftY = imageTopLeftY;
	}

	public int getImageBottomRightX() {
		return imageBottomRightX;
	}

	public void setImageBottomRightX(int imageBottomRightX) {
		this.imageBottomRightX = imageBottomRightX;
	}

	public int getImageBottomRightY() {
		return imageBottomRightY;
	}

	public void setImageBottomRightY(int imageBottomRightY) {
		this.imageBottomRightY = imageBottomRightY;
	}

	public Point getImageTopLeft() {
		return imageTopLeft;
	}

	public void setImageTopLeft(Point imageTopLeft) {
		this.imageTopLeft = imageTopLeft;
	}

	public Point getImageBottomRight() {
		return imageBottomRight;
	}

	public void setImageBottomRight(Point imageBottomRight) {
		this.imageBottomRight = imageBottomRight;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	public Dimension getImageSize() {
		return imageSize;
	}

	public void setImageSize(Dimension imageSize) {
		this.imageSize = imageSize;
	}

	public Rectangle getImageArea() {
		return imageArea;
	}

	public void setImageArea(Rectangle imageArea) {
		this.imageArea = imageArea;
	}

	public double getScaleFactor() {
		return scaleFactor;
	}

	public void setScaleFactor(double scaleFactor) {
		this.scaleFactor = scaleFactor;
	} 
	
}
