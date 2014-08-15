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

import br.org.funcate.jtdk.style.enumeration.AreaStyleEnum;
import br.org.funcate.jtdk.style.enumeration.LineFinalEnum;
import br.org.funcate.jtdk.style.enumeration.LineJoinEnum;
import br.org.funcate.jtdk.style.enumeration.LineStyleEnum;
import br.org.funcate.jtdk.style.model.LineStyleVisual;
import br.org.funcate.jtdk.style.model.PolygonCellRenderer;
import br.org.funcate.jtdk.style.model.PolygonStyleVisual;
import br.org.funcate.jtdk.style.model.VisualTableModel;
import br.org.funcate.jtdk.style.model.factory.SLDBuilder;
import br.org.funcate.jtdk.style.view.PolygonStyleView;

/**
 * This is the controller of the {@link PolygonStyleView}.
 * 
 * @author Moraes, Emerson Leite.
 *
 */
public class PolygonStyleController {

	private PolygonStyleView view;
	
	private PolygonStyleVisual currentVisual;
	
	public PolygonStyleController(PolygonStyleView view){
		this.view = view;
		this.initComponents();
	}
	
	/**
	 * This method initialize the {@link PolygonStyleView} components.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initComponents(){
		JComboBox cboAreaStyle = this.view.getCboAreaStyle();
		JComboBox cboContourStyle = this.view.getCboContourStyle();
		JComboBox cboContourJoin = this.view.getCboContourJoin();
		JComboBox cboContourFinal = this.view.getCboContourFinal();
		JComboBox cboAreaTransparency = this.view.getCboAreaTransparency();
		JTable tblVisual = this.view.getTblVisual();
		
		tblVisual.setModel(new VisualTableModel());
		
		TableColumn column = tblVisual.getColumnModel().getColumn(0);
		column.setCellRenderer(new PolygonCellRenderer());
		
		for (AreaStyleEnum area : AreaStyleEnum.values()){
			cboAreaStyle.addItem(area);
		}
		cboAreaStyle.setSelectedItem(AreaStyleEnum.SOLIDO);
		
		for (LineStyleEnum contour : LineStyleEnum.values()){
			cboContourStyle.addItem(contour);
		}
		
		for (LineFinalEnum finalName : LineFinalEnum.values()){
			cboContourFinal.addItem(finalName);
		}
		
		for (LineJoinEnum joinName : LineJoinEnum.values()){
			cboContourJoin.addItem(joinName);
		}
		
		int transparency = 0;
		
		while (transparency != 105){
			cboAreaTransparency.addItem(transparency);
			transparency += 5;
		}
		
		this.currentVisual = this.createPolygonStyleVisual();
		
		VisualTableModel model = (VisualTableModel) tblVisual.getModel();
		
		model.addRow(new Object[]{this.currentVisual});
		
		tblVisual.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tblVisual.changeSelection(0, tblVisual.getColumnCount(), false, false);
		
		tblVisual.setRowHeight(30);
		
		this.view.getCanvasPreview().setStyle(this.makeStyle());
	}
	
	private Style makeStyle(){
		
		JTable tblVisual = this.view.getTblVisual();
		VisualTableModel model = (VisualTableModel) tblVisual.getModel();
		SLDBuilder builder = new SLDBuilder();
		
		for (int i = 0; i < model.getRowCount(); i++){
			PolygonStyleVisual visual = (PolygonStyleVisual) model.getValueAt(i, 0);
			builder.addSymbolizer(visual);
		}
		
		Style style = builder.buildStyle("Polygon");
		
		return style;
	}
	
	@SuppressWarnings("rawtypes")
	private PolygonStyleVisual createPolygonStyleVisual(){
		JComboBox cboAreaStyle = this.view.getCboAreaStyle();
		JComboBox cboContourStyle = this.view.getCboContourStyle();
		JComboBox cboContourJoin = this.view.getCboContourJoin();
		JComboBox cboContourFinal = this.view.getCboContourFinal();
		JComboBox cboAreaTransparency = this.view.getCboAreaTransparency();
		JPanel pnlAreaColor = this.view.getPnlAreaColor();
		JPanel pnlContourColor = this.view.getPnlContourColor();
		JTextField txtContourWidth = this.view.getTxtContourWidth();
		
		PolygonStyleVisual polygonVisual = new PolygonStyleVisual();
		LineStyleVisual contourVisual = new LineStyleVisual();
		
		polygonVisual.setContour(contourVisual);
		polygonVisual.setAreaColor(pnlAreaColor.getBackground());
		polygonVisual.setAreaStyle((AreaStyleEnum)cboAreaStyle.getSelectedItem());
		polygonVisual.setAreaTransparency((Integer)cboAreaTransparency.getSelectedItem());
		
		
		contourVisual.setColor(pnlContourColor.getBackground());
		contourVisual.setStyle((LineStyleEnum) cboContourStyle.getSelectedItem());
		contourVisual.setJoin((LineJoinEnum) cboContourJoin.getSelectedItem());
		contourVisual.setLineFinal((LineFinalEnum) cboContourFinal.getSelectedItem());
		contourVisual.setWidth(Double.parseDouble(txtContourWidth.getText()));
		
		return polygonVisual;
	}
	
	/**
	 * Sets the user selection of area transparency factor.
	 */
	@SuppressWarnings("rawtypes")
	public void executeSelectAreaTransparency(){
		JComboBox cboAreaTransparency = this.view.getCboAreaTransparency();
		Integer transparency = (Integer) cboAreaTransparency.getSelectedItem();
		
		this.currentVisual.setAreaTransparency(transparency);
		
		this.refreshPreview();
	}
	
