package uz.diploma.gui.window.panel.details.overviewtab;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.EventListener;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.event.EventListenerList;

import uz.diploma.controller.overview.OverviewTabController;
import uz.diploma.controller.overview.TableOfContentsPanelController;
import uz.diploma.gui.window.panel.details.contexttab.panel.ComboBoxRenderer;
import uz.diploma.gui.window.panel.details.contexttab.panel.RecordElementListRenderer;
import uz.diploma.listeners.OverviewTabListener;
import uz.diploma.model.record.element.HistoricalPeriod;
import uz.diploma.model.record.element.MediumType;
import uz.diploma.model.record.element.Record;
import uz.diploma.model.record.element.RecordElement;
import uz.diploma.model.record.source.SourceLocation;
import uz.diploma.tools.Validator;

import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Dimension;

import javax.swing.SwingConstants;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * @author Spider
 * 
 * OverviewTab is concrete view class - one of the tab that is part of the right panel of the main application window
 *
 */
public class OverviewTab extends JPanel implements OverviewTabInterface {
	private EventListenerList listeners;
	private JTextField titleTextField;
	private JTextField authorTextField;
	private JTextField publicationDateTextField;
	private JTextField publishedTextField;
	private JLabel titleLabel;
	private JLabel publicationDateLabel;
	private JLabel authorLabel;
	private JLabel publishedLabel;
	private JLabel mediumTypeLabel;
	private SummaryPanelInterface overviewSummary;
	private TableOfContentsInterface overviewTableOfContents;
	private JComboBox<MediumType> mediumTypeComboBox;
	private DefaultComboBoxModel<MediumType> mediumTypeComboBoxModel;
	private JLabel previewLabel;
	private JLabel sourceLabel;
	private JComboBox<SourceLocation> sourceComboBox;
	private DefaultComboBoxModel<SourceLocation> sourceComboBoxModel;
	private JButton openButton;
	private JButton editBatton;
	private JLabel sourceTypeLabel;
	private JButton removeSourceButton;

