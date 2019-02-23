package uz.diploma.gui.window.panel.details.contexttab;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import uz.diploma.controller.context.ContextTabController;
import uz.diploma.controller.overview.OverviewTabController;
import uz.diploma.gui.window.panel.details.contexttab.panel.ComboBoxRenderer;
import uz.diploma.gui.window.panel.details.contexttab.panel.LocationsPanel;
import uz.diploma.gui.window.panel.details.contexttab.panel.PanelView;
import uz.diploma.listeners.ContextTabListener;
import uz.diploma.model.record.element.HistoricalEvent;
import uz.diploma.model.record.element.HistoricalPeriod;
import uz.diploma.model.record.element.Record;
import uz.diploma.tools.Validator;

import java.awt.Component;
import java.awt.Insets;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.event.EventListenerList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

/**
 * @author Spider
 * 
 * ContextTab is concrete view class and is one of the tab that is part of the right panel of the main application window
 *
 */
public class ContextTab extends JPanel implements ContextTabView {
	private EventListenerList listeners;
	private PanelView charactersPanel;
	private PanelView eventsPanel;
	private PanelView locationsPanel;
	private PanelView keyWordsPanel;
	private JComboBox<HistoricalPeriod> periodComboBox;
	private DefaultComboBoxModel<HistoricalPeriod> periodComboBoxModel;
	private JTextField filterTextField;
	private JSeparator separator;

	/**
	 * Create the panel.
	 */
	public ContextTab(PanelView charactersPanel, PanelView eventsPanel, PanelView locationsPanel, PanelView keyWordsPanel) {
		listeners = new EventListenerList();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 20, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		this.charactersPanel = charactersPanel;
		((JComponent) charactersPanel).setMinimumSize(new Dimension(200, 300));
		((JComponent) charactersPanel).setPreferredSize(new Dimension(200, 300));
		GridBagConstraints gbc_charactersPanel = new GridBagConstraints();
		gbc_charactersPanel.gridwidth = 2;
		gbc_charactersPanel.insets = new Insets(0, 0, 5, 5);
		gbc_charactersPanel.fill = GridBagConstraints.BOTH;
		gbc_charactersPanel.gridx = 0;
		gbc_charactersPanel.gridy = 1;
		add((Component) charactersPanel, gbc_charactersPanel);
		
		this.eventsPanel = eventsPanel;
		((JComponent) eventsPanel).setMinimumSize(new Dimension(200, 300));
		((JComponent) eventsPanel).setPreferredSize(new Dimension(200, 300));
		GridBagConstraints gbc_eventsPanel = new GridBagConstraints();
		gbc_eventsPanel.gridwidth = 3;
		gbc_eventsPanel.insets = new Insets(0, 0, 5, 5);
		gbc_eventsPanel.fill = GridBagConstraints.BOTH;
		gbc_eventsPanel.gridx = 2;
		gbc_eventsPanel.gridy = 1;
		add((Component) eventsPanel, gbc_eventsPanel);
		
		this.locationsPanel = new LocationsPanel();
		((JComponent) locationsPanel).setMinimumSize(new Dimension(200, 300));
		((JComponent) locationsPanel).setPreferredSize(new Dimension(200, 300));
		GridBagConstraints gbc_locationsPanel = new GridBagConstraints();
		gbc_locationsPanel.gridwidth = 2;
		gbc_locationsPanel.insets = new Insets(0, 0, 5, 5);
		gbc_locationsPanel.fill = GridBagConstraints.BOTH;
		gbc_locationsPanel.gridx = 5;
		gbc_locationsPanel.gridy = 1;
		add((Component) locationsPanel, gbc_locationsPanel);
		
		this.keyWordsPanel = keyWordsPanel;
		((JComponent) keyWordsPanel).setPreferredSize(new Dimension(200, 300));
		((JComponent) keyWordsPanel).setMinimumSize(new Dimension(200, 300));
		GridBagConstraints gbc_keyWordsPanel = new GridBagConstraints();
		gbc_keyWordsPanel.gridwidth = 2;
		gbc_keyWordsPanel.insets = new Insets(0, 0, 5, 5);
		gbc_keyWordsPanel.fill = GridBagConstraints.BOTH;
		gbc_keyWordsPanel.gridx = 7;
		gbc_keyWordsPanel.gridy = 1;
		add((Component) keyWordsPanel, gbc_keyWordsPanel);
		
		JLabel lblHistoricalPeriod = new JLabel("Historical Period:");
		GridBagConstraints gbc_lblHistoricalPeriod = new GridBagConstraints();
		gbc_lblHistoricalPeriod.anchor = GridBagConstraints.EAST;
		gbc_lblHistoricalPeriod.insets = new Insets(0, 0, 5, 5);
		gbc_lblHistoricalPeriod.gridx = 6;
		gbc_lblHistoricalPeriod.gridy = 0;
		add(lblHistoricalPeriod, gbc_lblHistoricalPeriod);
		
		periodComboBox = new JComboBox<HistoricalPeriod>();
		periodComboBox.addActionListener(event -> periodChanged((HistoricalPeriod)periodComboBox.getSelectedItem()));
		periodComboBoxModel = new DefaultComboBoxModel<HistoricalPeriod>();
		periodComboBox.setModel(periodComboBoxModel);
		periodComboBox.setRenderer(new ComboBoxRenderer());
		GridBagConstraints gbc_PeriodComboBox = new GridBagConstraints();
		gbc_PeriodComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_PeriodComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_PeriodComboBox.gridx = 8;
		gbc_PeriodComboBox.gridy = 0;
		add(periodComboBox, gbc_PeriodComboBox);
		
		separator = new JSeparator();
		separator.setPreferredSize(new Dimension(50, 1));
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.anchor = GridBagConstraints.SOUTH;
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 3;
		add(separator, gbc_separator);
		
		JLabel lblFilter = new JLabel("Filter");
		GridBagConstraints gbc_lblFilter = new GridBagConstraints();
		gbc_lblFilter.anchor = GridBagConstraints.WEST;
		gbc_lblFilter.insets = new Insets(0, 0, 5, 5);
		gbc_lblFilter.gridx = 1;
		gbc_lblFilter.gridy = 4;
		add(lblFilter, gbc_lblFilter);
		
		filterTextField = new JTextField();
		filterTextField.addCaretListener(e -> searchedTextChanged());
		filterTextField.setBorder(new LineBorder(Color.BLACK));
		filterTextField.setFocusAccelerator('f');
		GridBagConstraints gbc_filterTextField = new GridBagConstraints();
		gbc_filterTextField.insets = new Insets(0, 0, 5, 5);
		gbc_filterTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_filterTextField.gridx = 1;
		gbc_filterTextField.gridy = 5;
		add(filterTextField, gbc_filterTextField);
		filterTextField.setColumns(10);

	}

