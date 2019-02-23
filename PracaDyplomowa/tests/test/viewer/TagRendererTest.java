package test.viewer;

import static org.junit.Assert.*;

import java.awt.Rectangle;

import org.junit.Before;
import org.junit.Test;

import uz.diploma.viewer.model.ImageData;
import uz.diploma.viewer.model.TagRenderer;

public class TagRendererTest {
	
	TagRenderer tagRenderer;
	ImageData imageData;
	int xForTrue = 20;
	int yForTrue = 60;
	int xForFalse = 110;
	int yForFalse = 140;

	@Before
	public void setUp() throws Exception {
		tagRenderer = new TagRenderer();
		imageData = new ImageData(new Rectangle(0, 0, 100, 100), 1);
	}

	@Test
	public void isInImageArea_CoordinatesWithinImageAreaGiven_ShouldReturnTrue() {
		assertTrue(tagRenderer.isInImageArea(xForTrue, yForTrue, imageData));
	}
	
	@Test
	public void isInImageArea_XCoordinateOutOfImageAreaAndYCoordinateWithinImageAreaGiven_ShouldReturnFalse() {
		assertFalse(tagRenderer.isInImageArea(xForFalse, yForTrue, imageData));
	}
	
	@Test
	public void isInImageArea_YCoordinateOutOfImageAreaAndXCoordinateWithinImageAreaGiven_ShouldReturnFalse() {
		assertFalse(tagRenderer.isInImageArea(xForTrue, yForFalse, imageData));
	}

}
