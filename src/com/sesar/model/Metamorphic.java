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

public class Metamorphic implements Serializable {

	private String metamorphicType;
	
	public String getMetamorphicType() {
		return metamorphicType;
	}

	@XmlElement(name = "MetamorphicType")
	public void setMetamorphicType(String metamorphicType) {
		this.metamorphicType = metamorphicType;
	}
	
}
