package com.sinanbank.dto;

import com.sinanbank.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDtoConverter {
    public AccountDto convert(Account account){
        return AccountDto.builder()
                .id(account.getId())
                .balance(account.getBalance())
                .currency(account.getCurrencey())
                .customerId(account.getCustomerId())
                .build();
    }
}
