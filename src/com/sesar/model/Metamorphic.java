package com.sesar.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


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
