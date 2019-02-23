package uz.diploma.gui.window.panel.details.overviewtab;

import javax.swing.JPanel;

import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;

import java.awt.GridBagConstraints;

import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.EventListenerList;

import uz.diploma.listeners.TextFieldChangedListener;
import uz.diploma.model.record.element.Record;

import java.awt.Color;
import java.util.EventListener;

/**
 * @author Spider
 * 
 * concrete view class for panel presenting table of contents of the record
 * part of the overview tab
 *
 */
public class OverviewTableOfContents extends JPanel implements TableOfContentsInterface {
	private EventListenerList listeners;
	private JTextArea tableOfContentsTextArea;

	/**
	 * Create the panel.
	 */
	public OverviewTableOfContents() {
		listeners = new EventListenerList();
		
		setBorder(new TitledBorder(null, "Table of contents", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{200, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JScrollPane TableOfContentsScrollPane = new JScrollPane();
		GridBagConstraints gbc_TableOfContentsScrollPane = new GridBagConstraints();
		gbc_TableOfContentsScrollPane.fill = GridBagConstraints.BOTH;
		gbc_TableOfContentsScrollPane.gridx = 0;
		gbc_TableOfContentsScrollPane.gridy = 0;
		add(TableOfContentsScrollPane, gbc_TableOfContentsScrollPane);
		
		tableOfContentsTextArea = new JTextArea();
		tableOfContentsTextArea.addCaretListener(event -> textFieldChanged());
		tableOfContentsTextArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tableOfContentsTextArea.setColumns(5);
		tableOfContentsTextArea.setWrapStyleWord(true);
		tableOfContentsTextArea.setLineWrap(true);
		tableOfContentsTextArea.setRows(5);
		tableOfContentsTextArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		TableOfContentsScrollPane.setViewportView(tableOfContentsTextArea);
		tableOfContentsTextArea.setText("");

	}
	
	@Override
	public void setFieldValue(String fieldValue) {
		tableOfContentsTextArea.setText(fieldValue);
	}

	
	@Override
	public String getFieldValue() {
		return tableOfContentsTextArea.getText();
	}

	@Override
	public void addTextChangedListener(TextFieldChangedListener listener) {
		listeners.add(TextFieldChangedListener.class, listener);
	}

	@Override
	public void removeTextListener(TextFieldChangedListener listener) {
		listeners.remove(TextFieldChangedListener.class, listener);
	}

	@Override
	public void textFieldChanged() {
		for(TextFieldChangedListener listener : listeners.getListeners(TextFieldChangedListener.class)) {
				listener.onTextFieldChanged();
		}
	}

}
