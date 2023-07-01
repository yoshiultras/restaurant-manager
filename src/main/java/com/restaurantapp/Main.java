package com.restaurantapp;
import java.sql.*;
public class Main {
    public static void main(String[] args){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver" );
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2313_vine" ,
                    "std_2313_vine" , "12345678" );
            Statement statement = connection.createStatement();
            String query = "SELECT name FROM vine_type;" ;
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                String name = result.getString("name" );
                System.out.print(" , name = \" " + name + " \" " );
            }
            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }

    }
}