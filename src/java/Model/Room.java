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
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public class Room {

    String roomid, roomname, room_img, number, price, buildingname, member;

    public Room() {
        connect();
    }

    public Room(String roomid, String roomname, String room_img, String number, String price, String buildingname, String member) {
        this.roomid = roomid;
        this.roomname = roomname;
        this.room_img = room_img;
        this.number = number;
        this.price = price;
        this.buildingname = buildingname;
        this.member = member;
        connect();
    }

    public String getRoomid() {
        return roomid;
    }

    public String getRoomname() {
        return roomname;
    }

    public String getRoom_img() {
        return room_img;
    }

    public String getNumber() {
        return number;
    }

    public String getPrice() {
        return price;
    }

    public String getBuildingname() {
        return buildingname;
    }

    public String getMember() {
        return member;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public void setRoom_img(String room_img) {
        this.room_img = room_img;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setBuildingname(String buildingname) {
        this.buildingname = buildingname;
    }

    public void setMember(String member) {
        this.member = member;
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

    public ArrayList<Room> getListRoom() {
        ArrayList<Room> data = new ArrayList<>();
        try {
            String strSelect = "select a.roomid, a.roomname, a.room_img,b.number,b.price,c.buildingname, a.member\n"
                    + "from rooms a, roomtypes b, buildings c\n"
                    + "where a.building_id=c.building_id and b.rtype_id = a.rtype_id";

            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);

            while (rs.next()) {
                String roomid = rs.getString(1);
                String roomname = rs.getString(2);
                String room_img = rs.getString(3);
                String number = ""+rs.getInt(4);
                String price =""+rs.getDouble(5);
                String buildingname = rs.getString(6);
                String member = ""+rs.getInt(7);


                data.add(new Room(roomid, roomname, room_img, number, price, buildingname, member));

            }

        } catch (Exception e) {
            System.out.println("getListRoom:" + e.getMessage());

        }
        return data;
    }
    
         public Room getRoom(String room_id) {
         Room data = new Room();
        try {
            String strSelect ="select a.roomid, a.roomname, a.room_img,b.number,b.price,c.buildingname, a.member\n" +
"                    from rooms a, roomtypes b, buildings c\n" +
"                    where a.building_id=c.building_id and b.rtype_id = a.rtype_id and roomid=?";
            
        pstm = cnn.prepareStatement(strSelect);
        pstm.setString(1, room_id);
        rs = pstm.executeQuery();
            
            while(rs.next()){
                String roomid = rs.getString(1);
                String roomname = rs.getString(2);
                String room_img = rs.getString(3);
                String number = ""+rs.getInt(4);
                String price =""+rs.getDouble(5);
                String buildingname = rs.getString(6);
                String member = ""+rs.getInt(7);
                data = new Room(room_id, roomname, room_img, number, price, buildingname, member);
                
  
                return data;
                
            }
                
            
        } catch (Exception e) {
            System.out.println("getUser:"+e.getMessage());
            
        }
         return null;
     
     
     }

    public void upDateSlost(String room_id,int slost_room) {
        
        try {
            String strSelect ="UPDATE rooms SET member = ? WHERE roomid = ?;";
            
        pstm = cnn.prepareStatement(strSelect);
        pstm.setInt(1, slost_room);
        pstm.setInt(2, Integer.parseInt(room_id));
        rs = pstm.executeQuery();
            
           
                
            
        } catch (Exception e) {
            System.out.println("upDateSlost:"+e.getMessage());
            
        }
        
     
    }

    public void add(String id, String name, String buildingid, String roomtype, String img) {
        
        Room data = new Room();
        try {
            String strSelect ="insert into rooms values(?,?,?,?,?,0);";
            
        pstm = cnn.prepareStatement(strSelect);
        pstm.setInt(1, Integer.parseInt(id));
        pstm.setString(2, name);
        pstm.setInt(3, Integer.parseInt(buildingid));
        pstm.setInt(4, Integer.parseInt(roomtype));
        pstm.setString(5, img);
        
        rs = pstm.executeQuery();
            

        } catch (Exception e) {
            System.out.println("add Room:"+e.getMessage());
            
        }
       
     
     
     
    }
    
    

}
