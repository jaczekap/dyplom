package uz.diploma.gui.window.panel.details.contexttab.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.EtchedBorder;

import uz.diploma.model.record.element.RecordElement;

public class ComboBoxRenderer extends JLabel implements ListCellRenderer<RecordElement> {

	private Color foregroundColor;
	private Color backgroundColor;
	private Color alternateColor = Color.BLUE;
	
	@Override
	public Component getListCellRendererComponent(JList<? extends RecordElement> list,
			RecordElement element, int index, boolean isSelected, boolean cellHasFocus) {
		
		backgroundColor = isSelected ? list.getSelectionBackground() : list.getBackground();
		foregroundColor = isSelected ? list.getSelectionForeground() : list.getForeground();
		
		if(element != null)
			setText(element.toString());
		else
			setText("");
		
		setForeground(foregroundColor);
		setBackground(backgroundColor);
		
		if(index == list.getSelectedIndex())
			setOpaque(true);
		else
			setOpaque(false);
		
		return this;
	}

}
