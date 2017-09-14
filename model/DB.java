/*************************************************
* Filename      : DB.java
* Programmer    : Ranggi Rahman
* Date          : 2016-05-27
* Email         : ranggirahman@gmail.com
* Website       : www.ranggirahman.id
* Deskripsi     : proram yang menghubungkan program ke DB Sql
*
**************************************************/

package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DB{
	// menampung string koneksi untuk menghubungkan ke database
	private String ConAddress = "jdbc:mysql://localhost/tubes_pbo?user=root&password=";
	private Statement stmt = null;
	private ResultSet rs = null;
	private Connection conn = null;

	public DB() throws Exception, SQLException{

		// melakukan koneksi ke MySQL dan basis data
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			conn = DriverManager.getConnection(ConAddress);
			conn.setTransactionIsolation(conn.TRANSACTION_READ_UNCOMMITTED);
		}catch(SQLException e){
			throw e;
		}
	}

	public void createQuery(String Query)throws Exception, SQLException{

		// eksekusi query tanpa mengubah isi data
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(Query);

			if (stmt.execute(Query)) {
				rs = stmt.executeQuery(Query);
			}
		}catch (SQLException es) {
			throw es;
		}
	}

	public void createUpdate(String Query)throws Exception, SQLException{

		// eksekusi query yang mengubah isi data(update, insert dan delete)
		try{
			stmt = conn.createStatement();
			
			int hasil = stmt.executeUpdate(Query);
		}catch (SQLException es) {
			throw es;
		}
	}

	public ResultSet getResult()throws Exception{

		// memberi hasil query
		ResultSet Temp = null;
		try{
			return rs;
		}catch (Exception ex) {
			return Temp;
		}
	}

	public void closeResult()throws SQLException, Exception{

		// menutup hubungan dari eksekusi query
		if (rs != null) {
			try{
				rs.close();
			}catch (SQLException sqlEx) {
				rs = null;
				throw sqlEx;
			}
		}
		if (stmt != null) {
			try{
				stmt.close();
			}catch (SQLException sqlEx) {
				stmt = null;
				throw sqlEx;
			}
		}
	}

	public void closeConnection()throws SQLException, Exception{
		
		// menutup hubungan dengan MySQL dan basis data
		if (conn != null) {
			try{
				conn.close();
			}catch(SQLException sqlEx){
				conn = null;
			}
		}
	}
}
