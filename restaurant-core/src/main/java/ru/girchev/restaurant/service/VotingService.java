package ru.girchev.restaurant.service;

import ru.girchev.restaurant.domain.Restaurant;
import ru.girchev.restaurant.domain.User;
import ru.girchev.restaurant.domain.Vote;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 22.11.15.
 */
public interface VotingService {

    boolean vote (Restaurant restaurant, User user);

    boolean checkOneVote(User user);
}
