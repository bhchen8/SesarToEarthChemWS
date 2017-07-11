package com.sesar.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


public class SampleOtherNames implements Serializable {

	private List<String> sampleOtherName;

	public List<String> getSampleOtherName() {
		return sampleOtherName;
	}
	@XmlElement(name = "sample_other_name")
	public void setSampleOtherName(List<String> sampleOtherName) {
		this.sampleOtherName = sampleOtherName;
	}
	
}
