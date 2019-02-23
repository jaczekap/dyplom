package test.viewer;

import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

import uz.diploma.viewer.tools.ImageToolKit;

public class ImageToolKitTest {
	
	BufferedImage image;
	Component component;
	Path supportedPath;
	Path unSupportedPath;

	@Before
	public void setUp() throws Exception {
		image = new BufferedImage(400, 150, BufferedImage.TYPE_INT_RGB);
		component = new JPanel();
		component.setSize(400, 300);
		System.out.println(ImageToolKit.getScaleFactor(image, component));
		supportedPath = Paths.get("c:/ala.jpg");
		unSupportedPath = Paths.get("c:/ala.pdf");
	}

	@Test
	public void getScaleFactor_NotNullImageGiven_ShouldReturnExpectedValue() {
		assertTrue(ImageToolKit.getScaleFactor(image, component) == 1);
	}

	@Test
	public void computeRelativeCoordinate_ShouldReturnExpectedValue() {
		double value = ImageToolKit.computeRelativeCoordinate(100, 200, 50);
		assertTrue(value == 0.25);
	}

	@Test
	public void isSupportedImageType_PathAsStringWithUnsupportedExtensionGiven_ShouldReturnFalse() {
		assertFalse(ImageToolKit.isSupportedImageType(unSupportedPath));
	}
	
	@Test
	public void isSupportedImageType_PathAsStringWithSupportedExtensionGiven_ShouldReturnTrue() {
		assertTrue(ImageToolKit.isSupportedImageType(supportedPath));
	}

}
