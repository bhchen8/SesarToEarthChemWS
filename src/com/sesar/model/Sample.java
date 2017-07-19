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
	private String material;
	private String igsn;
	private String parentIgsn; //parent_igsn;
	private String classificationComment; //classification_comment;
	private String fieldName; //field_name;
	private String description;
	private Float ageMin; //age_min;
	private Float ageMax; //age_max
	//private String ageUnit; //age_unit
	private String geologicalAge; //geological_age
	private String geologicalUnit; //geological_unit
	private String collectionMethod; //collection_method
	private Float size;
	private String sizeUnit; //size_unit
	private String sampleComment;//sample_comment
	private String navigationType;//navigation_type
	private String primaryLocationType;//primary_location_type
	private String primaryLocationName;//primary_location_name
	private String locationDescription;//location_description
	private String locality;
	private String localityDescription;//locality_description
	private String country;//country
	private String province;
	private String county;
	private String city;
	private String currentArchive; //current_archive
	private String currentArchiveContact;//current_archive_contact
	private String originalArchive;//original_archive
	private String originalArchiveContact;
	private Float depthMin;//depth_min;
	private Float depthMax;//depth_max;
	private String depthScale;//depth_scale
	private String collectionStartDate;//collection_start_date
	private String collectionEndDate;//collection_end_date
	private String collectionMethodDescr; //collection_method_descr
	private String cruiseFieldPrgrm;//cruise_field_prgrm
	private String platformType;//platform_type
	private String platformName;//platform_name
	private String platformDescr;//platform_descr
	private String launchPlatformName;//launch_platform_name
	private String launchId;//launch_id
	private String launchTypeName;//
	private String collector;//collector
	private String collectorDetail;//collector_detail
