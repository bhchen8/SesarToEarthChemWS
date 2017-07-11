package com.sesar.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


public class Rock implements Serializable {

	private Metamorphic metamorphic;
	
	public Metamorphic getMetamorphic() {
		return metamorphic;
	}
	@XmlElement(name = "Metamorphic")
	public void setMetamorphic(Metamorphic metamorphic) {
		this.metamorphic = metamorphic;
	}
	
	
}
