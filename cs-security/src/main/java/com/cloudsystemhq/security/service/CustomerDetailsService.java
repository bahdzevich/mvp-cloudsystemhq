package com.cloudsystemhq.security.service;


import com.cloudsystemhq.repository.CustomerRepository;
import com.cloudsystemhq.repository.RoleRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@PropertySource("classpath:security.properties")
public class CustomerDetailsService implements UserDetailsService {

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDetailsService.class);

  private final CustomerRepository customerRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  @Value("${security.default-role}")
  private String DEFAULT_ROLE_NAME;

  @Autowired
  public CustomerDetailsService(
      CustomerRepository customerRepository,
      RoleRepository roleRepository,
      PasswordEncoder passwordEncoder) {
    this.customerRepository = customerRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    if (email == null) {
      throw new IllegalArgumentException("Email is null");
    }

    return customerRepository.findCustomerByEmail(email)
        .map(user -> {
          List<GrantedAuthority> authorities = user.getRoles()
              .stream()
              .map(role -> new SimpleGrantedAuthority(role.getName()))
              .collect(Collectors.toList());
          return new org.springframework.security.core.userdetails.
              User(user.getEmail(), user.getPassword(), authorities);
        })
        .orElseThrow(() -> new UsernameNotFoundException("Customer not found."));
  }
}
