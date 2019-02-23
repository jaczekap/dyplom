package uz.diploma.gui.dialog.element;

import java.awt.Color;
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
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.EventListenerList;

import uz.diploma.gui.window.panel.details.contexttab.panel.ComboBoxRenderer;
import uz.diploma.listeners.DialogViewListener;
import uz.diploma.model.record.element.HistoricalCharacter;
import uz.diploma.model.record.element.HistoricalEvent;
import uz.diploma.model.record.element.HistoricalPeriod;
import uz.diploma.model.record.element.KeyWord;
import uz.diploma.model.record.element.Location;
import uz.diploma.model.record.element.RecordElement;

/**
 * @author Spider
 * 
 * Class creates view for dialog window allowing to add keyword
 *
 */
public class AddingKeyWordDialog extends JDialog implements AddingElementDialog {

	private final JPanel contentPanel = new JPanel();
	private EventListenerList listeners;
	private DefaultComboBoxModel<KeyWord> selectKeyWordComboBoxModel;
	private JComboBox<KeyWord> selectKeyWordComboBox;
	private JPanel buttonPane;
	private JButton addButton;
	private JButton cancelButton;
	private JLabel selectKeyWordLabel;
	private JTextField keyWordTextField;
	private JLabel keyWordLabel;

	/**
	 * Create the dialog.
	 */
	public AddingKeyWordDialog() {
		listeners = new EventListenerList();
		
		setTitle("Add keyword");
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
			selectKeyWordLabel = new JLabel("Select:");
			GridBagConstraints gbc_selectCharacterLabel = new GridBagConstraints();
			gbc_selectCharacterLabel.anchor = GridBagConstraints.EAST;
			gbc_selectCharacterLabel.insets = new Insets(0, 0, 5, 5);
			gbc_selectCharacterLabel.gridx = 0;
			gbc_selectCharacterLabel.gridy = 0;
			contentPanel.add(selectKeyWordLabel, gbc_selectCharacterLabel);
		}
		{
			selectKeyWordComboBox = new JComboBox<KeyWord>();
			selectKeyWordComboBox.addActionListener(event -> comboBoxSelectionChanged(event));
			selectKeyWordComboBox.setEditable(false);
			GridBagConstraints gbc_selectCharacterComboBox = new GridBagConstraints();
			gbc_selectCharacterComboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_selectCharacterComboBox.insets = new Insets(0, 0, 5, 5);
			gbc_selectCharacterComboBox.gridx = 1;
			gbc_selectCharacterComboBox.gridy = 0;
			contentPanel.add(selectKeyWordComboBox, gbc_selectCharacterComboBox);
			selectKeyWordComboBoxModel = new DefaultComboBoxModel<KeyWord>();
			selectKeyWordComboBox.setRenderer(new ComboBoxRenderer());
			selectKeyWordComboBox.setModel(selectKeyWordComboBoxModel);
		}
		{
			keyWordLabel = new JLabel("Location:");
			GridBagConstraints gbc_locationLabel = new GridBagConstraints();
			gbc_locationLabel.anchor = GridBagConstraints.EAST;
			gbc_locationLabel.insets = new Insets(0, 0, 5, 5);
			gbc_locationLabel.gridx = 0;
			gbc_locationLabel.gridy = 1;
			contentPanel.add(keyWordLabel, gbc_locationLabel);
		}
		{
			keyWordTextField = new JTextField();
			keyWordTextField.addCaretListener(event -> textFieldChanged());
			GridBagConstraints gbc_nameTextField = new GridBagConstraints();
			gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_nameTextField.insets = new Insets(0, 0, 5, 5);
			gbc_nameTextField.gridx = 1;
			gbc_nameTextField.gridy = 1;
			contentPanel.add(keyWordTextField, gbc_nameTextField);
			keyWordTextField.setColumns(10);
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
	public void setSelectionComboBox(List<RecordElement> keyWordsList) {
		selectKeyWordComboBoxModel.removeAllElements();
		keyWordsList.forEach(keyWord -> selectKeyWordComboBoxModel.addElement((KeyWord)keyWord));
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
		KeyWord keyWord = selectKeyWordComboBoxModel.getElementAt(selectKeyWordComboBox.getSelectedIndex());
		keyWordTextField.setText(keyWord.getKeyWord());
	}

	@Override
	public void textFieldChanged() {
		for(DialogViewListener listener : listeners.getListeners(DialogViewListener.class)) {
			listener.onTextFieldChanged();
		}
	}

	public String getKeyWordFieldText() {
		return keyWordTextField.getText();
	}

	@Override
	public RecordElement getElement() {
		return selectKeyWordComboBoxModel.getElementAt(selectKeyWordComboBox.getSelectedIndex());
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
