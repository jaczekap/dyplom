package uz.diploma.gui.window.panel.details.quotationtab;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.Dimension;

import javax.swing.JList;

import java.awt.Insets;

import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.EventListenerList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JLabel;

import uz.diploma.controller.quotation.QuotationTabController;
import uz.diploma.listeners.OverviewTabListener;
import uz.diploma.model.record.element.Quotation;
import uz.diploma.model.record.element.Record;
import uz.diploma.model.record.element.Reference;

import javax.swing.ListSelectionModel;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

/**
 * @author Spider
 * 
 * QuotationTabPanel is concrete view class and is one of the tab that is part of the right panel of the main application window
 *
 */
public class QuotationTabPanel extends JPanel implements QuotationTabView {
	private EventListenerList listeners;
	private JButton referenceToAddButton;
	private JButton referenceToRemoveButton;
	private JTable referenceToTable;
	private JScrollPane scrollPane;
	private DefaultTableModel referenceToTableModel;
	private DefaultTableModel referenceFromTableModel;
	private DefaultTableModel quotationTableModel;
	private JTable referenceFromTable;
	private JScrollPane scrollPane_1;
	private JLabel referenceToLabel;
	private JLabel referenceFromLabel;
	private JLabel quotationsLabel;
	private JTable quotationsTable;
	private JScrollPane scrollPane_2;
	private JButton quotationAddButton;
	private JButton quotationRemoveButton;
	private JSeparator separator;

