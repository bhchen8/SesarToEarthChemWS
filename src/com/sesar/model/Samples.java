package com.sesar.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "samples")
public class Samples implements Serializable {
	private List<Sample> samples;

	public List<Sample> getSamples() {
		return samples;
	}
	@XmlElement(name="sample")
	public void setSamples(List<Sample> samples) {
		this.samples = samples;
	}
	
	

	
}
