package com.bridgelabz.book_store.filter;

import com.bridgelabz.book_store.util.TokenUtility;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class JwtFilter extends HttpFilter {


    private TokenUtility tokenUtility;

    public JwtFilter(TokenUtility tokenUtility) {
        this.tokenUtility = tokenUtility;
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String token = request.getHeader("Authorization");
        System.out.println(token);
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove "Bearer " prefix
            System.out.println(token);
            try {
                String email = tokenUtility.getEmailFromToken(token);
                Long userId = tokenUtility.getUserIdFromToken(token);
                String role = tokenUtility.getRoleFromToken(token);
                System.out.println(email + userId + role);
                request.setAttribute("emailId", email);
                request.setAttribute("userId", userId);
                request.setAttribute("role", role);
                // Proceed with the filter chain
                chain.doFilter(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid token");
                return;
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Authorization token missing or invalid");
        }
    }

    @Override
    public void init() throws ServletException {
        // Initialize the filter if needed
    }

    @Override
    public void destroy() {
        // Cleanup resources if needed
    }
}
