package ptit.QLKS.repository;

import ptit.QLKS.entity.Account;

import java.util.List;

public interface AccountCustomRepository {
    List<Account> getAccountByConditions(String username , String address , String tel , String idCard ,boolean isRequest, int page , int size , long totalElement);
    public Long getTotalElementByConditions(String username , String address , String tel , String idCard ,boolean isRequest);
}
