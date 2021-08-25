package com.sinanbank;


import com.sinanbank.model.Account;
import com.sinanbank.model.City;
import com.sinanbank.model.Customer;
import com.sinanbank.repository.AccountRepository;
import com.sinanbank.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SinanbankApplication implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public SinanbankApplication(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SinanbankApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Customer c1 = Customer.builder()
                .id("12345678")
                .name("Sinan")
                .city(City.IZMIR)
                .address("Ev")
                .dateOfBirth(1988)
                .build();
        Customer c2 = Customer.builder()
                .id("12345679")
                .name("Mehmet")
                .city(City.ISTANBUL)
                .address("Ev")
                .dateOfBirth(1989)
                .build();
        Customer c3 = Customer.builder()
                .id("12345671")
                .name("Ali")
                .city(City.MANISA)
                .address("Ev")
                .dateOfBirth(2002)
                .build();
        customerRepository.saveAll(Arrays.asList(c1, c2, c3));

        Account a1 = Account.builder()
                .id("100")
                .customerId("12345678")
                .city(City.ISTANBUL)
                .balance(1320.0)
                .build();
        Account a2 = Account.builder()
                .id("101")
                .customerId("12345679")
                .city(City.ISTANBUL)
                .balance(7898.0)
                .build();
        Account a3 = Account.builder()
                .id("102")
                .customerId("12345671")
                .city(City.ISTANBUL)
                .balance(120000.0)
                .build();
        accountRepository.saveAll(Arrays.asList(a1, a2, a3));
    }
}
