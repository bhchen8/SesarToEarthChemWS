package com.sesar.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class ExternalUrls implements Serializable {

	private List<ExternalUrl> externalUrl;

	public List<ExternalUrl> getExternalUrl() {
		return externalUrl;
	}
	@XmlElement (name = "external_url")
	public void setExternalUrl(List<ExternalUrl> externalUrl) {
		this.externalUrl = externalUrl;
	}

	
}
