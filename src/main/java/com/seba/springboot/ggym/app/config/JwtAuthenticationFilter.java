package com.seba.springboot.ggym.app.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.seba.springboot.ggym.app.services.JwtService;
import com.seba.springboot.ggym.app.services.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserService userService;
	
	@Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
		try {
			final String authHeader = request.getHeader("Authorization");
	        if (!StringUtils.hasLength(authHeader) || !StringUtils.startsWithIgnoreCase(authHeader, "Bearer ")) {
	            filterChain.doFilter(request, response);
	            return;
	        }
	        final String jwt = authHeader.substring(7);
	        final String userEmail = jwtService.extractUserName(jwt);
	        if (StringUtils.hasLength(userEmail)
	                && SecurityContextHolder.getContext().getAuthentication() == null) {
	            UserDetails userDetails = userService.userDetailsService()
	                    .loadUserByUsername(userEmail);
	            if (jwtService.isTokenValid(jwt, userDetails)) {
	                SecurityContext context = SecurityContextHolder.createEmptyContext();
	                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
	                        userDetails, null, userDetails.getAuthorities());
	                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                context.setAuthentication(authToken);
	                SecurityContextHolder.setContext(context);
	            }
	        }
	        filterChain.doFilter(request, response);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
         
    }

}
