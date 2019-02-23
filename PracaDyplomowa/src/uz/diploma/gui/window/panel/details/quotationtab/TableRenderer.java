package uz.diploma.gui.window.panel.details.quotationtab;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.table.TableCellRenderer;

public class TableRenderer extends JTextArea implements TableCellRenderer {
	
	private int rowHeightMax;
	
	public TableRenderer() {
		setLineWrap(true);
		setWrapStyleWord(true);
		setOpaque(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		setForeground( isSelected ? table.getSelectionForeground() : table.getForeground());
		setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
		
		setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));
		
		setText(value.toString());
		setRawHeight(table, row, column);
		
		return this;
	}

	
	private void setRawHeight(JTable table, int row, int column) {
		int columnWidth = table.getTableHeader().getColumnModel().getColumn(column).getWidth();
		setSize(new Dimension(columnWidth, Short.MAX_VALUE));
		rowHeightMax = Math.max(rowHeightMax, (int)getPreferredSize().getHeight());
		table.setRowHeight(row, rowHeightMax);
	}
}
