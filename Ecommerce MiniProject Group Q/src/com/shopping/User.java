package com.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class User {
	static Scanner sc = new Scanner(System.in);

	public static boolean registerUser() {
		boolean f = false;

		System.out.println("Welcome to the Registration Page");
		System.out.println("Please Enter your Username");
		String username = sc.nextLine();

		System.out.println("Please Enter your Password");
		String password = sc.nextLine();

		System.out.println("Please Enter your Address");
		String address = sc.nextLine();

		System.out.println("Please Enter your Phone Number");
		String phoneNumber = sc.nextLine();

		System.out.println("Please Enter your Full Name");
		String fullName = sc.nextLine();

		System.out.println("Please Enter your Email Id");
		String emailId = sc.nextLine();

		try {
			// Connection
			Connection connection = ConnectionJDBC.createConnection();
			// query
			String q = "insert into users(username, password, address, phone_number, full_name, email_id) values(?, ?, ?, ?, ?, ?)";
			// Adding Values
			PreparedStatement statement = connection.prepareStatement(q);
			// set the value of parameter
			statement.setString(1, username);
			statement.setString(2, password);
			statement.setString(3, address);
			statement.setString(4, phoneNumber);
			statement.setString(5, fullName);
			statement.setString(6, emailId);

			// Execute
			statement.executeUpdate();
			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	public static List<ProductDetails> showAllProducts() {
		List<ProductDetails> productDetailsList = new ArrayList<>();

		try {
			// connection
			Connection connection = ConnectionJDBC.createConnection();
			// Query
			String q = "select * from product";

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(q);
			while (resultSet.next()) {
				int product_id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				int price = resultSet.getInt(3);
				String description = resultSet.getString(4);

				ProductDetails productDetails = new ProductDetails(product_id, name, price, description);
				productDetailsList.add(productDetails);

			}
			Comparator<ProductDetails> comparator = Comparator.comparing(ProductDetails::getName);
			productDetailsList.sort(comparator);

			System.out.println("--------------------------------------------------");
			for (ProductDetails productDetails : productDetailsList) {
				System.out.println("Name : " + productDetails.getName());
				System.out.println("Description : " + productDetails.getDescription());
				System.out.println("Product Id : " + productDetails.getProduct_id());
				System.out.println("Price : " + productDetails.getPrice());
				System.out.println("--------------------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productDetailsList;
	}
}
