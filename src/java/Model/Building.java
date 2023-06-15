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
public class Building {
    String idbuilding, buildingname;

    public Building() {
    connect();
    }

    public Building(String idbuilding, String buildingname) {
        this.idbuilding = idbuilding;
        this.buildingname = buildingname;
        connect();
    }

    public String getIdbuilding() {
        return idbuilding;
    }

    public void setIdbuilding(String idbuilding) {
        this.idbuilding = idbuilding;
    }

    public String getBuildingname() {
        return buildingname;
    }

    public void setBuildingname(String buildingname) {
        this.buildingname = buildingname;
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
    
    public ArrayList<Building> getListBuilding() {
        ArrayList<Building> data = new ArrayList<>();
        try {
            String strSelect = "select * from buildings";

            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);

            while (rs.next()) {
                String buildingid = ""+rs.getInt(1);
                String buildingname = rs.getString(2);



                data.add(new Building(buildingid, buildingname));

            }

        } catch (Exception e) {
            System.out.println("getListBuilding:" + e.getMessage());

        }
        return data;
    }
    
}
