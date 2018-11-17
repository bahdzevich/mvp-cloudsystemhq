package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.model.domain.order.Order;
import com.cloudsystemhq.model.dto.request.OrderRequestDto;
import com.cloudsystemhq.model.dto.response.OrderResponseDto;
import com.cloudsystemhq.repository.CustomerRepository;
import com.cloudsystemhq.repository.OrderRepository;
import com.cloudsystemhq.service.IOrderService;
import com.cloudsystemhq.service.util.mapping.OrderMapper;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl
    extends AbstractBaseServiceImpl<Order, OrderRequestDto, OrderResponseDto, Long>
    implements IOrderService {

  private final static Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class.getName());
  private final CustomerRepository customerRepository;
  private final OrderRepository orderRepository;

  public OrderServiceImpl(
      OrderRepository repository,
      CustomerRepository customerRepository,
      OrderMapper mapper) {
    super(repository, mapper);
    this.customerRepository = customerRepository;
    this.orderRepository = repository;
  }

  public Optional<OrderResponseDto> create(final Long customerId,
      final OrderRequestDto orderRequestDto) {
    Order order = mapper.convertToEntity(orderRequestDto);
    return customerRepository.findById(customerId).map(customer -> {
      customer.getOrders().add(order);
      order.setCustomer(customer);
      if (order.getSupportInfo() != null) {
        order.getSupportInfo().setOrder(order);
      }
      if (order.getInfrastructureInfo() != null) {
        order.getInfrastructureInfo().setOrder(order);
      }
      if (order.getInvoices() != null) {
        order.getInvoices().forEach(invoice -> invoice.setOrder(order));
      }
      customerRepository.save(customer);
      return mapper
          .convertToDto(order);  // id == null, because returned object not persisted yet
    });
  }

  @Override
  public List<OrderResponseDto> findOrdersByCustomerId(final Long customerId) {
    if (!customerRepository.existsById(customerId)) {
      LOGGER.warn("There is no answer with id=" + customerId);
    }
    return orderRepository.findOrdersByCustomerId(customerId)
        .stream()
        .map(mapper::convertToDto)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<OrderResponseDto> update(final Long customerId, final Long orderId,
      final OrderRequestDto influenceOnPrice) {
    if (!customerRepository.existsById(customerId)) {
      LOGGER.warn("There is no customer with id=" + customerId);
    }
    return super.update(orderId, influenceOnPrice);
  }

  @Override
  Function<Order, Order> updateEntity(Order order) {
    return persistedOrder -> {
      if (order.getSupportInfo() != null) {
        order.getSupportInfo().setOrder(persistedOrder);
      }
      persistedOrder.setSupportInfo(order.getSupportInfo());
      persistedOrder.setUrgency(order.getUrgency());
      persistedOrder.setOrderType(order.getOrderType());
      persistedOrder.setIsFinished(order.getIsFinished());
      if (order.getInfrastructureInfo() != null) {
        order.getInfrastructureInfo().setOrder(persistedOrder);
      }
      persistedOrder.setInfrastructureInfo(order.getInfrastructureInfo());
      persistedOrder.getAnswers().clear();
      persistedOrder.getAnswers()
          .addAll(order.getAnswers());
      persistedOrder.getInvoices().clear();
      persistedOrder.getInvoices()
          .addAll(
              order.getInvoices()
                  .stream()
                  .peek(invoice -> invoice.setOrder(persistedOrder))
                  .collect(Collectors.toList()));
      return repository.save(persistedOrder);
    };
  }
}
