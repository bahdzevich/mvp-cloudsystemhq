package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.model.domain.Customer;
import com.cloudsystemhq.model.dto.request.CustomerRequestDto;
import com.cloudsystemhq.model.dto.response.CustomerResponseDto;
import com.cloudsystemhq.repository.CustomerRepository;
import com.cloudsystemhq.security.service.CustomerRegistrationService;
import com.cloudsystemhq.service.ICustomerService;
import com.cloudsystemhq.service.util.mapping.CustomerMapper;
import java.util.function.Function;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends
    AbstractBaseServiceImpl<Customer, CustomerRequestDto, CustomerResponseDto, Long> implements
    ICustomerService {

  //  private final CustomerRepository customerRepository;
  private final CustomerRegistrationService registrationService;


  public CustomerServiceImpl(
      CustomerRepository repository,
      CustomerMapper mapper,
      CustomerRegistrationService registrationService) {
    super(repository, mapper);
    this.registrationService = registrationService;
  }

  /*@Override
  public Customer create(final Customer customer) {
    Assert.notNull(customer, "Admin is null.");
    return registrationService.createCustomer(customer);
  }

  @Override
  public Optional<Customer> findOne(final Long id) {
    Assert.notNull(id, "Admin id is null.");
    return customerRepository.findById(id);
  }

  @Override
  public List<Customer> findAll() {
    return customerRepository.findAll();
  }

  @Override
  public Page<Customer> findPage(final Integer page, final Integer size) {
    Assert.notNull(page, "Page number is null.");
    Assert.notNull(size, "Page size is null.");
    return customerRepository.findAll(PageRequest.of(page, size));
  }

  @Override
  @Transactional
  public Optional<Customer> update(final Long id, final Customer customer) {
    Assert.notNull(id, "Customer id is null.");
    Assert.notNull(customer, "Customer is null.");
    return customerRepository.findById(id).map(updateUser(customer));
  }

  @Override
  @Transactional
  public Optional<Customer> delete(final Long id) {
    Assert.notNull(id, "Customer id is null.");
    Optional<Customer> userOptional = customerRepository.findById(id);
    userOptional.ifPresent(customerRepository::delete);
    return userOptional;
  }
*/

  @Override
  Function<Customer, Customer> updateEntity(final Customer newEntity) {
    return (persistedCustomer) -> {
      persistedCustomer.setEmail(newEntity.getEmail());
      persistedCustomer.setName(newEntity.getName());
      persistedCustomer.setPhone(newEntity.getPhone());
      return persistedCustomer;
    };
  }
}
