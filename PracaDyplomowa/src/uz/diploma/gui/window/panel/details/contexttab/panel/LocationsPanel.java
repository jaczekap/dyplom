package uz.diploma.gui.window.panel.details.contexttab.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.EventListenerList;

import uz.diploma.controller.context.PanelViewController;
import uz.diploma.gui.window.panel.record.ResultListListener;
import uz.diploma.model.record.element.HistoricalCharacter;
import uz.diploma.model.record.element.HistoricalPeriod;
import uz.diploma.model.record.element.Location;
import uz.diploma.model.record.element.Record;
import uz.diploma.model.record.element.RecordElement;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * @author Spider
 * 
 * concrete view for presenting geographical location panel
 * panel is part of the context tab
 *
 */
public class LocationsPanel extends JPanel implements PanelView {

	private EventListenerList listeners;
	private JButton addLocationButton;
	private JButton removeLocationButton;
	private JList<Location> locationsList;
	private DefaultListModel<Location> locationListModel;

	/**
	 * Create the panel.
	 */
	public LocationsPanel() {
		listeners = new EventListenerList();
		
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Locations", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		addLocationButton = new JButton("Add");
		addLocationButton.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGrabed();
			}
		});
		addLocationButton.addActionListener(event -> addButtonClicked());
		addLocationButton.setMinimumSize(new Dimension(75, 23));
		addLocationButton.setMaximumSize(new Dimension(75, 23));
		addLocationButton.setPreferredSize(new Dimension(75, 23));
		GridBagConstraints gbc_addLocationButton = new GridBagConstraints();
		gbc_addLocationButton.anchor = GridBagConstraints.WEST;
		gbc_addLocationButton.insets = new Insets(0, 0, 5, 5);
		gbc_addLocationButton.gridx = 0;
		gbc_addLocationButton.gridy = 0;
		add(addLocationButton, gbc_addLocationButton);
		
		removeLocationButton = new JButton("Remove");
		removeLocationButton.addActionListener(event -> removeButtonClicked());
		removeLocationButton.setMaximumSize(new Dimension(75, 23));
		removeLocationButton.setMinimumSize(new Dimension(75, 23));
		removeLocationButton.setPreferredSize(new Dimension(75, 23));
		GridBagConstraints gbc_removeLocationButton = new GridBagConstraints();
		gbc_removeLocationButton.anchor = GridBagConstraints.WEST;
		gbc_removeLocationButton.insets = new Insets(0, 0, 5, 0);
		gbc_removeLocationButton.gridx = 1;
		gbc_removeLocationButton.gridy = 0;
		add(removeLocationButton, gbc_removeLocationButton);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		locationsList = new JList<>();
		locationListModel = new DefaultListModel<>();
		locationsList.setBorder(new LineBorder(new Color(0, 0, 0)));
		locationsList.setModel(locationListModel);
		locationsList.setCellRenderer(new RecordElementListRenderer());
		scrollPane.setViewportView(locationsList);

	}
	
	@Override
	public void onNewRecord(Record selectedRecord) {
		locationListModel.removeAllElements();
		selectedRecord.getLocationsIterator().forEachRemaining(location -> locationListModel.addElement(location));
	}

	@Override
	public void addButtonClicked() {
		for(PanelViewController listener : listeners.getListeners(PanelViewController.class)) {
			listener.onAddButtonClicked();
		}
	}

	@Override
	public void focusGrabed() {
		for(PanelViewController listener : listeners.getListeners(PanelViewController.class)) {
			listener.onFocusGained();
		}
	}
	
	@Override
	public void addPanelViewListener(PanelViewController listener) {
		if(listeners != null)
			listeners.add(PanelViewController.class, listener);
	}

	@Override
	public void removePanelViewListener(PanelViewController listener) {
		listeners.remove(PanelViewController.class, listener);		
	}

	@Override
	public void removeButtonClicked() {
		for(PanelViewController listener : listeners.getListeners(PanelViewController.class)) {
			listener.onRemoveButtonClicked();
		}
	}

	@Override
	public RecordElement getSelection() {
		return locationsList.getSelectedValue();
	}

	@Override
	public int showRemoveConfirmationDialog(String message) {
		return JOptionPane.showConfirmDialog(this, message, "Confirmation Dialog", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	}

	@Override
	public void showItemNotSelectedDialog() {
		JOptionPane.showMessageDialog(this, "Select item to remove first", "Information Dialog", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public boolean canRemoveElement(String message) {
		int selectedValue = showRemoveConfirmationDialog(message);
		if(selectedValue == JOptionPane.CANCEL_OPTION)
			return false;
		else
			return true;
	}

	@Override
	public void updateView(List<RecordElement> locations) {
		locationListModel.clear();
		locations.stream().forEach(location -> locationListModel.addElement((Location)location));
	}
}
