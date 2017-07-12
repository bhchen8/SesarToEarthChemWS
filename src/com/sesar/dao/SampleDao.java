package com.sesar.dao;

import java.util.ArrayList;
import java.util.List;

import com.sesar.model.*;
import com.sesar.util.DatabaseUtil;
/**
* Retrieve data from XML and create updating queries for saving to database.
*
* @author  Bai
* @version 1.0
* @since   2017-07-11 
*/

public class SampleDao {

	private Sample sample;
	private int sfNum; //sampling_feature_num
	private List<String> queries = new ArrayList<String>();
	
	public SampleDao (Sample sample) {
		this.sample= sample;
		Object obj = DatabaseUtil.getUniqueResult("select max(sampling_feature_num) from sampling_feature");
		if(obj != null) sfNum = (Integer)obj; 
		else sfNum = 0;
	}
	
	public String saveDataToDB() {		
		String error = saveSamplingFeature();
		if(error==null) error=saveSpecimen();
		else return error;
		if(error==null) error=saveSamplingFeatureExternalIdentifierForIGSN();
		else return error;
	/*	System.out.println("bc-name: "+sample.getSampleType()+sample.getName());
		SampleOtherNames others = sample.getSampleOtherNames();
		String othernames = others.getSampleOtherName().get(0);
		System.out.println("bc-name2: "+othernames);
		ExternalUrls externalUrls = sample.getExternalUrls();
		List<ExternalUrl> urls = externalUrls.getExternalUrl();
		System.out.println("bc-name3: "+urls.get(0).getUrl());
		Classification classification = sample.getClassification();
		Rock rock = classification.getRock();
		rock.getMetamorphic().getMetamorphicType();
		System.out.println("bc-name4: "+rock.getMetamorphic().getMetamorphicType());
		System.out.println("bc-name5: "+sfNum);
		*/
		error = DatabaseUtil.update(queries);			
		return error;
	}

	
	private String saveSamplingFeature() {
		String name = sample.getName();
		Object obj =  DatabaseUtil.getUniqueResult("select sampling_feature_code from sampling_feature s where upper(sampling_feature_code) = upper('"+name+"')");
		if(obj != null) return "Sample name "+name+ " already exists in database";
		String type = sample.getSampleType();
		obj = DatabaseUtil.getUniqueResult("select sampling_feature_type_num from sampling_feature_type where upper(sampling_feature_type_name) = upper('"+type+"')");
		if(obj == null) return "sample_type: "+type+" is not found in database";
		int typeNum = (Integer)obj;
		String geometry = getGeometry(sample.getStartPoint(), sample.getEndPoint());
		String q = "INSERT INTO sampling_feature values ("+(++sfNum)+","+typeNum+",'"+name+"',null,'sesar',"+geometry+sample.getElevationM()+",'"+sample.getVerticalDatum()+"')";
		queries.add(q);	
		return null;
	}
	
	private String saveSpecimen() {
		String material = sample.getMaterial();
		if(material == null) return null;
		if(material.equals("Mineral")) material = "MIN";
		Object obj =  DatabaseUtil.getUniqueResult("select material_num from material where upper(material_code) = upper('"+material+"')");
		if(obj == null) return "Material "+material+ " is not found in database";
		String q ="INSERT INTO specimen VALUES ("+sfNum+","+obj+",'f')";
		queries.add(q);
		return null;
	}
	
	private String saveSamplingFeatureExternalIdentifierForIGSN() {
		String ic = sample.getIgsn();
		Object obj =  DatabaseUtil.getUniqueResult("select max(bridge_num) from sampling_feature_external_identifier");
		int bridgeNum = 1;
		if(obj != null ) bridgeNum = (Integer)obj+1;
		String q ="INSERT INTO sampling_feature_external_identifier VALUES ("+bridgeNum+","+sfNum+",upper('"+ic+"'),null,2)";
		queries.add(q); 	
		String ip = sample.getParentIgsn();
		if(ip != null && !"".equals(ip)) {
			obj =  DatabaseUtil.getUniqueResult("select sample_feature_num from sampling_feature_external_identifier where sampling_feature_external_id = upper('"+ip+"')");
			if(obj == null) return "Parent IGSN "+ip + " is not found in database";
			ip = ""+obj;
			obj= DatabaseUtil.getUniqueResult("select max(related_feature_num+1) from related_feature");
			q = "INSERT INTO related_feature VALUES ("+obj+","+sfNum+",13," +ip+")";		
			queries.add(q); 
		} 
		return null;
	}
	
	private String getGeometry(String p1, String p2) {
   	 if(p2 != null && p1 != null) {return "'LINE' ,ST_SetSRID(ST_MakeLINE(ST_MakePoint("+p1+"), ST_MakePoint("+p2+")), 4326),";  		
   	 } else if (p1 != null) {
   		 return "'POINT',ST_SetSRID(ST_MakePoint("+p1+"), 4326),";
   	 } else {
   		 return "'N/A',ST_SetSRID(ST_MakePoint(90,0), 4326),";
   	 }
    }
/*	
	private boolean saveSamplingFeature() {
		boolean flag = true;
		return flag;
	}
*/	
	
	
}
