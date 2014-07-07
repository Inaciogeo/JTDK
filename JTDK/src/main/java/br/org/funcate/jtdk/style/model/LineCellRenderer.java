package br.org.funcate.jtdk.style.model;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
public class LineCellRenderer extends JPanel implements TableCellRenderer{

	public LineCellRenderer(){
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		if (isSelected){
			Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
			this.setBorder(border);
		} else {
			Border border = BorderFactory.createLineBorder(Color.WHITE, 2);
			this.setBorder(border);
		}
		
		LineStyleVisual visual = (LineStyleVisual) value;
		
		this.setBackground(visual.getColor());
		
		this.setToolTipText("Estilo: " + visual.getStyle() +". "+
				"Espessura: " + visual.getWidth() +". "+ 
				"Final da Linha: " + visual.getLineFinal() +". "+
				"Junção da Linha: " + visual.getJoin());
		
		return this;
	}
}
