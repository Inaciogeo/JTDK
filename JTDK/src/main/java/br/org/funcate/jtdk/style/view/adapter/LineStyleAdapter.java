package br.org.funcate.jtdk.style.view.adapter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.org.funcate.jtdk.style.controller.LineStyleController;
import br.org.funcate.jtdk.style.view.LineStyleView;

/**
 * This class is responsible to listen the {@link LineStyleView} events.
 * 
 * @author Moraes, Emerson Leite.
 * 
 */
public class LineStyleAdapter implements ActionListener, ListSelectionListener, CaretListener {

	private LineStyleView view;

	private LineStyleController controller;

	public LineStyleAdapter(LineStyleView view) {
		this.view = view;
		controller = new LineStyleController(this.view);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.view.getBtnColor()) {
			this.controller.executeSelectColor();
		} else if (e.getSource() == this.view.getBtnCancel()) {
			this.controller.executeDispose();
		} else if (e.getSource() == this.view.getBtnAdd()) {
			this.controller.executeAddVisual();
		} else if (e.getSource() == this.view.getCboLineStyle()) {
			this.controller.executeSelectStyle();
		} else if (e.getSource() == this.view.getCboJoinLine()) {
			this.controller.executeSelectJoin();
		} else if (e.getSource() == this.view.getCboFinalLine()) {
			this.controller.executeSelectFinal();
		} else if (e.getSource() == this.view.getBtnUpArrow()) {
			this.controller.executeVisualUp();
		} else if (e.getSource() == this.view.getBtnDownArrow()) {
			this.controller.executeVisualDown();
		} else if (e.getSource() == this.view.getBtnDeleteVisual()) {
			this.controller.executeDeleteVisual();
		} else if (e.getSource() == this.view.getBtnCopyVisual()) {
			this.controller.executeCopyVisual();
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			return;
		}
		this.controller.updateCurrentStyle();
	}

	@Override
	public void caretUpdate(CaretEvent e) {

		if (e.getSource() == this.view.getTxtLineWidth()) {
			this.controller.executeSelectLineWidth();
		}

	}
}
