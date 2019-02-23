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
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.EventListenerList;
import javax.swing.UIManager;

import uz.diploma.controller.context.PanelViewController;
import uz.diploma.gui.window.panel.record.ResultListListener;
import uz.diploma.model.record.element.HistoricalCharacter;
import uz.diploma.model.record.element.HistoricalPeriod;
import uz.diploma.model.record.element.Record;
import uz.diploma.model.record.element.RecordElement;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * @author Spider
 * 
 * concrete view for presenting historical character panel
 * panel is part of the context tab
 *
 */
public class CharactersPanel extends JPanel implements PanelView {

	private EventListenerList listeners;
	private JButton addCharacterButton;
	private JButton removeCharacterButton;
	private DefaultListModel<HistoricalCharacter> characterListModel;
	private JList<HistoricalCharacter> charactersList;

	/**
	 * Create the panel.
	 */
	public CharactersPanel() {
		listeners = new EventListenerList();
		
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Characters", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		addCharacterButton = new JButton("Add");
		addCharacterButton.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGrabed();
			}
		});
		addCharacterButton.addActionListener(event -> addButtonClicked());
		addCharacterButton.setMinimumSize(new Dimension(75, 23));
		addCharacterButton.setMaximumSize(new Dimension(75, 23));
		addCharacterButton.setPreferredSize(new Dimension(75, 23));
		GridBagConstraints gbc_addCharacterButton = new GridBagConstraints();
		gbc_addCharacterButton.anchor = GridBagConstraints.WEST;
		gbc_addCharacterButton.insets = new Insets(0, 0, 5, 5);
		gbc_addCharacterButton.gridx = 0;
		gbc_addCharacterButton.gridy = 0;
		add(addCharacterButton, gbc_addCharacterButton);
		
		removeCharacterButton = new JButton("Remove");
		removeCharacterButton.addActionListener(event -> removeButtonClicked());
		removeCharacterButton.setMaximumSize(new Dimension(75, 23));
		removeCharacterButton.setMinimumSize(new Dimension(75, 23));
		removeCharacterButton.setPreferredSize(new Dimension(75, 23));
		GridBagConstraints gbc_removeCharacterButton = new GridBagConstraints();
		gbc_removeCharacterButton.anchor = GridBagConstraints.WEST;
		gbc_removeCharacterButton.insets = new Insets(0, 0, 5, 0);
		gbc_removeCharacterButton.gridx = 1;
		gbc_removeCharacterButton.gridy = 0;
		add(removeCharacterButton, gbc_removeCharacterButton);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		charactersList = new JList<HistoricalCharacter>();
		characterListModel = new DefaultListModel<>();
		charactersList.setBorder(new LineBorder(new Color(0, 0, 0)));
		charactersList.setModel(characterListModel);
		charactersList.setCellRenderer(new RecordElementListRenderer());
		scrollPane.setViewportView(charactersList);

	}

	@Override
	public void onNewRecord(Record selectedRecord) {
		characterListModel.removeAllElements();
		fillCharacterList(selectedRecord);
	}

	private void fillCharacterList(Record selectedRecord) {
		selectedRecord.getCharactersIterator().forEachRemaining(character -> characterListModel.addElement(character));
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
		return charactersList.getSelectedValue();
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
	public void updateView(List<RecordElement> characters) {
		characterListModel.clear();
		characters.stream().forEach(character -> characterListModel.addElement((HistoricalCharacter)character));
	}

}
