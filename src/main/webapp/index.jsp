<%
    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>
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

    <title>Todo App</title>
    <style>
        .form-label {
            text-align: left;
        }

        .gradient-custom {
            background-color: rgb(243, 247, 250);
        }

        .login-card-shadow {
            box-shadow: rgba(0, 0, 0, 0.18) 0px 2px 4px;
        }

        .navbar {
            background-color: white;
        }
    </style>
</head>

<body >

   <section class="vh-100" style="background-color: #e2d5de;">
       <div id="alert-container" style="position: relative; z-index: 5;"></div>
       <div class="container py-3 h-90">

               <div class="col col-xl-12">
                   <div class="card login-card-shadow" style="border-radius: 15px; height: 95vh;">
                       <div class="card-body p-md-4 p-sm-3">
                           <div style="height: 120px">
                               <div class="d-flex align-items-center justify-content-between mb-2" >
                                   <h6 class="mb-2">Awesome Todo List</h6>
                                   <div>
                                       <%
                                           String userName = (String) session.getAttribute("userName");
                                           if (userName != null) {
                                       %>
                                       <a class="nav-link d-sm-flex align-items-sm-center" href="UserAccount.jsp" style="color: #172F5F;">
                                           <img src="images/man.png" alt="<%= userName %>" width="30" height="30" />
                                           <strong class="d-none d-sm-block ms-1"><%= userName %></strong>
                                       </a>
                                       <%
                                           } else {
                                       %>
                                       <a class="btn btn-primary btn-lg" href="login.jsp" style="color: white; background-color: #172F5F; border: none;">
                                           <strong>Login</strong>
                                       </a>
                                       <%
                                           }
                                       %>
                                   </div>
                               </div>


                               <form id="todoForm" class="d-flex justify-content-center align-items-center  mb-4">
                                   <div class="form-outline flex-fill">
                                       <input type="text" id="taskInput" class="form-control form-control-lg" placeholder="Add a task" required/>
                                   </div>
                                   <button type="submit" class="btn btn-primary btn-lg ms-2" style="background-color: #172F5F; border: #172F5F;">
                                       Add
                                   </button>
                               </form>
                           </div>

                           <ul id="todoList" class="list-group mb-0" style="height: calc(100vh - 32vh);  overflow-y: auto;  padding: 10px;">

                           </ul>

                       </div>
                   </div>
               </div>
           </div>

   </section>

    <script type="text/javascript">
        function showAlert(message) {
                const alertContainer = document.getElementById('alert-container');
                const alertHTML = `
                    <div class="alert alert-warning alert-dismissible fade show" role="alert"
                        style="z-index: 5; position: absolute; right:10px; top:10px;">
                        ${message}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                `;

                alertContainer.innerHTML = alertHTML;

                setTimeout(() => {
                    alertContainer.innerHTML = '';
                }, 3000);
        }
    </script>
    <script src="JSFile/InsertTask.js"></script>
    <script src="JSFile/LoadTasks.js"></script>
    <script src="JSFile/UpdateTaskStatus.js"></script>
    <script src="JSFile/DeleteTask.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>

</html>