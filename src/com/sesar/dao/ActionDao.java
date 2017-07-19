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
	private Integer actionNum;
	private Integer maxFeatureActionNum;
	private Integer maxEquipmentNum;
	private Integer maxEqActionBridgeNum;
	private Integer maxActionByBridgeNum = 0;
	private Integer affiliationNum;
	private Integer orgNum;
	private String beginDate; //yyyy-mm-dd
	private String endDate;
	private String actionDescr;
	
	public ActionDao (Sample sample, int sfNum, List<String> queries) {
		this.sample = sample;
		this.sfNum = sfNum;
		this.queries = queries;
		
		actionNum = (Integer)DatabaseUtil.getUniqueResult("SELECT max(action_num) FROM action");
		maxFeatureActionNum = (Integer)DatabaseUtil.getUniqueResult("SELECT max(feature_action_num) FROM feature_action");
		maxEquipmentNum = (Integer)DatabaseUtil.getUniqueResult("SELECT max(equipment_num) FROM eqobj;uipment");
		maxEqActionBridgeNum = (Integer)DatabaseUtil.getUniqueResult("SELECT max(bridge_num) FROM equipment_action");
		Object obj = DatabaseUtil.getUniqueResult("SELECT max(bridge_num) FROM action_by");
		if(obj != null ) maxActionByBridgeNum = (Integer)obj;
		beginDate = sample.getCollectionStartDate();
		endDate = sample.getCollectionEndDate();
		actionDescr = sample.getCollectionMethodDescr();
		orgNum = (Integer)DatabaseUtil.getUniqueResult("select organization_num from organization where organization_name like 'UNKNOWN'");
		setMethod();
	}
	
	public List<String> getQueries() { return queries;}
	
	public String saveData() {
		String error = null;
		String name = sample.getCruiseFieldPrgrm();
		saveAction(name,"Cruise");
		String ename = sample.getPlatformName();
		String etype = sample.getPlatformType();
		String edescr = sample.getPlatformDescr();
		saveEquipmentAction(ename, etype, edescr);
		if(!"".equals(ename)) saveAction(null,"Launch from ship");
		ename = sample.getLaunchPlatformName();
		etype = sample.getLaunchTypeName();
		if(!"".equals(ename)) saveEquipmentAction(ename, etype, null);
		
		String person = sample.getCollector();
		if(!"".equals(person)) setAffiliationNum(person);
		return error;
	}
	
	private void saveAction(String name, String type) {
		if(name != null) name = "'"+name+"'";
		Integer typeNum = (Integer)DatabaseUtil.getUniqueResult("select action_type_num from action_type where action_type_name = '"+type+"'");
		String q = "INSERT INTO action values ("+(++actionNum)+","+typeNum+","+methodNum+",'"+beginDate+"',null,'"+endDate+
				"',null,'"+actionDescr+"',null,"+orgNum+","+name+",null)";
		queries.add(q);
		q ="INSERT INTO feature_action values ("+(++maxFeatureActionNum)+","+sfNum+","+actionNum+")";
		queries.add(q);
	}

	private void saveEquipmentAction(String name, String type, String descr) {
		if(descr != null) descr = "'"+descr+"'";
		Integer typeNum = null;
		Integer eqNum = null;
		Object obj = DatabaseUtil.getUniqueResult("select equipment_type_num from equipment_type where upper(equipment_type_name) = upper('"+type+"')");
		if(obj != null) {
			typeNum = (Integer) obj;
			obj = DatabaseUtil.getUniqueResult("select equipment_num  from equipment where upper(equipment_name) = upper('"+name+"') and equipment_type_num = "+typeNum);
			if(obj != null) eqNum = (Integer)obj;
		} else {
			String q="INSERT INTO equipment_type VALUES ((select max(equipment_type_num+1) from equipment_type),"+type+")";
			queries.add(q);
		}
		if(eqNum == null ) {
			String q  = "INSERT INTO equipment (equipment_num, equipment_code, equipment_name, equipment_type_num, equipment_description) VALUES ("+
					(++maxEquipmentNum)+",'"+name+"','"+name+"',"+typeNum+","+descr+")";
			eqNum = maxEquipmentNum;
			queries.add(q);
		}
		String q = "INSERT INTO equipment_action values ("+(++maxEqActionBridgeNum)+","+eqNum+","+actionNum+")";
		queries.add(q);
	}
	
	private void saveActionBy(String name) {
		
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
	
	private void setAffiliationNum(String person) {
		String []array = person.split(" ");
		int size = array.length;
		String first = null;
		String middle = null;
		String last = null;
		last = "'"+array[size-1]+"'";
		String q = "select person_num from person where upper(last_name)=upper("+last+")";
		if(size == 2) {
			first = "'"+array[0]+"'";	
			q +=" and upper(first_name)=upper("+first+")";
		} else if (size == 3) {
			first = "'"+array[0]+"'";
			middle = "'"+array[1]+"'";
			q +=" and upper(first_name)=upper("+first+") and upper(middle_name)=upper("+middle+")";
		} 
		Integer personNum = null;
		Object obj = DatabaseUtil.getUniqueResult("select person_num from person where last_name="+last);
		if(obj !=null) {
			personNum = (Integer)obj;
			obj = DatabaseUtil.getUniqueResult("select affiliation_num from affiliation where person_num"+personNum);
			if(obj != null) affiliationNum = (Integer)obj;
			//find affiation
		}
		else {
			personNum = (Integer)DatabaseUtil.getUniqueResult("select max(person_num+1) from person");
			q = "insert into person values ("+personNum+","+first+","+middle+","+last+",1)";
			queries.add(q);
		}
	
	}	
}
