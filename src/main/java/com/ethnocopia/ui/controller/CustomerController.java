package com.ethnocopia.ui.controller;

import com.ethnocopia.service.CustomerService;
import com.ethnocopia.shared.dto.CustomerDto;
import com.ethnocopia.ui.model.response.CustomerRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
        @Autowired
        CustomerService customerService;
        public record NewCustomerRequest(String name, String email, int age) {}


        @GetMapping
        public List<CustomerRest> getCustomers( @RequestParam(value = "page", defaultValue = "0" ) int page, @RequestParam(value = "limit",defaultValue = "25") int limit) {
                List<CustomerRest> returnValue = new ArrayList<>();
                List<CustomerDto> customers = customerService.getCustomers(page,limit);

                for (CustomerDto customerDto : customers){
                        CustomerRest customerRestModel = new CustomerRest(customerDto);
                        returnValue.add(customerRestModel);
                }

                return returnValue;
        }



        @PostMapping
        public String addCustomer(@RequestBody NewCustomerRequest request) {
                CustomerDto customerDto = new CustomerDto();
                BeanUtils.copyProperties(request,customerDto);
                customerService.createCustomer(customerDto);
                return "customer created successfully";
        }

        @DeleteMapping("{customerId}")
        public String deleteCustomer(@PathVariable("customerId") Integer id) {
                customerService.deleteCustomerById(id);
                return "customer with id "+id.toString()+" successfully deleted";
        }

        @PutMapping("{customerId}")
        public CustomerRest updateCustomer(@PathVariable("customerId") Integer id, @RequestBody NewCustomerRequest request) {
               CustomerDto customerDto = new CustomerDto();
               BeanUtils.copyProperties(request,customerDto);
               CustomerDto updatedCustomer =  customerService.updateCustomer(id,customerDto);
               CustomerRest returnValue = new CustomerRest(updatedCustomer);
               return returnValue;

        }
}

