package ru.girchev.restaurant.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.girchev.restaurant.domain.Restaurant;
import ru.girchev.restaurant.domain.User;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 21.11.15.
 */
@Service
public class VotingServiceImpl implements VotingService{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean vote(Restaurant restaurant, User user) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean checkOneVote(User user) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
