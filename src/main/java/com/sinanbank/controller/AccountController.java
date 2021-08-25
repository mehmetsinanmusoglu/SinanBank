package com.sinanbank.controller;

import com.sinanbank.dto.AccountDto;
import com.sinanbank.dto.CreateAccountRequest;
import com.sinanbank.dto.UpdateAccountRequest;
import com.sinanbank.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/Account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getALlAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAllAccountById(@PathVariable String id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        return ResponseEntity.ok(accountService.createAccount(createAccountRequest));

    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable String id, @RequestBody UpdateAccountRequest updateAccountRequest) {
        return ResponseEntity.ok(accountService.updateAccount(id, updateAccountRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAcount(@PathVariable String id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/withdraw/{id}/{amount}")
    public ResponseEntity<AccountDto> withdrawMoney(@PathVariable String id, @PathVariable Double amount){
        return ResponseEntity.ok(accountService.withdrawMoney(id,amount));
    }
    @PutMapping("/add/{id}/{amount}")
    public ResponseEntity<AccountDto> addMoney(@PathVariable String id, @PathVariable Double amount){
        return ResponseEntity.ok(accountService.addMoney(id,amount));
    }

}
