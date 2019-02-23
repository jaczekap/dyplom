package uz.diploma.viewer.view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JToolBar;

import java.awt.GridBagConstraints;

import javax.swing.JButton;

import java.awt.Insets;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JSlider;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;
import java.util.Hashtable;
import java.util.List;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;

import uz.diploma.gui.window.panel.details.contexttab.panel.RecordElementListRenderer;
import uz.diploma.viewer.controller.ImageViewerController;
import uz.diploma.viewer.controller.ImageViewerViewController;
import uz.diploma.viewer.model.ImageTag;

import javax.swing.JList;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.JToggleButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.EtchedBorder;

import java.awt.ComponentOrientation;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

/**
 * @author Spider
 * 
 * Main window of image viewer
 *
 */
public class ImageViewer extends JFrame implements ImageView {

	private JPanel contentPane;
	private JPanel panel;
	private JToolBar toolBar;
	private JLabel zoomLabel;
	private JButton fitToWindowButton;
	private JScrollPane scrollPane;
	private ImageContainer imageContainer;
	private EventListenerList listeners;
	private JSlider slider;
	private JList<ImageTag> tags;
	private DefaultListModel<ImageTag> tagsModel;
	private JToggleButton editMode;
	private JButton filler;
	private JToggleButton hideTagsButton;

	/**
	 * Create the frame.
	 */
	public ImageViewer() {
		listeners = new EventListenerList();
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 200, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{202, 0};
		gbl_panel.rowHeights = new int[]{23, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.fill = GridBagConstraints.BOTH;
		gbc_toolBar.anchor = GridBagConstraints.NORTHWEST;
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 0;
		panel.add(toolBar, gbc_toolBar);
		
		zoomLabel = new JLabel("Zoom: 100");
		zoomLabel.setMinimumSize(new Dimension(60, 14));
		zoomLabel.setMaximumSize(new Dimension(60, 14));
		zoomLabel.setPreferredSize(new Dimension(60, 14));
		toolBar.add(zoomLabel);
		
		slider = new JSlider();
		slider.setBorder(null);
		slider.setMaximumSize(new Dimension(300, 23));
		slider.setMinimumSize(new Dimension(150, 23));
		slider.setValue(100);
		slider.setMaximum(500);
		slider.setMinimum(10);
		slider.addChangeListener(event -> zoomFactorChanged());
		toolBar.add(slider);
		
		fitToWindowButton = new JButton("Fit in Window");
		fitToWindowButton.setPreferredSize(new Dimension(75, 23));
		fitToWindowButton.setMaximumSize(new Dimension(75, 50));
		fitToWindowButton.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		fitToWindowButton.addActionListener(event -> fitInWindowButtonClicked());
		
		filler = new JButton("");
		toolBar.add(filler);
		toolBar.add(fitToWindowButton);
		
		editMode = new JToggleButton("Edit");
		editMode.setMaximumSize(new Dimension(75, 50));
		editMode.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		toolBar.add(editMode);
		
		hideTagsButton = new JToggleButton("Hide");
		hideTagsButton.addActionListener(event -> hideButtonStateChanged());
		hideTagsButton.setMaximumSize(new Dimension(75, 90));
		hideTagsButton.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		hideTagsButton.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		toolBar.add(hideTagsButton);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		imageContainer = new ImageContainer();
		scrollPane.setViewportView(imageContainer);
		
		tags = new JList<ImageTag>();
		tags.addListSelectionListener(event -> listItemSelected());
		tagsModel = new DefaultListModel<ImageTag>();
		tags.setModel(tagsModel);
		tags.setCellRenderer(new RecordElementListRenderer());
		tags.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 1;
		gbc_list.gridy = 1;
		contentPane.add(tags, gbc_list);
		setVisible(true);
	}

	@Override
	public void zoomFactorChanged() {
			zoomLabel.setText("Zoom: " + slider.getValue());	
		for(ImageViewerViewController listener : listeners.getListeners(ImageViewerViewController.class))
			listener.onZoomFactorChanged(slider.getValue());
	}

	@Override
	public void addDialogViewListener(ImageViewerViewController listener) {
		listeners.add(ImageViewerViewController.class, listener);
		listener.setImageContainer(imageContainer);
	}

	@Override
	public void removeDialogViewListener(ImageViewerViewController listener) {
		listeners.remove(ImageViewerViewController.class, listener);
	}

	@Override
	public void setTagList(List<ImageTag> tags) {
		tagsModel.removeAllElements();
		tags.forEach(tag -> tagsModel.addElement(tag));
	}

	@Override
	public boolean isEditModeEnabled() {
		return editMode.isSelected();
	}

	@Override
	public void fitInWindowButtonClicked() {
		for(ImageViewerViewController listener : listeners.getListeners(ImageViewerViewController.class))
			listener.onfitInWindowButtonClicked();
	}

	public void setSelectedTagOnList(ImageTag selectedTag) {
		tags.setSelectedIndex(tagsModel.indexOf(selectedTag));
	}

	@Override
	public boolean isHideButtonOn() {
		return hideTagsButton.isSelected();
	}

	@Override
	public void hideButtonStateChanged() {
		for(ImageViewerViewController listener : listeners.getListeners(ImageViewerViewController.class))
			listener.onHideButtonStateChanged();
	}

	@Override
	public ImageTag getSelectedTag() {
		return tags.getSelectedValue();
	}

	@Override
	public void listItemSelected() {
		for(ImageViewerViewController listener : listeners.getListeners(ImageViewerViewController.class))
			listener.onlistItemSelected();
	}

	@Override
	public void showTagDescription(MouseEvent e, String description) {
		JPopupMenu popup = new JPopupMenu();
		JTextArea textToShow = new JTextArea(description, 10, 40);
		textToShow.setFont(new Font(Font.SERIF, Font.PLAIN, 12));
		textToShow.setLineWrap(true);
		textToShow.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(textToShow, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		popup.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		popup.add(scrollPane);
		popup.show(this, e.getX() + 15, e.getY() + 85);
	}
}
