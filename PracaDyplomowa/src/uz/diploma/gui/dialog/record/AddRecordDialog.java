package uz.diploma.gui.dialog.record;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.EventListenerList;

import uz.diploma.controller.dialog.record.AddRecordListener;
import uz.diploma.gui.window.WindowMenuListener;
import uz.diploma.gui.window.panel.details.contexttab.panel.ComboBoxRenderer;
import uz.diploma.listeners.DialogViewListener;
import uz.diploma.model.record.element.KeyWord;
import uz.diploma.model.record.element.MediumType;
import uz.diploma.model.record.element.RecordElement;
import uz.diploma.model.record.source.SourceType;

/**
 * @author Spider
 * 
 * Class creates view for dialog window allowing to add new record to project
 *
 */
public class AddRecordDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private EventListenerList listeners;
	private DefaultComboBoxModel<MediumType> selectMediumComboBoxModel;
	private JComboBox<MediumType> selectMediumComboBox;
	private JPanel buttonPane;
	private JButton addButton;
	private JButton cancelButton;
	private JLabel selectMediumLabel;
	private JTextField titleTextField;
	private JLabel titleLabel;
	private JLabel authorLabel;
	private JTextField authorTextField;
	private JLabel publicationDateLabel;
	private JTextField publicationDateTextField;

	/**
	 * Create the dialog.
	 */
	public AddRecordDialog() {
		listeners = new EventListenerList();
		
		setTitle("Add record");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 400, 200);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{394, 0};
		gridBagLayout.rowHeights = new int[]{114, 33, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		contentPanel.setBorder(new TitledBorder(null, "Add event", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_contentPanel = new GridBagConstraints();
		gbc_contentPanel.fill = GridBagConstraints.BOTH;
		gbc_contentPanel.insets = new Insets(5, 5, 5, 5);
		gbc_contentPanel.gridx = 0;
		gbc_contentPanel.gridy = 0;
		getContentPane().add(contentPanel, gbc_contentPanel);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{46, 220, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{20, 20, 0, 0, 16, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			selectMediumLabel = new JLabel("Select:");
			GridBagConstraints gbc_selectCharacterLabel = new GridBagConstraints();
			gbc_selectCharacterLabel.anchor = GridBagConstraints.EAST;
			gbc_selectCharacterLabel.insets = new Insets(0, 0, 5, 5);
			gbc_selectCharacterLabel.gridx = 0;
			gbc_selectCharacterLabel.gridy = 0;
			contentPanel.add(selectMediumLabel, gbc_selectCharacterLabel);
		}
		{
			selectMediumComboBox = new JComboBox<MediumType>();
			selectMediumComboBox.setEditable(false);
			GridBagConstraints gbc_selectCharacterComboBox = new GridBagConstraints();
			gbc_selectCharacterComboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_selectCharacterComboBox.insets = new Insets(0, 0, 5, 5);
			gbc_selectCharacterComboBox.gridx = 1;
			gbc_selectCharacterComboBox.gridy = 0;
			contentPanel.add(selectMediumComboBox, gbc_selectCharacterComboBox);
			selectMediumComboBoxModel = new DefaultComboBoxModel<MediumType>();
			selectMediumComboBox.setRenderer(new ComboBoxRenderer());
			selectMediumComboBox.setModel(selectMediumComboBoxModel);
		}
		{
			titleLabel = new JLabel("Title:");
			GridBagConstraints gbc_locationLabel = new GridBagConstraints();
			gbc_locationLabel.anchor = GridBagConstraints.EAST;
			gbc_locationLabel.insets = new Insets(0, 0, 5, 5);
			gbc_locationLabel.gridx = 0;
			gbc_locationLabel.gridy = 1;
			contentPanel.add(titleLabel, gbc_locationLabel);
		}
		{
			titleTextField = new JTextField();
			GridBagConstraints gbc_nameTextField = new GridBagConstraints();
			gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_nameTextField.insets = new Insets(0, 0, 5, 5);
			gbc_nameTextField.gridx = 1;
			gbc_nameTextField.gridy = 1;
			contentPanel.add(titleTextField, gbc_nameTextField);
			titleTextField.setColumns(10);
		}
		{
			authorLabel = new JLabel("Author:");
			GridBagConstraints gbc_authorLabel = new GridBagConstraints();
			gbc_authorLabel.anchor = GridBagConstraints.EAST;
			gbc_authorLabel.insets = new Insets(0, 0, 5, 5);
			gbc_authorLabel.gridx = 0;
			gbc_authorLabel.gridy = 2;
			contentPanel.add(authorLabel, gbc_authorLabel);
		}
		{
			authorTextField = new JTextField();
			GridBagConstraints gbc_authorTextField = new GridBagConstraints();
			gbc_authorTextField.insets = new Insets(0, 0, 5, 5);
			gbc_authorTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_authorTextField.gridx = 1;
			gbc_authorTextField.gridy = 2;
			contentPanel.add(authorTextField, gbc_authorTextField);
			authorTextField.setColumns(10);
		}
		{
			publicationDateLabel = new JLabel("Publication date: ");
			GridBagConstraints gbc_publicationDateLabel = new GridBagConstraints();
			gbc_publicationDateLabel.anchor = GridBagConstraints.EAST;
			gbc_publicationDateLabel.insets = new Insets(0, 0, 5, 5);
			gbc_publicationDateLabel.gridx = 0;
			gbc_publicationDateLabel.gridy = 3;
			contentPanel.add(publicationDateLabel, gbc_publicationDateLabel);
		}
		{
			publicationDateTextField = new JTextField();
			GridBagConstraints gbc_publicationDateTextField = new GridBagConstraints();
			gbc_publicationDateTextField.insets = new Insets(0, 0, 5, 5);
			gbc_publicationDateTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_publicationDateTextField.gridx = 1;
			gbc_publicationDateTextField.gridy = 3;
			contentPanel.add(publicationDateTextField, gbc_publicationDateTextField);
			publicationDateTextField.setColumns(10);
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

	public void setSelectionComboBox() {
		selectMediumComboBoxModel.removeAllElements();
		for(MediumType medium : MediumType.values()) {
			selectMediumComboBoxModel.addElement(medium);
		}
	}

	public void addListener(AddRecordListener listener) {
		if(listeners != null)
			listeners.add(AddRecordListener.class, listener);
		
	}

	public void removeListener(AddRecordListener listener) {
		listeners.remove(AddRecordListener.class, listener);
		
	}
	
	public void setSelectedMediumType() {
		selectMediumComboBox.setSelectedIndex(0);
	}

	public String getTitleFieldText() {
		return titleTextField.getText();
	}
	
	public String getAuthorFieldText() {
		return authorTextField.getText();
	}
	
	public String getPublicationFieldText() {
		return publicationDateTextField.getText();
	}
	
	public MediumType getMediumType() {
		return selectMediumComboBox.getItemAt(selectMediumComboBox.getSelectedIndex());
	}

	public void cancelButtonClicked() {
		for(AddRecordListener listener : listeners.getListeners(AddRecordListener.class)) {
			listener.onCancelButtonClicked();
		}
	}

	public void addButtonClicked() {
		for(AddRecordListener listener : listeners.getListeners(AddRecordListener.class)) {
			listener.onAddButtonClicked();
		}
	}
	
	public void showInformationDialog(String message) {
		JOptionPane.showMessageDialog(this, message, "Information Dialog", JOptionPane.INFORMATION_MESSAGE);
	}

}
