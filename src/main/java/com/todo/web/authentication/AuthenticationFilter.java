package com.todo.web.authentication;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String uri = httpRequest.getRequestURI();
        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

        if (uri.startsWith("/static/") || uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".png") || uri.endsWith(".jpg")) {
            chain.doFilter(request, response);
            return;
        }

        if (!isLoggedIn && (uri.endsWith("login.jsp") || uri.endsWith("signup.jsp") || uri.endsWith("/login") || uri.endsWith("/signup"))) {
            chain.doFilter(request, response);
            return;
        }

        if (isLoggedIn && (uri.endsWith("login.jsp") || uri.endsWith("signup.jsp"))) {
            httpResponse.sendRedirect("dashboard.jsp");
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
}
