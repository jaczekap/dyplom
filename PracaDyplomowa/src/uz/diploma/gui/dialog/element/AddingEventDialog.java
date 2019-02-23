package uz.diploma.gui.dialog.element;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.EventListenerList;

import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;

import java.awt.Component;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JScrollPane;

import uz.diploma.gui.window.panel.details.contexttab.panel.ComboBoxRenderer;
import uz.diploma.gui.window.panel.details.contexttab.panel.RecordElementListRenderer;
import uz.diploma.listeners.DialogViewListener;
import uz.diploma.listeners.TextFieldChangedListener;
import uz.diploma.model.record.date.Date;
import uz.diploma.model.record.date.PreciseDate;
import uz.diploma.model.record.date.RangeDate;
import uz.diploma.model.record.element.HistoricalEvent;
import uz.diploma.model.record.element.Record;
import uz.diploma.model.record.element.RecordElement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import javax.swing.text.MaskFormatter;
import javax.swing.JRadioButton;
import javax.swing.JFormattedTextField;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * @author Spider
 * 
 * Class creates view for dialog window allowing to add historical event
 *
 */
public class AddingEventDialog extends JDialog implements AddingElementDialog {

	private final JPanel contentPanel = new JPanel();
	private EventListenerList listeners;
	private DefaultComboBoxModel<HistoricalEvent> selectEventComboBoxModel;
	private JComboBox<HistoricalEvent> selectEventComboBox;
	private JPanel buttonPane;
	private JButton addButton;
	private JButton cancelButton;
	private JLabel selectEventLabel;
	private JTextField nameTextField;
	private JLabel nameLabel;
	private JLabel descriptionLabel;
	private JTextArea descriptionTextArea;
	private JScrollPane scrollPane;
	private JRadioButton preciseDateRadioButton;
	private JRadioButton rangeDateRadioButton;
	private JFormattedTextField preciseDateCenturyTextField;
	private JLabel preciseDateCenturyLabel;
	private JLabel preciseDateYearLabel;
	private JFormattedTextField preciseDateYearTextField;
	private JLabel preciseDateMonthLabel;
	private JFormattedTextField preciseDateMonthTextField;
	private JLabel preciseDateDayLabel;
	private JFormattedTextField preciseDateDayTextField;
	private JLabel preciseDateTimeLabel;
	private JFormattedTextField preciseDateTimeTextField;
	private JLabel rangeDateStartDateLabel;
	private JLabel startDateCenturyLabel;
	private JFormattedTextField startDateCenturyTextField;
	private JLabel startDateYearLabel;
	private JFormattedTextField startDateYearTextField;
	private JLabel startDateMonthLabel;
	private JFormattedTextField startDateMonthTextField;
	private JLabel startDateDayLabel;
	private JFormattedTextField startDateDayTextField;
	private JLabel startDateTimeLabel;
	private JFormattedTextField startDateTimeTextField;
	private JLabel rangeDateEndDateLabel;
	private JLabel endDateCenturyLabel;
	private JFormattedTextField endDateCenturyTextField;
	private JLabel endDateYearLabel;
	private JFormattedTextField endDateYearTextField;
	private JLabel endDateMonthLabel;
	private JFormattedTextField endDateMonthTextField;
	private JLabel endDateDayLabel;
	private JFormattedTextField endDateDayTextField;
	private JLabel endDateTimeLabel;
	private JFormattedTextField endDateTimeTextField;
	private JRadioButton BCRadioButton;
	private JRadioButton ADRadioButton;
	private ButtonGroup eraButtonGroup;
	private ButtonGroup dateButtonGroup;
	MaskFormatter format;

