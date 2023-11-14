package com.ethnocopia.service.impl;

import com.ethnocopia.CustomerRepository;
import com.ethnocopia.io.entity.Customer;
import com.ethnocopia.service.CustomerService;
import com.ethnocopia.shared.dto.CustomerDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl  implements CustomerService {
        @Autowired
        CustomerRepository customerRepository;
        @Override
        public void createCustomer(CustomerDto customer) {
                Customer customerEntity = new Customer();
                BeanUtils.copyProperties(customer,customerEntity);
                customerRepository.save(customerEntity);
        }

        @Override
        public void deleteCustomerById(int id) {
                customerRepository.deleteById(id);
        }

        @Override
        public List<CustomerDto> getCustomers(int page,int limit) {
                 List<CustomerDto> returnValue = new ArrayList<>();

                Pageable pageable = PageRequest.of(page,limit);

                Page<Customer> customerPage = customerRepository.findAll(pageable);
                List<Customer> customers = customerPage.getContent();

                for (Customer customer : customers){
                        CustomerDto customerDto = new CustomerDto();
                        BeanUtils.copyProperties(customer,customerDto);
                        returnValue.add(customerDto);
                }

                return returnValue;
        }

        @Override
        public CustomerDto updateCustomer(int id,CustomerDto customerDto) {
                Optional<Customer> customer = customerRepository.findById(id);
                customer.ifPresent(currentCustomer->{
                        currentCustomer.setAge(customerDto.getAge());
                        currentCustomer.setEmail(customerDto.getEmail());
                        currentCustomer.setName(customerDto.getName());
                        currentCustomer.setId(customerDto.getId());

                });
                CustomerDto returnValue = new CustomerDto();
                BeanUtils.copyProperties(customer,returnValue);
                return  returnValue;
        }

}
