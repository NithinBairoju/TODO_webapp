<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error Occurred</title>
</head>
<body>
    <h1>An Error Occurred</h1>
    <p><strong>Status Code:</strong> <%= request.getAttribute("javax.servlet.error.status_code") %></p>
    <p><strong>Exception Type:</strong> <%= request.getAttribute("javax.servlet.error.exception_type") %></p>
    <p><strong>Message:</strong> <%= request.getAttribute("javax.servlet.error.message") %></p>
    <p><strong>Request URI:</strong> <%= request.getAttribute("javax.servlet.error.request_uri") %></p>

    <p><strong>Stack Trace:</strong></p>
    <pre>
        <%=
            Throwable exception = (Throwable) request.getAttribute("javax.servlet.error.exception");

            if (exception != null) {
                exception.printStackTrace(new java.io.PrintWriter(response.getWriter()));
            } else {
                out.println("No exception details available.");
            }
        %>
    </pre>
</body>
</html>
