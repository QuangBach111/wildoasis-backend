package com.example.backend_v2.config.security;

import com.example.backend_v2.utils.JwtUtil;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider implements AuthenticationProvider {
	@Qualifier("userDetailsServiceImpl")
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	HttpSession session;

	@Autowired
	private JwtUtil jwtUtil;


	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = String.valueOf(authentication.getPrincipal());
		String password = String.valueOf(authentication.getCredentials());

		UserDetails userDetails = userDetailsService.loadUserByUsername(email);

		if(userDetails!= null) {
			if(passwordEncoder.matches(password, userDetails.getPassword())) {
				return new UsernamePasswordAuthenticationToken(email, password, userDetails.getAuthorities());
			}

			throw new BadCredentialsException("Your password is not correct!");
		}
		throw new UsernameNotFoundException("Your email is not correct!");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
}