/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Model.Admin;
import Model.Booking;
import Model.Building;
import Model.Room;
import Model.RoomType;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public class ManagermentUserController extends HttpServlet {
   
    
 

 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        try {
         HttpSession session = req.getSession();
        Admin a = (Admin) session.getAttribute("admin");
        
        if(a!=null){
        User  u = new User();
        ArrayList<User> user = u.getListUser();
        Room r = new Room();
        ArrayList<Room> room=r.getListRoom();
        Booking b= new Booking();
        ArrayList<Booking> booking=b.getListBooking();
        Building buil = new Building();
        ArrayList<Building> building = new ArrayList<>();
        building = buil.getListBuilding();
        RoomType rtype = new RoomType();
        ArrayList<RoomType> roomtype = new ArrayList<>();
        roomtype = rtype.getListRoomType();
        
        req.setAttribute("roomtype",roomtype );
        req.setAttribute("building",building);
        req.setAttribute("booking",booking);
        req.setAttribute("user", user);
        req.setAttribute("room", room);
        req.setAttribute("admin", a);
        req.getRequestDispatcher("usermanagement.jsp").forward(req, resp);
        }
        else{
            req.getRequestDispatcher("loginadmin.jsp").forward(req, resp);
        }   
        } catch (Exception e) {
            System.out.println("Display Room:"+e.getMessage());
        }
    } 

   
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        
        
    }

 
}
