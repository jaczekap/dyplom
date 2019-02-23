package uz.diploma.viewer.view;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.EventListener;

/**
 * @author Spider
 * 
 * Interface that need to be implemented by classes that need to be
 * notified about events occurred on component on which image is drawn 
 *
 */
public interface ImageContainerListener extends EventListener {

	void onGraphicsChanged(Graphics2D g);

	void onMouseClicked(MouseEvent event);

}
