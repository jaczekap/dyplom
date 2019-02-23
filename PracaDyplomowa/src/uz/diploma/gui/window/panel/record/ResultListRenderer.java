package uz.diploma.gui.window.panel.record;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.EtchedBorder;

import uz.diploma.model.record.element.Record;

public class ResultListRenderer extends JTextPane implements
		ListCellRenderer<Record> {
	
	private Color foregroundColor;
	private Color backgroundColor;
	private Color backgroundColorAlternate = new Color(240, 240, 240);
	

	@Override
	public Component getListCellRendererComponent(JList<? extends Record> list,
			Record record, int index, boolean isSelected, boolean cellHasFocus) {
		
		backgroundColor = isSelected ? list.getSelectionBackground() : (index % 2 == 0) ? list.getBackground() : backgroundColorAlternate;
		foregroundColor = isSelected ? list.getSelectionForeground() : list.getForeground();
		
		String author = record.getAuthor().getName();
		String title = record.getTitle();
		String publicationDate = record.getPublishingYear();
		if(author == null || author.trim().equals(""))
			author = "not set";
		if(title == null || title.trim().equals(""))
			title = "not set";
		if(publicationDate == null || publicationDate.trim().equals(""))
			publicationDate = "not set";
		
		setContentType("text/html");
		setText("<html><i>" + author + "</i><br><b>" + title + "</b><br>" + publicationDate + "</html>");
		//setText("<HTML>" + "ala ma kota" + "</HTML>");
		
		
		setForeground(foregroundColor);
		setBackground(backgroundColor);
		
		setBorder(new EtchedBorder());
		
		return this;
	}

}
