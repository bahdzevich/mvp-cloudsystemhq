package com.cloudsystemhq.security.service.details;


import com.cloudsystemhq.model.domain.Customer;
import com.cloudsystemhq.model.domain.Role;
import com.cloudsystemhq.repository.CustomerRepository;
import com.cloudsystemhq.repository.RoleRepository;
import com.cloudsystemhq.security.service.CustomerRegistrationService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
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
import org.springframework.util.Assert;

@Service
@PropertySource("classpath:security.properties")
public class CustomerDetailsService implements UserDetailsService, CustomerRegistrationService {

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

  @Override
  public Customer createCustomer(@NotNull Customer customer) {
    Assert.notNull(customer.getEmail(), "Customer email is null.");
    Assert.notNull(customer.getPassword(), "Customer password is null.");
    String encodedPassword = passwordEncoder.encode(customer.getPassword());
    customer.setPassword(encodedPassword);
    Role role = roleRepository.findRoleByName(DEFAULT_ROLE_NAME)
        .orElseThrow(() -> new RuntimeException("Default role not found."));
    customer.getRoles().add(role);
    return customerRepository.save(customer);
  }
}
