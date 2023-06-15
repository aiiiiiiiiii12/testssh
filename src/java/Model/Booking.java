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
public class Booking {

    String bookingid, room_id, account, in_date, notes, room_name, building_name;

    public Booking() {
        connect();
    }

    public Booking(String room_id, String account, String in_date, String notes) {
        this.room_id = room_id;
        this.account = account;
        this.in_date = in_date;
        this.notes = notes;
        connect();
    }

    public Booking(String bookingid, String room_id, String account, String in_date, String notes) {
        this.bookingid = bookingid;
        this.room_id = room_id;
        this.account = account;
        this.in_date = in_date;
        this.notes = notes;
        connect();
    }

    public Booking(String bookingid, String room_id, String account, String in_date, String notes, String room_name, String building_name) {
        this.bookingid = bookingid;
        this.room_id = room_id;
        this.account = account;
        this.in_date = in_date;
        this.notes = notes;
        this.room_name = room_name;
        this.building_name = building_name;
        connect();
    }

    public String getBuilding_name() {
        return building_name;
    }

    public void setBuilding_name(String building_name) {
        this.building_name = building_name;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public void setBookingid(String bookingid) {
        this.bookingid = bookingid;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setIn_date(String in_date) {
        this.in_date = in_date;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getBookingid() {
        return bookingid;
    }

    public String getRoom_id() {
        return room_id;
    }

    public String getAccount() {
        return account;
    }

    public String getIn_date() {
        return in_date;
    }

    public String getNotes() {
        return notes;
    }

    //khai báo các thành phần xử lý DB
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

    public ArrayList<Booking> getListBooking() {
        ArrayList<Booking> data = new ArrayList<Booking>();
        try {
//            String strSelect ="select * from booking where account=? and room_id=?";
            String strSelect = "select a.booking_id,a.room_id,a.account,a.in_date,a.notes, b.roomname, c.buildingname \n"
                    + "from booking a, rooms b, buildings c where a.room_id = b.roomid\n"
                    + "and b.building_id = c.building_id";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();

            while (rs.next()) {
                String booking_id = "" + rs.getInt(1);

                String room_id = "" + rs.getInt(2);
                String account = rs.getString(3);

                SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
                String in_date = "";
                if (rs.getDate(4) != null) {
                    in_date = f.format(rs.getDate(4));
                }

                String notes = rs.getString(5);
                String room_name = rs.getString(6);
                String building_name = rs.getString(7);
                data.add(new Booking(booking_id, room_id, account, in_date, notes, room_name, building_name));

            }

        } catch (Exception e) {
            System.out.println("getBooking:" + e.getMessage());

        }
        System.out.println(data.size());
        return data;
    }

    public void addBooking() {
        try {

            String strAdd = "INSERT INTO booking (room_id, account, in_date, notes) VALUES (?, ?, ?, ?);";
            pstm = cnn.prepareStatement(strAdd);

            pstm.setInt(1, Integer.parseInt(room_id));
            pstm.setString(2, account);
            pstm.setDate(3, Date.valueOf(in_date));
            pstm.setString(4, notes);

            pstm.execute();

        } catch (Exception e) {

            System.out.println("Add-booking: " + e.getMessage());
        }
    }

    public Booking getBooking(String account) {
        Booking data = new Booking();
        try {
//            String strSelect ="select * from booking where account=? and room_id=?";
            String strSelect = "select a.booking_id,a.room_id,a.account,a.in_date,a.notes, b.roomname, c.buildingname \n"
                    + "from booking a, rooms b, buildings c where account=? and a.room_id = b.roomid\n"
                    + "and b.building_id = c.building_id";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, account);
//        pstm.setString(2, room_id);
            rs = pstm.executeQuery();

            while (rs.next()) {
                String booking_id = "" + rs.getInt(1);
                String room_id = "" + rs.getInt(2);
//                String account = rs.getString(3);

                SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
                String in_date = "";
                if (rs.getDate(4) != null) {
                    in_date = f.format(rs.getDate(4));
                }

                String notes = rs.getString(5);
                String room_name = rs.getString(6);
                String building_name = rs.getString(7);
                data = new Booking(booking_id, room_id, account, in_date, notes, room_name, building_name);

                return data;

            }

        } catch (Exception e) {
            System.out.println("getBooking:" + e.getMessage());

        }
        return null;

    }

    public void deletebooking(String booking_id, String roomid, String account) {
        
        try {
            String strSelect = "UPDATE rooms SET member = member - 1 WHERE roomid = ?;\n"
                    + "Update Users set inroom =0 where account= ?;\n"
                    + "delete from booking where booking_id=?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, Integer.parseInt(roomid));
            pstm.setString(2, account);
            pstm.setInt(3, Integer.parseInt(booking_id));
            rs = pstm.executeQuery();
        } catch (Exception e) {
            System.out.println("deletebooking:" + e.getMessage());
        }

    }

}
