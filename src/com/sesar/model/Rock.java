package com.sesar.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
* Java bean used in Jersey
*
* @author  Bai
* @version 1.0
* @since   2017-07-11
*/

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
