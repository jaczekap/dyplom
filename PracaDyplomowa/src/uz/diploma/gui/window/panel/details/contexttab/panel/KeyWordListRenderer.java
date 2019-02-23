package uz.diploma.gui.window.panel.details.contexttab.panel;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.EtchedBorder;

import uz.diploma.model.record.element.RecordElement;

public class KeyWordListRenderer extends JTextPane implements
		ListCellRenderer<String> {

	private Color foregroundColor;
	private Color backgroundColor;
	private Color backgroundColorAlternate = new Color(240, 240, 240);
	
	@Override
	public Component getListCellRendererComponent(JList<? extends String> list,
			String keyWord, int index, boolean isSelected, boolean cellHasFocus) {
		backgroundColor = isSelected ? list.getSelectionBackground() : (index % 2 == 0) ? list.getBackground() : backgroundColorAlternate;
		foregroundColor = isSelected ? list.getSelectionForeground() : list.getForeground();
		
		setContentType("text/html");
		setText(keyWord);
		
		setForeground(foregroundColor);
		setBackground(backgroundColor);
		
		setBorder(new EtchedBorder());
		
		
		return this;
	}

}
