package br.org.funcate.jtdk.style.controller;

import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;

import org.geotools.styling.Style;

import br.org.funcate.jtdk.style.enumeration.LineFinalEnum;
import br.org.funcate.jtdk.style.enumeration.LineJoinEnum;
import br.org.funcate.jtdk.style.enumeration.LineStyleEnum;
import br.org.funcate.jtdk.style.model.LineCellRenderer;
import br.org.funcate.jtdk.style.model.LineStyleVisual;
import br.org.funcate.jtdk.style.model.VisualTableModel;
import br.org.funcate.jtdk.style.model.factory.SLDBuilder;
import br.org.funcate.jtdk.style.view.LineStyleView;

import com.vividsolutions.jts.geom.LineString;

/**
 * This is the controller of the {@link LineStyleView}.
 * 
 * @author Moraes, Emerson Leite.
 * 
 */
public class LineStyleController {

	private LineStyleView view;

	private LineStyleVisual currentVisual;

	/**
	 * Constructor.
	 * 
	 * @param view
	 */
	public LineStyleController(LineStyleView view) {
		this.view = view;
		this.initComponents();
	}

	/**
	 * This method initialize the {@link LineStyleView} components.
	 */
	private void initComponents() {
		JComboBox cboLineStyle = this.view.getCboLineStyle();
		JComboBox cboFinalLine = this.view.getCboFinalLine();
		JComboBox cboJoinLine = this.view.getCboJoinLine();

		for (LineStyleEnum styleName : LineStyleEnum.values()) {
			cboLineStyle.addItem(styleName);
		}

		for (LineFinalEnum finalName : LineFinalEnum.values()) {
			cboFinalLine.addItem(finalName);
		}

		for (LineJoinEnum joinName : LineJoinEnum.values()) {
			cboJoinLine.addItem(joinName);
		}

		JTable tblVisual = this.view.getTblVisual();

		TableColumn column = tblVisual.getColumnModel().getColumn(0);
		column.setCellRenderer(new LineCellRenderer());

		VisualTableModel model = (VisualTableModel) tblVisual.getModel();

		LineStyleVisual visual = this.createLineStyleVisual();

		model.addRow(new Object[] { visual });

		tblVisual.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tblVisual.changeSelection(0, tblVisual.getColumnCount(), false, false);

		this.currentVisual = visual;

		this.view.getCanvasPreview().setStyle(this.makeStyle());
	}

	/**
	 * Makes a {@link LineStyleVisual} object by the {@link LineStyleView} data.
	 * 
	 * @return
	 */
	private LineStyleVisual createLineStyleVisual() {
		JComboBox cboLineStyle = this.view.getCboLineStyle();
		JComboBox cboFinalLine = this.view.getCboFinalLine();
		JComboBox cboJoinLine = this.view.getCboJoinLine();
		JPanel pnlColor = this.view.getPnlColor();
		JTextField txtLineWidth = this.view.getTxtLineWidth();

		LineStyleVisual visual = new LineStyleVisual();

		visual.setColor(pnlColor.getBackground());
		visual.setWidth(Double.parseDouble(txtLineWidth.getText()));
		visual.setStyle((LineStyleEnum) cboLineStyle.getSelectedItem());
		visual.setLineFinal((LineFinalEnum) cboFinalLine.getSelectedItem());
		visual.setJoin((LineJoinEnum) cboJoinLine.getSelectedItem());

		return visual;
	}

	/**
	 * This method provides a {@link JColorChooser} to allow the user set a
	 * {@link Color} for the {@link LineString} style.
	 */
	public void executeSelectColor() {
		JPanel pnlLineColor = this.view.getPnlColor();

		Color newColor = JColorChooser.showDialog(this.view, "Selecione a Cor da Linha", pnlLineColor.getBackground());

		if (newColor != null) {
			this.currentVisual.setColor(newColor);
			pnlLineColor.setBackground(newColor);
			this.refreshScreen();
		}
	}

	/**
	 * This method execute the dispose of {@link LineStyleView}.
	 */
	public void executeDispose() {
		this.view.dispose();
	}

	/**
	 * Creates a new visual on the {@link JTable}.
	 */
	public void executeAddVisual() {
		JTable tblVisual = this.view.getTblVisual();

		VisualTableModel model = (VisualTableModel) tblVisual.getModel();

		LineStyleVisual visual = new LineStyleVisual(Color.CYAN, LineStyleEnum.SOLIDA, LineFinalEnum.PADRAO, LineJoinEnum.MILTRA, 1.00);

		model.addRow(new Object[] { visual });

		this.refreshScreen();
	}

	/**
	 * Refresh the {@link LineStyleView} components.
	 */
	private void refreshScreen() {
		JComboBox cboLineStyle = this.view.getCboLineStyle();
		JComboBox cboFinalLine = this.view.getCboFinalLine();
		JComboBox cboJoinLine = this.view.getCboJoinLine();
		JPanel pnlColor = this.view.getPnlColor();

		cboLineStyle.setSelectedItem(this.currentVisual.getStyle());
		cboFinalLine.setSelectedItem(this.currentVisual.getLineFinal());
		cboJoinLine.setSelectedItem(this.currentVisual.getJoin());
		pnlColor.setBackground(this.currentVisual.getColor());

		this.view.getTblVisual().repaint();
		this.view.getCanvasPreview().setStyle(this.makeStyle());
	}

