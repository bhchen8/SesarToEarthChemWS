package com.sesar.dao;

import java.util.ArrayList;
import java.util.List;

import com.sesar.model.*;
import com.sesar.util.DatabaseUtil;
/**
* Retrieve data from XML and create updating queries for saving to action, equipment, equipment_action and action_by
* 
* @author  Bai
* @version 1.0
* @since   2017-07-18
*/

public class ActionDao {

	private Sample sample;
	private int sfNum; //sampling_feature_num
	private List<String> queries;
//	private String error;
	private Integer methodNum;
	private Integer maxActionNum;
	private Integer maxFeatureActionNum;
	private Integer maxEquipmentNum;
	private Integer maxEqActionBridgeNum;
	private Integer maxActionByBridgeNum = 0;
	private String beginDate;
	private String endDate;
	private String actionDescr;
	
	public ActionDao (Sample sample, int sfNum, List<String> queries) {
		this.sample = sample;
		this.sfNum = sfNum;
		this.queries = queries;
		
		maxActionNum = (Integer)DatabaseUtil.getUniqueResult("SELECT max(action_num) FROM action");
		maxFeatureActionNum = (Integer)DatabaseUtil.getUniqueResult("SELECT max(feature_action_num) FROM feature_action");
		maxEquipmentNum = (Integer)DatabaseUtil.getUniqueResult("SELECT max(equipment_num) FROM eqobj;uipment");
		maxEqActionBridgeNum = (Integer)DatabaseUtil.getUniqueResult("SELECT max(bridge_num) FROM equipment_action");
		Object obj = DatabaseUtil.getUniqueResult("SELECT max(bridge_num) FROM action_by");
		if(obj != null ) maxActionByBridgeNum = (Integer)obj;
		beginDate = sample.getCollectionStartDate();
		endDate = sample.getCollectionEndDate();
		actionDescr = sample.getCollectionMethodDescr();
		setMethod();
	}
	
	public List<String> getQueries() { return queries;}
	
	public String saveData() {
		String error = null;
		saveCruiseAction();
		return error;
	}
	
	private String saveCruiseAction() {
		String name = sample.getCruiseFieldPrgrm();
		return null;
	}
	
	private void setMethod() {
		String method = sample.getCollectionMethod();
		Object obj = DatabaseUtil.getUniqueResult("SELECT method_num FROM method where method_code = ''"+method+"'");
		if(obj != null) methodNum = (Integer)obj;
		else {
			Integer typeNum = (Integer) DatabaseUtil.getUniqueResult("SELECT feature_of_interest_type_num FROM feature_of_interest_type where feature_of_interest_type_name = 'Collection Method'");
			methodNum = (Integer)DatabaseUtil.getUniqueResult("SELECT max(method_num+1) FROM method");
			queries.add("INSERT INTO method values ("+methodNum+","+typeNum+",'"+method+"','"+method+"','Sesar Method')");
		}		
	}		
}
