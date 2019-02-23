package uz.diploma.gui.window.panel.details.overviewtab;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;

import java.awt.GridBagConstraints;

import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.EventListenerList;

import uz.diploma.listeners.TextFieldChangedListener;
import uz.diploma.model.record.element.Record;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.util.EventListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

/**
 * @author Spider
 * 
 * concrete view class for panel presenting summary of the record
 * part of the overview tab 
 *
 */
public class OverviewSummary extends JPanel implements SummaryPanelInterface {
	private EventListenerList listeners;
	private JTextArea summaryTextArea;

	/**
	 * Create the panel.
	 */
	public OverviewSummary() {
		listeners = new EventListenerList();
		
		setBorder(new TitledBorder(null, "Summary", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{150, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JScrollPane summaryScrollPane = new JScrollPane();
		GridBagConstraints gbc_summaryScrollPane = new GridBagConstraints();
		gbc_summaryScrollPane.fill = GridBagConstraints.BOTH;
		gbc_summaryScrollPane.gridx = 0;
		gbc_summaryScrollPane.gridy = 0;
		add(summaryScrollPane, gbc_summaryScrollPane);
		
		summaryTextArea = new JTextArea();
		summaryTextArea.addCaretListener(event -> textFieldChanged());
		summaryTextArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
		summaryTextArea.setColumns(5);
		summaryTextArea.setRows(5);
		summaryTextArea.setLineWrap(true);
		summaryTextArea.setWrapStyleWord(true);
		summaryTextArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		summaryScrollPane.setViewportView(summaryTextArea);

	}
	
	@Override
	public void setFieldValue(String fieldValue) {
		summaryTextArea.setText(fieldValue);
	}
	
	@Override
	public String getFieldValue() {
		return summaryTextArea.getText();
	}

	@Override
	public void addTextChangedListener(TextFieldChangedListener listener) {
		listeners.add(TextFieldChangedListener.class, listener);
	}

	@Override
	public void removeTextChangedListener(TextFieldChangedListener listener) {
		listeners.remove(TextFieldChangedListener.class, listener);
	}

	@Override
	public void textFieldChanged() {
		for(TextFieldChangedListener listener : listeners.getListeners(TextFieldChangedListener.class)) {
				listener.onTextFieldChanged();
		}
	}

}
