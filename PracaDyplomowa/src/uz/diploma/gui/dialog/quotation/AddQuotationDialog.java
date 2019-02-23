package uz.diploma.gui.dialog.quotation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.EventListenerList;

import uz.diploma.controller.dialog.quotation.QuotationDialogController;
import uz.diploma.controller.dialog.quotation.ReferenceDialogController;
import uz.diploma.model.record.element.Record;

/**
 * @author Spider
 * 
 * Class creates view for dialog window allowing to add quotation
 *
 */
public class AddQuotationDialog extends JDialog implements QuotationDialogView {
	
	private final JPanel contentPanel = new JPanel();
	private JTextField locationTextField;
	private EventListenerList listeners;
	private JLabel locationTextFieldLabel;
	private JLabel Quotelabel;
	private JTextArea quoteTextArea;
	private JButton addButton;
	private JButton cancelButton;

	/**
	 * Create the dialog.
	 */
	public AddQuotationDialog() {
		listeners = new EventListenerList();
		
		setTitle("Add Quotation");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 420, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "Add Quotation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{70, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		{
			locationTextFieldLabel = new JLabel("Location:");
			GridBagConstraints gbc_locationTextFieldLabel = new GridBagConstraints();
			gbc_locationTextFieldLabel.anchor = GridBagConstraints.EAST;
			gbc_locationTextFieldLabel.insets = new Insets(0, 0, 5, 5);
			gbc_locationTextFieldLabel.gridx = 0;
			gbc_locationTextFieldLabel.gridy = 0;
			contentPanel.add(locationTextFieldLabel, gbc_locationTextFieldLabel);
		}
		{
			locationTextField = new JTextField();
			GridBagConstraints gbc_locationTextField = new GridBagConstraints();
			gbc_locationTextField.insets = new Insets(0, 0, 5, 5);
			gbc_locationTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_locationTextField.gridx = 1;
			gbc_locationTextField.gridy = 0;
			contentPanel.add(locationTextField, gbc_locationTextField);
			locationTextField.setColumns(10);
		}
		{
			Quotelabel = new JLabel("Quote:");
			GridBagConstraints gbc_Quotelabel = new GridBagConstraints();
			gbc_Quotelabel.anchor = GridBagConstraints.WEST;
			gbc_Quotelabel.insets = new Insets(0, 0, 5, 5);
			gbc_Quotelabel.gridx = 1;
			gbc_Quotelabel.gridy = 1;
			contentPanel.add(Quotelabel, gbc_Quotelabel);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 1;
			gbc_scrollPane.gridy = 2;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{
				quoteTextArea = new JTextArea();
				quoteTextArea.setWrapStyleWord(true);
				quoteTextArea.setLineWrap(true);
				scrollPane.setViewportView(quoteTextArea);
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
	public void addDialogViewListener(QuotationDialogController listener) {
		if(listeners != null)
			listeners.add(QuotationDialogController.class, listener);
	}

	@Override
	public void removeDialogViewListener(QuotationDialogController listener) {
		listeners.remove(QuotationDialogController.class, listener);
	}

	@Override
	public void cancelButtonClicked() {
		for(QuotationDialogController listener : listeners.getListeners(QuotationDialogController.class)) {
			listener.onCancelButtonClicked();
		}
	}

	@Override
	public void addButtonClicked() {
		for(QuotationDialogController listener : listeners.getListeners(QuotationDialogController.class)) {
			listener.onAddButtonClicked();
		}
	}

	@Override
	public String getQuote() {
		return quoteTextArea.getText();
	}

	@Override
	public String getLocationText() {
		return locationTextField.getText();
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
