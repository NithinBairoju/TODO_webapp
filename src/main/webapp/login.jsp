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

    <title>Login</title>
    <style>

        .form-label {
            text-align: left;
        }

        .gradient-custom {
            background-color: #E2D5DE;
        }
         .btn-dark{
            background-color:#373739;
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

                            <div class="mb-md-3 mt-md-3 pb-4 ">

                                <h2 class="fw-bold mb-2 text-uppercase text-center">Login</h2>
                                <p class="text-dark-50 mb-2 text-center">Please enter your login and password!</p>

                                <%
                                    String errorMessage = (String) request.getAttribute("error_message");
                                    if (errorMessage != null) {
                                %>
                                    <div class="alert alert-danger" role="alert">
                                      <%= errorMessage %>
                                    </div>
                                <% } %>

                                <form method="post" action="login">
                                <div data-mdb-input-init class="form-outline form-white mb-3">
                                    <label class="form-label text-start" for="typeEmailX">Email</label>
                                    <input type="email" id="typeEmailX" name="email"
                                        class="form-control form-control-lg" required/>
                                </div>

                                <div data-mdb-input-init class="form-outline form-white mb-4">
                                    <label class="form-label" for="typePasswordX">Password</label>
                                    <input type="password" id="typePasswordX" name="password"
                                        class="form-control form-control-lg" required/>
                                </div>

                                <p class="small mb-2 pb-lg-2 text-center"><a class="text-dark-50" href="#!">Forgot
                                        password?</a>
                                </p>

                                <button data-mdb-button-init data-mdb-ripple-init
                                    class="btn btn-dark btn-lg px-5 w-100" type="submit">Login</button>
                                </form>
                            </div>

                            <div class="text-center">
                                <p class="mb-0">Dont have an account? <a href="signup.jsp" class="text-dark-50 fw-bold">Sign
                                        Up</a>
                                </p>
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