/*	
	
	private Float purpose;//What is the extension_property.property_data_unit_num for "purpose"?
	
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
	public String getMaterial() {
		return material;
	}
	@XmlElement (name = "material")
	public void setMaterial(String material) {
		this.material = material;
	}
	
	public String getIgsn() {
		return igsn;
	}
	@XmlElement (name = "igsn")
	public void setIgsn(String igsn) {
		this.igsn = igsn;
	}
	public String getParentIgsn() {
		return parentIgsn;
	}
	@XmlElement (name = "parent_igsn")
	public void setParentIgsn(String parentIgsn) {
		this.parentIgsn = parentIgsn;
	}
	
	public String getClassificationComment() {
		return classificationComment;
	}
	@XmlElement (name = "classification_comment")
	public void setClassificationComment(String classificationComment) {
		this.classificationComment = classificationComment;
	}	
	public String getFieldName() {
		return fieldName;
	}
	@XmlElement (name = "field_name")
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getDescription() {
		return description;
	}
	@XmlElement (name = "description")
	public void setDescription(String description) {
		this.description = description;
	}	
	public Float getAgeMin() {
		return ageMin;
	}
	@XmlElement (name = "age_min")
	public void setAgeMin(Float ageMin) {
		this.ageMin = ageMin;
	}
	public Float getAgeMax() {
		return ageMax;
	}
	@XmlElement (name = "age_max")
	public void setAgeMax(Float ageMax) {
		this.ageMax = ageMax;
	}
	public String getGeologicalAge() {
		return geologicalAge;
	}
	@XmlElement (name = "geological_age")
	public void setGeologicalAge(String geologicalAge) {
		this.geologicalAge = geologicalAge;
	}
	public String getGeologicalUnit() {
		return geologicalUnit;
	}
	@XmlElement (name = "geological_unit")
	public void setGeologicalUnit(String geologicalUnit) {
		this.geologicalUnit = geologicalUnit;
	}	
	public String getCollectionMethod() {
		return collectionMethod;
	}
	@XmlElement (name = "collection_method")
	public void setCollectionMethod(String collectionMethod) {
		this.collectionMethod = collectionMethod;
	}
	public Float getSize() {
		return size;
	}
	@XmlElement
	public void setSize(Float size) {
		this.size = size;
	}
	public String getSizeUnit() {
		return sizeUnit;
	}
	@XmlElement (name = "size_unit")
	public void setSizeUnit(String sizeUnit) {
		this.sizeUnit = sizeUnit;
	}
	public String getSampleComment() {
		return sampleComment;
	}
	@XmlElement (name = "sample_comment")
	public void setSampleComment(String sampleComment) {
		this.sampleComment = sampleComment;
	}	
	public String getNavigationType() {
		return navigationType;
	}
	@XmlElement (name = "navigation_type")
	public void setNavigationType(String navigationType) {
		this.navigationType = navigationType;
	}
	public String getPrimaryLocationType() {
		return primaryLocationType;
	}
	@XmlElement (name = "primary_location_type")
	public void setPrimaryLocationType(String primaryLocationType) {
		this.primaryLocationType = primaryLocationType;
	}
	public String getPrimaryLocationName() {
		return primaryLocationName;
	}
	@XmlElement (name = "primary_location_name")
	public void setPrimaryLocationName(String primaryLocationName) {
		this.primaryLocationName = primaryLocationName;
	}
	public String getLocationDescription() {
		return locationDescription;
	}
	@XmlElement (name = "location_description")
	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}
	public String getLocality() {
		return locality;
	}
	@XmlElement
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public String getLocalityDescription() {
		return localityDescription;
	}
	@XmlElement (name = "locality_description")
	public void setLocalityDescription(String localityDescription) {
		this.localityDescription = localityDescription;
	}
	public String getCountry() {
		return country;
	}
	@XmlElement
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	@XmlElement
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCounty() {
		return county;
	}
	@XmlElement
	public void setCounty(String county) {
		this.county = county;
	}
	public String getCity() {
		return city;
	}
	@XmlElement
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCurrentArchive() {
		return currentArchive;
	}
	@XmlElement (name = "current_archive")
	public void setCurrentArchive(String currentArchive) {
		this.currentArchive = currentArchive;
	}
	public String getCurrentArchiveContact() {
		return currentArchiveContact;
	}
	@XmlElement (name = "current_archive_contact")
	public void setCurrentArchiveContact(String currentArchiveContact) {
		this.currentArchiveContact = currentArchiveContact;
	}
	public String getOriginalArchive() {
		return originalArchive;
	}
	@XmlElement (name = "original_archive")
	public void setOriginalArchive(String originalArchive) {
		this.originalArchive = originalArchive;
	}
	public String getOriginalArchiveContact() {
		return originalArchiveContact;
	}
	@XmlElement (name = "original_archive_contact")
	public void setOriginalArchiveContact(String originalArchiveContact) {
		this.originalArchiveContact = originalArchiveContact;
	}	
	public Float getDepthMin() {
		return depthMin;
	}
	@XmlElement (name = "depth_min")
	public void setDepthMin(Float depthMin) {
		this.depthMin = depthMin;
	}
	public Float getDepthMax() {
		return depthMax;
	}
	@XmlElement (name = "depth_max")
	public void setDepthMax(Float depthMax) {
		this.depthMax = depthMax;
	}
	
	public String getDepthScale() {
		return depthScale;
	}
	@XmlElement (name = "depth_scale")
	public void setDepthScale(String depthScale) {
		this.depthScale = depthScale;
	}
	public String getCollectionStartDate() {
		return collectionStartDate;
	}
	@XmlElement (name = "collection_start_date")
	public void setCollectionStartDate(String collectionStartDate) {
		this.collectionStartDate = collectionStartDate;
	}
	public String getCollectionEndDate() {
		return collectionEndDate;
	}
	@XmlElement (name = "collection_end_date")
	public void setCollectionEndDate(String collectionEndDate) {
		this.collectionEndDate = collectionEndDate;
	}	
	public String getCollectionMethodDescr() {
		return collectionMethodDescr;
	}
	@XmlElement (name = "collection_method_descr")
	public void setCollectionMethodDescr(String collectionMethodDescr) {
		this.collectionMethodDescr = collectionMethodDescr;
	}	
	public String getCruiseFieldPrgrm() {
		return cruiseFieldPrgrm;
	}
	@XmlElement (name = "cruise_field_prgrm")
	public void setCruiseFieldPrgrm(String cruiseFieldPrgrm) {
		this.cruiseFieldPrgrm = cruiseFieldPrgrm;
	}	
	public String getPlatformType() {
		return platformType;
	}
	@XmlElement (name = "platform_type")
	public void setPlatformType(String platformType) {
		this.platformType = platformType;
	}
	public String getPlatformName() {
		return platformName;
	}
	@XmlElement (name = "platform_name")	
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
	public String getPlatformDescr() {
		return platformDescr;
	}
	@XmlElement (name = "platform_descr")
	public void setPlatformDescr(String platformDescr) {
		this.platformDescr = platformDescr;
	}
	public String getLaunchPlatformName() {
		return launchPlatformName;
	}
	@XmlElement (name = "launch_platform_name")
	public void setLaunchPlatformName(String launchPlatformName) {
		this.launchPlatformName = launchPlatformName;
	}
	public String getLaunchId() {
		return launchId;
	}
	@XmlElement (name = "launch_id")
	public void setLaunchId(String launchId) {
		this.launchId = launchId;
	}
	@XmlElement (name = "launch_type_name")
	public String getLaunchTypeName() {
		return launchTypeName;
	}
	public void setLaunchTypeName(String launchTypeName) {
		this.launchTypeName = launchTypeName;
	}
	public String getCollector() {
		return collector;
	}
	@XmlElement (name = "collector")
	public void setCollector(String collector) {
		this.collector = collector;
	}
	public String getCollectorDetail() {
		return collectorDetail;
	}
	@XmlElement (name = "collector_detail")
	public void setCollectorDetail(String collectorDetail) {
		this.collectorDetail = collectorDetail;
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
