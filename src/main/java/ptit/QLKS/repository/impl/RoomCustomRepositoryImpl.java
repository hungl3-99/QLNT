package ptit.QLKS.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import ptit.QLKS.entity.Account;
import ptit.QLKS.entity.Room;
import ptit.QLKS.repository.AccountRepository;
import ptit.QLKS.repository.RoomCustomRepository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoomCustomRepositoryImpl implements RoomCustomRepository {

    @Resource
    EntityManager entityManager;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Room> getRoomByCondition(String location, String type, long number,String store, int page, int size , long totalElement) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<Room> room = criteriaQuery.from(Room.class);
        List<Predicate> predicates = getPredicateList(location, criteriaBuilder , room ,  type, number , store);
        int skipNumber;
        if (totalElement <= size) {
            skipNumber = 0;
        } else {
            skipNumber = (page - 1) * size;
        }
        criteriaQuery.select(room).where(predicates.toArray(new Predicate[]{})).orderBy(criteriaBuilder.desc(room.get("createdAt")));
        return entityManager.createQuery(criteriaQuery).setFirstResult(skipNumber).setMaxResults(size).getResultList();
    }

    @Override
    public Long getTotalElement(String location, String type, long number , String store) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Room> room = criteriaQuery.from(Room.class);
        List<Predicate> predicates = getPredicateList(location, criteriaBuilder, room , type , number , store);
        criteriaQuery.select(criteriaBuilder.count(room)).where(predicates.toArray(new Predicate[]{}));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    private List<Predicate> getPredicateList(String location , CriteriaBuilder criteriaBuilder , Root<Room> roomRoot , String type, long number , String store){
        List<Predicate> predicates = new ArrayList<>();

        if(!ObjectUtils.isEmpty(location)){
            predicates.add(criteriaBuilder.like(roomRoot.get("location") , "%"+ location +"%"));
        }

        if(!ObjectUtils.isEmpty(type)){
            predicates.add(criteriaBuilder.like(roomRoot.get("type") , "%"+ type +"%"));
        }

        if(!ObjectUtils.isEmpty(number)){
            predicates.add(criteriaBuilder.lessThanOrEqualTo(roomRoot.get("maxNumberPeople") ,  number));
        }

        if(!ObjectUtils.isEmpty(store)){
            Account account = accountRepository.getById(store);
            predicates.add(criteriaBuilder.equal(roomRoot.get("store") ,  account));
        }

        return predicates;
    }
}
