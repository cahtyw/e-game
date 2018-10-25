import java.sql.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.*;

public class Database{
	private int _code;
	private String _type;
	private String[] database = {"postgres", "sqladmin", "jdbc:postgresql://localhost:5432/e-game"};
	private Connection _pgsql;
	private ResultSet _result;
	private PreparedStatement _pStatement;
	private Statement _statement;

	static {
		try {
			this._pgsql = DriverManager.getConnection(this.database[2], this.database[0], this.database[1]);
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(null, "Deu sql, amigo: "+e.getMessage());
		}
		catch(ClassNotFoundException e){
			JOptionPane.showMessageDialog(null, "Deu sql2, amigo: "+e.getMessage());
		}
		JOptionPane.showMessageDialog(null, "ASDASJDADASJKDASD");
	}

	private static void setCode(int value){
		this._code = value;
	}

	private static void setType(String value){
		this._type = value;
	}

	private static int getCode(){
		return this._code;
	}

	private static String getType(){
		return this._type;
	}

	static void teste (){

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
			this._pStatement = _pgsql.prepareStatement(sql_in);
			int cont = 1;
			for(Object any : list2){
				this._pStatement.setObject(cont++, any);
			}
			_pStatement.execute();
			_pStatement.close();
		}
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Deu merda, amigo: "+e.getMessage());
		}
		JOptionPane.showMessageDialog(null, "Sei lรก: "+sql_in);
		Database.disconnect();
	}

	public static void select(String table, ArrayList<String> list){
		String sql_in = "SELECT ";
		try{
			Database.connect();
			this._pStatement = _pgsql.prepareStatement(null);
			int cont = 0;
			for(String any : list){
				if(cont == 0){
					sql_in+="?";
				}
				else{
					sql_in+=", ?";
				}
				_pStatement.setString(cont++, any);
			}
			sql_in+=" FROM "+table;
			_pStatement.execute(sql_in);
			_pStatement.close();
		}
		catch(Exception e){

		}
		Database.disconnect();
	}

	public static void select(String table, ArrayList<String> list, ArrayList<String> where){
		String sql_in = "SELECT ";
		try{
			Database.connect();
			this._pStatement = _pgsql.prepareStatement(null);
			int cont = 0;
			for(String any : list){
				if(cont == 0){
					sql_in+="?";
				}
				else{
					sql_in+=", ?";
				}
				_pStatement.setString(cont++, any);
			}
			sql_in+=" FROM "+table;
			sql_in+= " WHERE TRUE ";
			for(String any : where){
				sql_in+= (" AND "+any);
			}
			_pStatement.execute(sql_in);
			_pStatement.close();
		}
		catch(Exception e){

		}
		Database.disconnect();
	}


	public static void drop(String table, ArrayList<String> where){
		String sql_in = "DELETE FROM "+table+" WHERE TRUE";
		try{
			Database.connect();
			this._pStatement = _pgsql.prepareStatement(null);
			int cont = 0;
			for(String any : where){
				sql_in+=(" AND"+any);
			}
			_pStatement.execute(sql_in);
			_pStatement.close();
		}
		catch(Exception e){

		}
		Database.disconnect();
	}

	public static void update(String table, ArrayList<String> list, ArrayList<String> where){
		String sql_in = "UPDATE "+table+" SET";
		try{
			Database.connect();
			this._pStatement = _pgsql.prepareStatement(null);
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
			_pStatement.execute(sql_in);
			_pStatement.close();
		}
		catch(Exception e){

		}
		Database.disconnect();
	}

	private static void connect(){
		try{
			Class.forName(database[3]);
			_pgsql=DriverManager.getConnection(database[2],database[0],database[1]);
		}
		catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	private static void disconnect(){
		try{
			_pgsql.close();
		}
		catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
