package com.example.demo.mapper.account;

import com.example.demo.domain.account.AccountDTO;

import java.util.List;

public interface AccountMapper {

    public void insert(AccountDTO account);

    public AccountDTO read(String member_id);

    public void update(AccountDTO account);

    public void delete(String member_id);

    public void push(AccountDTO account);

    public void pull(AccountDTO account);

    public void transactionIn(AccountDTO account);

    public void transactionOut(AccountDTO account);

//    public List<AccountDTO> findAccountInfo(String account_number);

    public AccountDTO findAccountInfo(String account_number);

    public AccountDTO findMemberAccountInfo(String memberId);
}
