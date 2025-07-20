package com.HJ.BankReclamation.configuration;

import com.HJ.BankReclamation.service.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    public JwtFilter( JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader= request.getHeader("Authorization");

        if ( authHeader!=null && authHeader.startsWith("Bearer")){
            String token=authHeader.substring(7);
            if (jwtUtil.validateToken(token)){
                Integer idC= jwtUtil.getClientIdFromToken(token);
                request.setAttribute("idC",idC);
            }else{
                response.setStatus((HttpServletResponse.SC_UNAUTHORIZED));
                response.getWriter().write("{\"error\": \"Invalid or expired token\"}");
                return;
            }
        }else {
            response.setStatus((HttpServletResponse.SC_UNAUTHORIZED));
            response.getWriter().write("{\"error\": \"Missing Authorization header\"}");
            return;
        }
        filterChain.doFilter(request,response);
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getRequestURI().startsWith("/api/auth/");
    }
}
