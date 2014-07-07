package br.org.funcate.jtdk.canvas.event;

import java.util.EventObject;

import com.vividsolutions.jts.geom.Envelope;

@SuppressWarnings("serial")
public class ChangeBoxEvent extends EventObject{

	private Envelope box;
	
	public ChangeBoxEvent(Object arg0, Envelope box) {
		super(arg0);
		// TODO Auto-generated constructor stub
		this.setBox(box);
	}

	public Envelope getBox() {
		return box;
	}

	public void setBox(Envelope box) {
		this.box = box;
	}
	
	

	
}
