package com.chahar.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryBrowserApp {
	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String dbURL = "jdbc:mysql://192.168.10.60:3306/ggv_data?useSSL=true";
		String username = "root";
		String password = "root";

		try {

			Connection conn = DriverManager.getConnection(dbURL, username, password);

			if (conn != null) {
				System.out.println("Connected");
				displayTableData(conn, "pa_registration_mst");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static void displayTableData(Connection conn, String tableName) {
		try {
			Statement statement = conn.createStatement();
			String query = "select * from " + tableName+" limit 20";
			ResultSet rs = statement.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			Integer columnCount = rsmd.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				System.out.print(rsmd.getColumnName(i) + "\t");
			}
			while (rs.next())
				for (int i = 1; i <= columnCount; i++) {
					System.out.print(rs.getString(i) + "\t");
				}
			System.out.println();
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}

