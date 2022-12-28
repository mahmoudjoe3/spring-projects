package com.mahmoudjoe.springsecurityapp.config;

import com.mahmoudjoe.springsecurityapp.repo.UserRepo;
import com.mahmoudjoe.springsecurityapp.services.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserRepo userRepo;
    /*** Auth filtering steps on a GET request
     * [1] check if JWT exist  --> authHeader is existed
     *          if not doFilter and return
     *      [1.1] get the token and userName from authHeader
     * [2] check if userName not null and no Authentication in SecurityContextHolder
     *      if so
     *              [2.1] load user details from user details service object
     *              [2.2] check if he has valid token
     *                    if so
     *                          [2.2.1] get the user details auth token and assign request to it
     *                          [2.2.2] set authToken to SecurityContextHolder
     * [3] doFilter
     */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader(AUTHORIZATION);
        final String userName;
        final String jwtToken;

        if(authHeader == null|| !authHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }
        jwtToken = authHeader.substring(7); // header is starting with Bearer
        userName = jwtUtils.extractUsername(jwtToken);
        if(userName!=null && SecurityContextHolder.getContext().getAuthentication() == null)
        {
            UserDetails userDetails = userRepo.findUserByUserName(userName);
            if(jwtUtils.IsTokenValid(jwtToken,userDetails)){
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);
    }

}
