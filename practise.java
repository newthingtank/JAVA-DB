package exercises;

/*
 *JdbcEx1.java
 *Created on Apr 1, 2015
 *Purpose: Jdbc.java - connect to a database using Oracle Thin Driver
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;


/**
 * @author Rui
 */
  public class practise{
	 
	  static String option = "1";
	  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	  static Connection conn = null;
	 
	  static Statement stmt = null;
	  static String dbURL="jdbc:oracle:thin:@127.0.0.1:1521:XE";
	  static String user = "projects"; /**your login here*/
	  static String password = "projects";  /*use your password here*/

	  //new stuff
	  //System.out.print("Enter the Customer ID");
      static String sqlVQ1 = "SELECT productid AS \"Code\", RPAD(productname, 20, ' ') AS \"Product\"， "
		 +"To_Char(unitprice, '$9,999.99') AS \"Price\" FROM VQ1"; 
	 
      static String sqlVQ2 = "SELECT Orderid AS \"Order\", To_char(Orderdate,'yyyy-mm-dd') AS \"Order Date\"， "
		 +"To_Char(shippeddate, 'yyyy-mm-dd') AS \"ship date\", companyname FROM VQ2"; 
	 

	  
	  public static void main(String argus[]) throws SQLException, IOException{
		  
		  /**
		   *Register the driver with the Class 
		   *
		   */
		  
		
		  
		  try{
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			  } catch (ClassNotFoundException e){
				  	System.err.println(e.getMessage());
			  }
		  
		  /**
		   * Open and close the connection
		   * 
		   */
		  try{
			  Class.forName("oracle.jdbc.driver.OracleDriver");
		
		  }catch(ClassNotFoundException e){
			  System.err.println(e.getMessage());
		  }
		  /**
		   * 
		   *open and close the connection
		   */
		   try{
			   
			   conn=DriverManager.getConnection(dbURL, user, password);
			   conn.clearWarnings();
			   System.out.println("Connection opened!for driver ==>Oracle 11XE!");
			   stmt= conn.createStatement();
			   ResultSet rs = stmt.executeQuery("select * from Products");
		 
			   
			   
			   
			   
			   
			   while (!option.equalsIgnoreCase("X")){
				   System.out.print("Want to do Q1 (1) or Q2 (2) or (X) exit: ");
				   option = br.readLine();
				   System.out.println("");
				   
				   if (option.equalsIgnoreCase("1")){
				   	doVQ1(conn);
				  }
				   if (option.equalsIgnoreCase("2")){
					   doVQ2(conn);
				   }
				  
				   System.out.println("\n");
			   }

			rs.close();  
		  
		   }catch(SQLException e){
			   System.err.println(e.getMessage());
		}finally{
			if(!conn.isClosed()){
				conn.close();
				System.out.println("Connection closed! Oracle");
			}
		}
	  }
	         private static void doVQ1 (Connection conn) throws SQLException{
	        	// Statement stmt = null;
	        	 stmt = conn.createStatement();
	        	 ResultSet rs = stmt.executeQuery(sqlVQ1);
	        	ResultSetMetaData rsm = rs.getMetaData();
	        	
	        	System.out.println(rsm.getColumnName(1) + "\t\t" + rsm.getColumnName(2)+"\t\t   "
		    		  +"\t$"+rsm.getColumnName(3));
		    			  
	        	
	        //	 System.out.println("Code\t\t       Product\t\t    Price");
	        	 System.out.println("=======\t======================\t\t======");    	 
  
	        	  while(rs.next()){
	        		
	    			  System.out.println(rs.getString(1) + "\t "
	    			  + rs.getString(2)+"\t\t"+rs.getString(3));
	    			  }
	    			rs.close();  
	         }
	         
	    	 private static void doVQ2 (Connection conn2) throws SQLException{
	    				  
	    				   
	    				     Statement stmt = null;
	    		        	 stmt = conn2.createStatement();
	    		        	 ResultSet rs = stmt.executeQuery(sqlVQ2);
	    		        	 									//select * from vq2;
	    		        	 
	    		        	 System.out.println("Order\t Order Date\tShip Date\tCompany");
	    		        	 System.out.println("=====\t ==========\t=========\t=======");
	    		        	  while(rs.next()){
	    		        			  System.out.println(rs.getInt(1)+" \t" +" "+rs.getString(2) 
	    		        					  				+" \t"+ rs.getString(3)+" "+rs.getString(4));
	    		        			  											//rs.getDate(2)+" \t"+rs.getDate(3)
	    		        	  }
	    		        	  
	    		        	 
	    		    			rs.close();  
	    		    		
	    		    			
	         }
	         


				

	  }
	  
	  
  