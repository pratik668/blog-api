package com.blogApp.demo.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// 1. get token
		String requestToken = request.getHeader("Authorization");

		// Bearer token
		System.out.println(requestToken);

		String userName = null;
		String token = null;

		if (null != requestToken && requestToken.startsWith("Bearer")) {

			// remove Bearer from token
			token = requestToken.substring(7);

			try {
				userName = jwtTokenHelper.getUsernameFromToken(token);

			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token is expired");
			} catch (MalformedJwtException e) {
				System.out.println("Invalid JWT Token");
			}

		} else {
			System.out.println("JWT Token does not begin with Bearer");
		}

		// 2. validate token
		if (null != userName && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

			if (jwtTokenHelper.validateToken(token, userDetails)) {

				// now set authentication

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

			} else {
				System.out.println("Invalid JWT Token");
			}
			
		} else {
			System.out.println("Username is null or context is not null");
		}

		filterChain.doFilter(request, response);

	}

}
