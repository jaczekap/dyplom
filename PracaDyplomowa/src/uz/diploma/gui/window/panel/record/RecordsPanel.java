package uz.diploma.gui.window.panel.record;

import javax.swing.JPanel;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JComboBox;

import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import uz.diploma.listeners.SearchTextListener;
import uz.diploma.model.record.element.Record;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.EventListenerList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Component;

import javax.swing.Box;

import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

/**
 * @author Spider
 * 
 * Class is view for left panel of the application window
 *
 */
public class RecordsPanel extends JPanel implements RecordsView {
	private EventListenerList listeners;
	private JList<Record> resultList;
	private DefaultListModel<Record> resultListmodel = new DefaultListModel<>();
	private JTextField searchField;

	/**
	 * Create the panel.
	 */
	public RecordsPanel() {
		listeners = new EventListenerList();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		searchField = new JTextField();
		searchField.addCaretListener(event -> searchTextChanged(searchField.getText()));
		searchField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				searchField.selectAll();
				searchField.setFont(searchField.getFont().deriveFont(Font.PLAIN));
				searchField.setForeground(Color.BLACK);
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(searchField.getText().equalsIgnoreCase("")) {
					searchField.setText("type for search");
				}
				searchField.setForeground(new Color(120, 120, 120));
				searchField.setFont(searchField.getFont().deriveFont(Font.ITALIC));
			}
		});
		searchField.setText("type for search");
		searchField.setForeground(new Color(120, 120, 120));
		searchField.setFont(searchField.getFont().deriveFont(Font.ITALIC));
		GridBagConstraints gbc_searchField = new GridBagConstraints();
		gbc_searchField.insets = new Insets(0, 0, 5, 0);
		gbc_searchField.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchField.gridx = 0;
		gbc_searchField.gridy = 0;
		add(searchField, gbc_searchField);
		searchField.setColumns(10);
		
		JScrollPane resultScrollPane = new JScrollPane();
		GridBagConstraints gbc_resultScrollPane = new GridBagConstraints();
		gbc_resultScrollPane.fill = GridBagConstraints.BOTH;
		gbc_resultScrollPane.gridx = 0;
		gbc_resultScrollPane.gridy = 1;
		add(resultScrollPane, gbc_resultScrollPane);
		
		resultList = new JList<>();
		resultList.addListSelectionListener(event -> resultListItemSelected(resultList.getSelectedValue()));
		resultList.setBorder(new LineBorder(new Color(0, 0, 0)));
		resultList.setModel(resultListmodel);
		resultList.setCellRenderer(new ResultListRenderer());
		resultScrollPane.setViewportView(resultList);
		
	}
	
	@Override
	public void setResultList(List<Record> recordList) {
		 resultListmodel.removeAllElements();
		 recordList.stream().forEach(record -> resultListmodel.addElement(record));
	}


	@Override
	public void addResultListSelectedListener(ResultListListener Observer) {
		if(listeners != null)
		listeners.add(ResultListListener.class, Observer);
		
	}

	@Override
	public void removeResultListSelectedListener(ResultListListener Observer) {
		listeners.remove(ResultListListener.class, Observer);
		
	}
	
	@Override
	public void resultListItemSelected(Record selectedRecord) {
		for(ResultListListener controller : listeners.getListeners(ResultListListener.class))
			if(resultList.getSelectedIndex() > -1)
				controller.onResultListItemSelected(selectedRecord);
	}

	@Override
	public void setResultListSelection() {
		if(resultList != null) {
			resultList.setSelectedIndex(0);
			resultListItemSelected(resultList.getSelectedValue());
		}
	}

	@Override
	public void searchTextChanged(String textFromField) {
		for(SearchTextListener controller : listeners.getListeners(SearchTextListener.class))
				controller.onSearchTextChanged(textFromField);
	}

	@Override
	public void addSearchTextListener(SearchTextListener listener) {
		if(listeners != null)
			listeners.add(SearchTextListener.class, listener);
	}

	@Override
	public void removeSearchTextListener(SearchTextListener listener) {
		if(listeners != null)
			listeners.remove(SearchTextListener.class, listener);
	}

}