	private Style makeStyle() {

		JTable tblVisual = this.view.getTblVisual();
		VisualTableModel model = (VisualTableModel) tblVisual.getModel();
		SLDBuilder builder = new SLDBuilder();

		for (int i = 0; i < model.getRowCount(); i++) {
			LineStyleVisual visual = (LineStyleVisual) model.getValueAt(i, 0);
			builder.addSymbolizer(visual);
		}

		Style style = builder.buildStyle("Line");

		return style;
	}

	/**
	 * Updates the current visual to the user selected visual.
	 */
	public void updateCurrentStyle() {
		JTextField txtLineWidth = this.view.getTxtLineWidth();
		JTable tblVisual = this.view.getTblVisual();

		String strWidth = txtLineWidth.getText();

		double width = this.currentVisual.getWidth();

		try {
			width = Double.parseDouble(strWidth);
		} catch (NumberFormatException e) {

		}

		this.currentVisual.setWidth(width);

		VisualTableModel model = (VisualTableModel) tblVisual.getModel();

		int selectedRow = tblVisual.getSelectedRow();

		if (selectedRow == -1) {
			selectedRow = 0;
			tblVisual.changeSelection(selectedRow, tblVisual.getColumnCount(), false, false);
		}

		LineStyleVisual visual = (LineStyleVisual) model.getValueAt(selectedRow, 0);

		this.currentVisual = visual;

		txtLineWidth.setText(String.valueOf(this.currentVisual.getWidth()));

		this.refreshScreen();
	}

	/**
	 * Sets the user selection of {@link LineStyleEnum} in the current visual.
	 */
	public void executeSelectStyle() {
		JComboBox cboLineStyle = this.view.getCboLineStyle();

		LineStyleEnum style = (LineStyleEnum) cboLineStyle.getSelectedItem();

		this.currentVisual.setStyle(style);

		this.refreshScreen();
	}

	/**
	 * Sets the user selection of {@link LineJoinEnum} in the current visual.
	 */
	public void executeSelectJoin() {
		JComboBox cboJoinLine = this.view.getCboJoinLine();

		LineJoinEnum join = (LineJoinEnum) cboJoinLine.getSelectedItem();

		this.currentVisual.setJoin(join);

		this.refreshScreen();
	}

	/**
	 * Sets the user selection of {@link LineFinalEnum} in the current visual.
	 */
	public void executeSelectFinal() {
		JComboBox cboFinalLine = this.view.getCboFinalLine();

		LineFinalEnum lineFinal = (LineFinalEnum) cboFinalLine.getSelectedItem();

		this.currentVisual.setLineFinal(lineFinal);

		this.refreshScreen();
	}

	/**
	 * Moves a selected visual to up on the JTable.
	 */
	public void executeVisualUp() {
		JTable tblVisual = this.view.getTblVisual();

		int selectedRow = tblVisual.getSelectedRow();

		VisualTableModel model = (VisualTableModel) tblVisual.getModel();

		if (selectedRow == 0) {
			return;
		}

		LineStyleVisual aboveVisual = (LineStyleVisual) model.getValueAt(selectedRow - 1, 0);

		model.removeRow(selectedRow - 1);

		model.insertRow(selectedRow, new Object[] { aboveVisual });

		tblVisual.repaint();

		this.refreshScreen();
	}

	/**
	 * Moves a selected visual to down on the JTable.
	 */
	public void executeVisualDown() {
		JTable tblVisual = this.view.getTblVisual();

		int selectedRow = tblVisual.getSelectedRow();

		VisualTableModel model = (VisualTableModel) tblVisual.getModel();

		if (selectedRow == model.getRowCount() - 1) {
			return;
		}

		LineStyleVisual underVisual = (LineStyleVisual) model.getValueAt(selectedRow + 1, 0);

		model.removeRow(selectedRow + 1);

		model.insertRow(selectedRow, new Object[] { underVisual });

		tblVisual.repaint();

		this.refreshScreen();
	}

	/**
	 * Delete a selected visual. This method asks to user if he want to delete
	 * the visual.
	 */
	public void executeDeleteVisual() {
		JTable tblVisual = this.view.getTblVisual();

		int selectedRow = tblVisual.getSelectedRow();

		VisualTableModel model = (VisualTableModel) tblVisual.getModel();

		if (JOptionPane.showConfirmDialog(this.view, "Deseja Remover este Visual?") == JOptionPane.OK_OPTION) {
			tblVisual.changeSelection(0, tblVisual.getColumnCount(), false, false);
			model.removeRow(selectedRow);
		}

		tblVisual.repaint();

		this.refreshScreen();
	}

	/**
	 * Creates a clone of selected visual and add this clone on JTable.
	 */
	public void executeCopyVisual() {
		LineStyleVisual clone = this.currentVisual.clone();

		JTable tblVisual = this.view.getTblVisual();

		VisualTableModel model = (VisualTableModel) tblVisual.getModel();

		model.addRow(new Object[] { clone });

		tblVisual.repaint();

		this.refreshScreen();
	}

	/**
	 * Sets the width for {@link LineStyleVisual}.
	 */
	public void executeSelectLineWidth() {
		String widthStr = this.view.getTxtLineWidth().getText();
		double width = 0.0;
		try {
			width = Double.parseDouble(widthStr);
		} catch (NumberFormatException e) {
			return;
		}

		this.currentVisual.setWidth(width);

		this.refreshScreen();
	}

	private void createDefaultVisual() {

	}
}
