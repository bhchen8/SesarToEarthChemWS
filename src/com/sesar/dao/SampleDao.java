package com.sesar.dao;

import java.util.ArrayList;
import java.util.List;

import com.sesar.model.*;
import com.sesar.util.DatabaseUtil;

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
		String error = null;
		saveSamplingFeature();
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
		return error;
	}

	/*
	sampling_feature_num
	sampling_feature_type_num
	sampling_feature_code
	sampling_feature_name
	sampling_feature_description
	sampling_feature_geotype
	feature_geometry
	elevation_m
	elevation_datum
	*/
	private String saveSamplingFeature() {
		String type = sample.getSampleType();
		Object obj = DatabaseUtil.getUniqueResult("select sampling_feature_type_num from sampling_feature_type where upper(sampling_feature_type_name) = upper('"+type+"')");
		System.out.println("bc-1 "+type+":"+obj);
		if(obj == null) return "sample_type: "+type+" is not found in database";
		int typeNum = (Integer)obj;
		String name = sample.getName();
		System.out.println("bc-sample.getEndPoint() "+sample.getEndPoint());
		String geometry = getGeometry(sample.getStartPoint(), sample.getEndPoint());
		String q = "INSERT INTO sampling_feature values ("+(++sfNum)+","+typeNum+",'"+name+"',null,'sesar',"+geometry+sample.getElevationM()+",'"+sample.getVerticalDatum()+"')";
		System.out.println("bc-sfq :"+q);
		queries.add(q);
		DatabaseUtil.update(queries);
/*		INSERT INTO earthchemdb2.earthchem.sampling_feature
		(sampling_feature_num, sampling_feature_type_num, sampling_feature_code, sampling_feature_name, sampling_feature_description, sampling_feature_geotype, feature_geometry, elevation_m, elevation_datum)
	VALUES 
		(, , '', '', '', '', '', , '');
*/		

		
		
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
