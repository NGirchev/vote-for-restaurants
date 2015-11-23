package ru.girchev.restaurant.service;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.girchev.restaurant.domain.*;
import ru.girchev.restaurant.repository.RestaurantRepository;
import ru.girchev.restaurant.repository.UserRepository;
import ru.girchev.restaurant.repository.VoteRepository;
import ru.girchev.restaurant.util.DateUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.Objects;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 21.11.15.
 */
@Service
@Transactional
public class VotingServiceImpl implements VotingService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private VoteRepository voteRepository;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private RestaurantRepository restaurantRepository;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public boolean vote(Long restaurantId, User user) {
        if (checkVoteTime() && user != null) {
            final Restaurant restaurantEntity
                    = restaurantRepository.findByIdAndDeletedFalse(restaurantId);
            if (Objects.nonNull(restaurantEntity)) {
                Vote voteEntity = voteRepository.findByUser(user);
                if (Objects.isNull(voteEntity)) {
                    voteEntity = new Vote.Builder()
                            .withLastUpdated(new Date())
                            .withRestaurant(restaurantEntity)
                            .withUser(userRepository.findOne(user.getId())).build();
                } else {
                    voteEntity.setLastUpdated(new Date());
                    voteEntity.setRestaurant(restaurantEntity);
                }
                voteRepository.save(voteEntity);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkVoteTime() {
        return new Date().before(DateUtils.getTodayWithTime(11, 0));
    }

    @Override
    public int calculateRating(Long restaurantId) {
        int result = 0;
        if (Objects.nonNull(restaurantId)) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Vote> from = cq.from(Vote.class);
            cq.select(cb.count(from));
            Predicate p1 = cb.equal(from.get(Vote_.restaurant).get(Restaurant_.id), restaurantId);
            Predicate p2 = cb.greaterThanOrEqualTo(from.get(Vote_.lastUpdated),cb.currentDate());
            cq.where(p1, p2);
            result = em.createQuery(cq).getSingleResult().intValue();
        }
        return result;
    }


}
