/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import Model.User;
import Model.Room;

/**
 *
 * @author nguye
 */
public class LoginController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Nhập thông tin
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        
        
        //Sử lý thông tin
        
        User u = new User(account,password);
        boolean check= u.checkUser();
        Room r = new Room();
        
        
        // Trả kết quả về
        if(check){
            String name = u.getNameByAccount(account);
            req.setAttribute("name", name);
            User d =new User(account,password);
            User result = d.getUser(account);
            d=result;
            if(d.getInroom().equals("NotIndeed")){
            req.setAttribute("user", d);
            ArrayList<Room> room=r.getListRoom();
            req.setAttribute("room",room);
            req.getRequestDispatcher("homepage.jsp").forward(req, resp);
            }
            else{
                
            req.setAttribute("user", d);
//            ArrayList<User> data=u.getListUser();
            ArrayList<Room> room=r.getListRoom();
            req.setAttribute("room",room);
//            req.setAttribute("data", data);
            req.getRequestDispatcher("full.jsp").forward(req, resp);
            }
            
            
        }
        else{
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
        
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       resp.sendRedirect("home.jsp");
    }
    
    
    
}
