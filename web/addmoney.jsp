<%-- 
    Document   : addmoney
    Created on : 12-03-2023, 17:12:47
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- bs lib-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" integrity="sha384-..." crossorigin="anonymous"><!-- comment -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-..." crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-..." crossorigin="anonymous">
        <!--end bslib-->
        <link rel="stylesheet" href="css/addmoney.css"/>

    </head>
    <body>
        <form action="payment" method="get">
            <div class="container p-0">
                <div class="card px-4">
                    <p class="h8 py-3">Payment Details</p>
                    <div class="row gx-3">
                        <div class="col-12">
                            <div class="d-flex flex-column">
                                <p class="text mb-1">Account : ${user.getAccount()}</p>
                                <input type="hidden" name="account" value ="${user.getAccount()}">
                                <input class="form-control mb-3" type="text" placeholder="Name" value="${user.getName()}">
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="d-flex flex-column">
                                <p class="text mb-1">Card Number</p>
                                <input class="form-control mb-3" type="text" placeholder="1234 5678 435678">
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="d-flex flex-column">
                                <p class="text mb-1">Amount Paid</p>
                                <input class="form-control mb-3" type="text" id="amountPaidInput" name="paymnet" placeholder="xxx.VND">
                                <p class="text-danger d-none" id="amountPaidError">Please enter a valid amount.</p>
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="d-flex flex-column">
                                <p class="text mb-1">Expiry</p>
                                <input class="form-control mb-3" type="text" placeholder="MM/YYYY">
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="d-flex flex-column">
                                <p class="text mb-1">CVV/CVC</p>
                                <input class="form-control mb-3 pt-2 " type="password" placeholder="***">
                            </div>
                        </div>
                        <div class="col-12">
                            <input class="btn btn-primary mb-3" type="submit" value="Pay" id="payButton">
                        </div>
                    </div>
                </div>
            </div>
        </form>

        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script>
            // Get the input field and error message elements
            const amountPaidInput = document.getElementById("amountPaidInput");
            const amountPaidError = document.getElementById("amountPaidError");

            // Add an event listener for form submission
            document.querySelector("form").addEventListener("submit", function (event) {
                // Prevent the default form submission behavior
                event.preventDefault();

                // Validate the amount paid input field
                const amountPaid = parseFloat(amountPaidInput.value);
                if (isNaN(amountPaid) || amountPaid <= 0) {
                    amountPaidInput.classList.add("is-invalid");
                    amountPaidError.classList.remove("d-none");
                } else {
                    amountPaidInput.classList.remove("is-invalid");
                    amountPaidError.classList.add("d-none");

                    // Use SweetAlert to display a success message and submit the form
                    Swal.fire({
                        title: "Payment Successful",
                        text: "Try to login again!",
                        icon: "success",
                        confirmButtonText: "OK"
                    }).then(function () {
                        event.target.submit();
                    });
                }
            });

            // Add an event listener for the amount paid input field to remove the validation error when the user starts typing
            amountPaidInput.addEventListener("keydown", function () {
                amountPaidInput.classList.remove("is-invalid");
                amountPaidError.classList.add("d-none");
            });
        </script>
    </body>
</html>
