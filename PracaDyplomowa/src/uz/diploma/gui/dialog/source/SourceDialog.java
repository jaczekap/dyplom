package uz.diploma.gui.dialog.source;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.border.TitledBorder;
import javax.swing.event.EventListenerList;
import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JComboBox;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.nio.channels.Pipe.SourceChannel;
import java.util.List;

import javax.swing.JTextField;

import uz.diploma.controller.dialog.source.SourceDialogViewController;
import uz.diploma.gui.window.panel.details.contexttab.panel.ComboBoxRenderer;
import uz.diploma.gui.window.panel.details.contexttab.panel.RecordElementListRenderer;
import uz.diploma.listeners.DialogViewListener;
import uz.diploma.model.record.element.Location;
import uz.diploma.model.record.element.RecordElement;
import uz.diploma.model.record.source.SourceLocation;
import uz.diploma.model.record.source.SourceType;

import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.JToolBar;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

/**
 * @author Spider
 * 
 * Class creates view for dialog window allowing to add location of the source
 *
 */
public class SourceDialog extends JDialog implements SourceDialogView {

	private final JPanel contentPanel = new JPanel();
	private EventListenerList listeners;
	private JButton addSourceButton;
	private JButton cancelButton;
	private JPanel buttonPane;
	private JTextField pathTextField;
	private DefaultComboBoxModel<SourceLocation> sourceLocationComboBoxModel;
	private JLabel pathLabel;
	private JRadioButton fileRadioButton;
	private JLabel typeLabel;
	private JRadioButton internetRadioButton;
	private JRadioButton externalRadioButton;
	private ButtonGroup sourceButtonGroup;
	private JButton selectSourceButton;
	
	public final static String INTERNET_BUTTON_ACTION = "internet";
	public final static String FILE_BUTTON_ACTION = "file";
	public final static String EXTERNAL_BUTTON_ACTION = "external";

