package ptit.QLKS.mapper.impl;

import org.springframework.stereotype.Component;
import ptit.QLKS.dto.AccountDTO;
import ptit.QLKS.entity.Account;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountMapper {

    public AccountDTO toDto(Account account){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setUsername(account.getUsername());
        accountDTO.setFullName(account.getFullName());
        accountDTO.setAddress(account.getAddress());
        accountDTO.setTel(account.getTel());
        accountDTO.setBirthDay(account.getBirthDay());
        account.setIdCard(account.getIdCard());
        account.setActive(account.isActive());
        account.setRequest(account.isRequest());
        account.setAvatar(account.getAvatar());

        return accountDTO;
    }

    public List<AccountDTO> toListDto(List<Account> list){
        List<AccountDTO> result = new ArrayList<>();
        for(Account a:list){
            result.add(toDto(a));
        }
        return result;
    }
}
