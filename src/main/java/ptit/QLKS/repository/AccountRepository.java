package ptit.QLKS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.QLKS.entity.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account , String> {
    Account findByUsername(String username);

    Account findByUsernameAndPassword(String username , String password);

}
