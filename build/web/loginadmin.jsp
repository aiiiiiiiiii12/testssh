<%-- 
    Document   : login
    Created on : 01-03-2023, 21:10:08
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="css/login.css"/>

    <body>

        <div class="parent clearfix">
            <div class="bg-illustration">
                <a href="index.html"><img src="images/logo-login.png" alt="logo"></a>
                

                <div class="burger-btn">
                    <span></span>
                    <span></span>
                    <span></span>
                </div>

            </div>

            <div class="login">
                <div class="container">
                    <h1 style="color: #fdd6bd;">Admin login</h1>

                    <div class="login-form">
                        <form action="loginadmin" method="POST">
                            <input type="text" placeholder="Account" value="${account}" name="account">
                            <input type="password" placeholder="Password" value="${password}" name="password">
                            <span id="password"></span>
                            <div class="remember-form">
                                <input type="checkbox">
                                <span>Remember me</span>
                            </div>
                            <div class="forget-pass">
                                <a href="signin.jsp">You don't have account? </a>
                            </div>

                            <!--            <button type="submit">LOG-IN</button>-->
                            <input type="submit" value="LOG-IN"/>

                        </form>
                    </div>

                </div>
            </div>
        </div>

        <script>
            const password = document.getElementById("password");
            password.innerHTML = "${erropassword}";
        </script>
    </body>
</html>