	/**
	 * Create the dialog.
	 */
	public AddingEventDialog() {
		listeners = new EventListenerList();
		try {
			format = new MaskFormatter("##:##");
		} catch (ParseException e1) {}
		
		setTitle("Add event");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 494, 432);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{394, 0};
		gridBagLayout.rowHeights = new int[]{114, 33, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
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
		gbl_contentPanel.columnWidths = new int[]{46, 0, 30, 0, 45, 0, 30, 0, 30, 0, 45, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{20, 0, 0, 20, 0, 0, 30, 30, 30, 16, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			selectEventLabel = new JLabel("Select:");
			GridBagConstraints gbc_selectCharacterLabel = new GridBagConstraints();
			gbc_selectCharacterLabel.anchor = GridBagConstraints.EAST;
			gbc_selectCharacterLabel.insets = new Insets(0, 0, 5, 5);
			gbc_selectCharacterLabel.gridx = 0;
			gbc_selectCharacterLabel.gridy = 0;
			contentPanel.add(selectEventLabel, gbc_selectCharacterLabel);
		}
		{
			selectEventComboBox = new JComboBox<HistoricalEvent>();
			selectEventComboBox.addActionListener(event -> comboBoxSelectionChanged(event));
			selectEventComboBox.setEditable(false);
			GridBagConstraints gbc_selectCharacterComboBox = new GridBagConstraints();
			gbc_selectCharacterComboBox.gridwidth = 10;
			gbc_selectCharacterComboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_selectCharacterComboBox.insets = new Insets(0, 0, 5, 5);
			gbc_selectCharacterComboBox.gridx = 1;
			gbc_selectCharacterComboBox.gridy = 0;
			contentPanel.add(selectEventComboBox, gbc_selectCharacterComboBox);
			selectEventComboBoxModel = new DefaultComboBoxModel<HistoricalEvent>();
			selectEventComboBox.setRenderer(new ComboBoxRenderer());
			selectEventComboBox.setModel(selectEventComboBoxModel);
		}
		{
			nameLabel = new JLabel("Name:");
			GridBagConstraints gbc_nameLabel = new GridBagConstraints();
			gbc_nameLabel.anchor = GridBagConstraints.EAST;
			gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
			gbc_nameLabel.gridx = 0;
			gbc_nameLabel.gridy = 1;
			contentPanel.add(nameLabel, gbc_nameLabel);
		}
		{
			nameTextField = new JTextField();
			nameTextField.addCaretListener(event -> textFieldChanged());
			GridBagConstraints gbc_nameTextField = new GridBagConstraints();
			gbc_nameTextField.gridwidth = 10;
			gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_nameTextField.insets = new Insets(0, 0, 5, 5);
			gbc_nameTextField.gridx = 1;
			gbc_nameTextField.gridy = 1;
			contentPanel.add(nameTextField, gbc_nameTextField);
			nameTextField.setColumns(10);
		}
		{
			BCRadioButton = new JRadioButton("BC");
			GridBagConstraints gbc_BCRadioButton = new GridBagConstraints();
			gbc_BCRadioButton.insets = new Insets(0, 0, 5, 5);
			gbc_BCRadioButton.gridx = 1;
			gbc_BCRadioButton.gridy = 2;
			contentPanel.add(BCRadioButton, gbc_BCRadioButton);
		}
		{
			ADRadioButton = new JRadioButton("AD");
			ADRadioButton.setSelected(true);
			GridBagConstraints gbc_ADRadioButton = new GridBagConstraints();
			gbc_ADRadioButton.gridwidth = 2;
			gbc_ADRadioButton.insets = new Insets(0, 0, 5, 5);
			gbc_ADRadioButton.gridx = 2;
			gbc_ADRadioButton.gridy = 2;
			contentPanel.add(ADRadioButton, gbc_ADRadioButton);
		}
		{
			preciseDateRadioButton = new JRadioButton("date");
			preciseDateRadioButton.setSelected(true);
			preciseDateRadioButton.setHorizontalTextPosition(SwingConstants.LEFT);
			preciseDateRadioButton.setHorizontalAlignment(SwingConstants.RIGHT);
			preciseDateRadioButton.addActionListener(e -> getDate());
			GridBagConstraints gbc_preciseDateRadioButton = new GridBagConstraints();
			gbc_preciseDateRadioButton.anchor = GridBagConstraints.EAST;
			gbc_preciseDateRadioButton.insets = new Insets(0, 0, 5, 5);
			gbc_preciseDateRadioButton.gridx = 0;
			gbc_preciseDateRadioButton.gridy = 3;
			contentPanel.add(preciseDateRadioButton, gbc_preciseDateRadioButton);
		}
		{
			preciseDateCenturyLabel = new JLabel("century:");
			GridBagConstraints gbc_preciseDateCenturyLabel = new GridBagConstraints();
			gbc_preciseDateCenturyLabel.insets = new Insets(0, 0, 5, 5);
			gbc_preciseDateCenturyLabel.anchor = GridBagConstraints.EAST;
			gbc_preciseDateCenturyLabel.gridx = 1;
			gbc_preciseDateCenturyLabel.gridy = 3;
			contentPanel.add(preciseDateCenturyLabel, gbc_preciseDateCenturyLabel);
		}
		{
			preciseDateCenturyTextField = new JFormattedTextField();
			preciseDateCenturyTextField.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_preciseDateCentuaryTextField = new GridBagConstraints();
			gbc_preciseDateCentuaryTextField.insets = new Insets(0, 0, 5, 5);
			gbc_preciseDateCentuaryTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_preciseDateCentuaryTextField.gridx = 2;
			gbc_preciseDateCentuaryTextField.gridy = 3;
			
			contentPanel.add(preciseDateCenturyTextField, gbc_preciseDateCentuaryTextField);
		}
		{
			preciseDateYearLabel = new JLabel("year:");
			GridBagConstraints gbc_preciseDateYearLabel = new GridBagConstraints();
			gbc_preciseDateYearLabel.anchor = GridBagConstraints.EAST;
			gbc_preciseDateYearLabel.insets = new Insets(0, 0, 5, 5);
			gbc_preciseDateYearLabel.gridx = 3;
			gbc_preciseDateYearLabel.gridy = 3;
			contentPanel.add(preciseDateYearLabel, gbc_preciseDateYearLabel);
		}
		{
			preciseDateYearTextField = new JFormattedTextField();
			preciseDateYearTextField.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_preciseDateYearTextField = new GridBagConstraints();
			gbc_preciseDateYearTextField.insets = new Insets(0, 0, 5, 5);
			gbc_preciseDateYearTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_preciseDateYearTextField.gridx = 4;
			gbc_preciseDateYearTextField.gridy = 3;
			contentPanel.add(preciseDateYearTextField, gbc_preciseDateYearTextField);
		}
		{
			preciseDateMonthLabel = new JLabel("month:");
			GridBagConstraints gbc_preciseDateMonthLabel = new GridBagConstraints();
			gbc_preciseDateMonthLabel.anchor = GridBagConstraints.EAST;
			gbc_preciseDateMonthLabel.insets = new Insets(0, 0, 5, 5);
			gbc_preciseDateMonthLabel.gridx = 5;
			gbc_preciseDateMonthLabel.gridy = 3;
			contentPanel.add(preciseDateMonthLabel, gbc_preciseDateMonthLabel);
		}
		{
			preciseDateMonthTextField = new JFormattedTextField();
			preciseDateMonthTextField.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_preciseDateMonthTextField = new GridBagConstraints();
			gbc_preciseDateMonthTextField.insets = new Insets(0, 0, 5, 5);
			gbc_preciseDateMonthTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_preciseDateMonthTextField.gridx = 6;
			gbc_preciseDateMonthTextField.gridy = 3;
			contentPanel.add(preciseDateMonthTextField, gbc_preciseDateMonthTextField);
		}
		{
			preciseDateDayLabel = new JLabel("day:");
			GridBagConstraints gbc_preciseDateDayLabel = new GridBagConstraints();
			gbc_preciseDateDayLabel.anchor = GridBagConstraints.EAST;
			gbc_preciseDateDayLabel.insets = new Insets(0, 0, 5, 5);
			gbc_preciseDateDayLabel.gridx = 7;
			gbc_preciseDateDayLabel.gridy = 3;
			contentPanel.add(preciseDateDayLabel, gbc_preciseDateDayLabel);
		}
		{
			preciseDateDayTextField = new JFormattedTextField();
			preciseDateDayTextField.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_preciseDateDayTextField = new GridBagConstraints();
			gbc_preciseDateDayTextField.insets = new Insets(0, 0, 5, 5);
			gbc_preciseDateDayTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_preciseDateDayTextField.gridx = 8;
			gbc_preciseDateDayTextField.gridy = 3;
			contentPanel.add(preciseDateDayTextField, gbc_preciseDateDayTextField);
		}
		{
			preciseDateTimeLabel = new JLabel("time:");
			GridBagConstraints gbc_preciseDateTimeLabel = new GridBagConstraints();
			gbc_preciseDateTimeLabel.anchor = GridBagConstraints.EAST;
			gbc_preciseDateTimeLabel.insets = new Insets(0, 0, 5, 5);
			gbc_preciseDateTimeLabel.gridx = 9;
			gbc_preciseDateTimeLabel.gridy = 3;
			contentPanel.add(preciseDateTimeLabel, gbc_preciseDateTimeLabel);
		}
		{
			preciseDateTimeTextField = new JFormattedTextField();
			preciseDateTimeTextField.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					if(preciseDateTimeTextField.getText().trim().equals("hh:mm"))
						preciseDateTimeTextField.setText("");
					preciseDateTimeTextField.setFont(preciseDateTimeTextField.getFont().deriveFont(Font.PLAIN));
					preciseDateTimeTextField.setForeground(Color.BLACK);
				}
				@Override
				public void focusLost(FocusEvent e) {
					if(preciseDateTimeTextField.getText().trim().equals("")) {
						preciseDateTimeTextField.setText("hh:mm");
						preciseDateTimeTextField.setForeground(new Color(120, 120, 120));
						preciseDateTimeTextField.setFont(preciseDateTimeTextField.getFont().deriveFont(Font.ITALIC));
					}
				}
			});
			preciseDateTimeTextField.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_preciseDateTimeTextField = new GridBagConstraints();
			gbc_preciseDateTimeTextField.insets = new Insets(0, 0, 5, 5);
			gbc_preciseDateTimeTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_preciseDateTimeTextField.gridx = 10;
			gbc_preciseDateTimeTextField.gridy = 3;
			
