package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.model.domain.Customer;
import com.cloudsystemhq.repository.CustomerRepository;
import com.cloudsystemhq.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer create(final Customer admin) {
        Assert.notNull(admin, "Admin is null.");
        return customerRepository.save(admin);
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

    private Function<Customer, Customer> updateUser(final Customer updatedCustomer) {
        return (persistedCustomer) -> {
            persistedCustomer.setEmail(updatedCustomer.getEmail());
            persistedCustomer.setName(updatedCustomer.getName());
            persistedCustomer.setPhone(updatedCustomer.getPhone());
            return persistedCustomer; };
    }
}
