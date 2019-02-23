package uz.diploma.viewer.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.EventListenerList;

import uz.diploma.listeners.DialogViewListener;
import uz.diploma.model.record.element.HistoricalEvent;
import uz.diploma.model.record.element.RecordElement;

import javax.swing.UIManager;
import javax.swing.WindowConstants;

import java.awt.Color;

/**
 * @author Spider
 * 
 * Creates dialog window that enables to add tag on image
 *
 */
public class AddTagDialog extends JDialog implements AddTagDialogView {

	private final JPanel contentPanel = new JPanel();
	private EventListenerList listeners;
	private JPanel buttonPane;
	private JButton addButton;
	private JButton cancelButton;
	private JTextField nameTextField;
	private JLabel nameLabel;
	private JLabel descriptionLabel;
	private JTextArea descriptionTextArea;
	private JScrollPane scrollPane;

	/**
	 * Create the dialog.
	 */
	public AddTagDialog() {
		listeners = new EventListenerList();
		
		setTitle("Add event");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 400, 250);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{394, 0};
		gridBagLayout.rowHeights = new int[]{114, 33, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		contentPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Add Tag", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_contentPanel = new GridBagConstraints();
		gbc_contentPanel.fill = GridBagConstraints.BOTH;
		gbc_contentPanel.insets = new Insets(5, 5, 5, 5);
		gbc_contentPanel.gridx = 0;
		gbc_contentPanel.gridy = 0;
		getContentPane().add(contentPanel, gbc_contentPanel);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{46, 300, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{20, 0, 16, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
	
		{
			nameLabel = new JLabel("Name:");
			GridBagConstraints gbc_nameLabel = new GridBagConstraints();
			gbc_nameLabel.anchor = GridBagConstraints.EAST;
			gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
			gbc_nameLabel.gridx = 0;
			gbc_nameLabel.gridy = 0;
			contentPanel.add(nameLabel, gbc_nameLabel);
		}
		{
			nameTextField = new JTextField();
			nameTextField.addCaretListener(event -> textFieldChanged());
			GridBagConstraints gbc_nameTextField = new GridBagConstraints();
			gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_nameTextField.insets = new Insets(0, 0, 5, 5);
			gbc_nameTextField.gridx = 1;
			gbc_nameTextField.gridy = 0;
			contentPanel.add(nameTextField, gbc_nameTextField);
			nameTextField.setColumns(10);
		}
		{
			descriptionLabel = new JLabel("Description:");
			GridBagConstraints gbc_descriptionLabel = new GridBagConstraints();
			gbc_descriptionLabel.anchor = GridBagConstraints.NORTHWEST;
			gbc_descriptionLabel.insets = new Insets(0, 0, 5, 5);
			gbc_descriptionLabel.gridx = 1;
			gbc_descriptionLabel.gridy = 1;
			contentPanel.add(descriptionLabel, gbc_descriptionLabel);
		}
		{
			scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 1;
			gbc_scrollPane.gridy = 2;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{
				descriptionTextArea = new JTextArea();
				descriptionTextArea.addCaretListener(event -> textFieldChanged());
				descriptionTextArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
				scrollPane.setViewportView(descriptionTextArea);
				descriptionTextArea.setWrapStyleWord(true);
				descriptionTextArea.setLineWrap(true);
			}
		}
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			GridBagConstraints gbc_buttonPane = new GridBagConstraints();
			gbc_buttonPane.anchor = GridBagConstraints.NORTH;
			gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
			gbc_buttonPane.gridx = 0;
			gbc_buttonPane.gridy = 1;
			getContentPane().add(buttonPane, gbc_buttonPane);
			{
				addButton = new JButton("Add");
				addButton.addActionListener(event -> addButtonClicked());
				addButton.setMaximumSize(new Dimension(75, 23));
				addButton.setMinimumSize(new Dimension(75, 23));
				addButton.setPreferredSize(new Dimension(75, 23));
				addButton.setActionCommand("OK");
				buttonPane.add(addButton);
				getRootPane().setDefaultButton(addButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(event -> cancelButtonClicked());
				cancelButton.setMaximumSize(new Dimension(75, 23));
				cancelButton.setMinimumSize(new Dimension(75, 23));
				cancelButton.setPreferredSize(new Dimension(75, 23));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);		
	}

	@Override
	public void addTagDialogListener(TagDialogListener listener) {
		if(listeners != null)
			listeners.add(TagDialogListener.class, listener);
		
	}

	@Override
	public void removeTagDialogListener(TagDialogListener listener) {
		listeners.remove(TagDialogListener.class, listener);
		
	}
	
	@Override
	public void setAllTextFields(String name, String description) { 
		nameTextField.setText(name);
		descriptionTextArea.setText(description);
	}

	@Override
	public void textFieldChanged() {
		for(TagDialogListener listener : listeners.getListeners(TagDialogListener.class)) {
			listener.onTextFieldChanged();
		}
	}

	public String getNameFieldText() {
		return nameTextField.getText();
	}

	public String getDescriptionAreaText() {
		return descriptionTextArea.getText();
	}

	@Override
	public void cancelButtonClicked() {
		for(TagDialogListener listener : listeners.getListeners(TagDialogListener.class)) {
			listener.onCancelButtonClicked();
		}
	}

	@Override
	public void addButtonClicked() {
		for(TagDialogListener listener : listeners.getListeners(TagDialogListener.class)) {
			listener.onAddButtonClicked();
		}
	}

}
