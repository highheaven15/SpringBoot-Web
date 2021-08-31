package com.example.demo.service.account;

import com.example.demo.domain.account.AccountDTO;
import com.example.demo.mapper.account.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired //사용할 객체를 선언
    private AccountMapper mapper;


    @Override
    public void register(AccountDTO account) {

        mapper.insert(account);
    }

    @Override
    public AccountDTO get(String member_id) {

        return mapper.read(member_id);
    }

    @Override
    public void modify(AccountDTO account) {

        mapper.update(account);
    }

    @Override
    public void remove(String member_id) {

        mapper.delete(member_id);
    }

    @Override
    public void deposit(AccountDTO account) {

        mapper.push(account);
    }

    public void withdrawal(AccountDTO account){

        mapper.pull(account);
    }

    @Transactional
    @Override
    public void transfer(AccountDTO account) {
        mapper.transactionIn(account);
        mapper.transactionOut(account);
    }

    public String existenceChk(String account_number){

        //System.out.println(account_number);

        AccountDTO accountInfo = mapper.findAccountInfo(account_number);

        //System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+accountInfo);

        if (accountInfo!=null) {
            return "1";
        }
        return "0";
    }

    @Override
    public String accountChk(String memberId) {
        AccountDTO accountInfo = mapper.findMemberAccountInfo(memberId);
        if(accountInfo!=null){
            return "1";
        }
        return "0";
    }
}
