package com.productblog.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
