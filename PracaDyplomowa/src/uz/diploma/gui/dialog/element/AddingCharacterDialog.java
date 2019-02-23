package uz.diploma.gui.dialog.element;

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
import javax.swing.border.TitledBorder;
import javax.swing.event.EventListenerList;

import uz.diploma.gui.window.panel.details.contexttab.panel.ComboBoxRenderer;
import uz.diploma.listeners.DialogViewListener;
import uz.diploma.model.record.element.HistoricalCharacter;
import uz.diploma.model.record.element.HistoricalEvent;
import uz.diploma.model.record.element.HistoricalPeriod;
import uz.diploma.model.record.element.RecordElement;

import javax.swing.UIManager;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.awt.event.ActionEvent;

/**
 * @author Spider
 * 
 * Class creates view for dialog window allowing to add historical character 
 *
 */
public class AddingCharacterDialog extends JDialog implements AddingElementDialog {
	
	private final JPanel contentPanel = new JPanel();
	private EventListenerList listeners;
	private DefaultComboBoxModel<HistoricalCharacter> selectCharacterComboBoxModel;
	private JComboBox<HistoricalCharacter> selectCharacterComboBox;
	private JPanel buttonPane;
	private JButton addButton;
	private JButton cancelButton;
	private JLabel selectCharacterLabel;
	private JTextField nameTextField;
	private JLabel periodLabel;
	private JLabel nameLabel;
	private JLabel descriptionLabel;
	private JTextArea descriptionTextArea;
	private JScrollPane scrollPane;
	private JComboBox<HistoricalPeriod> periodComboBox;
	private DefaultComboBoxModel<HistoricalPeriod> periodComboBoxModel;
	
	public final static String PERIOD_COMBOBOX_ACTION = "period";
	public final static String SELECT_COMBOBOX_ACTION = "select";

