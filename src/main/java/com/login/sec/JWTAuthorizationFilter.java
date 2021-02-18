package com.login.sec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// verifier la signateur
		// reccuperation du headr
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Origin,Accept,X-Requested-With, Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,authorization");
		response.addHeader("Access-Control-Expose-Headers",
				"Access-Control-Allow-Origin,Access-Control-Allow-Credentials,authorization");
		if (request.getMethod().equals("OPTIONS")) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else if (request.getRequestURI().equals("/login")) {
			filterChain.doFilter(request, response);
			return;

		} else {

			String jwtToken = request.getHeader(SecurityParams.HEADER_NAME);
			System.out.println("Token =" + jwtToken);
			if (jwtToken == null || !jwtToken.startsWith(SecurityParams.HEADER_PREFIX)) {
				// je passe au filter suivant ,pas d'authentification
				filterChain.doFilter(request, response);
				return;
			}
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256("...")).build();
			String jwt = jwtToken.substring(SecurityParams.HEADER_PREFIX.length());
			// je decode le JWT
			DecodedJWT decodeJWT = verifier.verify(jwt);
			System.out.println("JWT=" + jwt);
			String username = decodeJWT.getSubject();

			List<String> roles = decodeJWT.getClaims().get("roles").asList(String.class);
			System.out.println("username" + username);
			System.out.println("roles" + roles);
			// transformer les roles a une collection de grantedAuto
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			roles.forEach(rn -> {
				authorities.add(new SimpleGrantedAuthority(rn));
			});
			// mnt authentification
			UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(username, null,
					authorities);
			SecurityContextHolder.getContext().setAuthentication(user);
			// stocker les roles dans JWT
			filterChain.doFilter(request, response);

		}

	}

}
