package uz.diploma.gui.dialog.quotation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JComboBox;

import java.awt.Insets;

import javax.swing.border.TitledBorder;
import javax.swing.event.EventListenerList;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import uz.diploma.controller.dialog.quotation.ReferenceDialogController;
import uz.diploma.gui.dialog.WindowDialogView;
import uz.diploma.gui.window.panel.details.contexttab.panel.ComboBoxRenderer;
import uz.diploma.listeners.DialogViewListener;
import uz.diploma.model.record.element.HistoricalCharacter;
import uz.diploma.model.record.element.Record;
import uz.diploma.model.record.element.RecordElement;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.List;
import java.awt.event.ActionListener;

/**
 * @author Spider
 * 
 * Class creates view for dialog window allowing to add reference
 *
 */
public class AddReferenceDialog extends JDialog implements ReferenceDialogView {

	private final JPanel contentPanel = new JPanel();
	private JTextField locationTextField;
	private EventListenerList listeners;
	private JLabel referencedRecordLabel;
	private JComboBox<Record> recordComboBox;
	private DefaultComboBoxModel<Record> recordComboBoxModel;
	private JLabel locationTextFieldLabel;
	private JLabel noteslabel;
	private JTextArea notesTextArea;
	private JButton addButton;
	private JButton cancelButton;

	/**
	 * Create the dialog.
	 */
	public AddReferenceDialog() {
		listeners = new EventListenerList();
		
		setTitle("Add Reference");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 420, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "Add Reference", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{70, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			referencedRecordLabel = new JLabel("Refer To:");
			GridBagConstraints gbc_referencedRecordLabel = new GridBagConstraints();
			gbc_referencedRecordLabel.anchor = GridBagConstraints.EAST;
			gbc_referencedRecordLabel.insets = new Insets(5, 5, 5, 5);
			gbc_referencedRecordLabel.gridx = 0;
			gbc_referencedRecordLabel.gridy = 0;
			contentPanel.add(referencedRecordLabel, gbc_referencedRecordLabel);
		}
		{
			recordComboBox = new JComboBox<Record>();
			GridBagConstraints gbc_recordComboBox = new GridBagConstraints();
			gbc_recordComboBox.insets = new Insets(5, 0, 5, 5);
			gbc_recordComboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_recordComboBox.gridx = 1;
			gbc_recordComboBox.gridy = 0;
			contentPanel.add(recordComboBox, gbc_recordComboBox);
			
			recordComboBoxModel = new DefaultComboBoxModel<Record>();
			recordComboBox.setModel(recordComboBoxModel);
		}
		{
			locationTextFieldLabel = new JLabel("Location:");
			GridBagConstraints gbc_locationTextFieldLabel = new GridBagConstraints();
			gbc_locationTextFieldLabel.anchor = GridBagConstraints.EAST;
			gbc_locationTextFieldLabel.insets = new Insets(0, 0, 5, 5);
			gbc_locationTextFieldLabel.gridx = 0;
			gbc_locationTextFieldLabel.gridy = 1;
			contentPanel.add(locationTextFieldLabel, gbc_locationTextFieldLabel);
		}
		{
			locationTextField = new JTextField();
			GridBagConstraints gbc_locationTextField = new GridBagConstraints();
			gbc_locationTextField.insets = new Insets(0, 0, 5, 5);
			gbc_locationTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_locationTextField.gridx = 1;
			gbc_locationTextField.gridy = 1;
			contentPanel.add(locationTextField, gbc_locationTextField);
			locationTextField.setColumns(10);
		}
		{
			noteslabel = new JLabel("Notes:");
			GridBagConstraints gbc_noteslabel = new GridBagConstraints();
			gbc_noteslabel.anchor = GridBagConstraints.WEST;
			gbc_noteslabel.insets = new Insets(0, 0, 5, 5);
			gbc_noteslabel.gridx = 1;
			gbc_noteslabel.gridy = 2;
			contentPanel.add(noteslabel, gbc_noteslabel);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 1;
			gbc_scrollPane.gridy = 3;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{
				notesTextArea = new JTextArea();
				notesTextArea.setWrapStyleWord(true);
				notesTextArea.setLineWrap(true);
				scrollPane.setViewportView(notesTextArea);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				addButton = new JButton("Add");
				addButton.addActionListener(event -> addButtonClicked());
				addButton.setPreferredSize(new Dimension(75, 23));
				addButton.setActionCommand("OK");
				buttonPane.add(addButton);
				getRootPane().setDefaultButton(addButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(event -> cancelButtonClicked());
				cancelButton.setPreferredSize(new Dimension(75, 23));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);	
	}

	@Override
	public void addDialogViewListener(ReferenceDialogController listener) {
		if(listeners != null)
			listeners.add(ReferenceDialogController.class, listener);
	}

	@Override
	public void removeDialogViewListener(ReferenceDialogController listener) {
		listeners.remove(ReferenceDialogController.class, listener);
	}

	@Override
	public void setSelectionComboBox(List<Record> elementsList) {
		recordComboBoxModel.removeAllElements();
		elementsList.stream().forEach(record -> recordComboBoxModel.addElement(record));
	}

	@Override
	public void cancelButtonClicked() {
		for(ReferenceDialogController listener : listeners.getListeners(ReferenceDialogController.class)) {
			listener.onCancelButtonClicked();
		}
	}

	@Override
	public void addButtonClicked() {
		for(ReferenceDialogController listener : listeners.getListeners(ReferenceDialogController.class)) {
			listener.onAddButtonClicked();
		}
	}

	@Override
	public String getLocationText() {
		return locationTextField.getText();
	}

	@Override
	public String getNotes() {
		return notesTextArea.getText();
	}

	@Override
	public Record getSelectedRecord() {
		return recordComboBoxModel.getElementAt(recordComboBox.getSelectedIndex());
	}
	
	@Override
	public int showQuestionDialog(String question) {
		return JOptionPane.showConfirmDialog(this, question, "Confirmation Dialog", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	}

	@Override
	public boolean positiveAnswerToQuestion(String message) {
		if(showQuestionDialog(message) == JOptionPane.OK_OPTION)
			return true;
		return false;
	}

	@Override
	public void showInformationDialog(String message) {
		JOptionPane.showMessageDialog(this, message, "Information Dialog", JOptionPane.INFORMATION_MESSAGE);
	}

}
