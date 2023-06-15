/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public class Feedback {
    String name, email, felling, feedb;

    public Feedback() {
        connect();
    }

    public Feedback(String name, String email, String felling, String feedb) {
        this.name = name;
        this.email = email;
        this.felling = felling;
        this.feedb = feedb;
    connect();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFelling() {
        return felling;
    }

    public void setFelling(String felling) {
        this.felling = felling;
    }

    public String getFeedb() {
        return feedb;
    }

    public void setFeedb(String feedb) {
        this.feedb = feedb;
    }
    Connection cnn;//kết nối DB
    Statement stm; //thực thi các câu lệnh SQL
    ResultSet rs;//Lưu trữ dữ liệu và xử lý
    PreparedStatement pstm;//Thực thi câu lệnh SQL.

    private void connect() {
        try {
            cnn = (new DBContext()).connection;
            if (cnn != null) {
                System.out.println("Connect success");

            }

        } catch (Exception e) {
        }

    }
    
    public ArrayList<Feedback> getListFeedback() {
        ArrayList<Feedback> data = new ArrayList<>();
        try {
            String strSelect = "select * from feedback";

            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);

            while (rs.next()) {
                String name = ""+rs.getString(1);
                String email = rs.getString(2);
                String feeling = rs.getString(3);
                String feedback = rs.getString(4);



                data.add(new Feedback(name, email, felling, feedback));

            }

        } catch (Exception e) {
            System.out.println("getListFeedback:" + e.getMessage());

        }
        return data;
    }

    public void add(String name, String email, String felling, String feedback) {
        
    }
    
    
    
    
}
