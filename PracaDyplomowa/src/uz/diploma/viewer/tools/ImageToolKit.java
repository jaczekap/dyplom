package uz.diploma.viewer.tools;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.nio.file.Path;
import java.util.Arrays;

import javax.imageio.ImageIO;
import uz.diploma.viewer.model.ImageData;

/**
 * @author Spider
 * 
 * Class provides set of tools for drawing image, checking if file is image supported type
 * and computing relative position
 *
 */
public class ImageToolKit {
	
	/**
	 * computes scale factor of the image relatively to canvas that image is drawn on
	 * compares vertical and horizontal scales of image and component and return whichever scale is lower 
	 * @param image - image for which scale factor is computed
	 * @param comp - component that is canvas for drawing
	 * @return - scale factor
	 */
	public static double getScaleFactor(Image image, Component comp) {
		int imageWidth = image.getWidth(null);
		int imageHeight = image.getHeight(null);
		
		int compWidth = comp.getWidth();
		int compHeight = comp.getHeight();
		
		double xScale = (double)compWidth / imageWidth;
		double yScale = (double)compHeight / imageHeight;
		
		return Math.min(xScale, yScale);
	}
	
	/**
	 * Draw image scaling it to fit in size of the canvas that this image is drawn on.
	 * Drawn image keeps original image aspect ratio
	 * @param img - image to be drawn
	 * @param comp - component on which canvas image is drawn
	 * @param g - graphics object on which drawing is performed
	 * @return object holding scaled image data 
	 */
	public static ImageData drawFittedImage(Image img, Component comp, Graphics g) {
		int imageWidth = img.getWidth(null);
		int imageHeight = img.getHeight(null);
		int compWidth = comp.getWidth();
		int compHeight = comp.getHeight();
		
		int topLeftX = 0;
		int topLeftY = 0;
		int bottomRightX = 0;
		int bottomRightY = 0;
		
		double scaleFactor = getScaleFactor(img, comp);
		
		int scaledImageWidth = (int)(imageWidth * scaleFactor);
		int scaledImageHeight = (int)(imageHeight * scaleFactor);
		
		 topLeftX = (compWidth - scaledImageWidth)  / 2;
		 topLeftY = (compHeight - scaledImageHeight) / 2;
		 bottomRightX = scaledImageWidth + topLeftX;
		 bottomRightY = scaledImageHeight + topLeftY;
		 g.drawImage(img, topLeftX, topLeftY, bottomRightX, bottomRightY, 0, 0, imageWidth, imageHeight, null);
		
		return new ImageData(new Rectangle(topLeftX, topLeftY, scaledImageWidth, scaledImageHeight), scaleFactor);
	}
	
	/**
	 * Draw scaled image
	 * @param img - image to be scaled
	 * @param comp - component on which canvas image is drawn
	 * @param zoomFactor - scale factor
	 * @param g - graphics object on which drawing is performed
	 * @return object holding scaled image data
	 */
	public static ImageData drawScaledImage(Image img, Component comp, double zoomFactor, Graphics g) {
		int imageWidth = img.getWidth(null);
		int imageHeight = img.getHeight(null);
		
		if(zoomFactor < 0.01)
			zoomFactor = 0.01;
		
		int scaledImageWidth = (int)(imageWidth * zoomFactor);
		int scaledImageHeight = (int)(imageHeight * zoomFactor);
		
		int topLeftX = 0;
		int topLeftY = 0;
		
		if(comp.getWidth() >= scaledImageWidth)
			topLeftX = (comp.getWidth() - scaledImageWidth) / 2;
		if(comp.getHeight() >= scaledImageHeight)
			topLeftY = (comp.getHeight() - scaledImageHeight) / 2;
		
		comp.setPreferredSize(new Dimension(scaledImageWidth, scaledImageHeight));
		comp.invalidate();
		
		g.drawImage(img, topLeftX, topLeftY, scaledImageWidth, scaledImageHeight, null);
		
		return new ImageData(new Rectangle(topLeftX, topLeftY, scaledImageWidth, scaledImageHeight), zoomFactor);
	}
	
	/**
	 * translate one coordinate of the point in the image to coordinate on canvas on which this image is drawn
	 * @param coordinatePosition - coordinate position on the image
	 * @param length - length of the side of image (top/bottom for x coordinate, left/right for y coordinate)
	 * @param offset - coordinate (x for x coordinate translation, y for y coordinate translation) position of 
	 * the top left corner of the image relatively to canvas 
	 * @return translated coordinate
	 */
	public static double computeRelativeCoordinate(double coordinatePosition, double length, double offset) {
		return (coordinatePosition - offset) / length;
	}
	
	/**
	 * Tests if file located in given path is supported image file format
	 * @param path - path where file to test is stored
	 * @return true if file format is supported
	 */
	public static boolean isSupportedImageType(Path path) {
		String[] splittedByDot = path.getFileName().toString().split("\\.");
		String fileExtension = splittedByDot[splittedByDot.length - 1];
		
		if(Arrays.asList(ImageIO.getReaderFormatNames()).contains(fileExtension)) {
			return true;
		}
		return false;
	}
}
