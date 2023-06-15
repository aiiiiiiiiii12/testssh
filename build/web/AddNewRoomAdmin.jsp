<%-- 
    Document   : homepageadmin
    Created on : 15-03-2023, 16:38:11
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
        <title>Modern Admin Dashboard</title>
        <link rel="stylesheet" href="css/homepageadmin.css">
        <link rel="stylesheet" href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">
        <!----===== Iconscout CSS ===== -->
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <!-- SweetAlert2 CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.4/dist/sweetalert2.min.css">

        <!-- SweetAlert2 JS -->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.4/dist/sweetalert2.min.js"></script>
        <<link rel="stylesheet" href="css/newroomcss.css"/>
    </head>
    <body>


        <input type="checkbox" id="menu-toggle">
        <div class="sidebar">
            <div class="side-header">
                <h3>A<span>dmin</span></h3>
            </div>

            <div class="side-content">
                <div class="profile">
                    <div class="profile-img bg-img" style="background-image: url(./images/avt-img.jpg)"></div>
                    <h4>${admin.getAccount()}</h4>
                    <small>${admin.getEmail()}</small>
                </div>

                <div class="side-menu">
                    <ul>
                        <li>
                            <a href="#" >
                                <span class="las la-home"></span>
                                <small>Booking</small>
                            </a>
                        </li>
                        <li>
                            <form id="newuser-form" action="usermanager">
                                <button type="submit" style="display:none;"></button>
                                <a href="#" id="newuser-button">
                                    <span class="las la-user-alt" aria-hidden="true"></span>
                                    <small>Profile</small>
                                </a>
                            </form>
                        </li>
                        <li>
                            <a href="">
                                <span class="las la-envelope"></span>
                                <small>Mailbox</small>
                            </a>
                        </li>
                        <li>
                            <form id="newroom-form" action="newroom">
                                <button type="submit" style="display:none;"></button>
                                <a href="#" id="newroom-button" class="active">
                                    <span class="las la-clipboard-list" aria-hidden="true"></span>
                                    <small>Room</small>
                                </a>
                            </form>
                        </li>
                        <li>
                            <form id="newroom-form" action="newroom">
                                <button type="submit" style="display:none;"></button>
                                <a href="#" id="newroom-button">
                                    <span class="las la-clipboard-list" aria-hidden="true"></span>
                                    <small>XXX</small>
                                </a>
                            </form>
                        </li>
                        <li>
                            <a href="">
                                <span class="las la-tasks"></span>
                                <small>Tasks</small>
                            </a>
                        </li>

                    </ul>
                </div>
            </div>
        </div>

        <div class="main-content">

            <header>
                <div class="header-content">
                    <label for="menu-toggle">
                        <span class="las la-bars"></span>
                    </label>

                    <div class="header-menu">
                        <label for="">
                            <span class="las la-search"></span>
                        </label>

                        <div class="notify-icon">
                            <span class="las la-envelope"></span>
                            <span class="notify">4</span>
                        </div>

                        <div class="notify-icon">
                            <span class="las la-bell"></span>
                            <span class="notify">3</span>
                        </div>

                        <div class="user">
                            <div class="bg-img" style="background-image: url(img/1.jpeg)"></div>

                            <span class="las la-power-off"><a href="loginadmin.jsp"></a></span>
                            <span><a href="loginadmin.jsp">Logout</a></span>
                        </div>
                    </div>
                </div>
            </header>


            <main>

                <div class="page-header">
                    <h1>Adminstrator</h1>
                    <small><a>Booking</a> / Adminstrator</small>
                </div>

                <div class="page-content">
                    <!--                    form add-->
                    <div class="form-container" >
                        <form action="addroom" style="display: block;"  id="add-room-form">
                            ID:<input type="text" name="id" placeholder="Enter Id need is a NUMBER"><br>
                            RoomName:<input type="text" name="name" placeholder="Enter the room Name"> <br>               
                            Building: <select name="buildingid">
                                <c:forEach var="item" items="${building}">
                                    <option value="${item.getIdbuilding()}">${item.getBuildingname()}</option>
                                </c:forEach>
                            </select><br>
                            Roomtype: <select name="roomtype">
                                <c:forEach var="item" items="${roomtype}">
                                    <option value="${item.getRtypeid()}">${item.getNumber()}/${item.getPrice()}</option>
                                </c:forEach>
                                
                            </select><br>
                            <input type="file" id="file-input" name="roomimg" data-file="">
                            <label for="file-input">Choose file</label><br>
                            <input type="submit" value="AddNewRoom">          
                        </form>           
                    </div>

                    <div class="analytics">

                        <div class="card">
                            <div class="card-head">
                                <h2>${user.size()}</h2>
                                <span class="las la-user-friends"></span>
                            </div>
                            <div class="card-progress">
                                <small>User</small>
                                <div class="card-indicator">
                                    <div class="indicator one" style="width: 60%"></div>
                                </div>
                            </div>
                        </div>

                        <div class="card">
                            <div class="card-head">
                                <h2>${room.size()}</h2>
                                <span class="las la-eye"></span>
                            </div>
                            <div class="card-progress">
                                <small>Room</small>
                                <div class="card-indicator">
                                    <div class="indicator two" style="width: 80%"></div>
                                </div>
                            </div>
                        </div>

                        <div class="card">
                            <div class="card-head">
                                <h2>${booking.size()}</h2>
                                <span class="las la-shopping-cart"></span>
                            </div>
                            <div class="card-progress">
                                <small>Booking</small>
                                <div class="card-indicator">
                                    <div class="indicator three" style="width: 65%"></div>
                                </div>
                            </div>
                        </div>

                        <div class="card">
                            <div class="card-head">
                                <h2>47,500</h2>
                                <span class="las la-envelope"></span>
                            </div>
                            <div class="card-progress">
                                <small>New E-mails received</small>
                                <div class="card-indicator">
                                    <div class="indicator four" style="width: 90%"></div>
                                </div>
                            </div>
                        </div>

                    </div>


                    <div class="records table-responsive">

                        <div class="record-header">
                            <div class="add">
                                <span>Entries</span>
                                <select name="" id="">
                                    <option value="">ID</option>
                                </select>
                                <button onclick="toggleForm()" class="button-to-form">Add New Room</button>
                            </div>

                            <div class="browse">
                                <input type="search" placeholder="Search" class="record-search">
                                <select name="" id="">
                                    <option value="">Status</option>
                                </select>
                            </div>
                        </div>

                        <div>
                            <table width="100%">
                                <thead>
                                    <tr>
                                        <th>IDRoom</th>
                                        <th><span class="las la-sort"></span> Roomname</th>
                                        <th><span class="las la-sort"></span> BuildingName</th>
                                        <th><span class="las la-sort"></span> Member/Number</th>
                                        <th><span class="las la-sort"></span> Price</th>
                                        <th><span class="las la-sort"></span> Edit</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach  items="${room}" var="item">
                                        <tr>
                                            <td>#${item.getRoomid()}
                                            </td>
                                            <td>
                                                ${item.getRoomname()}
                                            </td>
                                            <td>
                                                ${item.getBuildingname()}

                                            </td>
                                            <td>
                                                ${item.getMember()}/${item.getNumber()}   
                                            </td>
                                            <td>
                                                ${item.getPrice()}
                                            </td>
                                            <td>
                                                <div class="actions">
                                                    <form action="admincl" id="myform${item.getRoomid()}">
                                                        <input type="hidden" value="" name="accountadmin">
                                                        <input type="hidden" value="" name="idbooking">
                                                        <input type="hidden" value="" name="account">
                                                        <input type="hidden" value="default" name="getsubmit" id="getsubmit">
                                                        <span class="uil uil-edit-alt" onclick="updateBooking(event)" value="update"></span>   
                                                        <span class="uil uil-trash-alt" onclick="deleteBooking(event)" value="delete"></span>
                                                        <span class="las la-ellipsis-v" onclick="viewBooking(event)" value="view"></span>
                                                    </form>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>


                                </tbody>
                            </table>
                        </div>

                    </div>

                </div>

            </main>

        </div>

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script>
//            function updateBooking(event) {
//                event.preventDefault();
//                // Update the value of the hidden input field
//
//                document.getElementById("getsubmit").value = "updatecheck";
//
//                // Prevent the default form submission
//
//
//                // Find the form element and its value
//                var form = event.target.closest('form');
//                var formValue = form.querySelector('#getsubmit').value;
//
//                // Update the value of the hidden input field
//                if (formValue === 'default') {
//                    form.querySelector('#getsubmit').value = 'updatecheck';
//                }
//
//                // Submit the form
//                form.submit();
//
//
//            }
//
//
//            function deleteBooking() {
//                event.preventDefault();
//                // Update the value of the hidden input field
//                document.getElementById("getsubmit").value = "deletecheck";
//
//                // Display a confirmation dialog using SweetAlert
//                Swal.fire({
//                    title: 'Are you sure?',
//                    text: 'You will not be able to recover this booking!',
//                    icon: 'warning',
//                    showCancelButton: true,
//                    confirmButtonColor: '#3085d6',
//                    cancelButtonColor: '#d33',
//                    confirmButtonText: 'Yes, delete it!'
//                }).then((result) => {
//                    if (result.isConfirmed) {
//                        // Find the form element and its value
//                        var form = event.target.closest('form');
//                        var formValue = form.querySelector('#getsubmit').value;
//
//                        // Update the value of the hidden input field
//                        if (formValue === 'default') {
//                            form.querySelector('#getsubmit').value = 'deletecheck';
//                        }
//
//                        // Submit the form
//                        form.submit();
//                    }
//                });
//            }
//
//            function viewBooking() {
//                // Update the value of the hidden input field
//                document.getElementById("getsubmit").value = "viewcheck";
//
//                // Submit the form
//                document.getElementById("myform").submit();
//            }
//
//            //new room
//            const newroomButton = document.querySelector('#newroom-button');
//            const newroomForm = document.querySelector('#newroom-form');
//
//            newroomButton.addEventListener('click', (event) => {
//                event.preventDefault();
//                newroomForm.submit();
//            });

    document.getElementById("add-room-form").addEventListener("submit", function(event) {
        event.preventDefault(); // prevent the form from submitting
        
        swal({
            title: "Are you sure?",
            text: "Once added, you will not be able to modify this room!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
        .then((willAdd) => {
            if (willAdd) {
                // submit the form if user confirms
                document.getElementById("add-room-form").submit();
            } else {
                // do nothing if user cancels
            }
        });
    });


            // Get the form container
            var formContainer = document.querySelector(".form-container");

            // Hide the form container initially
            formContainer.style.display = "none";

            // Define a function to toggle the form container
            function toggleForm() {
                // Toggle the display of the form container
                if (formContainer.style.display === "none" || formContainer.style.display === "") {
                    formContainer.style.display = "block";
                } else {
                    formContainer.style.display = "none";
                }
            }
            
             //new user
            const newuserButton = document.querySelector('#newuser-button');
            const newuserForm = document.querySelector('#newuser-form');

            newuserButton.addEventListener('click', (event) => {
                event.preventDefault();
                newuserForm.submit();
            });
        </script>
    </body>
</html>
