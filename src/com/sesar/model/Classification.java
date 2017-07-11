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
