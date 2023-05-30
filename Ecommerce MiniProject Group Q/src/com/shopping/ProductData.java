package com.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProductData {
	public static CartDetails getProduct(int productId,int quantity){
        try {
            //Connection
            Connection connection = ConnectionJDBC.createConnection();
            //Query
            String q = "select * from product where p_id = " + productId + "";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(q);

            while (resultSet.next()){
                int product_id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int price = resultSet.getInt(3);
                String description = resultSet.getString(4);

                System.out.println("Product Id : " + product_id);
                System.out.println("Name : " + name);
                System.out.println("Price : " + price);
                System.out.println("Description : " + description);
                System.out.println("Quantity : " + quantity);
                System.out.println("--------------------------------------");

                return new CartDetails(name, product_id, price, quantity);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static void postCheckOuts(int productId, int quantity){
        boolean f = false;

        try {
            Connection connection = ConnectionJDBC.createConnection();

            String q = "update product set p_quantity = p_quantity - "+ quantity +" where p_id = "+productId+" and p_quantity > 0";

            PreparedStatement statement = connection.prepareStatement(q);
            statement.executeUpdate();
            f = true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
