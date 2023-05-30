package com.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class UserData {
	public static void isTablePresent(String username) {
		try {
			Connection connection = ConnectionJDBC.createConnection();

			String q = "select * from " + username + "";

			Statement statement = connection.createStatement();

			statement.executeQuery(q);

		} catch (Exception e) {
			createTable(username);
		}
	}

	public static void createTable(String username) {
		try {
			// Connection
			Connection connection = ConnectionJDBC.createConnection();

			// Query
			String q = "create table " + username + " (\n" + "product_id int,\n" + "name varchar(20),\n"
					+ "price int,\n" + "quantity int)";

			// Statement
			Statement statement = connection.createStatement();
			statement.executeUpdate(q);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void userHistory(String username, int productId, String name, int price, int quantity) {
		try {
			// Connection
			Connection connection = ConnectionJDBC.createConnection();
			// Query
			String q = "insert into " + username + "(product_id, name, price,quantity) values(?, ?, ?, ?)";

			// Adding Values
			PreparedStatement statement = connection.prepareStatement(q);

			// Set the value of parameter
			statement.setInt(1, productId);
			statement.setString(2, name);
			statement.setInt(3, price);
			statement.setInt(4, quantity);

			// Execute
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
