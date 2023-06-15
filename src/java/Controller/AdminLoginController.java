/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Model.Admin;
import Model.User;
import Model.Room;
import java.util.ArrayList;
import Model.Booking;
import jakarta.servlet.http.HttpSession;

public class AdminLoginController extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        req.getRequestDispatcher("loginadmin.jsp").forward(req, resp);
    } 


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        HttpSession session = req.getSession();
        
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        Admin a = new Admin(account, password);
        
        if(a.checkAdmin()){

        User  u = new User();
        ArrayList<User> user = u.getListUser();
        Room r = new Room();
        ArrayList<Room> room=r.getListRoom();
        a=a.getAdmin(account);
        Booking b= new Booking();
        ArrayList<Booking> booking=b.getListBooking();
        
        req.setAttribute("booking",booking);
        req.setAttribute("room",room);
        req.setAttribute("user", user);
        req.setAttribute("admin", a);
        session.setAttribute("admin", a);
        req.getRequestDispatcher("homepageadmin.jsp").forward(req, resp);
        }
        else{
        req.setAttribute("account", account);
        req.setAttribute("password", password);
        req.setAttribute("erropassword", "Need input valid PassWord!");
        req.getRequestDispatcher("loginadmin.jsp").forward(req, resp);
        }
        
    }


}
