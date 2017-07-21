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
	private Integer maxActionNum;
	private Integer maxFeatureActionNum;
	private Integer maxEquipmentNum;
	private Integer maxEqActionBridgeNum;
	private Integer maxActionByBridgeNum = 0;
	private Integer orgNum;
	private String beginDate; //yyyy-mm-dd
	private String endDate;
	private String actionDescr;
	
	public ActionDao (Sample sample, int sfNum, List<String> queries) {
		this.sample = sample;
		this.sfNum = sfNum;
		this.queries = queries;
		maxFeatureActionNum = (Integer)DatabaseUtil.getUniqueResult("SELECT max(feature_action_num) FROM feature_action");
		maxEquipmentNum = (Integer)DatabaseUtil.getUniqueResult("SELECT max(equipment_num) FROM equipment");
		maxEqActionBridgeNum = (Integer)DatabaseUtil.getUniqueResult("SELECT max(bridge_num) FROM equipment_action");
		maxActionNum = (Integer)DatabaseUtil.getUniqueResult("select max(action_num) from action");
		beginDate = sample.getCollectionStartDate();
		endDate = sample.getCollectionEndDate();
		actionDescr = sample.getCollectionMethodDescr();
		if(!"".equals(actionDescr)) actionDescr = "'"+actionDescr+"'";
		else actionDescr = null;
		orgNum = (Integer)DatabaseUtil.getUniqueResult("select organization_num from organization where organization_name like 'UNKNOWN'");
		Object obj = DatabaseUtil.getUniqueResult("SELECT max(bridge_num) FROM action_by");
		if(obj != null ) maxActionByBridgeNum = (Integer)obj;
		setMethod();
	}
	
	public List<String> getQueries() { return queries;}
	
	public void saveData() {
		Integer affiliationNum=null;
		String person = sample.getCollector();
		if(!"".equals(person)) affiliationNum = getAffiliationNum(person);
		//action and equipment_action
		String name = sample.getCruiseFieldPrgrm();
		saveAction(name,"Cruise");
		//save action_by ?
		String ename = sample.getPlatformName();
		String etype = sample.getPlatformType();
		String edescr = sample.getPlatformDescr();
		if(!"".equals(ename) && !"".equals(etype)) saveEquipmentAction(ename, etype, edescr);
		if(affiliationNum != null) saveActionBy(affiliationNum);
		name = sample.getLaunchId();
		ename = sample.getLaunchPlatformName();
		etype = sample.getLaunchTypeName();
		if(!"".equals(name)) saveAction(name,"Launch from ship");
		if(!"".equals(ename) && !"".equals(etype)) saveEquipmentAction(ename, etype, null);
		if(affiliationNum != null) saveActionBy(affiliationNum);
	}
	
	private void saveAction(String name, String type) {
		Integer typeNum = (Integer)DatabaseUtil.getUniqueResult("select action_type_num from action_type where action_type_name = '"+type+"'");
		if(name != null || beginDate != null ||  actionDescr != null || methodNum != null) {
		
			String q = "select action_num from action where action_type_num ="+typeNum;
	
			if(name != null) {
				name = "'"+name+"'";
				q += " and upper(action_name) = upper("+name+")"; 
			}
			if(beginDate != null) q += " and begin_date_time ='"+beginDate+"' ";
			if(actionDescr != null) q += " and upper(action_description) = upper("+actionDescr+")";
			if(methodNum != null) q += " and method_num="+methodNum;
			Object obj = DatabaseUtil.getUniqueResult(q);
			if(obj != null) actionNum = (Integer)obj;
			else {
				actionNum = ++maxActionNum;
				q = "INSERT INTO action values ("+actionNum+","+typeNum+","+methodNum+",'"+beginDate+"',null,'"+endDate+
					"',null,"+actionDescr+",null,"+orgNum+","+name+",null)";
				queries.add(q);
			}	
		}
		else {
			actionNum = (Integer)DatabaseUtil.getUniqueResult("select action_num from action where action_type_num ="+typeNum+" and action_name like 'Unknown%' ");
		}
		queries.add("INSERT INTO feature_action values ("+(++maxFeatureActionNum)+","+sfNum+","+actionNum+")");
	
	}

	private void saveEquipmentAction(String name, String type, String descr) {
		if(descr != null) descr = "'"+descr+"'";
		Integer typeNum = null;
		Integer eqNum = null;
		Object obj = DatabaseUtil.getUniqueResult("select equipment_type_num from equipment_type where upper(equipment_type_name) = upper('"+type+"')");
		//set typeNum
		if(obj != null) typeNum = (Integer) obj;		
		else {
			typeNum = (Integer)DatabaseUtil.getUniqueResult("select max(equipment_type_num+1) from equipment_type");
			String q="INSERT INTO equipment_type VALUES ("+typeNum+",'"+type+"','Sesar')";
			queries.add(q);
		}
		//set eqNum
		obj = DatabaseUtil.getUniqueResult("select equipment_num  from equipment where upper(equipment_name) = upper('"+name+"') and equipment_type_num = "+typeNum);
		if(obj != null) eqNum = (Integer)obj;
		else {
			String q  = "INSERT INTO equipment (equipment_num, equipment_code, equipment_name, equipment_type_num, equipment_description) VALUES ("+
					(++maxEquipmentNum)+",'"+name+"','"+name+"',"+typeNum+","+descr+")";
			queries.add(q);
			eqNum = maxEquipmentNum;
		}
		obj = DatabaseUtil.getUniqueResult("select bridge_num from equipment_action where action_num = "+actionNum+" and equipment_num = "+eqNum);
		if(obj == null) {
			String q = "INSERT INTO equipment_action values ("+(++maxEqActionBridgeNum)+","+eqNum+","+actionNum+")";
			queries.add(q);
		}
	}
	
	private void saveActionBy(Integer affiliationNum) {
		
		String descr = sample.getCollectorDetail();
		String q = "INSERT INTO action_by values ("+(++maxActionByBridgeNum)+","+actionNum+","+affiliationNum+",1,'"+descr+"')";
		queries.add(q);
	}

	
	private void setMethod() {
		String method = sample.getCollectionMethod();
		if(!"".equals(method)) {
			Object obj = DatabaseUtil.getUniqueResult("SELECT method_num FROM method where method_code = '"+method+"'");
			if(obj != null) methodNum = (Integer)obj;
			else {
				Integer typeNum = (Integer) DatabaseUtil.getUniqueResult("select method_type_num from method_type where method_type_name = 'Collection'");
				methodNum = (Integer)DatabaseUtil.getUniqueResult("SELECT max(method_num+1) FROM method");
				queries.add("INSERT INTO method values ("+methodNum+","+typeNum+",'"+method+"','"+method+"','Sesar Method')");
			}	
		}
	}	
	
	private Integer getAffiliationNum(String person) {
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
		//Object obj = DatabaseUtil.getUniqueResult("select person_num from person where last_name="+last);
		Object obj = DatabaseUtil.getUniqueResult(q);
		if(obj !=null) {
			personNum = (Integer)obj;
			obj = DatabaseUtil.getUniqueResult("select affiliation_num from affiliation where person_num="+personNum);
			if(obj != null) return (Integer)obj;
		}
		else {
			personNum = (Integer)DatabaseUtil.getUniqueResult("select max(person_num+1) from person");
			q = "insert into person values ("+personNum+","+first+","+middle+","+last+",1)";
			queries.add(q);
		}
		Integer afNum = (Integer) DatabaseUtil.getUniqueResult("select max(affiliation_num+1) from affiliation");
		q ="insert into affiliation values ("+afNum+","+personNum+","+orgNum+")";
		queries.add(q);
		return afNum;
	}	
}
