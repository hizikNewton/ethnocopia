package com.ethnocopia.shared.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class CustomerDto  implements Serializable {
        private Integer id;
        private String name;
        private String email;
        private Integer age;
}
