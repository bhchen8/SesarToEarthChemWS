package com.sesar.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


public class Classification implements Serializable {

	private Rock rock;

	public Rock getRock() {
		return rock;
	}
	@XmlElement(name = "Rock")
	public void setRock(Rock rock) {
		this.rock = rock;
	}	
	
}
