    <%
        if (session == null || session.getAttribute("userID") == null) {
            response.sendRedirect("login.jsp");
        }
    %>

<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>Logout</title>
    <style>
        .gradient-custom {
            background-color: #E2D5DE;
        }

        .login-card-shadow {
            box-shadow: rgba(0, 0, 0, 0.18) 0px 2px 4px;
        }
    </style>
</head>

<body>
    <section class="vh-100 gradient-custom">
        <div class="container py-3 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                    <div class="card login-card-shadow" style="border-radius: 1rem;">
                        <div class="card-body p-4 ">


                           <%
                               String warningMessage = (String) session.getAttribute("warning_message");
                               String userName = (String) session.getAttribute("userName");
                           %>
                           <div class="d-flex">
                           <a href="/TODO_webapp/UserAccount.jsp" class="me-3" style="color:#212529 ;">
                                <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-arrow-left" viewBox="0 0 16 16">
                                  <path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8" stroke="#212529" stroke-width="1" />
                                </svg>
                           </a>

                               <div class="ms-2">
                                    <h5 class="card-title ">Hello! <%= userName != null ? userName : "Guest" %></h5>
                                   <%
                                       if (warningMessage != null) {
                                   %>
                                       <p class="card-text" style="color: red;"><%= warningMessage %></p>
                                   <%
                                           session.removeAttribute("warning_message");
                                       } else {
                                   %>
                                       <p class="card-text" style="color: red;">Do you really want to <a href="logout" >Logout?</a></p>
                                   <%
                                       }
                                   %>

                               </div>

                           </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>

</html>