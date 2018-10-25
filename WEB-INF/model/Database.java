package model;

import java.sql.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.*;

public class Database
{
	private static int code;
	private static String type;
	private static String[] database = {"postgres", "sqladmin", "jdbc:postgresql://localhost:5432/e-game"};
	private static Connection pgsql;
	private static ResultSet result;
	private static PreparedStatement preparedStatement;
	private static Statement statement;

	static {
		try {
			Database.pgsql = DriverManager.getConnection(Database.database[2], Database.database[0], Database.database[1]);
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(null, "Deu sql, amigo: "+e.getMessage());
		}
		//JOptionPane.showMessageDialog(null, "ASDASJDADASJKDASD");
	}

	private static void setCode(int value){
		Database.code = value;
	}

	private static void setType(String value){
		Database.type = value;
	}

	private static int getCode(){
		return Database.code;
	}

	private static String getType(){
		return Database.type;
	}

	public static void teste (){
		Database.connect();
	}

	public static void insert(String table, ArrayList<?> list){
		ArrayList<Object> list2 = new ArrayList<Object>();
		String sql_in = "INSERT INTO "+table+" VALUES(DEFAULT";
		try{
			Database.connect();
			for(Object any : list){
				sql_in+=", ?";
				list2.add(any);
			}
			sql_in+= ")";
			Database.preparedStatement = pgsql.prepareStatement(sql_in);
			int cont = 1;
			for(Object any : list2){
				Database.preparedStatement.setObject(cont++, any);
			}
			preparedStatement.execute();
			preparedStatement.close();
		}
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Deu merda, amigo: "+e.getMessage());
		}
		JOptionPane.showMessageDialog(null,"xxxxxxxxxxx"+sql_in);
		Database.disconnect();
	}

	public static void select(String table, ArrayList<String> list){
		String sql_in = "SELECT ";
		try{
			Database.connect();
			Database.preparedStatement = pgsql.prepareStatement(null);
			int cont = 0;
			for(String any : list){
				if(cont == 0){
					sql_in+="?";
				}
				else{
					sql_in+=", ?";
				}
				preparedStatement.setString(cont++, any);
			}
			sql_in+=" FROM "+table;
			preparedStatement.execute(sql_in);
			preparedStatement.close();
		}
		catch(Exception e){

		}
		Database.disconnect();
	}

	public static void select(String table, ArrayList<String> list, ArrayList<String> where){
		String sql_in = "SELECT ";
		try{
			Database.connect();
			Database.preparedStatement = pgsql.prepareStatement(null);
			int cont = 0;
			for(String any : list){
				if(cont == 0){
					sql_in+="?";
				}
				else{
					sql_in+=", ?";
				}
				preparedStatement.setString(cont++, any);
			}
			sql_in+=" FROM "+table;
			sql_in+= " WHERE TRUE ";
			for(String any : where){
				sql_in+= (" AND "+any);
			}
			preparedStatement.execute(sql_in);
			preparedStatement.close();
		}
		catch(Exception e){

		}
		Database.disconnect();
	}


	public static void drop(String table, ArrayList<String> where){
		String sql_in = "DELETE FROM "+table+" WHERE TRUE";
		try{
			Database.connect();
			Database.preparedStatement = pgsql.prepareStatement(null);
			int cont = 0;
			for(String any : where){
				sql_in+=(" AND"+any);
			}
			preparedStatement.execute(sql_in);
			preparedStatement.close();
		}
		catch(Exception e){

		}
		Database.disconnect();
	}

	public static void update(String table, ArrayList<String> list, ArrayList<String> where){
		String sql_in = "UPDATE "+table+" SET";
		try{
			Database.connect();
			Database.preparedStatement = pgsql.prepareStatement(null);
			int cont = 0;
			for(String any : list){
				if(cont == 0){
					sql_in+=any;
				}
				else{
					sql_in+=(", "+any);
				}
				cont++;
			}
			sql_in+= " WHERE TRUE ";
			for(String any : where){
				sql_in+=(" AND"+any);
			}
			preparedStatement.execute(sql_in);
			preparedStatement.close();
		}
		catch(Exception e){

		}
		Database.disconnect();
	}

	private static void connect(){
		try{
			pgsql=DriverManager.getConnection(database[2],database[0],database[1]);
		}
		catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	private static void disconnect(){
		try{
			pgsql.close();
		}
		catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
