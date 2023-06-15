/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Model.Room;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Model.User;
import java.util.ArrayList;



/**
 *
 * @author nguye
 */
@WebServlet(name="PaymentController", urlPatterns={"/payment"})
public class PaymentController extends HttpServlet {
   

 

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
       
        String account = req.getParameter("account");
        
        String payment = req.getParameter("paymnet");
        User u = new User();
        u= u.getUser(account);
        
        double updatepaym= Double.parseDouble(u.getMoney())+Double.parseDouble(payment);
        
        u.updatepay(updatepaym);
        Room r = new Room();
        ArrayList<Room> room=r.getListRoom();
        u= u.getUser(account);
        req.setAttribute("user", u);
            req.setAttribute("room",room);
         req.getRequestDispatcher("homepage.jsp").forward(req, resp);
        
        
        
    } 


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        
       
    }

  
}
