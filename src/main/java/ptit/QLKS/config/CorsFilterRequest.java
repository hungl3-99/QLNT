package ptit.QLKS.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import ptit.QLKS.entity.Account;
import ptit.QLKS.repository.AccountRepository;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Log4j2
public class CorsFilterRequest extends AbstractAuthenticationProcessingFilter implements Filter {

    @Resource
    JwtUtils jwtUtils;

    @Resource
    AccountRepository accountRepository;

    public CorsFilterRequest() {
        super("/api/**");
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "xsrf-token");
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String method = request.getMethod();
        String paramUrl = "";
        String requestUri = request.getRequestURI();

        if (requestUri.startsWith("/api")) {
            String[] parts = requestUri.split("/");
            paramUrl = "/" + parts[2];
        }
        Map<String, String> mapParam = new HashMap<>();
        mapParam.put("token", authHeader);


        if (!(method.equals("POST") || method.equals("PUT") || method.equals("GET") ) || paramUrl.isEmpty() ||(method.equals("GET")  &&  paramUrl.equalsIgnoreCase("/room"))) {
            chain.doFilter(request, response);
            return;
        }
        if (paramUrl.startsWith("/login") || paramUrl.equalsIgnoreCase("/logout") || paramUrl.equalsIgnoreCase("/register")
                || paramUrl.equalsIgnoreCase("/forgot-password") ||paramUrl.equalsIgnoreCase("/upload")) {
            chain.doFilter(request, response);
        }
        else {
            if (authHeader != null) {
                String token = authHeader.replace("Bearer ", "");
                String username;
                try {
                    username = verifyToken(token);
                    log.info(username);
                } catch (Exception e) {
                    response.getWriter().write(e.getMessage());
                    return;
                }
                Account account = accountRepository.findByUsername(username);
                setStaffAuthentication(request, account);
                chain.doFilter(request, response);
                return;
            }
            else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("User Have no permission !!!");
            }
        }
    }

    private String verifyToken(String encodedAccessToken) {
        String accessToken = new String(Base64.getDecoder().decode(encodedAccessToken));
        String subject = jwtUtils.extractSubject(accessToken);
        String[] parts = subject.split(Pattern.quote("|"));

        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid access token");
        }

        log.info(parts[0] +" " + parts[1]);
        Account account = accountRepository.findByUsername(parts[0]);

        if (account == null) {
            throw new IllegalArgumentException("Invalid access token");
        }

        if (jwtUtils.isTokenExpired(accessToken)) {
            throw new IllegalArgumentException("Token is expired");
        }

        return parts[0];
    }

    private void setStaffAuthentication(ServletRequest req, Account account) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(account.getRole()));
        UserDetails userDetail = new User(account.getUsername(), "", authorities);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
                authorities);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) req));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public void destroy() {
    }

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        // TODO Auto-generated method stub
        return null;
    }
}
