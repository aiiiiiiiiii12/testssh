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
        <link rel="stylesheet" href="css/signin.css"/>

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

            <div class="login" >
                <div class="container" style="margin: 0 10px;">
                    <h1 style="color: #fdd6bd; margin-top: 10px">Create Your Account</h1>

                    <div class="login-form" style="margin-top: 10px;">
                        <form action="signin" method="get">
                            <input type="text" placeholder="Account" value="${account}" name="account">
                            <span id="account"></span>      
                            <input type="password" placeholder="Password" value="${password}" name="password">
                            <span id="password"></span>
                            <input type="password" placeholder="Re-Password" value="${repassword}" name="repassword">
                            <span id="repassword"></span>
                            <input type="text" placeholder="Name" value="${name}" name="name">
                            <span id="name"></span>
                            <div class="gender-div"> <h2>Male</h2><input class="btn-gender" type="radio" value="0" name="gender"checked>
                                <h2>FeMale</h2><input class="btn-gender" type="radio" value="1" name="gender"></div>
                            <input type="date" value="${dob}" name="dob" placeholder="DateOfBirth">
                            <span id="dob"></span>
                            <input type="email" placeholder="Email" value="${email}" name="email">
                            <span id="email"></span>
                            <input type="text" placeholder="Address" value="${address}" name="address">
                            <span id="address"></span>
                            <div class="login-al">
                                <a href="login.jsp">Do you already have an account? </a>
                            </div>         
                            <input style="margin-top: 50px;" type="submit" value="SIGN-IN"/>            
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script>
            const account = document.getElementById("account");
            account.innerHTML = "${erroaccount}";
            const password = document.getElementById("password");
            password.innerHTML = "${erropassword}";
            const repassword = document.getElementById("repassword");
            repassword.innerHTML = "${errorepassword}";
            const name = document.getElementById("name");
            name.innerHTML = "${erroname}";
            const dob = document.getElementById("dob");
            dob.innerHTML = "${errodob}";
            const email = document.getElementById("email");
            email.innerHTML = "${erroemail}";
            const address = document.getElementById("address");
            address.innerHTML = "${erroaddress}";
            
            const form = document.querySelector('form');
            const passwordInput = document.querySelector('input[name="password"]');
            const repasswordInput = document.querySelector('input[name="repassword"]');

            form.addEventListener('submit', function (event) {
                event.preventDefault();

                // Check if password and repassword fields match
                if (passwordInput.value !== repasswordInput.value) {
                    document.querySelector('#repassword').textContent = 'Passwords do not match.';
                    return;
                }

                // Check if any field is empty
                const inputs = form.querySelectorAll('input');
                let emptyField = false;
                inputs.forEach(function (input) {
                    if (!input.value) {
                        document.querySelector(`#${input.name}`).textContent = 'This field is required.';
                        emptyField = true;
                    }
                });

                if (!emptyField) {
                    // Submit the form if all validations pass
                    form.submit();
                }
            });
        </script>
    </body>
</html>
