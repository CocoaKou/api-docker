package ai.wbw.service.common.config;

// import ai.wbw.service.common.filter.JwtTokenFilter;

import ai.wbw.service.core.service.impl.DefaultUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  //    @Autowired
  //    private JwtTokenFilter jwtTokenFilter;
  @Autowired private DefaultUserDetailsService defaultUserDetailsService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(defaultUserDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
      throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  LogoutHandler logoutHandler() {
    return new SecurityContextLogoutHandler();
  }

  @Bean
  @Order(0)
  SecurityFilterChain resources(HttpSecurity http) throws Exception {
    return http.securityMatcher("/images/**", "/**.css", "/**.js")
        .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
        .securityContext(AbstractHttpConfigurer::disable)
        .sessionManagement(AbstractHttpConfigurer::disable)
        .requestCache(RequestCacheConfigurer::disable)
        .build();
  }

  @Bean
  @Order(1)
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    // 禁用csrf(防止跨站请求伪造攻击)
    http.csrf(csrf -> csrf.disable());
    // 配置请求的拦截规则
    http.securityMatcher("/**")
        .authorizeHttpRequests(
            authorize ->
                authorize
                    // 允许所有OPTIONS请求
                    .requestMatchers(HttpMethod.OPTIONS, "/**")
                    .permitAll()
                    //                        .requestMatchers("/**").permitAll()
                    .requestMatchers(
                        "/images/**",
                        "/**.css",
                        "/**.js",
                        "/**",
                        "/login/**",
                        "/app/auth/**",
                        "/oauth2/**",
                        "/api/v1/app/auth/**")
                    .permitAll()
                    .requestMatchers("/error")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
        .formLogin(Customizer.withDefaults())
        .oauth2Login(Customizer.withDefaults())
        //                .formLogin(c -> c.loginPage("/login")
        //                        .loginProcessingUrl("/authenticate")
        //                        .usernameParameter("username")
        //                        .passwordParameter("password")
        //                        .defaultSuccessUrl("/user")
        //                )
        //                .logout(c -> c.logoutSuccessUrl("/?logout"))
        //                .oauth2Login(oc -> oc
        //                        .loginPage("/login")
        //                        .defaultSuccessUrl("/user")
        //                        .userInfoEndpoint(ui -> ui
        //                                .userService(oauth2LoginHandler))
        //                )
        // 禁用缓存
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        // 使用无状态session，即不使用session缓存数据
        // 添加JWT过滤器
        //                .addFilterBefore(jwtTokenFilter,
        // UsernamePasswordAuthenticationFilter.class)
        // 登出操作
        .logout()
        .logoutUrl("/api/v1/auth/logout")
        .addLogoutHandler(logoutHandler())
        .logoutSuccessHandler(
            (request, response, authentication) -> SecurityContextHolder.clearContext());
    return http.build();
  }
}
