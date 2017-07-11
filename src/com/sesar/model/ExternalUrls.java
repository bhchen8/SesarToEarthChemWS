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
