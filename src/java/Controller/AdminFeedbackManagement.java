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
import Model.Feedback;


/**
 *
 * @author nguye
 */
public class AdminFeedbackManagement extends HttpServlet {
   
 


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        try {
        String name= req.getParameter("name");
        String email = req.getParameter("email");
        String felling = req.getParameter("felling");
        String feedback = req.getParameter("getfeedback");
        
        Feedback f = new Feedback(name, email, felling, feedback);
        f.add(name,email,felling,feedback);
        
        
        
        } catch (Exception e) {
            System.out.println("Get feedback:"+e.getMessage());
        }
       
        
    } 

  
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    }


}
