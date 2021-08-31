package com.example.demo.service.account;

import com.example.demo.domain.account.AccountDTO;

public interface AccountService {

    public void register(AccountDTO account);

    public AccountDTO get(String member_id);

    public void modify(AccountDTO account);

    public void remove(String member_id);

    public void deposit(AccountDTO account);

    public void withdrawal(AccountDTO account);

    public void transfer(AccountDTO account);

    public String existenceChk(String account_number);

    public String accountChk(String memberId);
}
