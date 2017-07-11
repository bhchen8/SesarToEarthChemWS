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
public class ExternalUrl implements Serializable {

	private String url;
	private String description;
	private String urlType;//url_type
	
	public String getUrl() {
		return url;
	}
	@XmlElement
	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}
	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrlType() {
		return urlType;
	}
	@XmlElement(name = "url_type")
	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}
	
	
}