	/**
	 * Sets the user selection of {@link AreaStyleEnum} in the current visual.
	 */
	@SuppressWarnings("rawtypes")
	public void executeSelectAreaStyle(){
		JComboBox cboAreaStyle = this.view.getCboAreaStyle();
		AreaStyleEnum areaStyle = (AreaStyleEnum) cboAreaStyle.getSelectedItem();
		
		this.currentVisual.setAreaStyle(areaStyle);
		
		this.refreshPreview();
	}
	
	/**
	 * Sets the contour's {@link LineStyleEnum} of the {@link PolygonStyleVisual}.
	 */
	@SuppressWarnings("rawtypes")
	public void executeSelectContourStyle(){
		JComboBox cboContourStyle = this.view.getCboContourStyle();
		LineStyleEnum contourStyle = (LineStyleEnum) cboContourStyle.getSelectedItem();
		
		LineStyleVisual contourVisual = this.currentVisual.getContour();
		
		contourVisual.setStyle(contourStyle);
		
		this.refreshPreview();
	}
	
	/**
	 * Sets the contour's {@link LineFinalEnum} of the {@link PolygonStyleVisual}.
	 */
	@SuppressWarnings("rawtypes")
	public void executeSelectContourFinal(){
		JComboBox cboContourFinal = this.view.getCboContourFinal();
		LineFinalEnum finalLine = (LineFinalEnum) cboContourFinal.getSelectedItem();
		
		LineStyleVisual contourVisual = this.currentVisual.getContour();
		
		contourVisual.setLineFinal(finalLine);
		
		this.refreshPreview();
	}
	
	/**
	 * Sets the contour's {@link LineJoinEnum} of the {@link PolygonStyleVisual}
	 */
	@SuppressWarnings("rawtypes")
	public void executeSelectContourJoin(){
		JComboBox cboContourJoin = this.view.getCboContourJoin();
		LineJoinEnum join = (LineJoinEnum) cboContourJoin.getSelectedItem();
		
		LineStyleVisual contourVisual = this.currentVisual.getContour();
		
		contourVisual.setJoin(join);
		
		this.refreshPreview();
	}
	
