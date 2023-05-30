package com.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Admin {
	static Scanner sc = new Scanner(System.in);

    public static void adminRights() {
        System.out.println("As an Admin you have few rights");
        System.out.println();
        System.out.println("Press 1 to check quantity of any product");
        System.out.println("Press 2 to see registered user list");
        System.out.println("Press 3 to check user history for product purchase");
        System.out.println("Press 4 to add Product to Database");
        int right = sc.nextInt();
        switch (right) {
            case 1: {
                showQuantity();
                break;
            }
            case 2: {
                showAllRegisteredUsers();
                break;
            }
            case 3: {
                showUserHistory();
                break;
            }
            case 4:{
            	insertProductsToDb();
            }
        }

        System.out.println("Press 1 if you want to perform any more actions, else press any key to exit");
        int i = sc.nextInt();
        if (i==1){
            adminRights();
        }
        else {
            System.out.println("Thank you for using us, have a great day");
        }

    }

    public static void showUserHistory() {
        sc.nextLine();
        System.out.println("Enter username");
        String username = sc.nextLine();
        try {
            //Connection
            Connection connection = ConnectionJDBC.createConnection();
            //Query
            String q = "select * from " + username + "";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(q);

            while (resultSet.next()) {
                int product_id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int price = resultSet.getInt(3);
                int quantity = resultSet.getInt(4);

                System.out.println("Product Id : " + product_id);
                System.out.println("Name : " + name);
                System.out.println("Price : " + price);
                System.out.println("Quantity : " + quantity);
                System.out.println("--------------------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showAllRegisteredUsers() {
        try {
            //Connection
            Connection connection = ConnectionJDBC.createConnection();
            //Query
            String q = "select * from users";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(q);

            while (resultSet.next()) {
                String username = resultSet.getString(1);
                String password = resultSet.getString(2);
                String address = resultSet.getString(3);
                String phoneNumber = resultSet.getString(4);
                String fullName = resultSet.getString(5);
                String emailId = resultSet.getString(6);


                System.out.println("UserName : " + username);
                System.out.println("Password : " + password);
                System.out.println("Address : " + address);
                System.out.println("Phone Number : " + phoneNumber);
                System.out.println("Full Name : " + fullName);
                System.out.println("Email Id : " + emailId);
                System.out.println("--------------------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showQuantity() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Product Id");
        int n = sc.nextInt();
        try {
            //Connection
            Connection connection = ConnectionJDBC.createConnection();

            //Query
            String q = "select p_quantity from product where p_id = " + n + "";

            //Statement

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(q);

            while (resultSet.next()) {
                int quantity = resultSet.getInt(1);

                System.out.println("Quantity : " + quantity);
                System.out.println("--------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showAllProducts() {
        try {
            //Connection
            Connection connection = ConnectionJDBC.createConnection();
            //Query
            String q = "select * from product";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(q);

            while (resultSet.next()) {
                int product_id = resultSet.getInt(1);
                String name = resultSet.getString(4);
                int price = resultSet.getInt(3);
                String description = resultSet.getString(2);
                int quantity = resultSet.getInt(5);

                System.out.println("Product Id : " + product_id);
                System.out.println("Name : " + name);
                System.out.println("Price : " + price);
                System.out.println("Description : " + description);
                System.out.println("Quantity : " + quantity);
                System.out.println("--------------------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertProductsToDb() {


        PreparedStatement pst=null;
		Connection connection=null;
		try {
            //Connection
           connection = ConnectionJDBC.createConnection();
            //Query
            String query = "Insert into product values \r\n" + "(?,?,?,?,?);";

			pst = connection.prepareStatement(query);

			Scanner sc = new Scanner(System.in);
			System.out.println("How Many inputs you want to Enter");
			int TotalInput = sc.nextInt();

			for (int i = 0; i <= TotalInput - 1; i++) {

				System.out.println("Enter Product Id");
				int PId = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter Product Name");
				String PName = sc.nextLine();
				
				System.out.println("Enter Product Description");
				String Pdescription = sc.nextLine();
				System.out.println("Enter Available Qty");
				int AvailQty = sc.nextInt();
				System.out.println("Enter Product Price");
				int PPrice = sc.nextInt();

				pst.setInt(1, PId);
				pst.setString(2, PName);
				pst.setNString(4, Pdescription);
				pst.setInt(5, AvailQty);
				pst.setInt(3, PPrice);
				
				int i1;
				i1 = pst.executeUpdate();
				System.out.println(i1 + " Rows Inserted Succesfully");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			try {
				if (connection != null || pst != null) {
					connection.close();
					pst.close();
				}
			} catch (SQLException e) {

				e.printStackTrace();

			}
		}

	}


}