	/**
	 * Create the panel.
	 */
	public QuotationTabPanel() {
		
		listeners = new EventListenerList();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 30, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		referenceToAddButton = new JButton("Add");
		referenceToAddButton.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGot();
			}
		});
		referenceToAddButton.addActionListener(event -> addReferenceToButtonPressed());
		
		referenceToLabel = new JLabel("Reference to: ");
		GridBagConstraints gbc_sepLabel = new GridBagConstraints();
		gbc_sepLabel.anchor = GridBagConstraints.WEST;
		gbc_sepLabel.gridwidth = 2;
		gbc_sepLabel.insets = new Insets(5, 5, 5, 5);
		gbc_sepLabel.gridx = 0;
		gbc_sepLabel.gridy = 0;
		add(referenceToLabel, gbc_sepLabel);
		referenceToAddButton.setPreferredSize(new Dimension(75, 23));
		referenceToAddButton.setMinimumSize(new Dimension(75, 23));
		referenceToAddButton.setMaximumSize(new Dimension(75, 23));
		GridBagConstraints gbc_quotationAddButton = new GridBagConstraints();
		gbc_quotationAddButton.insets = new Insets(0, 0, 5, 5);
		gbc_quotationAddButton.gridx = 0;
		gbc_quotationAddButton.gridy = 1;
		add(referenceToAddButton, gbc_quotationAddButton);
		
		referenceToRemoveButton = new JButton("Remove");
		referenceToRemoveButton.addActionListener(event -> removeReferenceToButtonPressed());
		referenceToRemoveButton.setMaximumSize(new Dimension(75, 23));
		referenceToRemoveButton.setMinimumSize(new Dimension(75, 23));
		referenceToRemoveButton.setPreferredSize(new Dimension(75, 23));
		GridBagConstraints gbc_removeReferenceButton = new GridBagConstraints();
		gbc_removeReferenceButton.insets = new Insets(0, 0, 5, 5);
		gbc_removeReferenceButton.gridx = 1;
		gbc_removeReferenceButton.gridy = 1;
		add(referenceToRemoveButton, gbc_removeReferenceButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		
		referenceToTable = new JTable();
		referenceToTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		referenceToTable.setPreferredScrollableViewportSize(new Dimension(450, 100));
		scrollPane.setViewportView(referenceToTable);
		referenceToTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"Reference", "Position", "Notes"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			@Override
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true
			};
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		referenceToTable.getColumnModel().getColumn(0).setResizable(false);
		referenceToTable.getColumnModel().getColumn(0).setPreferredWidth(200);
		referenceToTable.getColumnModel().getColumn(0).setMaxWidth(250);
		referenceToTable.getColumnModel().getColumn(1).setResizable(false);
		referenceToTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		referenceToTable.getColumnModel().getColumn(1).setMaxWidth(100);
		referenceToTable.getColumnModel().getColumn(2).setResizable(false);
		referenceToTable.getColumnModel().getColumn(2).setPreferredWidth(394);
		
		referenceToTable.setDefaultRenderer(String.class, new TableRenderer());
		
		referenceFromLabel = new JLabel("Reference from:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.insets = new Insets(5, 5, 10, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 3;
		add(referenceFromLabel, gbc_lblNewLabel_1);
		
		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridwidth = 3;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 4;
		add(scrollPane_1, gbc_scrollPane_1);
		
		referenceFromTable = new JTable();
		referenceFromTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"Reference", "Position", "Notes"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			@Override
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true
			};
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		referenceFromTable.getColumnModel().getColumn(0).setPreferredWidth(200);
		referenceFromTable.getColumnModel().getColumn(0).setMaxWidth(200);
		referenceFromTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		referenceFromTable.getColumnModel().getColumn(1).setMaxWidth(100);
		scrollPane_1.setViewportView(referenceFromTable);
		referenceFromTable.setPreferredScrollableViewportSize(new Dimension(450, 100));
		
		referenceFromTable.setDefaultRenderer(String.class, new TableRenderer());
		
		separator = new JSeparator();
		separator.setPreferredSize(new Dimension(0, 5));
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.anchor = GridBagConstraints.SOUTH;
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridwidth = 4;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 6;
		add(separator, gbc_separator);
		
		quotationsLabel = new JLabel("Quotations");
		GridBagConstraints gbc_lblQuotations = new GridBagConstraints();
		gbc_lblQuotations.anchor = GridBagConstraints.WEST;
		gbc_lblQuotations.gridwidth = 2;
		gbc_lblQuotations.insets = new Insets(5, 5, 5, 5);
		gbc_lblQuotations.gridx = 0;
		gbc_lblQuotations.gridy = 7;
		add(quotationsLabel, gbc_lblQuotations);
		
		quotationAddButton = new JButton("Add");
		quotationAddButton.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGot();
			}
		});
		quotationAddButton.addActionListener(event -> addQuotationButtonPressed());
		quotationAddButton.setPreferredSize(new Dimension(75, 23));
		quotationAddButton.setMinimumSize(new Dimension(75, 23));
		quotationAddButton.setMaximumSize(new Dimension(75, 23));
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.insets = new Insets(0, 0, 5, 5);
		gbc_button_2.gridx = 0;
		gbc_button_2.gridy = 8;
		add(quotationAddButton, gbc_button_2);
		
		quotationRemoveButton = new JButton("Remove");
		quotationRemoveButton.addActionListener(event -> removeQuotationButtonPressed());
		quotationRemoveButton.setPreferredSize(new Dimension(75, 23));
		quotationRemoveButton.setMinimumSize(new Dimension(75, 23));
		quotationRemoveButton.setMaximumSize(new Dimension(75, 23));
		GridBagConstraints gbc_button_3 = new GridBagConstraints();
		gbc_button_3.insets = new Insets(0, 0, 5, 5);
		gbc_button_3.gridx = 1;
		gbc_button_3.gridy = 8;
		add(quotationRemoveButton, gbc_button_3);
		
		scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.gridwidth = 3;
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_2.gridx = 0;
		gbc_scrollPane_2.gridy = 9;
		add(scrollPane_2, gbc_scrollPane_2);
		
		quotationsTable = new JTable();
		quotationsTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Position", "Quotation"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			@Override
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		quotationsTable.getColumnModel().getColumn(0).setPreferredWidth(150);
		quotationsTable.getColumnModel().getColumn(0).setMaxWidth(150);
		
		scrollPane_2.setViewportView(quotationsTable);
		quotationsTable.setPreferredScrollableViewportSize(new Dimension(450, 100));
		
		quotationsTable.setDefaultRenderer(String.class, new TableRenderer());
		
		referenceToTableModel = (DefaultTableModel)referenceToTable.getModel();
		referenceFromTableModel = (DefaultTableModel)referenceFromTable.getModel();
		quotationTableModel = (DefaultTableModel)quotationsTable.getModel();
	}

	@Override
	public void addListener(QuotationTabController listener) {
		listeners.add(QuotationTabController.class, listener);
	}

	@Override
	public void removeListener(QuotationTabController listener) {
		listeners.remove(QuotationTabController.class, listener);
	}

	@Override
	public void addReferenceToButtonPressed() {
		for(QuotationTabController listener : listeners.getListeners(QuotationTabController.class)) {
			listener.onAddReferenceToButtonPressed();
		}
	}

	@Override
	public void removeReferenceToButtonPressed() {
		for(QuotationTabController listener : listeners.getListeners(QuotationTabController.class)) {
			listener.onRemoveReferenceToButtonPressed();
		}
	}

	@Override
	public void addQuotationButtonPressed() {
		for(QuotationTabController listener : listeners.getListeners(QuotationTabController.class)) {
			listener.onAddQuotationButtonPressed();
		}
	}

	@Override
	public void removeQuotationButtonPressed() {
		for(QuotationTabController listener : listeners.getListeners(QuotationTabController.class)) {
			listener.onRemoveQuotationButtonPressed();
		}
	}

	@Override
	public void updateReferenceList(List<Reference> references) {
		int toReferenceRowCount = referenceToTableModel.getRowCount();
		for(int i = toReferenceRowCount - 1; i >= 0; i--)
			referenceToTableModel.removeRow(i);
		
		int fromReferenceRowCount = referenceFromTableModel.getRowCount();
		for(int i = fromReferenceRowCount - 1; i >= 0; i--)
			referenceFromTableModel.removeRow(i);
			
		references.forEach(reference -> {
			if(reference.isToReference())
				referenceToTableModel.addRow( new Object[] {reference.getReferenceRecord(), reference.getLocation(), reference.getNotes()});
			else
				referenceFromTableModel.addRow( new Object[] {reference.getReferenceRecord(), reference.getLocation(), reference.getNotes()});
		});
	}

	@Override
	public void focusGot() {
		for(QuotationTabController listener : listeners.getListeners(QuotationTabController.class)) {
			listener.onFocusGot();
		}
	}

	@Override
	public Record getReferenceRecord() {
		return (Record)referenceToTableModel.getValueAt(referenceToTable.getSelectedRow(), 0);
	}

	@Override
	public String getReferenceLocation() {
		return (String)referenceToTableModel.getValueAt(referenceToTable.getSelectedRow(), 1);
	}

	@Override
	public String getReferenceNotes() {
		return (String)referenceToTableModel.getValueAt(referenceToTable.getSelectedRow(), 2);
	}

	@Override
	public void updateQuotationsList(List<Quotation> quotations) {
		int quotationRowCount = quotationTableModel.getRowCount();
		for(int i = quotationRowCount - 1; i >= 0; i--)
			quotationTableModel.removeRow(i);
		
		
		quotations.forEach(quotation -> quotationTableModel.addRow( new Object[] {quotation.getLocation(), quotation.getQuote()}));
	}

	@Override
	public String getQuotationLocation() {
		return (String)quotationTableModel.getValueAt(quotationsTable.getSelectedRow(), 0);
	}

	@Override
	public String getQuote() {
		return (String)quotationTableModel.getValueAt(quotationsTable.getSelectedRow(), 1);
	}

}
