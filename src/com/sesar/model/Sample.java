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

@XmlRootElement(name = "sample")
public class Sample implements Serializable {
	private String sampleType;
	private String name;
	private String latitude;
	private String longitude;
	private String latitudeEnd;//latitude_end
	private String longitudeEnd;//longitude_end
	private float elevation;
	private float elevationEnd;
	private String elevationUnit;
	private String verticalDatum;//vertical_datum
	
/*	private String material;
	private String igsn;
	private String parentIgsn; //parent_igsn;
	private String classificationComment; //classification_comment;
	private String fieldName; //field_name;
	private String description;
	private int ageMin; //age_min;
	private int ageMax; //age_max
	private String ageUnit; //age_unit
	private String geologicalAge; //geological_age
	private String geologicalUnit; //geological_unit
	private String collectionMethod; //collection_method
	private String collectionMethodDescr; //collection_method_descr
	private float size;
	private String size_unit; //size_unit
	private String sampleComment;//sample_comment
	
	
	
	private String navigationType;//navigation_type
	private String primaryLocation_type;//primary_location_type
	private String primaryLocation_name;//primary_location_name
	private String locationDescription;//location_description
	private String locality;
	private String localityDescription;//locality_description
	private String country;//country
	private String province;
	private String county;
	private String city;
	private String cruiseFieldPrgrm;//cruise_field_prgrm
	private String platformType;//platform_type
	private String platformName;//platform_name
	private String platformDescr;//platform_descr
	private String launchPlatformName;//launch_platform_name
	private String launchId;//launch_id
	private String launchTypeName;//
	private String collector;//collector
	private String collectorDetail;//collector_detail
	private String collectionStart_date;//collection_start_date
	private String collectionEnd_date;//collection_end_date
	private String currentArchive; //current_archive
	private String currentArchiveContact;//current_archive_contact
	private String originalArchive;//original_archive
	private String originalArchiveContact;
	private float depthMin;//depth_min;
	private float depthMax;//depth_max;
	private String depthScale;//depth_scale
	private String sampleOtherNames;//sample_other_names
*/	
	private SampleOtherNames sampleOtherNames;
	private ExternalUrls externalUrls;
	private Classification classification;
	
	public String getSampleType() {
		return sampleType;
	}
	@XmlElement(name="sample_type")
	public void setSampleType(String sampleType) {
		this.sampleType = sampleType;
	}
	public String getName() {
		return name;
	}
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	public String getLatitude() {
		return latitude;
	}
	@XmlElement(name="latitude")
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	@XmlElement(name="longitude")
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitudeEnd() {
		return latitudeEnd;
	}
	@XmlElement(name="latitude_end")
	public void setLatitudeEnd(String latitudeEnd) {
		this.latitudeEnd = latitudeEnd;
	}
	public String getLongitudeEnd() {
		return longitudeEnd;
	}
	@XmlElement(name="longitude_end")
	public void setLongitudeEnd(String longitudeEnd) {
		this.longitudeEnd = longitudeEnd;
	}
	public SampleOtherNames getSampleOtherNames() {
		return sampleOtherNames;
	}
	@XmlElement (name="sample_other_names")
	public void setSampleOtherNames(SampleOtherNames sampleOtherNames) {
		this.sampleOtherNames = sampleOtherNames;
	}
	public ExternalUrls getExternalUrls() {
		return externalUrls;
	}
	@XmlElement (name = "external_urls")
	public void setExternalUrls(ExternalUrls externalUrls) {
		this.externalUrls = externalUrls;
	}
	public Classification getClassification() {
		return classification;
	}
	@XmlElement (name = "classification")
	public void setClassification(Classification classification) {
		this.classification = classification;
	}
	public float getElevation() {
		return elevation;
	}
	@XmlElement (name = "elevation")
	public void setElevation(float elevation) {
		this.elevation = elevation;
	}
	public float getElevationEnd() {
		return elevationEnd;
	}
	@XmlElement (name = "elevation_end")	
	public void setElevationEnd(float elevationEnd) {
		this.elevationEnd = elevationEnd;
	}
	public String getElevationUnit() {
		return elevationUnit;
	}
	@XmlElement (name = "elevation_unit")	
	public void setElevationUnit(String elevationUnit) {
		this.elevationUnit = elevationUnit;
	}
	public String getVerticalDatum() {
		return verticalDatum;
	}
	@XmlElement (name = "vertical_datum")
	public void setVerticalDatum(String verticalDatum) {
		this.verticalDatum = verticalDatum;
	}
	/////////////
	public String getStartPoint() {
		if((latitude!=null && !"".equals(latitude))  
				&& (longitude!=null && !"".equals(longitude))) return ""+longitude+","+latitude;
		else return null;
	}
	public String getEndPoint() {
		if((latitudeEnd !=null && !"".equals(latitudeEnd)) && (longitudeEnd !=null && !"".equals(longitudeEnd))) return ""+longitudeEnd+","+latitudeEnd;
		else return null;
	}
	public Float getElevationM() {
		if("feet".equalsIgnoreCase(elevationUnit)) {
			elevation *= 0.3048;
			elevationEnd *= 0.3048;
		}
		if(getEndPoint()!= null) {
			return elevationEnd;
		}
		else if(getStartPoint()!= null) {		
			return elevation;
		}
		else return null;
	}
	
}
