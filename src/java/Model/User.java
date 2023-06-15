/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public class User {
    private String account,email,password,name,dob,gender,address,money,inroom;

    public User() {
        connect();
    }

    public User(String account, String password) {
        this.account = account;
        this.password = password;
        connect();
    }

    public User(String account, String email, String password, String name, String gender,  String dob,String address,String money,String inroom) {
        this.account = account;
        this.email = email;
        this.password = password;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.money = money;
        this.inroom = inroom;
        connect();
    }

    public String getInroom() {
        return inroom;
    }

    public void setInroom(String inroom) {
        this.inroom = inroom;
    }
    

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
    

    public String getAccount() {
        return account;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
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
    
     public boolean checkUser() {

 try {
        String strSelect = "SELECT * FROM Users WHERE account=? AND password=?";
        pstm = cnn.prepareStatement(strSelect);
        pstm.setString(1, account);
        pstm.setString(2, password);
        rs = pstm.executeQuery();
            while(rs.next()){
                return true;
            }
            
            
        } catch (Exception e) {
            System.out.println("checkUser:"+e.getMessage());
            
        }
        return false;
        
    }
     
     public ArrayList<User> getListUser() {
        ArrayList<User> data = new ArrayList<User>();
        try {
            String strSelect ="select * from Users";
            
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs=stm.executeQuery(strSelect);
            
            while(rs.next()){
                String account = rs.getString(1);
                String email = rs.getString(2);
                String password = rs.getString(3);
                String name = rs.getString(4);
                String gender="Male";
                if(!rs.getBoolean(5)){
                    gender="Female";
                }
               
                
                SimpleDateFormat f= new SimpleDateFormat("dd-MM-yyyy");
                String dob="";
                if(rs.getDate(6)!=null){
                    dob= f.format(rs.getDate(6));
                }
                
                 String address = rs.getString(7);
                 String money = rs.getString(8);
                
                    String inroom = "NotIndeed";
                    if (rs.getBoolean(9)) {
                        inroom = "Indeed";
                    }
                data.add(new User(account, email, password, name, gender, dob,address,money,inroom));
                
                
            }
            
            
        } catch (Exception e) {
            System.out.println("getListUser:"+e.getMessage());
            
        }
         return data;
    }
     
     public String getNameByAccount(String account) {
         try {
            String strSelect ="select * from Users where account='"+account+"'";
            pstm.setString(1, account);
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs=stm.executeQuery(strSelect);
            while(rs.next()){
                String name = rs.getString(3);
                return name;
            }
            
            
        } catch (Exception e) {
            System.out.println("getNameByAccount:"+e.getMessage());
            
        }
         return "";
    }
     


      public User getUser(String account) {
         User data = new User();
        try {
            String strSelect ="select * from Users where account= ?";
            
        pstm = cnn.prepareStatement(strSelect);
        pstm.setString(1, account);
        rs = pstm.executeQuery();
            
            while(rs.next()){
                account = rs.getString(1);
                String email = rs.getString(2);
                String password = rs.getString(3);
                String name = rs.getString(4);
                String gender="Male";
                if(!rs.getBoolean(5)){
                    gender="Female";
                }
               
                
                SimpleDateFormat f= new SimpleDateFormat("dd-MM-yyyy");
                String dob="";
                if(rs.getDate(6)!=null){
                    dob= f.format(rs.getDate(6));
                }
                
                 String address = rs.getString(7);
                 String money = rs.getString(8);
                
                 String inroom ="NotIndeed";
                 if(rs.getBoolean(9)== true){
                     inroom = "Indeed";
                 }
                data = new User(account, email, password, name, gender, dob,address,money,inroom);

                return data;
                
            }
            
            
        } catch (Exception e) {
            System.out.println("getUser:"+e.getMessage());
            
        }
         return null;
     
     
     }
    public void updateMoney(double price) {
        
         try {
             
            String strUpdate="update Users set money=?,inroom=?  where account=?";
            pstm=cnn.prepareStatement(strUpdate);
           
            pstm.setDouble(1,price);
            pstm.setString(2, "1");
            pstm.setString(3,account);

            pstm.execute();
            
            connect();

            
        } catch (Exception e) {
            
            System.out.println("Updatemoney: " +e.getMessage());
        }
        
    }

    public void updatepay(double updatepaym) {
        try {
             
            String strUpdate="update Users set money=?  where account= ?";
            pstm=cnn.prepareStatement(strUpdate);
            pstm.setDouble(1,updatepaym);
            pstm.setString(2,account);

            pstm.execute();
            
            connect();

            
        } catch (Exception e) {
            
            System.out.println("updatepay: " +e.getMessage());
        }
    }
    
    public boolean checkUserExist(String account) {

 try {
        String strSelect = "SELECT * FROM Users WHERE account=?";
        pstm = cnn.prepareStatement(strSelect);
        pstm.setString(1, account);
        rs = pstm.executeQuery();
            while(rs.next()){
                return true;
            }
            
            
        } catch (Exception e) {
            System.out.println("checkUserExist:"+e.getMessage());
            
        }
        return false;
        
    }

    public void addnewUser() {
        try {

            String strAdd = "insert into Users values(?,?,?,?,?,?,?,?,?)";
            pstm = cnn.prepareStatement(strAdd);
            
            pstm.setString(1, account);
            pstm.setString(2, email);
            pstm.setString(3, password);
            pstm.setString(4, name);
            pstm.setInt(5, Integer.parseInt(gender));
            pstm.setDate(6, Date.valueOf(dob));
            pstm.setString(7, address);
            pstm.setDouble(8, 0);
            pstm.setInt(9, 0);

            pstm.execute();

        } catch (Exception e) {

            System.out.println("addnewUser: " + e.getMessage());
        }
    }

    public void outroom(String user) {
        try {
             
            String strUpdate="update booking set notes='delete'  where account= ?";
            pstm=cnn.prepareStatement(strUpdate);
         
            pstm.setString(1,account);

            pstm.execute();
            
            connect();

            
        } catch (Exception e) {
            
            System.out.println("updateoutroom: " +e.getMessage());
        }
    }
    
}
