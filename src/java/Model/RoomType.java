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
public class RoomType {
    
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
    
    String rtypeid,number,price;

    public RoomType() {
        connect();
    }

    public RoomType(String rtypeid, String number, String price) {
        this.rtypeid = rtypeid;
        this.number = number;
        this.price = price;
        connect();
    }
    public ArrayList<RoomType> getListRoomType() {
        ArrayList<RoomType> data = new ArrayList<>();
        try {
            String strSelect = "select * from roomtypes";

            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);

            while (rs.next()) {
                String roomtypeid = ""+rs.getInt(1);
                String number = ""+rs.getInt(2);
                String price = ""+rs.getDouble(3);



                data.add(new RoomType(roomtypeid, number, price));

            }

        } catch (Exception e) {
            System.out.println("getListRoomType:" + e.getMessage());

        }
        return data;
    }

    public String getRtypeid() {
        return rtypeid;
    }

    public void setRtypeid(String rtypeid) {
        this.rtypeid = rtypeid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
    
    
}
