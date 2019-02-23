package uz.diploma.gui.window;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;
import javax.swing.JMenu;

import java.awt.Dimension;

import uz.diploma.MainApp;
import uz.diploma.WindowBuilder;
import uz.diploma.gui.window.panel.details.RightPanel;
import uz.diploma.gui.window.panel.details.RightPanelInterface;
import uz.diploma.gui.window.panel.record.*;
import uz.diploma.listeners.DataStoreEventsListener;
import uz.diploma.tools.Validator;

import java.awt.Cursor;

import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.event.EventListenerList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventListener;

/**
 * @author Spider
 * 
 * application main window
 *
 */
public class AppWindow implements WindowView {

	private EventListenerList listeners;
	private EventListenerList windowMenuListeners;
	private JFrame frame;
	private JSplitPane windowSplitPane;
	private RecordsView leftPanel;
	private RightPanelInterface rightPanel;
	private JFileChooser fileChooser;
	
	private JMenuBar menuBar;
	
	private JMenu fileMenu;
	
	private JMenuItem newMenuItem;
	private JMenuItem openMenuItem;
	private JMenuItem saveMenuItem;
	private JSeparator separator;
	private JMenuItem exitMenuItem;
	private JMenuItem saveAsMenuItem;
	private JMenu recordMenu;
	private JMenuItem addMenuItem;
	private JMenuItem removeMenuItem;

	/**
	 * Create the application.
	 */
	public AppWindow(RecordsView leftPanel, RightPanelInterface rightPanel) {
		Validator.ensureIsNotNull(leftPanel, "leftPanel");
		Validator.ensureIsNotNull(rightPanel, "rightPanel");
		
		this.leftPanel = leftPanel;
		this.rightPanel = rightPanel;
		listeners = new EventListenerList();
		windowMenuListeners = new EventListenerList();
		fileChooser = new JFileChooser();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 800);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		windowSplitPane = new JSplitPane();
		windowSplitPane.setDividerSize(2);
		windowSplitPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frame.getContentPane().add(windowSplitPane, BorderLayout.CENTER);
		
		((RecordsPanel) leftPanel).setMinimumSize(new Dimension(200, 73));
		((RecordsPanel) leftPanel).setPreferredSize(new Dimension(250, 404));
		windowSplitPane.setLeftComponent((RecordsPanel) leftPanel);
		windowSplitPane.setRightComponent((RightPanel) rightPanel);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		newMenuItem = new JMenuItem("New");
		newMenuItem.addActionListener(event -> new WindowBuilder().build());
		
		fileMenu.add(newMenuItem);
		
		openMenuItem = new JMenuItem("Open");
		openMenuItem.addActionListener(event -> open());
		fileMenu.add(openMenuItem);
		
		saveMenuItem = new JMenuItem("Save");
		saveMenuItem.setEnabled(false);
		saveMenuItem.addActionListener(event -> save());
		fileMenu.add(saveMenuItem);
		
		saveAsMenuItem = new JMenuItem("Save As...");
		saveAsMenuItem.addActionListener(event -> saveAs());
		fileMenu.add(saveAsMenuItem);
		
		separator = new JSeparator();
		fileMenu.add(separator);
		
		exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(event -> System.exit(0));
		fileMenu.add(exitMenuItem);
		
		recordMenu = new JMenu("Record");
		menuBar.add(recordMenu);
		
		addMenuItem = new JMenuItem("Add");
		addMenuItem.addActionListener(event -> addRecordSelected());
		recordMenu.add(addMenuItem);
		
		removeMenuItem = new JMenuItem("Remove");
		removeMenuItem.addActionListener(event -> removeRecordSelected());
		recordMenu.add(removeMenuItem);

		frame.setVisible(true);
	}

	@Override
	public void open() {
		int selectedOption = fileChooser.showOpenDialog(frame);
		if(selectedOption == JFileChooser.APPROVE_OPTION) {
			String path = fileChooser.getSelectedFile().getPath();
			for(DataStoreEventsListener listener : listeners.getListeners(DataStoreEventsListener.class)) {
				listener.onOpen(path);
			}
		}
	}

	@Override
	public void saveAs() {
		int selectedOption = fileChooser.showSaveDialog(frame);
		if(selectedOption == JFileChooser.APPROVE_OPTION) {
			String path = fileChooser.getSelectedFile().getPath();
			for(DataStoreEventsListener listener : listeners.getListeners(DataStoreEventsListener.class)) {
				listener.onSaveAs(path);
			}
		}
	}

	@Override
	public void setSaveMenuItemEnabled(boolean isEnabled) {
		saveMenuItem.setEnabled(isEnabled);
	}

	@Override
	public void save() {
		for(DataStoreEventsListener listener : listeners.getListeners(DataStoreEventsListener.class)) {
			listener.onSave();
		}
	}

	@Override
	public void addListener(DataStoreEventsListener listener) {
		listeners.add(DataStoreEventsListener.class, listener);
	}

	@Override
	public void removeListener(DataStoreEventsListener listener) {
		listeners.remove(DataStoreEventsListener.class, listener);
	}

	@Override
	public void addMenuListener(WindowMenuListener listener) {
		windowMenuListeners.add(WindowMenuListener.class, listener);
	}

	@Override
	public void removeMenuListener(WindowMenuListener listener) {
		windowMenuListeners.remove(WindowMenuListener.class, listener);
	}

	@Override
	public void removeRecordSelected() {
		for(WindowMenuListener listener : windowMenuListeners.getListeners(WindowMenuListener.class)) {
			listener.onRemoveRecordSelected();
		}
	}

	@Override
	public void addRecordSelected() {
		for(WindowMenuListener listener : windowMenuListeners.getListeners(WindowMenuListener.class)) {
			listener.onAddRecordSelected();
		}
	}
	
	@Override
	public void showInformationDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "Information Dialog", JOptionPane.INFORMATION_MESSAGE);
	}
}
