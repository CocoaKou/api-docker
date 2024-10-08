// package ai.wbw.service.common.filter;
//
// import ai.wbw.service.core.dto.TokenSubject;
// import ai.wbw.service.core.service.impl.JwtTokenService;
// import io.jsonwebtoken.JwtException;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Service;
// import org.springframework.util.StringUtils;
// import org.springframework.web.filter.OncePerRequestFilter;
// import java.io.IOException;
// import java.io.PrintWriter;
// import java.util.Objects;
//
// @Service
// public class JwtTokenFilter extends OncePerRequestFilter {
//    @Autowired
//    private JwtTokenService jwtTokenService;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
// FilterChain filterChain) throws ServletException, IOException {
//        try {
//            String token = request.getHeader("Authorization");
//            if(Objects.isNull(token) || !token.startsWith("Bearer ")) {
//                filterChain.doFilter(request, response);
//                return;
//            }
//            token = token.substring(7);
//
//            if (StringUtils.hasText(token) && jwtTokenService.validateToken(token)) {
//                TokenSubject tokenSubject = jwtTokenService.parseToken(token);
//                Authentication authenticate = authenticationManager.authenticate(new
// UsernamePasswordAuthenticationToken(tokenSubject.getUserid(), tokenSubject.getPasswd()));
//                SecurityContextHolder.getContext().setAuthentication(authenticate);
//            }
//
//        } catch (JwtException ex) {
//            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//            PrintWriter writer = response.getWriter();
//            writer.write(ex.getMessage());
//            writer.flush();
//            writer.close();
//            return;
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
// }
