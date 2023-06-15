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
@WebServlet(name="SignInController", urlPatterns={"/signin"})
public class SignInController extends HttpServlet {
   
 
 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");
        String gender = req.getParameter("gender");
        String name= req.getParameter("name");
        String dob = req.getParameter("dob");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        
        User u = new User();
        Boolean checkUserExist = u.checkUserExist(account);
        
            String erroaccount="";
            String erropassword="";
            String errorepassword="";
            String erroname ="";
            String errodob ="";
            String erroemail ="";
            String erroaddress="";
            if(password.equals(repassword)==false){
                erropassword="PassWord need equals RePassWord";
                errorepassword="PassWord need equals RePassWord";
            }
            if(checkUserExist==true){
            erroaccount="Account is not availible";
            }
            if(account.isEmpty()){
                erroaccount="Account can not be empty";
            }
            if(password.isEmpty()){
                erroaccount="Password can not be empty";
            }
            if(repassword.isEmpty()){
                errorepassword="Re Password can not be empty";
            }
            if(name.isEmpty()){
                erroname="Name can not be empty";
            }
            if(dob.isEmpty()){
                errodob="Date of birth can not be empty";
            }
            if(email.isEmpty()){
                erroemail="Email can not be empty";
            }
            if(address.isEmpty()){
                erroaddress ="Address can not be empty";
            }
            if(password.equals(repassword)==false){
                erropassword="PassWord need equals RePassWord";
                errorepassword="PassWord need equals RePassWord";
            }
            if(erroaccount.isEmpty()&&erropassword.isEmpty()&&errorepassword.isEmpty()
               && errodob.isEmpty()&& erroemail.isEmpty() && erroaddress.isEmpty()
                && erroname.isEmpty()){
            User putin = new User(account, email, password, name, gender, dob, address, "0", "0");
            putin.addnewUser();
            putin.setInroom("NotIndeed");
            req.setAttribute("user", putin);
            Room r = new Room();
            ArrayList<Room> room=r.getListRoom();
            req.setAttribute("room",room);
            req.getRequestDispatcher("homepage.jsp").forward(req, resp);
                
                
            }
            else{
            req.setAttribute("erroaccount", erroaccount);
            req.setAttribute("erropassword", erropassword);
            req.setAttribute("errorepassword", errorepassword);
            req.setAttribute("errodob", errodob);
            req.setAttribute("erroemail", erroemail);
            req.setAttribute("erroaddress", erroaddress);
            req.setAttribute("erroname", erroname);
            
                
            req.setAttribute("account", account);
            req.setAttribute("password", password);
            req.setAttribute("repassword", repassword);
            req.setAttribute("gender", gender);
            req.setAttribute("name", name);
            req.setAttribute("dob", dob);
            req.setAttribute("email", email);
            req.setAttribute("address", address);
            req.getRequestDispatcher("signin.jsp").forward(req, resp);
            }
       
    } 


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
       
    }

  

}
