/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

/**
 *
 * @author nguye
 */
public class Admin {
    String account, password, email,name;

    public Admin() {
        connect();
    }

    public Admin(String account, String password) {
        this.account = account;
        this.password = password;
        connect();
    }

    public Admin(String account, String password, String email,String name) {
        this.account = account;
        this.password = password;
        this.email = email;
        this.name = name;
        connect();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    //khai báo các thành phần xử lý DB
    Connection cnn;//kết nối DB
    Statement stm; //thực thi các câu lệnh SQL
    ResultSet rs;//Lưu trữ dữ liệu và xử lý
    PreparedStatement pstm;//Thực thi câu lệnh SQL.
    
    private void connect() {
        try {
            cnn=(new DBContext()).connection;
            if(cnn!=null){
                System.out.println("Connect success");
                
            }
           
        } catch (Exception e) {
        }
        
    }
    
     public boolean checkAdmin() {

 try {
        String strSelect = "SELECT * FROM admin WHERE username=? AND password=?";
        pstm = cnn.prepareStatement(strSelect);
        pstm.setString(1, account);
        pstm.setString(2, password);
        rs = pstm.executeQuery();
            while(rs.next()){
                return true;
            }
            
        } catch (Exception e) {
            System.out.println("checkAdmin:"+e.getMessage());
            
        }
        return false;
        
    }
     
      public Admin getAdmin(String account) {
         Admin data = new Admin();
        try {
            String strSelect ="select * from admin where username= ?";
            
        pstm = cnn.prepareStatement(strSelect);
        pstm.setString(1, account);
        rs = pstm.executeQuery();
            
            while(rs.next()){
                account = rs.getString(1);
                String password = rs.getString(2);
                String email = rs.getString(3);
                String name = rs.getString(4);

                data = new Admin(account, password, email, name);

                return data;
                
            }
      
            
        } catch (Exception e) {
            System.out.println("getAdmin:"+e.getMessage());
            
        }
         return null;
     
     
     }
    
    
}
