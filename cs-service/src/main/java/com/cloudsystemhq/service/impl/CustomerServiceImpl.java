package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.model.domain.Customer;
import com.cloudsystemhq.model.dto.request.CustomerRequestDto;
import com.cloudsystemhq.model.dto.response.CustomerResponseDto;
import com.cloudsystemhq.repository.CustomerRepository;
import com.cloudsystemhq.security.service.CustomerRegistrationService;
import com.cloudsystemhq.security.service.EntityAlreadyExistsException;
import com.cloudsystemhq.service.ICustomerService;
import com.cloudsystemhq.service.IEmailService;
import com.cloudsystemhq.service.IOtpService;
import com.cloudsystemhq.service.ISmsProviderService;
import com.cloudsystemhq.service.smsApi.BaseResponse;
import com.cloudsystemhq.service.smsApi.SendOtpResponse;
import com.cloudsystemhq.service.smsApi.SentSms;
import com.cloudsystemhq.service.smsApi.SmsList;
import com.cloudsystemhq.service.smsApi.VerifyOtpResponse;
import com.cloudsystemhq.service.util.mapping.CustomerMapper;
import java.util.Optional;
import java.util.Random;
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
  private final ISmsProviderService smsProviderService;
  private final IOtpService otpService;
  private final CustomerRepository customerRepository;
  private final IEmailService emailService;

  public CustomerServiceImpl(
      CustomerRepository repository,
      CustomerMapper mapper,
      CustomerRegistrationService registrationService,
      ISmsProviderService smsProviderService,
      IOtpService otpService,
      IEmailService emailService
  ) {
    super(repository, mapper);
    this.customerRepository = repository;
    this.registrationService = registrationService;
    this.smsProviderService = smsProviderService;
    this.otpService = otpService;
    this.emailService = emailService;
  }

  @Override
  @Transactional
  public CustomerResponseDto create(final CustomerRequestDto customerRequestDto) {
    Assert.notNull(customerRequestDto, "Admin is null.");
    Customer savedCustomer = registrationService
        .createCustomer(mapper.convertToEntity(customerRequestDto));
    emailService.sendWelcomeMessage(savedCustomer.getEmail(), "Registration in CloudSystemHQ",
        savedCustomer.getName());
    return mapper.convertToDto(savedCustomer);
  }

  @Override
  public SentSms sendSms(final Long id) {
    Optional<Customer> customerOptional = this.repository.findById(id);
    if (customerOptional.isPresent()) {
      return smsProviderService.sendSmsToCustomer(customerOptional.get().getPhone(),
          String.valueOf(10000 + new Random().nextInt(90000))); // random from 10000 to 90000
    }
    return new SentSms("error", null, "There is no such customer");
  }

  @Override
  public BaseResponse confirmCustomer(final Long id, final String customerSmsCode) {
    Optional<Customer> customerOptional = this.repository.findById(id);
    if (customerOptional.isPresent()) {
      Customer customer = customerOptional.get();
      SmsList recentCustomersSms = smsProviderService.getRecentCustomersSms(customer.getPhone());
      boolean confirmed = recentCustomersSms.getMessages().stream()
          .anyMatch(message -> message.getText().equals(customerSmsCode));
      if (confirmed) {
        Customer confirmedCustomer = customerOptional.get();
        confirmedCustomer.setConfirmed(true);
        repository.save(confirmedCustomer);
        return new BaseResponse("success", null, "Customer was confirmed");
      } else {
        return new BaseResponse("error", null, "Invalid customer's code");
      }
    }
    return new BaseResponse("error", null, "There is no such customer");
  }

  @Override
  public SendOtpResponse sendOtp(Long id) {
    Optional<Customer> customerOptional = this.repository.findById(id);
    if (customerOptional.isPresent()) {
      return otpService.sendOtp(customerOptional.get().getId(), customerOptional.get().getPhone(),
          "Nuclear confirmation code: $OTP");
    }
    return new SendOtpResponse(false, null, null, null, "Customer with such id was not found");
  }

  @Override
  public VerifyOtpResponse verifyOtp(Long id, Integer receivedOtp) {
    Optional<Customer> customerOptional = this.repository.findById(id);
    if (customerOptional.isPresent()) {
      return otpService.verifyOtp(id, receivedOtp);
    }
    return new VerifyOtpResponse(false, false, "Customer with such id was not found");
  }

  @Override
  Function<Customer, Customer> updateEntity(final Customer customer) {
    customerRepository
        .findCustomerByEmail(customer.getEmail())
        .ifPresent(entity -> {
          throw new EntityAlreadyExistsException(
              String.format("Customer with email: '%s' already exists",
                  customer.getEmail()));
        });
    return (persistedCustomer) -> {
      persistedCustomer.setEmail(customer.getEmail()); //ToDo: additional logic to update email
      persistedCustomer.setName(customer.getName());
      persistedCustomer.setPhone(customer.getPhone()); //ToDo: additional logic to update phone
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
