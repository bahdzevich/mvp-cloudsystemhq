package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.model.domain.Customer;
import com.cloudsystemhq.model.dto.request.CustomerRequestDto;
import com.cloudsystemhq.model.dto.response.CustomerResponseDto;
import com.cloudsystemhq.repository.CustomerRepository;
import com.cloudsystemhq.security.service.CustomerRegistrationService;
import com.cloudsystemhq.service.ICustomerService;
import com.cloudsystemhq.service.util.mapping.CustomerMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl
        extends AbstractBaseServiceImpl<Customer, CustomerRequestDto, CustomerResponseDto, Long>
        implements ICustomerService {

  private final CustomerRegistrationService registrationService;


  public CustomerServiceImpl(
      CustomerRepository repository,
      CustomerMapper mapper,
      CustomerRegistrationService registrationService) {
    super(repository, mapper);
    this.registrationService = registrationService;
  }

  @Override
  @Transactional
  public CustomerResponseDto create(final CustomerRequestDto customerRequestDto) {
    Assert.notNull(customerRequestDto, "Admin is null.");
    Customer savedCustomer = registrationService.createCustomer(mapper.convertToEntity(customerRequestDto));
    return mapper.convertToDto(savedCustomer);
  }

  @Override
  Function<Customer, Customer> updateEntity(final Customer newEntity) {
    return (persistedCustomer) -> {
      persistedCustomer.setEmail(newEntity.getEmail());
      persistedCustomer.setName(newEntity.getName());
      persistedCustomer.setPhone(newEntity.getPhone());
      persistedCustomer.setDiscount(newEntity.getDiscount());
      persistedCustomer.getInvoices().clear();
      persistedCustomer.getInvoices()
              .addAll(
                      newEntity.getInvoices()
                              .stream()
                              .peek(invoice -> invoice.setCustomer(persistedCustomer)) // violates not-null constraint
                              .collect(Collectors.toSet())
              );
      persistedCustomer.getOrders().clear();
      persistedCustomer.getOrders()
              .addAll(
                      newEntity.getOrders()
                              .stream()
                              .peek(order -> order.setCustomer(persistedCustomer))
                              .collect(Collectors.toSet())
      );
      return repository.save(persistedCustomer);
    };
  }
}
