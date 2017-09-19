package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class OracleConet {


	 //orcl为oracle数据库中的数据库名，localhost表示连接本机的oracle数据库     
	  //1521为连接的端口号     
	   private static String url="jdbc:oracle:thin:@127.0.0.1:1521:orcl";    
	   //system为登陆oracle数据库的用户名     
	   private static String user="mumu";    
	   //manager为用户名system的密码     
	   private static String password="123456";    
	   public static Connection conn;    
	   public static PreparedStatement ps;    
	   public static ResultSet rs;    
	    public static Statement st ;    
	    //连接数据库的方法     
	    public void getConnection(){    
	        try {    
	            //初始化驱动包     
	            Class.forName("oracle.jdbc.driver.OracleDriver");    
	            //根据数据库连接字符，名称，密码给conn赋值     
	            conn=DriverManager.getConnection(url, user, password);    
	                
	        } catch (Exception e) {    
	            // TODO: handle exception     
	            e.printStackTrace();    
	        }    
	    }    
	     //测试能否与oracle数据库连接成功     
	     public static void main(String[] args) {    
	    	 OracleConet basedao=new OracleConet();    
	        basedao.getConnection();    
	        if(conn==null){    
	            System.out.println("与oracle数据库连接失败！");    
	        }else{    
	            System.out.println("与oracle数据库连接成功！");    
	        }    
	     }    

	
	
}
