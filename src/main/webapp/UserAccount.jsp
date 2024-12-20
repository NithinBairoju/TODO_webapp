    <%
        if (session == null || session.getAttribute("userID") == null) {
            response.sendRedirect("login.jsp");
        }
    %>
    <%
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
    %>

<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>User Profile</title>
    <style>
        .login-card-shadow {
            box-shadow: rgba(0, 0, 0, 0.18) 0px 2px 4px;
        }
        .btn-dark{
        background-color:#373739;
        }

    </style>
</head>

<body>

    <section class="vh-100" style="background-color: #E2D5DE;">

                <% String userName=(String) session. getAttribute("userName");
                    String userEmail=(String) session.getAttribute("userEmail");
                    String successMessage= (String) request.getAttribute("successMessage");
                    String errorMessage= (String) request.getAttribute("errorMessage"); %>
                <% if(successMessage !=null){ %>
                    <div class="alert alert-warning alert-dismissible fade show" role="alert" id="auto-close-alert"
                    style="z-index: 5; position: absolute; right:10px; top:10px;">
                    <%= successMessage %>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
               <%}%>

                <% if(errorMessage !=null){ %>
                    <div class="alert alert-warning alert-dismissible fade show" role="alert"
                    id="auto-close-alert" style="z-index: 5; position: absolute; right:10px; top:10px;">
                    <%= errorMessage %>
                    <button type="button" class="btn-close" data-bs-dismiss="alert"
                    aria-label="Close"></button>
                    </div>
                <%}%>
                <div class="container py-5 h-100">
                    <div class="row d-flex justify-content-center align-items-center h-100">
                        <div class="col col-lg-12 mb-4 mb-lg-0">
                            <div class="card login-card-shadow mb-3" style="border-radius: .5rem;">
                                <div class="row g-0 d-flex">
                                    <div class="col-md-4 gradient-custom text-center text-white"
                                        style="background-image: linear-gradient(to right,#817B83 , #BCABB6);border-top-left-radius: .5rem; border-bottom-left-radius: .5rem;">
                                         <img src="images/man.png" class="mt-5 mb-3" alt="user image" width="80" height="80" />
                                        <h5>
                                            <%= userName %>
                                        </h5>
                                        <p>
                                            <%= userEmail %>
                                        </p>
                                        <a href="logout.jsp" class="btn btn-dark" style="background-color:#373739;">Log Out</a>
                                    </div>

                                    <div class="col-md-8 border-right">
                                        <div class="p-3 py-5">
                                            <div class="d-flex align-items-center mb-3">
                                                 <a href="/TODO_webapp" class="me-3" style="color:#343A40 ;">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-arrow-left" viewBox="0 0 16 16">
                                                        <path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8" stroke="#343A40" stroke-width="1" />
                                                    </svg>
                                                 </a>
                                                <h4 class="text-right" style="margin-bottom: 0px;">Profile Settings</h4>
                                            </div>
                                            <div class="row mt-2">
                                                <form method="POST" action="UserProfile">
                                                    <input type="hidden" name="action" value="updateUserName">
                                                    <label class="labels">Update User Name</label>
                                                    <div class="col-md-12 d-flex justify-content-between align-items-center">
                                                        <input type="text" class="form-control me-3" name="userName"
                                                            placeholder="enter new user name" required>
                                                        <input type="submit" class="btn btn-dark" value="Save Profile">
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="row mt-2">
                                                <form method="POST" action="UserProfile">
                                                    <input type="hidden" name="action" value="updateMail">
                                                    <label class="labels">Update Mail</label>
                                                    <div class="col-md-12 d-flex justify-content-between align-items-center">
                                                        <input type="email" class="form-control me-3" name="userMail"
                                                            placeholder="enter new email" required>
                                                        <input type="submit" class="btn btn-dark" value="Save Profile">

                                                    </div>
                                                </form>
                                            </div>
                                            <div class="row mt-3">
                                                <form method="POST" action="UserProfile">
                                                    <input type="hidden" name="action" value="updatePassword">
                                                    <label class="labels">Update Password</label>
                                                    <div class="col-md-12 d-flex justify-content-between align-items-center">
                                                        <input type="password" class="form-control me-3" name="userPassword"
                                                            placeholder="enter new Password" required>
                                                        <input type="submit" class="btn btn-dark" value="Save Profile">

                                                    </div>
                                                </form>
                                            </div>
                                            <div class="row mt-3">
                                                <div class="col-md-12 ">
                                                    <form method="POST" action="UserProfile">
                                                        <input type="hidden" name="action" value="DeleteAccount">
                                                        <p>Delete Account</p>
                                                        <input type="submit" class="btn btn-danger profile-button" value="Delete account">
                                                    </form>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </section>

        <script type="text/javascript">
            setTimeout(() => {
                const alert = document.getElementById('auto-close-alert');
                if (alert) {
                    alert.classList.remove('show'); // Remove the 'show' class to hide the alert
                }
            }, 3000);
        </script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</body>

</html>