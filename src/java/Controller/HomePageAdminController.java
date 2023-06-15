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
import Model.Booking;
import java.util.ArrayList;
import Model.Admin;
import Model.Room;
import Model.User;

/**
 *
 * @author nguye
 */
public class HomePageAdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {

            if (req.getParameter("getsubmit").equals("updatecheck")) {
                String booking_id = req.getParameter("idbooking");
                String account = req.getParameter("account");
                String accountadmin = req.getParameter("accountadmin");
                Admin admin = new Admin();
                admin = admin.getAdmin(accountadmin);
                Booking booking = new Booking();
                booking = booking.getBooking(account);
                System.out.println(booking.getRoom_id());
                String roomid = booking.getRoom_id();
                System.out.println(booking_id+" "+roomid+" "+account);
                booking.deletebooking(booking_id, roomid, account);
                ArrayList<Booking> bookinglist = booking.getListBooking();
                System.out.println(bookinglist.size());
                User u = new User();
                ArrayList<User> user = u.getListUser();
                Room r = new Room();
                ArrayList<Room> room = r.getListRoom();
                req.setAttribute("room", room);
                req.setAttribute("user", user);
                req.setAttribute("admin", admin);
                req.setAttribute("booking", bookinglist);
                req.getRequestDispatcher("homepageadmin.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            System.out.println("GetBookingfrom Admin:" + e.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    }

}