	/**
	 * Sets the contour's width of the {@link PolygonStyleVisual}.
	 */
	public void executeSelectContourWidth(){
		String widthStr = this.view.getTxtContourWidth().getText();
		double width = 0.0;
		try {
			width = Double.parseDouble(widthStr);
		} catch (NumberFormatException e){
			return;
		}
		
		this.currentVisual.getContour().setWidth(width);
		
		this.refreshPreview();
	}
	
	@SuppressWarnings("rawtypes")
	private void refreshScreen() {
		JComboBox cboAreaStyle = this.view.getCboAreaStyle();
		JComboBox cboContourStyle = this.view.getCboContourStyle();
		JComboBox cboContourJoin = this.view.getCboContourJoin();
		JComboBox cboContourFinal = this.view.getCboContourFinal();
		JComboBox cboAreaTransparency = this.view.getCboAreaTransparency();
		JPanel pnlAreaColor = this.view.getPnlAreaColor();
		JPanel pnlContourColor = this.view.getPnlContourColor();
		
		cboAreaStyle.setSelectedItem(this.currentVisual.getAreaStyle());
		cboAreaTransparency.setSelectedItem((int)this.currentVisual.getAreaTransparency());
		pnlAreaColor.setBackground(this.currentVisual.getAreaColor());
		
		cboContourStyle.setSelectedItem(this.currentVisual.getContour().getStyle());
		cboContourJoin.setSelectedItem(this.currentVisual.getContour().getJoin());
		cboContourFinal.setSelectedItem(this.currentVisual.getContour().getLineFinal());
		pnlContourColor.setBackground(this.currentVisual.getContour().getColor());
		
		this.view.getTblVisual().repaint();
		this.view.getCanvasPreview().setStyle(this.makeStyle());
	}
	
	private void refreshPreview(){
		this.view.getTblVisual().repaint();
		this.view.getCanvasPreview().setStyle(this.makeStyle());
	}

	/**
	 * This method execute the dispose of {@link PolygonStyleView}.
	 */
	public void executeDispose(){
		this.view.dispose();
	}
	
	/**
	 * This method provides a {@link JColorChooser} to allow the user set
	 * a {@link Color} for the {@link Polygon} style.
	 */
	public void executeSelectAreaColor(){
		JPanel pnlAreaColor = this.view.getPnlAreaColor();
		
		Color newColor = JColorChooser.showDialog(this.view, "Selecione a Cor da Area", pnlAreaColor.getBackground());
		
		if (newColor != null){
			this.currentVisual.setAreaColor(newColor);
			pnlAreaColor.setBackground(newColor);
			this.refreshScreen();
		}
	}
	
	/**
	 * This method provides a {@link JColorChooser} to allow the user set
	 * a {@link Color} for the {@link Polygon} contour style.
	 */
	public void executeSelectContourColor(){
		JPanel pnlContourColor = this.view.getPnlContourColor();
		
		Color newColor = JColorChooser.showDialog(this.view, "Selecione a Cor do Contorno", pnlContourColor.getBackground());
		
		if (newColor != null){
			this.currentVisual.getContour().setColor(newColor);
			pnlContourColor.setBackground(newColor);
			this.refreshScreen();
		}
	}

	public void updateCurrentStyle() {
		JTextField txtContourWidth = this.view.getTxtContourWidth();
		JTable tblVisual = this.view.getTblVisual();
		
		String strWidth = txtContourWidth.getText();
		
		double width = this.currentVisual.getContour().getWidth();
		
		try{
			width = Double.parseDouble(strWidth);
		} catch (NumberFormatException e){
			
		}
		
		this.currentVisual.getContour().setWidth(width);
	
		VisualTableModel model = (VisualTableModel) tblVisual.getModel();
		
		int selectedRow = tblVisual.getSelectedRow();
		
		if (selectedRow == -1){
			selectedRow = 0;
			tblVisual.changeSelection(selectedRow, tblVisual.getColumnCount(), false, false);
		}
		
		PolygonStyleVisual visual = (PolygonStyleVisual) model.getValueAt(selectedRow, 0);
		
		this.currentVisual = visual;
		
		txtContourWidth.setText(String.valueOf(this.currentVisual.getContour().getWidth()));
		
		this.refreshScreen();
		
	}
	
