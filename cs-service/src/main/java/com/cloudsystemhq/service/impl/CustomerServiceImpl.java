package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.model.domain.Customer;
import com.cloudsystemhq.model.dto.request.CustomerRequestDto;
import com.cloudsystemhq.model.dto.response.CustomerResponseDto;
import com.cloudsystemhq.repository.CustomerRepository;
import com.cloudsystemhq.security.service.CustomerRegistrationService;
import com.cloudsystemhq.service.ICustomerService;
import com.cloudsystemhq.service.util.mapping.CustomerMapper;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
    Customer savedCustomer = registrationService
        .createCustomer(mapper.convertToEntity(customerRequestDto));
    return mapper.convertToDto(savedCustomer);
  }

  @Override
  Function<Customer, Customer> updateEntity(final Customer customer) {
    return (persistedCustomer) -> {
      persistedCustomer.setEmail(customer.getEmail());
      persistedCustomer.setName(customer.getName());
      persistedCustomer.setPhone(customer.getPhone());
      persistedCustomer.setDiscount(customer.getDiscount());
      persistedCustomer.getOrders().clear();
      persistedCustomer.getOrders()
          .addAll(
              customer.getOrders()
                  .stream()
                  .peek(order -> {
                    order.setCustomer(persistedCustomer);
                    if (order.getSupportInfo() != null) {
                      order.getSupportInfo().setOrder(order);
                    }
                    if (order.getInfrastructureInfo() != null) {
                      order.getInfrastructureInfo().setOrder(order);
                    }
                    order.getInvoices()
                        .forEach(invoice -> invoice
                            .setOrder(order)); // violates not-null constraint
                  })
                  .collect(Collectors.toList())
          );
      return repository.save(persistedCustomer);
    };
  }
}
