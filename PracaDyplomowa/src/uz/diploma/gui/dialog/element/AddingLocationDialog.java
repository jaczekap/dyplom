package uz.diploma.gui.dialog.element;

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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.EventListenerList;

import uz.diploma.gui.window.panel.details.contexttab.panel.ComboBoxRenderer;
import uz.diploma.listeners.DialogViewListener;
import uz.diploma.model.record.element.HistoricalEvent;
import uz.diploma.model.record.element.Location;
import uz.diploma.model.record.element.RecordElement;

/**
 * @author Spider
 * 
 * Class creates view for dialog window allowing to add geographical location
 *
 */
public class AddingLocationDialog extends JDialog implements AddingElementDialog {

	private final JPanel contentPanel = new JPanel();
	private EventListenerList listeners;
	private DefaultComboBoxModel<Location> selectLocationComboBoxModel;
	private JComboBox<Location> selectLocationComboBox;
	private JPanel buttonPane;
	private JButton addButton;
	private JButton cancelButton;
	private JLabel selectLocationtLabel;
	private JTextField locationTextField;
	private JLabel locationLabel;

	/**
	 * Create the dialog.
	 */
	public AddingLocationDialog() {
		listeners = new EventListenerList();
		
		setTitle("Add location");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 400, 150);
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
		gbl_contentPanel.columnWidths = new int[]{46, 300, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{20, 20, 16, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			selectLocationtLabel = new JLabel("Select:");
			GridBagConstraints gbc_selectCharacterLabel = new GridBagConstraints();
			gbc_selectCharacterLabel.anchor = GridBagConstraints.EAST;
			gbc_selectCharacterLabel.insets = new Insets(0, 0, 5, 5);
			gbc_selectCharacterLabel.gridx = 0;
			gbc_selectCharacterLabel.gridy = 0;
			contentPanel.add(selectLocationtLabel, gbc_selectCharacterLabel);
		}
		{
			selectLocationComboBox = new JComboBox<Location>();
			selectLocationComboBox.addActionListener(event -> comboBoxSelectionChanged(event));
			selectLocationComboBox.setEditable(false);
			GridBagConstraints gbc_selectCharacterComboBox = new GridBagConstraints();
			gbc_selectCharacterComboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_selectCharacterComboBox.insets = new Insets(0, 0, 5, 5);
			gbc_selectCharacterComboBox.gridx = 1;
			gbc_selectCharacterComboBox.gridy = 0;
			contentPanel.add(selectLocationComboBox, gbc_selectCharacterComboBox);
			selectLocationComboBoxModel = new DefaultComboBoxModel<Location>();
			selectLocationComboBox.setRenderer(new ComboBoxRenderer());
			selectLocationComboBox.setModel(selectLocationComboBoxModel);
		}
		{
			locationLabel = new JLabel("Location:");
			GridBagConstraints gbc_locationLabel = new GridBagConstraints();
			gbc_locationLabel.anchor = GridBagConstraints.EAST;
			gbc_locationLabel.insets = new Insets(0, 0, 5, 5);
			gbc_locationLabel.gridx = 0;
			gbc_locationLabel.gridy = 1;
			contentPanel.add(locationLabel, gbc_locationLabel);
		}
		{
			locationTextField = new JTextField();
			locationTextField.addCaretListener(event -> textFieldChanged());
			GridBagConstraints gbc_nameTextField = new GridBagConstraints();
			gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_nameTextField.insets = new Insets(0, 0, 5, 5);
			gbc_nameTextField.gridx = 1;
			gbc_nameTextField.gridy = 1;
			contentPanel.add(locationTextField, gbc_nameTextField);
			locationTextField.setColumns(10);
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
	public void setSelectionComboBox(List<RecordElement> locationsList) {
		selectLocationComboBoxModel.removeAllElements();
		locationsList.forEach(location -> selectLocationComboBoxModel.addElement((Location)location));
	}

	@Override
	public void comboBoxSelectionChanged(ActionEvent event) {
		for(DialogViewListener listener : listeners.getListeners(DialogViewListener.class)) {
			listener.onSelectionChanged(event);
		}
	}

	@Override
	public void addDialogViewListener(DialogViewListener listener) {
		if(listeners != null)
			listeners.add(DialogViewListener.class, listener);
		
	}

	@Override
	public void removeDialogViewListener(DialogViewListener listener) {
		listeners.remove(DialogViewListener.class, listener);
		
	}
	
	@Override
	public void setAllTextFields() {
		Location currentLocation = selectLocationComboBoxModel.getElementAt(selectLocationComboBox.getSelectedIndex());
		locationTextField.setText(currentLocation.getLocation());
	}

	@Override
	public void textFieldChanged() {
		for(DialogViewListener listener : listeners.getListeners(DialogViewListener.class)) {
			listener.onTextFieldChanged();
		}
	}

	public String getLocationFieldText() {
		return locationTextField.getText();
	}

	@Override
	public RecordElement getElement() {
		return selectLocationComboBoxModel.getElementAt(selectLocationComboBox.getSelectedIndex());
	}

	@Override
	public void cancelButtonClicked() {
		for(DialogViewListener listener : listeners.getListeners(DialogViewListener.class)) {
			listener.onCancelButtonClicked();
		}
	}

	@Override
	public void addButtonClicked() {
		for(DialogViewListener listener : listeners.getListeners(DialogViewListener.class)) {
			listener.onAddButtonClicked();
		}
	}

	@Override
	public int showQuestionDialog(String question) {
		return JOptionPane.showConfirmDialog(this, question, "Confirmation Dialog", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	}

	@Override
	public boolean positiveAnswerToQuestion(String question) {
		if(showQuestionDialog(question) == JOptionPane.OK_OPTION)
			return true;
		return false;
	}

	@Override
	public void showInformationDialog(String message) {
		JOptionPane.showMessageDialog(this, message, "Information Dialog", JOptionPane.INFORMATION_MESSAGE);
	}
}
