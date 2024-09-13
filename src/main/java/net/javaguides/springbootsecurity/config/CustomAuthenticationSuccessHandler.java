package net.javaguides.springbootsecurity.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String role = authentication.getAuthorities().stream()
                .map(auth -> auth.getAuthority())
                .findFirst()
                .orElse("ROLE_USER"); // Default role if none found

        String targetUrl = "/";
        if (role.equals("ROLE_ADMIN")) {
            targetUrl = "/home"; // Redirect URL for admins
        } else if (role.equals("ROLE_USER")) {
            targetUrl = "/userHome"; // Redirect URL for users
        }

        response.sendRedirect(targetUrl);
    }
}

