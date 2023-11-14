package com.ethnocopia.ui.model.response;
import com.ethnocopia.shared.dto.CustomerDto;

public record CustomerRest(Integer id, String name, String email, Integer age) {
        public CustomerRest(CustomerDto customerDto){
                this(customerDto.getId(),customerDto.getName(),customerDto.getEmail(),customerDto.getAge());
        }
}