	/**
	 * Create the dialog.
	 */
	public SourceDialog() {
		setResizable(false);
		listeners = new EventListenerList();
		
		setModal(true);
		setBounds(100, 100, 410, 158);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{379, 0};
		gridBagLayout.rowHeights = new int[]{0, 33, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		contentPanel.setBorder(new TitledBorder(null, "Source", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_contentPanel = new GridBagConstraints();
		gbc_contentPanel.fill = GridBagConstraints.BOTH;
		gbc_contentPanel.insets = new Insets(5, 5, 5, 5);
		gbc_contentPanel.gridx = 0;
		gbc_contentPanel.gridy = 0;
		getContentPane().add(contentPanel, gbc_contentPanel);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{50, 70, 70, 110, 75, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			typeLabel = new JLabel("Type:");
			GridBagConstraints gbc_typeLabel = new GridBagConstraints();
			gbc_typeLabel.anchor = GridBagConstraints.EAST;
			gbc_typeLabel.insets = new Insets(0, 0, 5, 5);
			gbc_typeLabel.gridx = 0;
			gbc_typeLabel.gridy = 0;
			contentPanel.add(typeLabel, gbc_typeLabel);
		}
		{
			externalRadioButton = new JRadioButton("External");
			externalRadioButton.setActionCommand(EXTERNAL_BUTTON_ACTION);
			GridBagConstraints gbc_rdbtnExternal = new GridBagConstraints();
			gbc_rdbtnExternal.anchor = GridBagConstraints.WEST;
			gbc_rdbtnExternal.insets = new Insets(0, 0, 5, 5);
			gbc_rdbtnExternal.gridx = 1;
			gbc_rdbtnExternal.gridy = 0;
			contentPanel.add(externalRadioButton, gbc_rdbtnExternal);
		}
		{
			internetRadioButton = new JRadioButton("Internet");
			internetRadioButton.setActionCommand(INTERNET_BUTTON_ACTION);
			GridBagConstraints gbc_rdbtnInternet = new GridBagConstraints();
			gbc_rdbtnInternet.anchor = GridBagConstraints.WEST;
			gbc_rdbtnInternet.insets = new Insets(0, 0, 5, 5);
			gbc_rdbtnInternet.gridx = 2;
			gbc_rdbtnInternet.gridy = 0;
			contentPanel.add(internetRadioButton, gbc_rdbtnInternet);
		}
		{
			fileRadioButton = new JRadioButton("File");
			fileRadioButton.setActionCommand(FILE_BUTTON_ACTION);
			GridBagConstraints gbc_rdbtnFile = new GridBagConstraints();
			gbc_rdbtnFile.anchor = GridBagConstraints.WEST;
			gbc_rdbtnFile.insets = new Insets(0, 0, 5, 5);
			gbc_rdbtnFile.gridx = 3;
			gbc_rdbtnFile.gridy = 0;
			contentPanel.add(fileRadioButton, gbc_rdbtnFile);
		}
		
		sourceButtonGroup = new ButtonGroup();
		sourceButtonGroup.add(externalRadioButton);
		sourceButtonGroup.add(internetRadioButton);
		sourceButtonGroup.add(fileRadioButton);
		{
			sourceLocationComboBoxModel = new DefaultComboBoxModel<SourceLocation>();
		}
		{
			pathLabel = new JLabel("Name:");
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.anchor = GridBagConstraints.EAST;
			gbc_lblName.insets = new Insets(0, 0, 0, 5);
			gbc_lblName.gridx = 0;
			gbc_lblName.gridy = 1;
			contentPanel.add(pathLabel, gbc_lblName);
		}
		{
			pathTextField = new JTextField();
			pathTextField.setMaximumSize(new Dimension(210, 2147483647));
			pathTextField.setPreferredSize(new Dimension(210, 20));
			pathTextField.setMinimumSize(new Dimension(210, 20));
			pathTextField.addCaretListener(event -> textFieldChanged());
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridwidth = 3;
			gbc_textField.insets = new Insets(0, 0, 0, 5);
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 1;
			contentPanel.add(pathTextField, gbc_textField);
			pathTextField.setColumns(10);
		}
		{
			selectSourceButton = new JButton("Choose");
			selectSourceButton.addActionListener(event -> selectSourceButtonClicked());
			selectSourceButton.setPreferredSize(new Dimension(75, 23));
			selectSourceButton.setMinimumSize(new Dimension(75, 23));
			selectSourceButton.setMaximumSize(new Dimension(75, 23));
			GridBagConstraints gbc_selectSourceButton = new GridBagConstraints();
			gbc_selectSourceButton.insets = new Insets(0, 0, 0, 5);
			gbc_selectSourceButton.gridx = 4;
			gbc_selectSourceButton.gridy = 1;
			contentPanel.add(selectSourceButton, gbc_selectSourceButton);
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
				addSourceButton = new JButton("Add");
				addSourceButton.addActionListener(event -> addButtonClicked());
				addSourceButton.setPreferredSize(new Dimension(75, 23));
				addSourceButton.setMinimumSize(new Dimension(75, 23));
				addSourceButton.setMaximumSize(new Dimension(75, 23));
				buttonPane.add(addSourceButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(event -> cancelButtonClicked());
				cancelButton.setPreferredSize(new Dimension(75, 23));
				cancelButton.setMinimumSize(new Dimension(75, 23));
				cancelButton.setMaximumSize(new Dimension(75, 23));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	@Override
	public void setSelectionComboBox(List<RecordElement> sourceList) {
		sourceLocationComboBoxModel.removeAllElements();
		sourceList.forEach(source -> sourceLocationComboBoxModel.addElement((SourceLocation)source));
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
//		SourceLocation currentLocation = sourceLocationComboBoxModel.getElementAt(sourceLocationComboBox.getSelectedIndex());
//		if(currentLocation != null) {
//		setSourceType(currentLocation.getSourceType());
//		pathTextField.setText(currentLocation.getSource());
//		} else {
//			sourceButtonGroup.clearSelection();
//			pathTextField.setText("");
//		}
	}

	@Override
	public void textFieldChanged() {
		for(DialogViewListener listener : listeners.getListeners(DialogViewListener.class)) {
			listener.onTextFieldChanged();
		}
	}

	public String getSourceFieldText() {
		return pathTextField.getText();
	}

	@Override
	public RecordElement getElement() {
		//return sourceLocationComboBoxModel.getElementAt(sourceLocationComboBox.getSelectedIndex());
		return null;
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
	public void removeButtonClicked() {
		for(DialogViewListener listener : listeners.getListeners(DialogViewListener.class)) {
			((SourceDialogViewController)listener).onRemoveButtonClicked();
		}
	}

	@Override
	public void setSourceType(SourceType currentType) {
		if(currentType == SourceType.EXTERNAL)
			externalRadioButton.setSelected(true);
		else if(currentType == SourceType.INTERNET)
			internetRadioButton.setSelected(true);
		else
			fileRadioButton.setSelected(true);
	}

	public String getSelectedRadioButtonActionCommand() {
		if(sourceButtonGroup.getSelection() != null)
			return sourceButtonGroup.getSelection().getActionCommand();
		return null;
	}

	@Override
	public void selectSourceButtonClicked() {
		for(DialogViewListener listener : listeners.getListeners(DialogViewListener.class)) {
			((SourceDialogViewController)listener).onSelectSourceButtonClicked();
		}
	}
	
	public void setPathTextField(String textToSet) {
		pathTextField.setText(textToSet);
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
