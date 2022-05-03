package ptit.QLKS.repository.impl;

import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import ptit.QLKS.entity.Account;
import ptit.QLKS.repository.AccountCustomRepository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountCustomRepositoryImpl implements AccountCustomRepository {

    @Resource
    EntityManager entityManager;

    @Override
    public List<Account> getAccountByConditions(String username, String address, String tel, String idCard , boolean isRequest, String role , int page, int size, long totalElement) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<Account> account = criteriaQuery.from(Account.class);
        List<Predicate> predicates = getPredicateList(username, criteriaBuilder,account , address , tel , idCard , isRequest , role);
        int skipNumber;
        if (totalElement <= size) {
            skipNumber = 0;
        } else {
            skipNumber = (page - 1) * size;
        }
        criteriaQuery.select(account).where(predicates.toArray(new Predicate[]{})).orderBy(criteriaBuilder.desc(account.get("createdAt")));
        return entityManager.createQuery(criteriaQuery).setFirstResult(skipNumber).setMaxResults(size).getResultList();

    }

    @Override
    public Long getTotalElementByConditions(String username, String address, String tel, String idCard , boolean isRequest , String role) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Account> account = criteriaQuery.from(Account.class);
        List<Predicate> predicates = getPredicateList(username, criteriaBuilder,account , address , tel , idCard , isRequest , role);
        criteriaQuery.select(criteriaBuilder.count(account)).where(predicates.toArray(new Predicate[]{}));
        return entityManager.createQuery(criteriaQuery).getSingleResult();

    }

    private List<Predicate> getPredicateList(String username , CriteriaBuilder criteriaBuilder , Root<Account> accountRoot , String address, String tel, String idCard , boolean isRequest , String role){
        List<Predicate> predicates = new ArrayList<>();

        if(!ObjectUtils.isEmpty(username)){
            predicates.add(criteriaBuilder.like(accountRoot.get("username") , "%"+ username +"%"));
        }

        if(!ObjectUtils.isEmpty(address)){
            predicates.add(criteriaBuilder.like(accountRoot.get("address") , "%"+ address +"%"));
        }

        if(!ObjectUtils.isEmpty(tel)){
            predicates.add(criteriaBuilder.equal(accountRoot.get("tel") ,  tel));
        }

        if(!ObjectUtils.isEmpty(idCard)){
            predicates.add(criteriaBuilder.equal(accountRoot.get("idCard") ,  idCard));
        }

        if(!ObjectUtils.isEmpty(isRequest)){
            predicates.add(criteriaBuilder.equal(accountRoot.get("isRequest"), isRequest));
        }

        if(!ObjectUtils.isEmpty(role)){
            predicates.add(criteriaBuilder.equal(accountRoot.get("role"), role));
        }

        return predicates;

    }
}