	/**
	 * Create the panel.
	 */
	public OverviewTab(SummaryPanelInterface summary, TableOfContentsInterface tableOfContentsPanel) {
		Validator.ensureIsNotNull(summary, "summary");
		Validator.ensureIsNotNull(tableOfContentsPanel, "tableOfContents");
		
		listeners = new EventListenerList();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 100, 0, 0, 0, 300, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		titleLabel = new JLabel("Title:");
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.anchor = GridBagConstraints.EAST;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.gridx = 1;
		gbc_titleLabel.gridy = 0;
		add(titleLabel, gbc_titleLabel);
		
		titleTextField = new JTextField();
		titleTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				titleChanged(titleTextField.getText());
			}
		});
		GridBagConstraints gbc_titleTextField = new GridBagConstraints();
		gbc_titleTextField.gridwidth = 2;
		gbc_titleTextField.insets = new Insets(0, 0, 5, 5);
		gbc_titleTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleTextField.gridx = 2;
		gbc_titleTextField.gridy = 0;
		add(titleTextField, gbc_titleTextField);
		titleTextField.setColumns(10);
		
		previewLabel = new JLabel("");
		previewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		//previewLabel.setIcon(new ImageIcon(OverviewTab.class.getResource("/com/sun/javafx/scene/control/skin/caspian/images/vk-dark-pressed.png")));
		GridBagConstraints gbc_previewLabel = new GridBagConstraints();
		gbc_previewLabel.gridwidth = 2;
		gbc_previewLabel.fill = GridBagConstraints.BOTH;
		gbc_previewLabel.gridheight = 5;
		gbc_previewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_previewLabel.gridx = 4;
		gbc_previewLabel.gridy = 0;
		add(previewLabel, gbc_previewLabel);
		
		authorLabel = new JLabel("Author:");
		GridBagConstraints gbc_authorLabel = new GridBagConstraints();
		gbc_authorLabel.anchor = GridBagConstraints.EAST;
		gbc_authorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_authorLabel.gridx = 1;
		gbc_authorLabel.gridy = 1;
		add(authorLabel, gbc_authorLabel);
		
		authorTextField = new JTextField();
		authorTextField.addCaretListener(event -> authorChanged(authorTextField.getText()));
		GridBagConstraints gbc_authorTextField = new GridBagConstraints();
		gbc_authorTextField.gridwidth = 2;
		gbc_authorTextField.insets = new Insets(0, 0, 5, 5);
		gbc_authorTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_authorTextField.gridx = 2;
		gbc_authorTextField.gridy = 1;
		add(authorTextField, gbc_authorTextField);
		authorTextField.setColumns(10);
		
		mediumTypeLabel = new JLabel("Type:");
		GridBagConstraints gbc_mediumTypeLabel = new GridBagConstraints();
		gbc_mediumTypeLabel.anchor = GridBagConstraints.EAST;
		gbc_mediumTypeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_mediumTypeLabel.gridx = 1;
		gbc_mediumTypeLabel.gridy = 2;
		add(mediumTypeLabel, gbc_mediumTypeLabel);
		
		this.overviewSummary = summary;
		GridBagConstraints gbc_overviewSummary = new GridBagConstraints();
		gbc_overviewSummary.gridwidth = 6;
		gbc_overviewSummary.insets = new Insets(0, 0, 5, 5);
		gbc_overviewSummary.fill = GridBagConstraints.BOTH;
		gbc_overviewSummary.gridx = 1;
		gbc_overviewSummary.gridy = 6;
		add((OverviewSummary)overviewSummary, gbc_overviewSummary);
		
		this.overviewTableOfContents = tableOfContentsPanel;
		GridBagConstraints gbc_overviewTableOfContents = new GridBagConstraints();
		gbc_overviewTableOfContents.gridwidth = 6;
		gbc_overviewTableOfContents.insets = new Insets(0, 0, 0, 5);
		gbc_overviewTableOfContents.fill = GridBagConstraints.BOTH;
		gbc_overviewTableOfContents.gridx = 1;
		gbc_overviewTableOfContents.gridy = 7;
		add((OverviewTableOfContents)overviewTableOfContents, gbc_overviewTableOfContents);
		
		mediumTypeComboBox = new JComboBox<MediumType>();
		mediumTypeComboBox.addActionListener(event -> mediumChanged((MediumType)mediumTypeComboBox.getSelectedItem()));
		mediumTypeComboBoxModel = new DefaultComboBoxModel<MediumType>();
		mediumTypeComboBox.setModel(mediumTypeComboBoxModel);
		mediumTypeComboBox.setRenderer(new ComboBoxRenderer());
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 2;
		add(mediumTypeComboBox, gbc_comboBox);
		
		publicationDateLabel = new JLabel("Publcation date:");
		GridBagConstraints gbc_publicationDateLabel = new GridBagConstraints();
		gbc_publicationDateLabel.anchor = GridBagConstraints.EAST;
		gbc_publicationDateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_publicationDateLabel.gridx = 1;
		gbc_publicationDateLabel.gridy = 3;
		add(publicationDateLabel, gbc_publicationDateLabel);
		
		publicationDateTextField = new JTextField();
		publicationDateTextField.addCaretListener(event -> publicationDateChanged(publicationDateTextField.getText()));
		GridBagConstraints gbc_publicationDateTextField = new GridBagConstraints();
		gbc_publicationDateTextField.gridwidth = 2;
		gbc_publicationDateTextField.insets = new Insets(0, 0, 5, 5);
		gbc_publicationDateTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_publicationDateTextField.gridx = 2;
		gbc_publicationDateTextField.gridy = 3;
		add(publicationDateTextField, gbc_publicationDateTextField);
		publicationDateTextField.setColumns(10);
		
		publishedLabel = new JLabel("Published:");
		GridBagConstraints gbc_publishedLabel = new GridBagConstraints();
		gbc_publishedLabel.anchor = GridBagConstraints.EAST;
		gbc_publishedLabel.insets = new Insets(0, 0, 5, 5);
		gbc_publishedLabel.gridx = 1;
		gbc_publishedLabel.gridy = 4;
		add(publishedLabel, gbc_publishedLabel);
		
		publishedTextField = new JTextField();
		publishedTextField.addCaretListener(event -> publishedChanged(publishedTextField.getText()));
		GridBagConstraints gbc_publishedTextField = new GridBagConstraints();
		gbc_publishedTextField.gridwidth = 2;
		gbc_publishedTextField.insets = new Insets(0, 0, 5, 5);
		gbc_publishedTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_publishedTextField.gridx = 2;
		gbc_publishedTextField.gridy = 4;
		add(publishedTextField, gbc_publishedTextField);
		publishedTextField.setColumns(10);
		
		sourceLabel = new JLabel("Source location:");
		GridBagConstraints gbc_sourceLabel = new GridBagConstraints();
		gbc_sourceLabel.anchor = GridBagConstraints.EAST;
		gbc_sourceLabel.insets = new Insets(0, 0, 5, 5);
		gbc_sourceLabel.gridx = 1;
		gbc_sourceLabel.gridy = 5;
		add(sourceLabel, gbc_sourceLabel);
		
		sourceComboBox = new JComboBox<SourceLocation>();
		sourceComboBox.setMaximumSize(new Dimension(235, 32767));
		sourceComboBox.setPreferredSize(new Dimension(235, 20));
		sourceComboBoxModel = new DefaultComboBoxModel<SourceLocation>();
		sourceComboBox.addActionListener(event -> sourceComboBoxChanged(getCurrentLocation()));
		sourceComboBox.setModel(sourceComboBoxModel);
		sourceComboBox.setRenderer(new ComboBoxRenderer());
		GridBagConstraints gbc_sourceComboBox = new GridBagConstraints();
		gbc_sourceComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_sourceComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_sourceComboBox.gridx = 2;
		gbc_sourceComboBox.gridy = 5;
		add(sourceComboBox, gbc_sourceComboBox);
		
		sourceTypeLabel = new JLabel("external");
		sourceTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sourceTypeLabel.setPreferredSize(new Dimension(75, 23));
		sourceTypeLabel.setMaximumSize(new Dimension(75, 0));
		sourceTypeLabel.setMinimumSize(new Dimension(75, 0));
		GridBagConstraints gbc_sourceTypeLabel = new GridBagConstraints();
		gbc_sourceTypeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_sourceTypeLabel.gridx = 3;
		gbc_sourceTypeLabel.gridy = 5;
		add(sourceTypeLabel, gbc_sourceTypeLabel);
		
		openButton = new JButton("Open");
		openButton.addActionListener(event -> openButtonClicked(getCurrentLocation()));
		openButton.setPreferredSize(new Dimension(75, 23));
		openButton.setMinimumSize(new Dimension(75, 23));
		openButton.setMaximumSize(new Dimension(75, 23));
		GridBagConstraints gbc_openButton = new GridBagConstraints();
		gbc_openButton.insets = new Insets(0, 0, 5, 5);
		gbc_openButton.gridx = 4;
		gbc_openButton.gridy = 5;
		add(openButton, gbc_openButton);
		
		editBatton = new JButton("Add");
		editBatton.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGrabed();
			}
		});
		editBatton.addActionListener(event -> editButtonClicked());
		editBatton.setMinimumSize(new Dimension(75, 23));
		editBatton.setMaximumSize(new Dimension(75, 23));
		editBatton.setPreferredSize(new Dimension(75, 23));
		GridBagConstraints gbc_editBatton = new GridBagConstraints();
		gbc_editBatton.insets = new Insets(0, 0, 5, 5);
		gbc_editBatton.gridx = 5;
		gbc_editBatton.gridy = 5;
		add(editBatton, gbc_editBatton);
		
		removeSourceButton = new JButton("Remove");
		removeSourceButton.addActionListener(e -> removeButtonClicked(getCurrentLocation()));
		removeSourceButton.setBorderPainted(false);
		removeSourceButton.setPreferredSize(new Dimension(75, 23));
		removeSourceButton.setMinimumSize(new Dimension(75, 23));
		removeSourceButton.setMaximumSize(new Dimension(75, 23));
		GridBagConstraints gbc_removeSourceButton = new GridBagConstraints();
		gbc_removeSourceButton.anchor = GridBagConstraints.WEST;
		gbc_removeSourceButton.insets = new Insets(0, 0, 5, 5);
		gbc_removeSourceButton.gridx = 6;
		gbc_removeSourceButton.gridy = 5;
		add(removeSourceButton, gbc_removeSourceButton);
		
		

	}

	@Override
	public void setPublishedField(String fieldValue) {
		publishedTextField.setText(fieldValue);
	}

	@Override
	public void setPublicationDateField(String fieldValue) {
		publicationDateTextField.setText(fieldValue);
	}

	@Override
	public void setTitleField(String fieldValue) {
		titleTextField.setText(fieldValue);
	}

	@Override
	public void setAuthorField(String fieldValue) {
		authorTextField.setText(fieldValue);
	}

	@Override
	public void addOverviewTabListener(OverviewTabListener listener) {
		if(listeners != null)
			listeners.add(OverviewTabListener.class, listener);
	}

	@Override
	public void removeOverviewTabListener(OverviewTabListener listener) {
		listeners.remove(OverviewTabListener.class, listener);
	}

	@Override
	public void fillMediumTypeList() {
		mediumTypeComboBoxModel.removeAllElements();
		for(MediumType medium : MediumType.values()) {
			mediumTypeComboBoxModel.addElement(medium);
		}	
	}

	@Override
	public void setCurrentMediumType(MediumType medium) {
		mediumTypeComboBox.setSelectedItem(medium);
	}

	@Override
	public void mediumChanged(MediumType selectedMedium) {
		for(OverviewTabListener listener : listeners.getListeners(OverviewTabListener.class)) {
			listener.onMediumChanged(selectedMedium);
		}
	}

	@Override
	public void titleChanged(String fieldValue) {
		for(OverviewTabListener listener : listeners.getListeners(OverviewTabListener.class)) {
			listener.onTitleChanged(fieldValue);
		}
	}

	@Override
	public void publicationDateChanged(String fieldValue) {
		for(OverviewTabListener listener : listeners.getListeners(OverviewTabListener.class)) {
			listener.onPublicationDateChanged(fieldValue);
		}
	}

	@Override
	public void authorChanged(String fieldValue) {
		for(OverviewTabListener listener : listeners.getListeners(OverviewTabListener.class)) {
			listener.onAuthorChanged(fieldValue);
		}
	}

	@Override
	public void publishedChanged(String fieldValue) {
		for(OverviewTabListener listener : listeners.getListeners(OverviewTabListener.class)) {
			listener.onPublishedChanged(fieldValue);
		}
	}

	@Override
	public void sourceComboBoxChanged(SourceLocation selectionValue) {
		for(OverviewTabListener listener : listeners.getListeners(OverviewTabListener.class)) {
			listener.onSourceComboBoxChanged(selectionValue);
		}
	}

	@Override
	public void setSourceComboBox(List<RecordElement> locationsList) {
		sourceComboBoxModel.removeAllElements();
		locationsList.forEach(source -> sourceComboBoxModel.addElement((SourceLocation) source));
	}

	@Override
	public void setLocationTypeLabel() {
		sourceTypeLabel.setText(getCurrentLocation().getSourceType().toString());
	}

	@Override
	public void openButtonClicked(SourceLocation currentLocation) {
		for(OverviewTabListener listener : listeners.getListeners(OverviewTabListener.class)) {
			listener.onOpenButtonClicked(currentLocation);
		}
	}

	@Override
	public void setOpenBattonEnabled(boolean option) {
		openButton.setEnabled(option);
	}
	
	private SourceLocation getCurrentLocation() {
		if(sourceComboBox.getSelectedIndex() > -1) {
			return (SourceLocation) sourceComboBox.getSelectedItem();
		}
		return null;
	}

	@Override
	public void editButtonClicked() {
		for(OverviewTabListener listener : listeners.getListeners(OverviewTabListener.class)) {
			listener.onEditButtonClicked();
		}
	}

	@Override
	public void focusGrabed() {
		for(OverviewTabListener listener : listeners.getListeners(OverviewTabListener.class)) {
			listener.onFocusGrabed();
		}
	}
	
	@Override
	public void showInformationDialog(String message) {
		JOptionPane.showMessageDialog(this, message, "Information Dialog", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void removeButtonClicked(SourceLocation currentLocation) {
		for(OverviewTabListener listener : listeners.getListeners(OverviewTabListener.class)) {
			listener.onRemoveButtonClicked(currentLocation);
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

}
