package br.org.funcate.jtdk.style.view.adapter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.org.funcate.jtdk.style.controller.PolygonStyleController;
import br.org.funcate.jtdk.style.view.PolygonStyleView;

public class PolygonStyleAdapter implements ActionListener, ListSelectionListener, CaretListener{

	private PolygonStyleView view;
	
	private PolygonStyleController controller;
	
	public PolygonStyleAdapter(PolygonStyleView view){
		this.view = view;
		this.controller = new PolygonStyleController(this.view);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.view.getBtnAreaColor()){
			this.controller.executeSelectAreaColor();
		} else if (e.getSource() == this.view.getBtnContourColor()){
			this.controller.executeSelectContourColor();
		} else if (e.getSource() == this.view.getBtnCancel()){
			this.controller.executeDispose();
		} else if (e.getSource() == this.view.getCboAreaStyle()){
			this.controller.executeSelectAreaStyle();
		} else if (e.getSource() == this.view.getCboAreaTransparency()){
			this.controller.executeSelectAreaTransparency();
		} else if (e.getSource() == this.view.getCboContourStyle()){
			this.controller.executeSelectContourStyle();
		} else if (e.getSource() == this.view.getCboContourFinal()){
			this.controller.executeSelectContourFinal();
		} else if (e.getSource() == this.view.getCboContourJoin()){
			this.controller.executeSelectContourJoin();
		} else if (e.getSource() == this.view.getBtnAddVisual()){
			this.controller.executeAddVisual();
		} else if (e.getSource() == this.view.getBtnUpArrow()){
			this.controller.executeVisualUp();
		} else if (e.getSource() == this.view.getBtnDownArrow()){
			this.controller.executeVisualDown();
		} else if (e.getSource() == this.view.getBtnDeleteVisual()){
			this.controller.executeDeleteVisual();
		} else if (e.getSource() == this.view.getBtnCopyVisual()){
			this.controller.executeCopyVisual();
		}
		
	}

	@Override
	public void caretUpdate(CaretEvent e) {
		if (e.getSource() == this.view.getTxtContourWidth()){
			this.controller.executeSelectContourWidth();
		}
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()){
			return;
		}
		
		this.controller.updateCurrentStyle();
		
	}

}
