package uz.diploma.gui.window.panel.details.contexttab.panel;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.border.TitledBorder;
import javax.swing.JList;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Window;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.EventListenerList;

import uz.diploma.controller.context.PanelViewController;
import uz.diploma.gui.window.panel.record.ResultListListener;
import uz.diploma.listeners.TextFieldChangedListener;
import uz.diploma.model.record.element.HistoricalCharacter;
import uz.diploma.model.record.element.HistoricalEvent;
import uz.diploma.model.record.element.HistoricalPeriod;
import uz.diploma.model.record.element.Record;
import uz.diploma.model.record.element.RecordElement;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * @author Spider
 * 
 * concrete view for presenting historical events panel
 * panel is part of the context tab
 *
 */
public class EventsPanel extends JPanel implements PanelView {
	private EventListenerList listeners;
	private JButton addEventButton;
	private JButton removeEventButton;
	private JList<HistoricalEvent> eventsList;
	private DefaultListModel<HistoricalEvent> eventListmodel;

	/**
	 * Create the panel.
	 */
	public EventsPanel() {
		listeners = new EventListenerList();
		
		setBorder(new TitledBorder(null, "Events", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		addEventButton = new JButton("Add");
		addEventButton.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGrabed();
			}
		});
		addEventButton.addActionListener(event -> addButtonClicked());
		addEventButton.setMinimumSize(new Dimension(75, 23));
		addEventButton.setMaximumSize(new Dimension(75, 23));
		addEventButton.setPreferredSize(new Dimension(75, 23));
		GridBagConstraints gbc_addEventButton = new GridBagConstraints();
		gbc_addEventButton.anchor = GridBagConstraints.WEST;
		gbc_addEventButton.insets = new Insets(0, 0, 5, 5);
		gbc_addEventButton.gridx = 0;
		gbc_addEventButton.gridy = 0;
		add(addEventButton, gbc_addEventButton);
		
		removeEventButton = new JButton("Remove");
		removeEventButton.addActionListener(event -> removeButtonClicked());
		removeEventButton.setMaximumSize(new Dimension(75, 23));
		removeEventButton.setMinimumSize(new Dimension(75, 23));
		removeEventButton.setPreferredSize(new Dimension(75, 23));
		GridBagConstraints gbc_removeEventButton = new GridBagConstraints();
		gbc_removeEventButton.anchor = GridBagConstraints.WEST;
		gbc_removeEventButton.insets = new Insets(0, 0, 5, 0);
		gbc_removeEventButton.gridx = 1;
		gbc_removeEventButton.gridy = 0;
		add(removeEventButton, gbc_removeEventButton);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		eventsList = new JList<>();
		eventListmodel = new DefaultListModel<>();
		eventsList.setBorder(new LineBorder(new Color(0, 0, 0)));
		eventsList.setModel(eventListmodel);
		eventsList.setCellRenderer(new RecordElementListRenderer());
		scrollPane.setViewportView(eventsList);

	}

	@Override
	public void onNewRecord(Record selectedRecord) {
		eventListmodel.removeAllElements();
		selectedRecord.getEventsIterator().forEachRemaining(event -> eventListmodel.addElement(event));
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
		return eventsList.getSelectedValue();
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
	public void updateView(List<RecordElement> events) {
		eventListmodel.clear();
		events.stream().forEach(event -> eventListmodel.addElement((HistoricalEvent)event));
	}

}