	/**
	 * Creates a new visual on the {@link JTable}.
	 */
	public void executeAddVisual(){
		JTable tblVisual = this.view.getTblVisual();
		
		VisualTableModel model = (VisualTableModel) tblVisual.getModel();
		
		PolygonStyleVisual visual = this.createDefaultVisual();
		
		model.addRow(new Object[]{visual});
		
		this.refreshScreen();
	}
	
	/**
	 * Moves a selected visual to up on the JTable.
	 */
	public void executeVisualUp(){
		JTable tblVisual = this.view.getTblVisual();
		
		int selectedRow = tblVisual.getSelectedRow();
		
		VisualTableModel model = (VisualTableModel) tblVisual.getModel();
		
		if (selectedRow == 0){
			return;
		}
		
		PolygonStyleVisual aboveVisual = (PolygonStyleVisual) model.getValueAt(selectedRow - 1, 0);
		
		model.removeRow(selectedRow - 1);
		
		model.insertRow(selectedRow, new Object[]{aboveVisual});
		
		tblVisual.repaint();
		
		this.refreshScreen();
	}
	
	/**
	 * Moves a selected visual to down on the JTable.
	 */
	public void executeVisualDown(){
		JTable tblVisual = this.view.getTblVisual();
		
		int selectedRow = tblVisual.getSelectedRow();
		
		VisualTableModel model = (VisualTableModel) tblVisual.getModel();
		
		if (selectedRow == model.getRowCount() - 1){
			return;
		}
		
		PolygonStyleVisual underVisual = (PolygonStyleVisual) model.getValueAt(selectedRow + 1, 0);
		
		model.removeRow(selectedRow + 1);
		
		model.insertRow(selectedRow, new Object[]{underVisual});
		
		tblVisual.repaint();
		
		this.refreshScreen();
	}
	
	/**
	 * Delete a selected visual. This method asks to user if he want to delete the visual.
	 */
	public void executeDeleteVisual(){
		JTable tblVisual = this.view.getTblVisual();
		
		int selectedRow = tblVisual.getSelectedRow();
		
		VisualTableModel model = (VisualTableModel) tblVisual.getModel();
		
		if (JOptionPane.showConfirmDialog(this.view, "Deseja Remover este Visual?") == JOptionPane.OK_OPTION){
			if (model.getRowCount() == 1){
				model.addRow(new Object[]{this.createDefaultVisual()});
			}
			model.removeRow(selectedRow);
			tblVisual.changeSelection(0, tblVisual.getColumnCount(), false, false);
		}
		
		tblVisual.repaint();
		
		this.refreshScreen();
	}
	
	/**
	 * Creates a clone of selected visual and add this clone on JTable.
	 */
	public void executeCopyVisual(){
		PolygonStyleVisual clone = this.currentVisual.clone();
		
		JTable tblVisual = this.view.getTblVisual();
		
		VisualTableModel model = (VisualTableModel) tblVisual.getModel();
		
		model.addRow(new Object[]{clone});
		
		tblVisual.repaint();
		
		this.refreshPreview();
	}
	
	private PolygonStyleVisual createDefaultVisual(){
		
		LineStyleVisual contourVisual = new LineStyleVisual(Color.BLACK, LineStyleEnum.SOLIDA, LineFinalEnum.PADRAO, LineJoinEnum.MILTRA, 1.00);
		
		PolygonStyleVisual visual = new PolygonStyleVisual(Color.RED, 0, AreaStyleEnum.SOLIDO, null, 0, contourVisual);
		
		return visual;
	}
}
