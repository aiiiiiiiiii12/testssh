/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Model.User;
import Model.Room;
import Model.Booking;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author nguye
 */
@WebServlet(name = "BookroomController", urlPatterns = {"/book"})
public class BookroomController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

       
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
 
        try {
            

            
         if(req.getParameter("sendcheckroom").equals("addmoney")) {
             
            String account = req.getParameter("account");
            User u = new User();
            u = u.getUser(account);
            req.setAttribute("user", u);
            req.getRequestDispatcher("addmoney.jsp").forward(req, resp);

        } else if (req.getParameter("sendcheckroom").equals("bookroom")) {

            //deal booking
            
            //user
            String account = req.getParameter("account");
            User u = new User();
            u = u.getUser(account);
            //room
            String room_id = req.getParameter("roomid");
            Room r = new Room();
            r = r.getRoom(room_id);
             System.out.println(u.getInroom());
            if(u.getInroom().equals("NotIndeed")){

            //realtime
            LocalDate now = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date = now.format(formatter);
            
           // all value book
           String notes=" ";
            Booking b = new Booking(room_id, account, date, notes);
            
            b.addBooking();
            int slost_room = Integer.parseInt(r.getMember())+1;
            
            r.upDateSlost(room_id,slost_room);
            b = b.getBooking(account);
            // update money from user
            double money = Double.parseDouble(u.getMoney());
            
            double price = Double.parseDouble(r.getPrice());
            double change = money - price;
            
            u.updateMoney(change);
            u = u.getUser(account);
             

            System.out.println("check roomid:" +room_id);
            System.out.println("check");
            req.setAttribute("booking", b);
            req.setAttribute("room", r);
            req.setAttribute("user", u);
            req.getRequestDispatcher("Information_booking.jsp").forward(req, resp);
            
            }
            else{
            Booking b= new Booking();
            b = b.getBooking(account);
            req.setAttribute("booking", b);
            req.setAttribute("room", r);
            req.setAttribute("user", u);
            req.getRequestDispatcher("Information_booking.jsp").forward(req, resp);
            }
            

        }
        else{
        }
       } 
        catch (Exception e) {
            System.out.println("Booking fail;");
        }
    }

}
