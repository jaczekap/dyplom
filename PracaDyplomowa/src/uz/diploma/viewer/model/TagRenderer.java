package uz.diploma.viewer.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import uz.diploma.tools.Validator;
import uz.diploma.viewer.tools.ImageToolKit;

/**
 * @author Spider
 * 
 * Class is responsible for rendering tags presented on the image
 * It provides methods for positioning, auto-changing color and size of the tag
 * and checking if specified point is in image area
 *
 */
public class TagRenderer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3040389524299219236L;
	private final static int MAX_TAG_WIDTH = 20;
	private final static int MAX_TAG_HEIGHT = 20;
	private final static int MIN_TAG_WIDTH = 10;
	private final static int MIN_TAG_HEIGHT = 10;
	
	/**
	 * Render tags on the image, settings is color, shape and size
	 * @param imageData - object holding all data about image that tags are rendered on
	 * @param tags - list of tags that are to be rendered
	 * @param selectedTag - currently selected tag
	 * @param g - graphics object on which drawing is performed
	 */
	public void renderTag(ImageData imageData, List<ImageTag> tags, ImageTag selectedTag, Graphics2D g) {
		Validator.ensureIsNotNull(imageData, "imageData");
		Validator.ensureIsNotNull(tags, "tags");
		Validator.ensureIsNotNull(g, "g");
		
		List<Ellipse2D> tagRepresentation = new ArrayList<Ellipse2D>();
		Ellipse2D selectedTagDisplay = null;
		
		for(ImageTag tag : tags) {
			Point tagPosition = tag.computeAbsoluteTagPosition(imageData.getImageWidth(), imageData.getImageHeight());
			Point translatedTagPoint = translateTagPosition(tagPosition, imageData);
			double translatedX = translatedTagPoint.getX();
			double translatedY = translatedTagPoint.getY();
			if(tag.equals(selectedTag)) {
				selectedTagDisplay = new Ellipse2D.Double(translatedX, translatedY, translateTagHeight(imageData.getImageHeight()), translateTagWidth(imageData.getImageHeight()));
			}
			tagRepresentation.add(new Ellipse2D.Double(translatedX, translatedY, translateTagHeight(imageData.getImageHeight()), translateTagWidth(imageData.getImageHeight())));
		}

		g.setColor(Color.BLUE);
		tagRepresentation.forEach(tag -> g.fill(tag));
		if(selectedTag != null) {
			g.setColor(Color.YELLOW);
			g.fill(selectedTagDisplay);
		}
	}
	
	private double translateTagHeight(int scaleFactor) {
		return Math.max(MIN_TAG_WIDTH, Math.min(MAX_TAG_WIDTH, scaleFactor * 0.01f));
	}
	
	private double translateTagWidth(int scaleFactor) {
		return Math.max(MIN_TAG_HEIGHT, Math.min(MAX_TAG_HEIGHT, scaleFactor * 0.01f));
	}
	
	/**
	 * Tests if given coordinates are in the image bounds if the image does not occupy whole visible window 
	 * @param x - x coordinate to be tested
	 * @param y - y coordinate to be tested
	 * @param imageRenderedArea - object holding all data about image that tags are rendered on 
	 * @return true if both given coordinates are in image bounds
	 */
	public boolean isInImageArea(int x, int y, ImageData imageRenderedArea) {
		boolean isXInArea = ((x >= imageRenderedArea.getImageTopLeftX()) 
				&& (x <= imageRenderedArea.getImageBottomRightX()));
		boolean isYInArea = ((y >= imageRenderedArea.getImageTopLeftY()) 
				&& (y <= imageRenderedArea.getImageBottomRightY()));
		return isXInArea && isYInArea;
	}
	
	/**
	 * Tests if in given coordinates is tag and if yes return it
	 * @param tags - list of tags on the image
	 * @param x - x coordinate to be tested
	 * @param y - y coordinate to be tested
	 * @param imageData - object holding all data about image that tags are rendered on
	 * @return image tag that is on the given coordinates, null if in this position is no tag
	 */
	public ImageTag getSelectedTag(List<ImageTag> tags,  double x, double y, ImageData imageData) {
		for(ImageTag tag : tags) {
			Point tagPosition = tag.computeAbsoluteTagPosition(imageData.getImageWidth(), imageData.getImageHeight());
			if(isTagSelected(x, y, tagPosition, imageData))
				return tag;
		}
		return null;
	}
	
	private Point translateTagPosition(Point absoluteTagPosition, ImageData imageRenderedArea) {
		double translatedX = absoluteTagPosition.getX() + imageRenderedArea.getImageTopLeftX();
		double translatedY = absoluteTagPosition.getY() + imageRenderedArea.getImageTopLeftY();
		double centeredX = translatedX - (translateTagWidth(imageRenderedArea.getImageHeight()) / 2);
		double centeredY = translatedY - (translateTagHeight(imageRenderedArea.getImageHeight()) / 2);
		return new Point((int)centeredX, (int)centeredY);
	}
	
	private boolean isTagSelected(double x, double y, Point tagPosition, ImageData imageData) {
		Point translatedTagPoint = translateTagPosition(tagPosition, imageData);
		double translatedX = translatedTagPoint.getX();
		double translatedY = translatedTagPoint.getY();
		double xTolerance = translateTagWidth(imageData.getImageHeight());
		double yTolerance = translateTagHeight(imageData.getImageHeight());
		boolean containsX = x >= translatedX && x <= (translatedX + xTolerance);
		boolean containsY = y >= translatedY && y <= (translatedY + yTolerance);
		return containsX && containsY;
	}
}
