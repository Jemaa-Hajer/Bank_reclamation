package com.HJ.BankReclamation.configuration;

import com.HJ.BankReclamation.service.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final AuthService authService;

    public JwtFilter( JwtUtil jwtUtil, AuthService authService) {
        this.jwtUtil = jwtUtil;
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader= request.getHeader("Authorization");
        String token=null;
        Integer idC=null;

        if ( authHeader!=null && authHeader.startsWith("Bearer")){
            token=authHeader.substring(7);
            if (jwtUtil.validateToken(token)){
                idC= jwtUtil.getClientIdFromToken(token);
            }
        }

        if (idC != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = authService.loadUserByClientId(idC);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken( userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request,response);
    }
    @Override
    protected boolean NoFilter(HttpServletRequest request){
        return request.getRequestURI().startsWith("/api/auth/");
    }
;}
