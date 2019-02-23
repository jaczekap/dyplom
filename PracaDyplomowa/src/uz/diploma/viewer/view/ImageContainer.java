package uz.diploma.viewer.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;




import javax.swing.event.EventListenerList;

/**
 * @author Spider
 * 
 * Component on which image is drawn.
 *
 */
public class ImageContainer extends JPanel {

	private EventListenerList listeners;
	
	public ImageContainer() {
		listeners = new EventListenerList();
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedOnImage(e);
			}
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);
		graphicsChanged(g2);
	}
	
	private void graphicsChanged(Graphics2D g) {
		for(ImageContainerListener listener : listeners.getListeners(ImageContainerListener.class)) {
			listener.onGraphicsChanged(g);
		}
	}
	
	public void addListener(ImageContainerListener listener) {
		listeners.add(ImageContainerListener.class, listener);
	}

	public void removeListener(ImageContainerListener listener) {
		listeners.remove(ImageContainerListener.class, listener);
	}
	
	public void mouseClickedOnImage(MouseEvent event) {
		for(ImageContainerListener listener : listeners.getListeners(ImageContainerListener.class)) {
			listener.onMouseClicked(event);
		}
	}

}