	/**
	 * Create the dialog.
	 */
	public AddingCharacterDialog() {
		listeners = new EventListenerList();
		
		setTitle("Add character");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 395, 250);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{414, 0};
		gridBagLayout.rowHeights = new int[]{114, 33, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		contentPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Add character", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_contentPanel = new GridBagConstraints();
		gbc_contentPanel.fill = GridBagConstraints.BOTH;
		gbc_contentPanel.insets = new Insets(5, 5, 5, 5);
		gbc_contentPanel.gridx = 0;
		gbc_contentPanel.gridy = 0;
		getContentPane().add(contentPanel, gbc_contentPanel);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{46, 300, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{20, 20, 20, 0, 16, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			selectCharacterLabel = new JLabel("Select:");
			GridBagConstraints gbc_selectCharacterLabel = new GridBagConstraints();
			gbc_selectCharacterLabel.anchor = GridBagConstraints.EAST;
			gbc_selectCharacterLabel.insets = new Insets(0, 0, 5, 5);
			gbc_selectCharacterLabel.gridx = 0;
			gbc_selectCharacterLabel.gridy = 0;
			contentPanel.add(selectCharacterLabel, gbc_selectCharacterLabel);
		}
		{
			selectCharacterComboBox = new JComboBox<HistoricalCharacter>();
			selectCharacterComboBox.setActionCommand(SELECT_COMBOBOX_ACTION);
			selectCharacterComboBox.addActionListener(event -> comboBoxSelectionChanged(event));
			selectCharacterComboBox.setEditable(false);
			GridBagConstraints gbc_selectCharacterComboBox = new GridBagConstraints();
			gbc_selectCharacterComboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_selectCharacterComboBox.insets = new Insets(0, 0, 5, 5);
			gbc_selectCharacterComboBox.gridx = 1;
			gbc_selectCharacterComboBox.gridy = 0;
			contentPanel.add(selectCharacterComboBox, gbc_selectCharacterComboBox);
			selectCharacterComboBoxModel = new DefaultComboBoxModel<HistoricalCharacter>();
			selectCharacterComboBox.setRenderer(new ComboBoxRenderer());
			selectCharacterComboBox.setModel(selectCharacterComboBoxModel);
		}
		{
			periodLabel = new JLabel("Period:");
			GridBagConstraints gbc_periodLabel = new GridBagConstraints();
			gbc_periodLabel.anchor = GridBagConstraints.EAST;
			gbc_periodLabel.insets = new Insets(0, 0, 5, 5);
			gbc_periodLabel.gridx = 0;
			gbc_periodLabel.gridy = 1;
			contentPanel.add(periodLabel, gbc_periodLabel);
		}
		{
			periodComboBox = new JComboBox<HistoricalPeriod>();
			periodComboBox.setActionCommand(PERIOD_COMBOBOX_ACTION);
			periodComboBox.addActionListener(event -> comboBoxSelectionChanged(event));
			GridBagConstraints gbc_periodComboBox = new GridBagConstraints();
			gbc_periodComboBox.insets = new Insets(0, 0, 5, 5);
			gbc_periodComboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_periodComboBox.gridx = 1;
			gbc_periodComboBox.gridy = 1;
			contentPanel.add(periodComboBox, gbc_periodComboBox);
			periodComboBoxModel = new DefaultComboBoxModel<HistoricalPeriod>();
			periodComboBox.setRenderer(new ComboBoxRenderer());
			periodComboBox.setModel(periodComboBoxModel);
		}
		{
			nameLabel = new JLabel("Name:");
			GridBagConstraints gbc_nameLabel = new GridBagConstraints();
			gbc_nameLabel.anchor = GridBagConstraints.EAST;
			gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
			gbc_nameLabel.gridx = 0;
			gbc_nameLabel.gridy = 2;
			contentPanel.add(nameLabel, gbc_nameLabel);
		}
		{
			nameTextField = new JTextField();
			nameTextField.addCaretListener(event -> textFieldChanged());
			GridBagConstraints gbc_nameTextField = new GridBagConstraints();
			gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_nameTextField.insets = new Insets(0, 0, 5, 5);
			gbc_nameTextField.gridx = 1;
			gbc_nameTextField.gridy = 2;
			contentPanel.add(nameTextField, gbc_nameTextField);
			nameTextField.setColumns(10);
		}
		{
			descriptionLabel = new JLabel("Description:");
			GridBagConstraints gbc_descriptionLabel = new GridBagConstraints();
			gbc_descriptionLabel.anchor = GridBagConstraints.NORTHWEST;
			gbc_descriptionLabel.insets = new Insets(0, 0, 5, 5);
			gbc_descriptionLabel.gridx = 1;
			gbc_descriptionLabel.gridy = 3;
			contentPanel.add(descriptionLabel, gbc_descriptionLabel);
		}
		{
			scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 1;
			gbc_scrollPane.gridy = 4;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{
				descriptionTextArea = new JTextArea();
				descriptionTextArea.addCaretListener(event -> textFieldChanged());
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
	public void setSelectionComboBox(List<RecordElement> charactersList) {
		selectCharacterComboBoxModel.removeAllElements();
		charactersList.forEach(character -> selectCharacterComboBoxModel.addElement((HistoricalCharacter)character));
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
		HistoricalCharacter currentCharacter = (HistoricalCharacter)getElement();
		setPeriodComboBox(currentCharacter);
		nameTextField.setText(currentCharacter.getName());
		descriptionTextArea.setText(currentCharacter.getDescription());
	}

	@Override
	public void textFieldChanged() {
		for(DialogViewListener listener : listeners.getListeners(DialogViewListener.class)) {
			listener.onTextFieldChanged();
		}
	}
	
	public String getNameFieldText() {
		return nameTextField.getText();
	}

	public HistoricalPeriod getSelectedPeriod() {
		return periodComboBoxModel.getElementAt(periodComboBox.getSelectedIndex());
	}

	public String getDescriptionAreaText() {
		return descriptionTextArea.getText();
	}
	
	private void setPeriodComboBox(HistoricalCharacter character) {
		periodComboBoxModel.removeAllElements();
		for(HistoricalPeriod period : HistoricalPeriod.values()) {
			periodComboBoxModel.addElement(period);
		}
		periodComboBox.setSelectedItem(character.getHistoricalPeriod());
	}

	@Override
	public RecordElement getElement() {
		return selectCharacterComboBoxModel.getElementAt(selectCharacterComboBox.getSelectedIndex());
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
	public boolean positiveAnswerToQuestion(String question) {
		if(showQuestionDialog(question) == JOptionPane.OK_OPTION)
			return true;
		return false;
	}

	@Override
	public int showQuestionDialog(String question) {
		return JOptionPane.showConfirmDialog(this, question, "Confirmation Dialog", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	}

	@Override
	public void showInformationDialog(String message) {
		JOptionPane.showMessageDialog(this, message, "Information Dialog", JOptionPane.INFORMATION_MESSAGE);
	}

}
