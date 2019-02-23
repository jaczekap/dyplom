package uz.diploma.gui.window.panel.details.contexttab.panel;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.EtchedBorder;

import uz.diploma.model.record.element.HistoricalEvent;
import uz.diploma.model.record.element.Record;
import uz.diploma.model.record.element.RecordElement;

public class RecordElementListRenderer extends JTextPane implements ListCellRenderer<RecordElement> {

	private Color foregroundColor;
	private Color backgroundColor;
	private Color backgroundColorAlternate = new Color(240, 240, 240);
	
	@Override
	public Component getListCellRendererComponent(JList<? extends RecordElement> list,
			RecordElement element, int index, boolean isSelected, boolean cellHasFocus) {
		backgroundColor = isSelected ? list.getSelectionBackground() : (index % 2 == 0) ? list.getBackground() : backgroundColorAlternate;
		foregroundColor = isSelected ? list.getSelectionForeground() : list.getForeground();
		
		setContentType("text/html");
		setText(element.toString());
		
		setForeground(foregroundColor);
		setBackground(backgroundColor);
		
		setBorder(new EtchedBorder());
		
		
		return this;
	}

}
