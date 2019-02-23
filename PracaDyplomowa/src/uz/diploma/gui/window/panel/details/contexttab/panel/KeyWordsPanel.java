package uz.diploma.gui.window.panel.details.contexttab.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.EventListenerList;

import uz.diploma.controller.context.PanelViewController;
import uz.diploma.gui.window.panel.record.ResultListListener;
import uz.diploma.model.record.element.HistoricalCharacter;
import uz.diploma.model.record.element.HistoricalPeriod;
import uz.diploma.model.record.element.KeyWord;
import uz.diploma.model.record.element.Location;
import uz.diploma.model.record.element.Record;
import uz.diploma.model.record.element.RecordElement;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * @author Spider
 * 
 * concrete view for presenting keywords panel
 * panel is part of the context tab
 *
 */
public class KeyWordsPanel extends JPanel implements PanelView {

	private EventListenerList listeners;
	private JButton addKeyWordButton;
	private JButton removeKeyWordButton;
	private JList<KeyWord> keyWordsList;
	private DefaultListModel<KeyWord> keyWordListModel;

	/**
	 * Create the panel.
	 */
	public KeyWordsPanel() {
		listeners = new EventListenerList();
		
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Keywords", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		addKeyWordButton = new JButton("Add");
		addKeyWordButton.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGrabed();
			}
		});
		addKeyWordButton.addActionListener(event -> addButtonClicked());
		addKeyWordButton.setMinimumSize(new Dimension(75, 23));
		addKeyWordButton.setMaximumSize(new Dimension(75, 23));
		addKeyWordButton.setPreferredSize(new Dimension(75, 23));
		GridBagConstraints gbc_addKeyWordButton = new GridBagConstraints();
		gbc_addKeyWordButton.anchor = GridBagConstraints.WEST;
		gbc_addKeyWordButton.insets = new Insets(0, 0, 5, 5);
		gbc_addKeyWordButton.gridx = 0;
		gbc_addKeyWordButton.gridy = 0;
		add(addKeyWordButton, gbc_addKeyWordButton);
		
		removeKeyWordButton = new JButton("Remove");
		removeKeyWordButton.addActionListener(event -> removeButtonClicked());
		removeKeyWordButton.setMaximumSize(new Dimension(75, 23));
		removeKeyWordButton.setMinimumSize(new Dimension(75, 23));
		removeKeyWordButton.setPreferredSize(new Dimension(75, 23));
		GridBagConstraints gbc_removeKeyWordButton = new GridBagConstraints();
		gbc_removeKeyWordButton.anchor = GridBagConstraints.WEST;
		gbc_removeKeyWordButton.insets = new Insets(0, 0, 5, 0);
		gbc_removeKeyWordButton.gridx = 1;
		gbc_removeKeyWordButton.gridy = 0;
		add(removeKeyWordButton, gbc_removeKeyWordButton);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		keyWordsList = new JList<KeyWord>();
		keyWordListModel = new DefaultListModel<>();
		keyWordsList.setBorder(new LineBorder(new Color(0, 0, 0)));
		keyWordsList.setModel(keyWordListModel);
		keyWordsList.setCellRenderer(new RecordElementListRenderer());
		scrollPane.setViewportView(keyWordsList);

	}
	
	@Override
	public void onNewRecord(Record selectedRecord) {
		keyWordListModel.removeAllElements();
		selectedRecord.getKeyWordIterator().forEachRemaining(keyWord -> keyWordListModel.addElement(keyWord));
	}

	@Override
	public void addButtonClicked() {
		for(PanelViewController listener : listeners.getListeners(PanelViewController.class)) {
			listener.onAddButtonClicked();
		}
	}

	@Override
	public void focusGrabed() {
		for(PanelViewController listener : listeners.getListeners(PanelViewController.class)) {
			listener.onFocusGained();
		}
	}
	
	@Override
	public void addPanelViewListener(PanelViewController listener) {
		if(listeners != null)
			listeners.add(PanelViewController.class, listener);
	}

	@Override
	public void removePanelViewListener(PanelViewController listener) {
		listeners.remove(PanelViewController.class, listener);		
	}

	@Override
	public void removeButtonClicked() {
		for(PanelViewController listener : listeners.getListeners(PanelViewController.class)) {
			listener.onRemoveButtonClicked();
		}
	}

	@Override
	public RecordElement getSelection() {
		return keyWordsList.getSelectedValue();
	}

	@Override
	public int showRemoveConfirmationDialog(String message) {
		return JOptionPane.showConfirmDialog(this, message, "Confirmation Dialog", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	}

	@Override
	public void showItemNotSelectedDialog() {
		JOptionPane.showMessageDialog(this, "Select item to remove first", "Information Dialog", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public boolean canRemoveElement(String message) {
		int selectedValue = showRemoveConfirmationDialog(message);
		if(selectedValue == JOptionPane.CANCEL_OPTION)
			return false;
		else
			return true;
	}

	@Override
	public void updateView(List<RecordElement> keywords) {
		keyWordListModel.clear();
		keywords.stream().forEach(keyword -> keyWordListModel.addElement((KeyWord)keyword));
	}
}
