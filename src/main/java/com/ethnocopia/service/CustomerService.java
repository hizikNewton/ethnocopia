package com.ethnocopia.service;

import com.ethnocopia.shared.dto.CustomerDto;
import java.util.List;
public interface CustomerService {
        void createCustomer(CustomerDto customer);
         List<CustomerDto> getCustomers(int page, int limit);
         void deleteCustomerById(int id);

         CustomerDto updateCustomer(int id);
}
