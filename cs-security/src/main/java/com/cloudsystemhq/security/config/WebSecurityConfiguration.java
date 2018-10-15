package com.cloudsystemhq.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@PropertySource("classpath:security.properties")
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Value("${security.realm-name}")
  private String SECURITY_REALM;

  @Value(value = "${security.signing-key}")
  private String SIGN_IN_KEY;

  @Value("${security.jwt.validity.access-token}")
  private int ACCESS_TOKEN_VALIDITY_SECONDS;

  @Value("${security.jwt.validity.refresh-token}")
  private int REFRESH_TOKEN_VALIDITY_SECONDS;

  private final UserDetailsService userDetailsService;
  private final PasswordEncoder passwordEncoder;


  @Autowired
  public WebSecurityConfiguration(
      @Qualifier("customerDetailsService")
          UserDetailsService userDetailsService,
      PasswordEncoder passwordEncoder) {
    this.userDetailsService = userDetailsService;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .httpBasic().realmName(SECURITY_REALM)
        .and()
        .authorizeRequests().anyRequest().authenticated()
        .and()
        .csrf().disable();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  /**
   * To enable the resource server to decode access tokens an {@link JwtAccessTokenConverter} bean
   * must be used by both servers.
   *
   * @return {@link JwtAccessTokenConverter}
   */
  @Bean(name = "jwtAccessTokenConverter")
  public JwtAccessTokenConverter jwtAccessTokenConverter() {
    JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
    jwtAccessTokenConverter.setSigningKey(SIGN_IN_KEY);
    return jwtAccessTokenConverter;
  }

  /**
   * A JwtTokenStore bean is needed by the authorization server.
   *
   * @return {@link JwtTokenStore}
   */
  @Bean
  public TokenStore tokenStore() {
    return new JwtTokenStore(jwtAccessTokenConverter());
  }

  @Bean
  @Primary
  public DefaultTokenServices tokenServices() {
    DefaultTokenServices tokenServices = new DefaultTokenServices();
    tokenServices.setTokenStore(tokenStore());
    tokenServices.setSupportRefreshToken(true);
    tokenServices.setTokenEnhancer(jwtAccessTokenConverter());
    tokenServices.setAccessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS);
    tokenServices.setRefreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
    return tokenServices;
  }
}
