package test.viewer;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import uz.diploma.viewer.model.ImageTag;

public class ImageTagTest {
	
	ImageTag imageTag;

	@Before
	public void setUp() throws Exception {
		imageTag = new ImageTag(10.0, 10.0);
	}

	@Test
	public void computeAbsoluteTagPosition_ValidArgumentsGiven_ReturnsPointObjectWithCorrectlyComputedCoordinatesValues() {
		int width = 5;
		int height = 10;
		Point computedValue = imageTag.computeAbsoluteTagPosition(width, height);
		assertTrue(computedValue.equals(new Point(50, 100)));
	}

}
