package com.swipeup.blog.security;

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
public class JWTAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService detailsService;

	@Autowired
	private JWTTokenHelp jwtTokenHelp;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		
		String requestHeader = request.getHeader("Authorization");
        //Bearer 2352345235sdfrsfgsdfsdf
	
		System.out.println("Request header " + requestHeader);
		String userName = null;

		String token = null;

		if (requestHeader != null ) {

			try {

				token = requestHeader.substring(7);
				userName = jwtTokenHelp.getUsernameFromToken(token);
			} catch (IllegalArgumentException e) {
				System.out.print("Unable to get JWT Token");

			} catch (ExpiredJwtException e) {
				System.out.print("Token Expired");

			} catch (MalformedJwtException e) {
				System.out.print("Invalid JWT Exception");

			}

		} else {

			System.out.print("Token is null ");
		}

		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails usr = this.detailsService.loadUserByUsername(userName);
			if (this.jwtTokenHelp.validateToken(token, usr)) {

				UsernamePasswordAuthenticationToken usrPassAuthToken = new UsernamePasswordAuthenticationToken(usr,
						null, usr.getAuthorities());
				usrPassAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usrPassAuthToken);

			} else {

				System.out.print("Token invalid");
			}

		} else {
			System.out.print("username is null or context is not null");

		}
		
		
		filterChain.doFilter(request, response);

	}

}
