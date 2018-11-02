package com.cloudsystemhq.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * Resource server configuration class
 *
 * {@link EnableResourceServer} enables a resource server. By default this annotation creates a
 * security filter which authenticates requests via an incoming OAuth2 token. The filter is an
 * instance of WebSecurityConfigurerAdapter which has an hard-coded order of 3 (Due to some
 * limitations of Spring Framework). You need to tell Spring Boot to set OAuth2 request filter order
 * to 3 to align with the hardcoded value. You do that by adding security.oauth2.resource.filter-order
 * = 3 in the security.properties file.
 *
 * @author Eugene Bogdevich
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

  private ResourceServerTokenServices tokenServices;

  @Value("${security.jwt.resource-ids}")
  private String RESOURCE_ID;

  @Autowired
  public ResourceServerConfiguration(ResourceServerTokenServices tokenServices) {
    super();
    this.tokenServices = tokenServices;
  }

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources.resourceId(RESOURCE_ID).tokenServices(tokenServices);
  }

  /**
   *
   * @param http
   * @throws Exception
   */
  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
        .requestMatchers()
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/api/customers").permitAll()
        .antMatchers("/api/**").authenticated()
        .anyRequest().authenticated();
        /*
                .antMatchers("/phonebook/api/profiles/csv/**").hasRole("ADMIN")
                .antMatchers("/phonebook/api/profiles/**").authenticated()
                .antMatchers("/phonebook/api/news/**").authenticated();
        */
  }
}
