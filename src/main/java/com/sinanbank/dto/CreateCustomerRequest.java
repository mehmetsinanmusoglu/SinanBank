package com.sinanbank.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCustomerRequest extends BaseCustomerRequest {
    private String id;

}