			contentPanel.add(preciseDateTimeTextField, gbc_preciseDateTimeTextField);
		}
		{
			rangeDateRadioButton = new JRadioButton("range");
			rangeDateRadioButton.setHorizontalTextPosition(SwingConstants.LEFT);
			rangeDateRadioButton.setHorizontalAlignment(SwingConstants.LEFT);
			rangeDateRadioButton.addActionListener(e -> getDate());
			GridBagConstraints gbc_rangeDateRadioButton = new GridBagConstraints();
			gbc_rangeDateRadioButton.anchor = GridBagConstraints.EAST;
			gbc_rangeDateRadioButton.insets = new Insets(0, 0, 5, 5);
			gbc_rangeDateRadioButton.gridx = 0;
			gbc_rangeDateRadioButton.gridy = 4;
			contentPanel.add(rangeDateRadioButton, gbc_rangeDateRadioButton);
		}
		{
			rangeDateStartDateLabel = new JLabel("Start date:");
			rangeDateStartDateLabel.setHorizontalTextPosition(SwingConstants.LEFT);
			rangeDateStartDateLabel.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_rangeDateStartDateLabel = new GridBagConstraints();
			gbc_rangeDateStartDateLabel.anchor = GridBagConstraints.WEST;
			gbc_rangeDateStartDateLabel.gridwidth = 2;
			gbc_rangeDateStartDateLabel.insets = new Insets(0, 0, 5, 5);
			gbc_rangeDateStartDateLabel.gridx = 1;
			gbc_rangeDateStartDateLabel.gridy = 4;
			contentPanel.add(rangeDateStartDateLabel, gbc_rangeDateStartDateLabel);
		}
		{
			startDateCenturyLabel = new JLabel("century:");
			GridBagConstraints gbc_startDateCenturyLabel = new GridBagConstraints();
			gbc_startDateCenturyLabel.anchor = GridBagConstraints.EAST;
			gbc_startDateCenturyLabel.insets = new Insets(0, 0, 5, 5);
			gbc_startDateCenturyLabel.gridx = 1;
			gbc_startDateCenturyLabel.gridy = 5;
			contentPanel.add(startDateCenturyLabel, gbc_startDateCenturyLabel);
		}
		{
			startDateCenturyTextField = new JFormattedTextField();
			startDateCenturyTextField.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_startDateCentuaryTextField = new GridBagConstraints();
			gbc_startDateCentuaryTextField.insets = new Insets(0, 0, 5, 5);
			gbc_startDateCentuaryTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_startDateCentuaryTextField.gridx = 2;
			gbc_startDateCentuaryTextField.gridy = 5;
			contentPanel.add(startDateCenturyTextField, gbc_startDateCentuaryTextField);
		}
		{
			startDateYearLabel = new JLabel("year:");
			GridBagConstraints gbc_startDateYearLabel = new GridBagConstraints();
			gbc_startDateYearLabel.anchor = GridBagConstraints.EAST;
			gbc_startDateYearLabel.insets = new Insets(0, 0, 5, 5);
			gbc_startDateYearLabel.gridx = 3;
			gbc_startDateYearLabel.gridy = 5;
			contentPanel.add(startDateYearLabel, gbc_startDateYearLabel);
		}
		{
			startDateYearTextField = new JFormattedTextField();
			startDateYearTextField.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_startDateYearTextField = new GridBagConstraints();
			gbc_startDateYearTextField.insets = new Insets(0, 0, 5, 5);
			gbc_startDateYearTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_startDateYearTextField.gridx = 4;
			gbc_startDateYearTextField.gridy = 5;
			contentPanel.add(startDateYearTextField, gbc_startDateYearTextField);
		}
		{
			startDateMonthLabel = new JLabel("month:");
			GridBagConstraints gbc_startDateMonthLabel = new GridBagConstraints();
			gbc_startDateMonthLabel.anchor = GridBagConstraints.EAST;
			gbc_startDateMonthLabel.insets = new Insets(0, 0, 5, 5);
			gbc_startDateMonthLabel.gridx = 5;
			gbc_startDateMonthLabel.gridy = 5;
			contentPanel.add(startDateMonthLabel, gbc_startDateMonthLabel);
		}
		{
			startDateMonthTextField = new JFormattedTextField();
			startDateMonthTextField.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_startDateMonthTextField = new GridBagConstraints();
			gbc_startDateMonthTextField.insets = new Insets(0, 0, 5, 5);
			gbc_startDateMonthTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_startDateMonthTextField.gridx = 6;
			gbc_startDateMonthTextField.gridy = 5;
			contentPanel.add(startDateMonthTextField, gbc_startDateMonthTextField);
		}
		{
			startDateDayLabel = new JLabel("day:");
			GridBagConstraints gbc_startDateDayLabel = new GridBagConstraints();
			gbc_startDateDayLabel.anchor = GridBagConstraints.EAST;
			gbc_startDateDayLabel.insets = new Insets(0, 0, 5, 5);
			gbc_startDateDayLabel.gridx = 7;
			gbc_startDateDayLabel.gridy = 5;
			contentPanel.add(startDateDayLabel, gbc_startDateDayLabel);
		}
		{
			startDateDayTextField = new JFormattedTextField();
			startDateDayTextField.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_startDateDayTextField = new GridBagConstraints();
			gbc_startDateDayTextField.insets = new Insets(0, 0, 5, 5);
			gbc_startDateDayTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_startDateDayTextField.gridx = 8;
			gbc_startDateDayTextField.gridy = 5;
			contentPanel.add(startDateDayTextField, gbc_startDateDayTextField);
		}
		{
			startDateTimeLabel = new JLabel("time:");
			GridBagConstraints gbc_startDateTimeLabel = new GridBagConstraints();
			gbc_startDateTimeLabel.anchor = GridBagConstraints.EAST;
			gbc_startDateTimeLabel.insets = new Insets(0, 0, 5, 5);
			gbc_startDateTimeLabel.gridx = 9;
			gbc_startDateTimeLabel.gridy = 5;
			contentPanel.add(startDateTimeLabel, gbc_startDateTimeLabel);
		}
		{
			startDateTimeTextField = new JFormattedTextField();
			startDateTimeTextField.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					if(startDateTimeTextField.getText().trim().equals("hh:mm"))
						startDateTimeTextField.setText("");
					startDateTimeTextField.setFont(startDateTimeTextField.getFont().deriveFont(Font.PLAIN));
					startDateTimeTextField.setForeground(Color.BLACK);
				}
				@Override
				public void focusLost(FocusEvent e) {
					if(startDateTimeTextField.getText().trim().equals("")) {
						startDateTimeTextField.setText("hh:mm");
						startDateTimeTextField.setForeground(new Color(120, 120, 120));
						startDateTimeTextField.setFont(startDateTimeTextField.getFont().deriveFont(Font.ITALIC));
					}
				}
			});
			startDateTimeTextField.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_startDateTimeTextField = new GridBagConstraints();
			gbc_startDateTimeTextField.insets = new Insets(0, 0, 5, 5);
			gbc_startDateTimeTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_startDateTimeTextField.gridx = 10;
			gbc_startDateTimeTextField.gridy = 5;
			contentPanel.add(startDateTimeTextField, gbc_startDateTimeTextField);
		}
		{
			rangeDateEndDateLabel = new JLabel("End date:");
			GridBagConstraints gbc_rangeDateEndDateLabel = new GridBagConstraints();
			gbc_rangeDateEndDateLabel.anchor = GridBagConstraints.WEST;
			gbc_rangeDateEndDateLabel.gridwidth = 2;
			gbc_rangeDateEndDateLabel.insets = new Insets(0, 0, 5, 5);
			gbc_rangeDateEndDateLabel.gridx = 1;
			gbc_rangeDateEndDateLabel.gridy = 6;
			contentPanel.add(rangeDateEndDateLabel, gbc_rangeDateEndDateLabel);
		}
		{
			endDateCenturyLabel = new JLabel("century:");
			GridBagConstraints gbc_endDateCenturyLabel = new GridBagConstraints();
			gbc_endDateCenturyLabel.anchor = GridBagConstraints.EAST;
			gbc_endDateCenturyLabel.insets = new Insets(0, 0, 5, 5);
			gbc_endDateCenturyLabel.gridx = 1;
			gbc_endDateCenturyLabel.gridy = 7;
			contentPanel.add(endDateCenturyLabel, gbc_endDateCenturyLabel);
		}
		{
			endDateCenturyTextField = new JFormattedTextField();
			endDateCenturyTextField.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_endDateCentuaryTextField = new GridBagConstraints();
			gbc_endDateCentuaryTextField.insets = new Insets(0, 0, 5, 5);
			gbc_endDateCentuaryTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_endDateCentuaryTextField.gridx = 2;
			gbc_endDateCentuaryTextField.gridy = 7;
			contentPanel.add(endDateCenturyTextField, gbc_endDateCentuaryTextField);
		}
		{
			endDateYearLabel = new JLabel("year:");
			GridBagConstraints gbc_endDateYearLabel = new GridBagConstraints();
			gbc_endDateYearLabel.anchor = GridBagConstraints.EAST;
			gbc_endDateYearLabel.insets = new Insets(0, 0, 5, 5);
			gbc_endDateYearLabel.gridx = 3;
			gbc_endDateYearLabel.gridy = 7;
			contentPanel.add(endDateYearLabel, gbc_endDateYearLabel);
		}
		{
			endDateYearTextField = new JFormattedTextField();
			endDateYearTextField.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_endDateYearTextField = new GridBagConstraints();
			gbc_endDateYearTextField.insets = new Insets(0, 0, 5, 5);
			gbc_endDateYearTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_endDateYearTextField.gridx = 4;
			gbc_endDateYearTextField.gridy = 7;
			contentPanel.add(endDateYearTextField, gbc_endDateYearTextField);
		}
		{
			endDateMonthLabel = new JLabel("month:");
			GridBagConstraints gbc_endDateMonthLabel = new GridBagConstraints();
			gbc_endDateMonthLabel.anchor = GridBagConstraints.EAST;
			gbc_endDateMonthLabel.insets = new Insets(0, 0, 5, 5);
			gbc_endDateMonthLabel.gridx = 5;
			gbc_endDateMonthLabel.gridy = 7;
			contentPanel.add(endDateMonthLabel, gbc_endDateMonthLabel);
		}
		{
			endDateMonthTextField = new JFormattedTextField();
			endDateMonthTextField.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_endDateMonthTextField = new GridBagConstraints();
			gbc_endDateMonthTextField.insets = new Insets(0, 0, 5, 5);
			gbc_endDateMonthTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_endDateMonthTextField.gridx = 6;
			gbc_endDateMonthTextField.gridy = 7;
			contentPanel.add(endDateMonthTextField, gbc_endDateMonthTextField);
		}
		{
			endDateDayLabel = new JLabel("day:");
			GridBagConstraints gbc_endDateDayLabel = new GridBagConstraints();
			gbc_endDateDayLabel.anchor = GridBagConstraints.EAST;
			gbc_endDateDayLabel.insets = new Insets(0, 0, 5, 5);
			gbc_endDateDayLabel.gridx = 7;
			gbc_endDateDayLabel.gridy = 7;
			contentPanel.add(endDateDayLabel, gbc_endDateDayLabel);
		}
		{
			endDateDayTextField = new JFormattedTextField();
			endDateDayTextField.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_endDateDayTextField = new GridBagConstraints();
			gbc_endDateDayTextField.insets = new Insets(0, 0, 5, 5);
			gbc_endDateDayTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_endDateDayTextField.gridx = 8;
			gbc_endDateDayTextField.gridy = 7;
			contentPanel.add(endDateDayTextField, gbc_endDateDayTextField);
		}
		{
			endDateTimeLabel = new JLabel("time:");
			GridBagConstraints gbc_endDateTimeLabel = new GridBagConstraints();
			gbc_endDateTimeLabel.anchor = GridBagConstraints.EAST;
			gbc_endDateTimeLabel.insets = new Insets(0, 0, 5, 5);
			gbc_endDateTimeLabel.gridx = 9;
			gbc_endDateTimeLabel.gridy = 7;
			contentPanel.add(endDateTimeLabel, gbc_endDateTimeLabel);
		}
		{
			endDateTimeTextField = new JFormattedTextField();
			endDateTimeTextField.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					if(endDateTimeTextField.getText().trim().equals("hh:mm"))
						endDateTimeTextField.setText("");
					endDateTimeTextField.setFont(endDateTimeTextField.getFont().deriveFont(Font.PLAIN));
					endDateTimeTextField.setForeground(Color.BLACK);
				}
				@Override
				public void focusLost(FocusEvent e) {
					if(endDateTimeTextField.getText().trim().equals("")) {
						endDateTimeTextField.setText("hh:mm");
						endDateTimeTextField.setForeground(new Color(120, 120, 120));
						endDateTimeTextField.setFont(endDateTimeTextField.getFont().deriveFont(Font.ITALIC));
					}
				}
			});
			endDateTimeTextField.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_endDateTimeTextField = new GridBagConstraints();
			gbc_endDateTimeTextField.insets = new Insets(0, 0, 5, 5);
			gbc_endDateTimeTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_endDateTimeTextField.gridx = 10;
			gbc_endDateTimeTextField.gridy = 7;
			contentPanel.add(endDateTimeTextField, gbc_endDateTimeTextField);
		}
		{
			descriptionLabel = new JLabel("Description:");
			GridBagConstraints gbc_descriptionLabel = new GridBagConstraints();
			gbc_descriptionLabel.gridwidth = 11;
			gbc_descriptionLabel.anchor = GridBagConstraints.SOUTHWEST;
			gbc_descriptionLabel.insets = new Insets(0, 0, 5, 5);
			gbc_descriptionLabel.gridx = 1;
			gbc_descriptionLabel.gridy = 8;
			contentPanel.add(descriptionLabel, gbc_descriptionLabel);
		}
		{
			scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.gridwidth = 10;
			gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 1;
			gbc_scrollPane.gridy = 9;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{
				descriptionTextArea = new JTextArea();
				descriptionTextArea.addCaretListener(event -> textFieldChanged());
				scrollPane.setViewportView(descriptionTextArea);
				descriptionTextArea.setWrapStyleWord(true);
				descriptionTextArea.setLineWrap(true);
			}
			
			eraButtonGroup = new ButtonGroup();
			eraButtonGroup.add(ADRadioButton);
			eraButtonGroup.add(BCRadioButton);
			
			dateButtonGroup = new ButtonGroup();
			dateButtonGroup.add(preciseDateRadioButton);
			dateButtonGroup.add(rangeDateRadioButton);
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
	public void setSelectionComboBox(List<RecordElement> eventsList) {
		selectEventComboBoxModel.removeAllElements();
		eventsList.forEach(event -> selectEventComboBoxModel.addElement((HistoricalEvent)event));
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
		HistoricalEvent currentEvent = selectEventComboBoxModel.getElementAt(selectEventComboBox.getSelectedIndex());
		nameTextField.setText(currentEvent.getName());
		descriptionTextArea.setText(currentEvent.getDesciption());
		setDateTextFields(currentEvent.getEventDate());
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

	public String getDescriptionAreaText() {
		return descriptionTextArea.getText();
	}

	@Override
	public RecordElement getElement() {
		return selectEventComboBoxModel.getElementAt(selectEventComboBox.getSelectedIndex());
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
	
	private void setPreciseDateTextFields(PreciseDate eventDate) {
		preciseDateCenturyTextField.setValue(eventDate.getCenturyAsString());
		preciseDateYearTextField.setValue(eventDate.getYearAsString());
		preciseDateMonthTextField.setValue(eventDate.getMonthAsString());
		preciseDateDayTextField.setValue(eventDate.getDayAsString());
		preciseDateTimeTextField.setValue(eventDate.getTime());
	}
	
	private void setRangeDateTextFields(RangeDate eventDate) {
		PreciseDate startDate = eventDate.getStartDate();
		PreciseDate endDate = eventDate.getEndDate();
		
		startDateCenturyTextField.setValue(startDate.getCenturyAsString());
		startDateYearTextField.setValue(startDate.getYearAsString());
		startDateMonthTextField.setValue(startDate.getMonthAsString());
		startDateDayTextField.setValue(startDate.getDayAsString());
		startDateTimeTextField.setValue(startDate.getTime());
		
		endDateCenturyTextField.setValue(endDate.getCenturyAsString());
		endDateYearTextField.setValue(endDate.getYearAsString());
		endDateMonthTextField.setValue(endDate.getMonthAsString());
		endDateDayTextField.setValue(endDate.getDayAsString());
		endDateTimeTextField.setValue(endDate.getTime());
	}
	
	private void resetPreciseDateTextFields() {
		preciseDateCenturyTextField.setValue(null);
		preciseDateYearTextField.setValue(null);
		preciseDateMonthTextField.setValue(null);
		preciseDateDayTextField.setValue(null);
		preciseDateTimeTextField.setValue(null);
	}
	
	private void resetRangeDateTextFields() {
		startDateCenturyTextField.setValue(null);
		startDateYearTextField.setValue(null);
		startDateMonthTextField.setValue(null);
		startDateDayTextField.setValue(null);
		startDateTimeTextField.setValue(null);
		
		endDateCenturyTextField.setValue(null);
		endDateYearTextField.setValue(null);
		endDateMonthTextField.setValue(null);
		endDateDayTextField.setValue(null);
		endDateTimeTextField.setValue(null);
	}
	
	private void setDateTextFields(Date eventDate) {
		if(eventDate.isBeforeChristus())
			eraButtonGroup.setSelected(BCRadioButton.getModel(), true);
		else 
			eraButtonGroup.setSelected(ADRadioButton.getModel(), true);
		if(eventDate instanceof PreciseDate) {
			PreciseDate date = (PreciseDate)eventDate;
			setPreciseDateTextFields(date);
			dateButtonGroup.setSelected(preciseDateRadioButton.getModel(), true);
			resetRangeDateTextFields();
			enablePreciseDateTextFields();
			disableRangeDateTextFields();
		} else {
			RangeDate date = (RangeDate)eventDate;
			setRangeDateTextFields(date);
			dateButtonGroup.setSelected(rangeDateRadioButton.getModel(), true);
			resetPreciseDateTextFields();
			enableRangeDateTextFields();
			disablePreciseDateTextFields();
		}
		
		if(preciseDateTimeTextField.getText().trim().equals("")) {
			preciseDateTimeTextField.setText("hh:mm");
			preciseDateTimeTextField.setForeground(new Color(120, 120, 120));
			preciseDateTimeTextField.setFont(preciseDateTimeTextField.getFont().deriveFont(Font.ITALIC));
		}
		
		if(startDateTimeTextField.getText().trim().equals("")) {
			startDateTimeTextField.setText("hh:mm");
			startDateTimeTextField.setForeground(new Color(120, 120, 120));
			startDateTimeTextField.setFont(startDateTimeTextField.getFont().deriveFont(Font.ITALIC));
		}
		
		if(endDateTimeTextField.getText().trim().equals("")) {
			endDateTimeTextField.setText("hh:mm");
			endDateTimeTextField.setForeground(new Color(120, 120, 120));
			endDateTimeTextField.setFont(endDateTimeTextField.getFont().deriveFont(Font.ITALIC));
		}
	}
	
	public String[] getDate() {
			
			if(isPreciseDateSelected()) {
				String century = preciseDateCenturyTextField.getText();
				String year = preciseDateYearTextField.getText();
				String month = preciseDateMonthTextField.getText();
				String day = preciseDateDayTextField.getText();
				String time = preciseDateTimeTextField.getText();
				
				if(preciseDateTimeTextField.getText().trim().equals("hh:mm"))
					time = "";
				
				enablePreciseDateTextFields();
				disableRangeDateTextFields();
				
					return new String[] {century, year, month, day, time};
			} else {
				String startCentury = startDateCenturyTextField.getText();
				String startYear = startDateYearTextField.getText();
				String startMonth = startDateMonthTextField.getText();
				String startDay = startDateDayTextField.getText();
				String startTime = startDateTimeTextField.getText();
				
				if(startDateTimeTextField.getText().trim().equals("hh:mm"))
					startTime = "";
				
				String endCentury = endDateCenturyTextField.getText();
				String endYear = endDateYearTextField.getText();
				String endMonth = endDateMonthTextField.getText();
				String endDay = endDateDayTextField.getText();
				String endTime = endDateTimeTextField.getText();
				
				if(endDateTimeTextField.getText().trim().equals("hh:mm"))
					endTime = "";
				
				enableRangeDateTextFields();
				disablePreciseDateTextFields();
				
				return new String[] {startCentury, startYear, startMonth, startDay, startTime, endCentury, endYear, endMonth, endDay, endTime};
			}
	}

	public boolean isBeforeChristusButtonSelected() {
		return eraButtonGroup.isSelected(BCRadioButton.getModel());
	}

	public boolean isPreciseDateSelected() {
		return dateButtonGroup.isSelected(preciseDateRadioButton.getModel());
	}
	
	private void disablePreciseDateTextFields() {
		preciseDateCenturyTextField.setEnabled(false);
		preciseDateYearTextField.setEnabled(false);
		preciseDateMonthTextField.setEnabled(false);
		preciseDateDayTextField.setEnabled(false);
		preciseDateTimeTextField.setEnabled(false);
	}
	
	private void enablePreciseDateTextFields() {
		preciseDateCenturyTextField.setEnabled(true);
		preciseDateYearTextField.setEnabled(true);
		preciseDateMonthTextField.setEnabled(true);
		preciseDateDayTextField.setEnabled(true);
		preciseDateTimeTextField.setEnabled(true);
	}
	
	private void disableRangeDateTextFields() {
		startDateCenturyTextField.setEnabled(false);
		startDateYearTextField.setEnabled(false);
		startDateMonthTextField.setEnabled(false);
		startDateDayTextField.setEnabled(false);
		startDateTimeTextField.setEnabled(false);
		
		endDateCenturyTextField.setEnabled(false);
		endDateYearTextField.setEnabled(false);
		endDateMonthTextField.setEnabled(false);
		endDateDayTextField.setEnabled(false);
		endDateTimeTextField.setEnabled(false);
	}
	
	private void enableRangeDateTextFields() {
		startDateCenturyTextField.setEnabled(true);
		startDateYearTextField.setEnabled(true);
		startDateMonthTextField.setEnabled(true);
		startDateDayTextField.setEnabled(true);
		startDateTimeTextField.setEnabled(true);
		
		endDateCenturyTextField.setEnabled(true);
		endDateYearTextField.setEnabled(true);
		endDateMonthTextField.setEnabled(true);
		endDateDayTextField.setEnabled(true);
		endDateTimeTextField.setEnabled(true);
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
