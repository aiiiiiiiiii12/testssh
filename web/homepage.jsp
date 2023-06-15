<%-- 
    Document   : homepage.jsp
    Created on : 07-03-2023, 21:40:06
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>--%>
<!DOCTYPE html>


<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!--sweetalert-->
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script><!-- comment -->

        <!----======== CSS ======== -->
        <link rel="stylesheet" href="css/homepage.css">

        <!----===== Iconscout CSS ===== -->
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">


        <!--JsLib-->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <!--<title>Admin Dashboard Panel</title>--> 


    </head>
    <body>
        <nav>
            <div class="logo-name">
                <div class="logo-image">
                    <img src="images/logo-login.png" alt="">
                </div>

                <span class="logo_name">FPT University</span>
            </div>

            <div class="menu-items">
                <ul class="nav-links">
                    <li><a href="#">
                            <i class="uil uil-user"></i>
                            <span class="link-name">Account: ${user.getAccount()}</span>
                        </a></li>
                    <li><a href="#">
                            <i class="uil uil-user-exclamation"></i>
                            <span class="link-name">Name User:${user.getName()}</span>
                        </a></li>
                    <li>
                       
                        <a href="${pageContext.request.contextPath}/banking?account=${user.getAccount()}">
                            <i class="uil uil-money-bill-stack"></i>
                            <span class="link-name">Account balance: ${user.getMoney()}</span>
                        </a></li>
                    <li><a href="#">
                            <i class="uil uil-building"></i>
                            <span class="link-name">Inroom: ${user.getInroom()}</span>
                           
                        </a></li>

                </ul>

                <ul class="logout-mode">
                    <li><a href="login.jsp">
                            <i class="uil uil-signout"></i>
                            <span class="link-name">Logout</span>
                        </a></li>

                    <li class="mode">
                        <a href="#">
                            <i class="uil uil-moon"></i>
                            <span class="link-name">Dark Mode</span>
                        </a>

                        <div class="mode-toggle">
                            <span class="switch"></span>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>

        <section class="dashboard">
            <div class="top">
                <i class="uil uil-bars sidebar-toggle"></i>

                <div class="search-box">
                    <i class="uil uil-search"></i>
                    <input type="text" placeholder="Search here...">
                </div>


                <div class="action">
                    <div class="profile" onclick="menuToggle();">
                        <img src="images/avt-img.jpg" alt="" />
                    </div>
                    <div class="menu">
                        <h3>Xin Chào </h3>
                        <h3>${user.getName()}</h3>
                        <ul>
                            <li>
                                <img src="./icons/user.png" /><a href="#">My profile</a>
                            </li>
                            <li>
                                <img src="./icons/edit.png" /><a href="#">Edit profile</a>
                            </li>
                            <li>
                                <img src="./icons/envelope.png" /><a href="#">Inbox</a>
                            </li>
                            <li>
                                <img src="./icons/log-out.png" /><a href="login.jsp">Logout</a>
                            </li>
                        </ul>
                    </div>
                </div>

            </div>

            <div class="dash-content">
                <c:forEach  items="${room}" var="item">
                    <div class="room-details">
                        <img src="./images/${item.getRoom_img()}" alt="Room Image">
                        <h2>${item.getBuildingname()}</h2>
                        <p>Room: ${item.getRoomname()}</p>
                        <p>Room Id: ${item.getRoomid()} </p>
                        <p>Price: ${item.getPrice()}</p>
                        <p>Slost: ${item.getMember()} / ${item.getNumber()}</p>
                        <form id="myForm${item.getRoomid()}" action="book" method="post">
                            <input type="hidden" name="roomid" value="${item.getRoomid()}">
                            <input type="hidden" name="account" value="${user.getAccount()}">
                            <c:set var="money" value="${Double.parseDouble(user.getMoney())}" />
                            <c:set var="price" value="${Double.parseDouble(item.getPrice())}" />

                            <c:choose>
                                <c:when test="${item.getMember() lt item.getNumber()}">
                                    <c:choose>
                                        <c:when test="${money >= price}">
                                            <input type="hidden" name="sendcheckroom" value="bookroom">
                                            <button id="book-now-btn" onclick="showConfirmation(event)" >Book Now</button>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="hidden" name="sendcheckroom" value="addmoney">
                                            <button id="book-now-btn" onclick="showConfirmation1(event)" >Book Now</button>
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    <button id="book-now-btn"class="full-room" onclick="showFullMessage()" type="button">Full</button>
                                </c:otherwise>
                            </c:choose>
                        </form> 
                    </div>
                </c:forEach>
            </div>
        </section>

        <script src="js/sbhomepage.js"></script>
        
        <script>

                                        function menuToggle() {
                                            const toggleMenu = document.querySelector(".menu");
                                            toggleMenu.classList.toggle("active");
                                        }

//                                            event.preventDefault();
                                        function showConfirmation(event) {
                                            event.preventDefault();
                                            const form = event.target.closest('form');
                                            const roomIdInput = form.querySelector('[name="roomid"]');
                                            const roomId = roomIdInput.value;
                                            swal({
                                                title: "Bạn có chắc là đặt phòng không? Không thể đặt lại.",
                                                icon: "warning",
                                                buttons: ["Cancel", "Yes, do it!"],
                                                dangerMode: true,
                                            }).then((willSubmit) => {
                                                if (willSubmit) {
                                                    swal("Đặt phòng thành công!", {
                                                        icon: "success",
                                                    });
                                                    // The user clicked the "Yes, submit it!" button
                                                    // Submit the form or perform any other action here
                                                    setTimeout(() => {
                                                        form.submit();
                                                    }, 1000);
                                                } else {
                                                    // The user clicked the "Cancel" button or closed the dialog
                                                    // Do nothing or perform any other action here
                                                }
                                            });
                                            return false;
                                        }

                                        function showConfirmation1(event) {
                                            event.preventDefault();
                                            swal({
                                                title: "Bạn không đủ tiền để thực hiện. Vui lòng nạp thêm.",
                                                icon: "warning",
                                                buttons: ["Cancel", "Yes, add money!"],
                                                dangerMode: true,
                                            }).then((willSubmit) => {
                                                if (willSubmit) {

                                                    var formId = event.target.form.id;
                                                    $('#' + formId).submit();
                                                }
                                            });
                                        }



                                        function showFullMessage() {
                                            swal({
                                                title: "Full Room",
                                                text: "Sorry, this room is already full.",
                                                icon: "warning",
                                                button: "OK",
                                            })
                                                    .then(() => {
                                                        // Nothing to do here
                                                    });
                                        }

        </script>
    </body>
</html>

