/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ADMIN
 */
public class DB_Connection {
    public static Connection getCon(){
        
        try{
            String url="jdbc:sqlserver://ADMIN\\SQLEXPRESS";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QuanLyCuaHangDienThoai","sa","123");
        }
        catch(Exception e){
            System.out.println(e);
             return null;
        }
       
        
    }
    public static void main(String[] args) {
        System.out.println(getCon());
    }
}