	@Override
	public void fillHistoricalPeriodList() {
		periodComboBoxModel.removeAllElements();
		for(HistoricalPeriod period : HistoricalPeriod.values()) {
			periodComboBoxModel.addElement(period);
		}
	}

	@Override
	public void setCurrentPeriod(HistoricalPeriod period) {
		periodComboBox.setSelectedItem(period);
	}

	@Override
	public void periodChanged(HistoricalPeriod period) {
		for(ContextTabListener listener : listeners.getListeners(ContextTabListener.class)) {
			listener.onPeriodChanged(period);
		}
	}

	@Override
	public void addContextTabListener(ContextTabListener listener) {
		if(listeners != null)
			listeners.add(ContextTabListener.class, listener);
	}

	@Override
	public void removeContextTabListener(ContextTabListener listener) {
		listeners.remove(ContextTabListener.class, listener);
	}

	@Override
	public void searchedTextChanged() {
		for(FilterTextFieldListener listener : listeners.getListeners(FilterTextFieldListener.class)) {
			listener.onSearchedTextChanged(filterTextField.getText());
		}
	}

	@Override
	public void addFilterListener(FilterTextFieldListener listener) {
		if(listeners != null)
			listeners.add(FilterTextFieldListener.class, listener);
	}

	@Override
	public void removeFilterTabListener(FilterTextFieldListener listener) {
		listeners.remove(FilterTextFieldListener.class, listener);
	}

}
