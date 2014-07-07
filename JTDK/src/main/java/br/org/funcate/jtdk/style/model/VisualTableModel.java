package br.org.funcate.jtdk.style.model;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class VisualTableModel extends DefaultTableModel {

	private String[] columnNames;

	public VisualTableModel() {
		columnNames = new String[] { "Visuais" };
		this.setColumnIdentifiers(columnNames);
	}

	public boolean isCellEditable(int col, int row) {
		return false;
	}
}
