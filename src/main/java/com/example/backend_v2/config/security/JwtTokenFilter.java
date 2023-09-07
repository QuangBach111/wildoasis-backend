package com.example.backend_v2.config.security;

import com.example.backend_v2.utils.JwtUtil;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	@Qualifier("userDetailsServiceImpl")
	private UserDetailsService userDetailsService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		final String header = request.getHeader("Authorization");

		//Get authorization header and validate
		if(header == null || !header.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		// Get jwt token and validate
		String token = header.split(" ")[1].trim();
		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtUtil.extractUsername(token));

		// token is not valid
		if (!jwtUtil.isTokenValid(token, userDetails)) {
			request.setAttribute("Authorization", null);
			filterChain.doFilter(request, response);
			return;
		}

		// token is valid
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authToken);
		filterChain.doFilter(request, response);
	}
}