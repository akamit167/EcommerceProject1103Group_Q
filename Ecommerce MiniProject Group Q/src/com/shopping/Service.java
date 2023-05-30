package com.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Service {
	static Scanner sc = new Scanner(System.in);

	static List<CartDetails> cart = new ArrayList<>();
	static String userName = "";

	public static void eCommMarket() {
		System.out.println("Welcome to Shopping Website");
		System.out.println("Please login or register to continue");
		System.out.println("To Login, press 1 or to Register press 2");

		// Login and Registration
		int i = sc.nextInt();
		sc.nextLine();
		if (i == 1) {
			System.out.println();
			String username = getLogin();
			userName = username;
			System.out.println("Welcome " + userName);
			System.out.println();
		} else if (i == 2) {
			registerUser();
		} else {
			System.out.println("Incorrect input, please try again");
			eCommMarket();
		}
		
        if (userName.equals("admin")){
            Admin.adminRights();
        }
        else {
            //Products
            System.out.println("We have these products");
            List<ProductDetails> productDetails = User.showAllProducts();

            //Adding into cart
            cart(productDetails);

        }
		

	}

	public static String getLogin() {
		System.out.println("Please enter Username");
		String username = sc.nextLine();
		System.out.println("Please enter Password");
		String password = sc.nextLine();
		checkFromDb(username, password);
		return username;
	}

	public static void checkFromDb(String username, String password) {
		try {
			// Connection
			Connection connection = ConnectionJDBC.createConnection();

			// Query
			String q = "select username, password from users where username = ? and password = ?";

			// Adding values
			PreparedStatement statement = connection.prepareStatement(q);

			// Setting Values of parameter
			statement.setString(1, username);
			statement.setString(2, password);

			// Execute
			ResultSet resultSet = statement.executeQuery();
			if (!resultSet.next()) {
				System.out.println("Wrong Username and Password");
				System.out.println("If you want to try again, press 1 or else press 2 to register");
				int i = sc.nextInt();

				if (i == 1) {
					getLogin();
				} else if (i == 2) {
					registerUser();
				}
			} else {
				System.out.println("You are successfully Logged in");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void registerUser() {
		boolean b = User.registerUser();
		if (b) {
			System.out.println("User Registered Successfully");
			System.out.println("Redirecting to Login Page..........");
			getLogin();
		} else {
			System.out.println("Something went wrong, try again");
			eCommMarket();
		}
	}
	
	public static void cart(List<ProductDetails> productDetails){

        System.out.println("To add into cart please enter the specified Product Id");
        int productId = sc.nextInt();

        if(productId > productDetails.size()){
            System.out.println("Invalid product id, please enter again");
            User.showAllProducts();
            cart(productDetails);
        }
        else {

            sc.nextLine();
            addToCart(productDetails, productId);
        }
    }
    private static List<CartDetails> addToCart(List<ProductDetails> productDetails, int productId) {
        System.out.println("Enter quantity");
        int quantity = sc.nextInt();

        CartDetails product = ProductData.getProduct(productId, quantity);
        cart.add(product);


        System.out.println("Do you want to add more items to the cart, If yes press 1 or for no press 0");
        int addMore = sc.nextInt();
        if (addMore == 1){
            User.showAllProducts();
            cart(productDetails);
        }
        else {
            System.out.println("Do you want to check out ?");
            System.out.println("Press 1 to check out or press 0 check more products");
            int checkOut = sc.nextInt();
            if (checkOut == 1){
                checkOut(cart);

            }
            else {
                User.showAllProducts();
                System.out.println("Press 0 to go on checkout page, or press 1 to add to cart");
                int j = sc.nextInt();
                if (j == 1){
                    cart(productDetails);
                }
                else if(j == 0){
                    checkOut(cart);
                }
                else {
                    System.out.println("Invalid Input");
                    System.out.println("Redirecting to products page.....");
                    User.showAllProducts();
                    cart(productDetails);
                }
            }
        }
        return cart;
    }
    public static void checkOut(List<CartDetails> cart){
        int total = 0;
        System.out.println("You cart");
        for (int i = 0; i < cart.size(); i++) {
            total += cart.get(i).getProductPrice() * cart.get(i).getQuantityToAdd();
            System.out.println("Product Name : " + cart.get(i).getProductName());
            System.out.println("Product Id : " + cart.get(i).getProductId());
            System.out.println("Product Price : " + cart.get(i).getProductPrice());
            System.out.println("Quantity : " + cart.get(i).getQuantityToAdd());
            System.out.println("--------------------------------------------------");
        }
        System.out.println("Total Amount : " + total);
        System.out.println("--------------------------------------------------");

        System.out.println();
        System.out.println("Press 1 to Proceed to Payment page, else press 0  to exit");
        int i = sc.nextInt();
        if (i==1){
            postCheckOut(cart);
        }
        else {
            System.out.println("Thank You for using us, Have a great day");
        }
        
    } 
        public static void postCheckOut(List<CartDetails> cart){
            UserData.isTablePresent(userName);
            for (int i = 0; i < cart.size(); i++) {
                ProductData.postCheckOuts(cart.get(i).getProductId(), cart.get(i).getQuantityToAdd());
                UserData.userHistory(userName, cart.get(i).getProductId(), cart.get(i).getProductName(), cart.get(i).getProductPrice(),cart.get(i).getQuantityToAdd());
            }
            System.out.println("Thank you for shopping with us, have a great day");
        }
}
