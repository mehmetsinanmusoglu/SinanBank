package com.sinanbank.service;

import com.sinanbank.dto.AccountDto;
import com.sinanbank.dto.AccountDtoConverter;
import com.sinanbank.dto.CreateAccountRequest;
import com.sinanbank.dto.UpdateAccountRequest;
import com.sinanbank.model.Account;
import com.sinanbank.model.Customer;
import com.sinanbank.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final AccountDtoConverter accountDtoConverter;

    public AccountService(AccountRepository accountRepository, CustomerService customerService, AccountDtoConverter accountDtoConverter) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.accountDtoConverter = accountDtoConverter;
    }

    public AccountDto createAccount(CreateAccountRequest createAccountRequest) {
        Customer customer = customerService.getCustomerById(createAccountRequest.getCustomerId());
        if (customer.getId() == "" || customer.getId() == null) {
            return AccountDto.builder().build();
        }
        Account account = Account.builder()
                .id(createAccountRequest.getId())
                .balance(createAccountRequest.getBalance())
                .currencey(createAccountRequest.getCurrency())
                .customerId(createAccountRequest.getCustomerId())
                .city(createAccountRequest.getCity())
                .build();

        return accountDtoConverter.convert(accountRepository.save(account));
    }

    public AccountDto updateAccount(String id, UpdateAccountRequest request) {
        Customer customer = customerService.getCustomerById(request.getCustomerId());
        if (customer.getId() == "" || customer.getId() == null) {
            return AccountDto.builder().build();
        }
        Optional<Account> accountOptional = accountRepository.findById(id);
        accountOptional.ifPresent(account -> {
            account.setBalance(request.getBalance());
            account.setCity(request.getCity());
            account.setCurrencey(request.getCurrency());
            account.setCustomerId(request.getCustomerId());
            accountRepository.save(account);

        });
        return accountOptional.map(accountDtoConverter::convert).orElse(new AccountDto());
    }

    public List<AccountDto> getALlAccounts() {
        List<Account> accountList = accountRepository.findAll();
        return accountList.stream().map(accountDtoConverter::convert).collect(Collectors.toList());
    }

    public AccountDto getAccountById(String id) {
        return accountRepository.findById(id).map(accountDtoConverter::convert).orElse(new AccountDto());
    }

    public void deleteAccount(String id) {
        accountRepository.deleteById(id);
    }

    public AccountDto withdrawMoney(String id, Double amount) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        accountOptional.ifPresent(account -> {
            if (account.getBalance() > amount) {
                account.setBalance(account.getBalance() - amount);
                accountRepository.save(account);
            } else {
                System.out.println("Insufficient funds -> accountId: " + id + " balance: " + account.getBalance() + " amount: " + amount);

            }
        });
        return accountOptional.map(accountDtoConverter::convert).orElse(new AccountDto());
    }

    public AccountDto addMoney(String id, Double amount) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        accountOptional.ifPresent(account -> {

            account.setBalance(account.getBalance() + amount);
            accountRepository.save(account);
        });
        return accountOptional.map(accountDtoConverter::convert).orElse(new AccountDto());
    }

}
