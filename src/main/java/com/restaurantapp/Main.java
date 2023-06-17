package com.restaurantapp;
import java.sql.*;
public class Main {
    public static void main(String[] args){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver" );
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:22/std_2313_test" ,
                    "std_2313_test" , "12345678" );
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM `author`;" ;
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                int id = result.getInt(" author_id " );
                String name = result.getString(" name_author " );
                System.out.print(" Vacant post: " );
                System.out.print(" id = " + id);
                System.out.print(" , name = \" " + name + " \" " );
            }
            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}