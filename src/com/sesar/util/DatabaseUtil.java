package com.sesar.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
* This class is used for making database connection and for retrieving data from database by JDBC. 
*
* @author  Bai
* @version 1.0
* @since   2017-07-06 
*/
public class DatabaseUtil {

	private static DataSource dataSource;

    static {
        try {
        	//update at C:\apache-tomcat-8.0.39\conf\context.xml
            dataSource = (DataSource) new InitialContext().lookup( "java:/comp/env/jdbc/postgres" );
        }
        catch (NamingException e) { 
        	 System.err.println(e);
        }
    }
    
    public static List<Object[]> getRecords(String query) {
    	List<Object[]> records=new ArrayList<Object[]>();
    	Connection con = null;
    	Statement stmt = null;
    	ResultSet rs = null;
    	
    	try {
    		con = dataSource.getConnection();
    		stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            int cols = rs.getMetaData().getColumnCount();
            while(rs.next()){
            	 Object[] arr = new Object[cols]; 
            	 for(int i=0; i<cols; i++){ 
            		 arr[i] = rs.getObject(i+1); 
            	 } 
            	 records.add(arr); }
    	} catch (SQLException e) { 
       	 	System.err.println(e);
        } finally {
        	try {
        		if(rs != null) rs.close();
        		if(stmt != null) stmt.close();
        		if(con != null) con.close();   
        	} catch (SQLException e) {
        		System.err.println(e);
        	}
        }
    	return records;
    }
    
    public static Object getUniqueResult(String query) {
    	Object record =null;
    	Connection con = null;
    	Statement stmt = null;
    	ResultSet rs = null;
    	
    	try {
    		con = dataSource.getConnection();
    		stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            if(rs.next())
            	 record = rs.getObject(1);
    	} catch (SQLException e) { 
       	 	System.err.println(e);
        } finally {
        	try {
        		if(rs != null) rs.close();
        		if(stmt != null) stmt.close();
        		if(con != null) con.close();   
        	} catch (SQLException e) {
        		System.err.println(e);
        	}
        }
    	return record;
    }
    
    public static String update(List<String> queries) {
    	String error = null;
    	Connection con = null;
    	Statement stmt = null;
    	
    	try {
    		con = dataSource.getConnection();
    		stmt = con.createStatement();
    		con.setAutoCommit(false);
    		for(String q: queries) stmt.executeUpdate(q);
    		con.commit();
    	} catch (SQLException e) { 
       	 	System.err.println(e);
       	 	error = e.getMessage();
        } finally {
        	try {
        		if(stmt != null) stmt.close();
        		if(con != null) con.close();   	
        	} catch (SQLException e) {
        		System.err.println(e);
        	}
        }  
    	return error;       	
    }
}
