package com.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    @NotNull(message = "Name can not be null or empty")
    private String name;
    @NotNull(message = "Email can not be null or empty")
    @Email(message = "Email address should b a valid value")
    private String email;
@Pattern(regexp = "^$|[0-9]{10}",message = "Mobile Number must be 10 digits")
    private String mobileNumber;
}
