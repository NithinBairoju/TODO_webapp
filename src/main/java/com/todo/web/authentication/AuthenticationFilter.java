package com.todo.web.authentication;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.todo.model.User;

import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String uri = httpRequest.getRequestURI();
        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);


        if (uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".png") || uri.endsWith(".jpg")) {
            chain.doFilter(request, response);
            return;
        }


        if (isLoggedIn && (uri.endsWith("login.jsp") || uri.endsWith("signup.jsp"))) {
            httpResponse.sendRedirect("logout.jsp");
            return;
        }

        // Allow unauthenticated users to access login/signup/logout pages
        if (!isLoggedIn && (uri.endsWith("login.jsp") || uri.endsWith("signup.jsp") || uri.endsWith("/login") || uri.endsWith("/signup") )) {
            chain.doFilter(request, response);
            return;
        }


        if (!isLoggedIn) {
            httpResponse.sendRedirect("login.jsp");
            return;
        }


        httpResponse.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        httpResponse.setHeader("Pragma", "no-cache");
        httpResponse.setDateHeader("Expires", 0);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup logic (if needed)
    }
}
