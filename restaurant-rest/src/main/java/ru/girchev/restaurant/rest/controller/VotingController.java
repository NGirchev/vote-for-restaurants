package ru.girchev.restaurant.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.girchev.restaurant.domain.Restaurant;
import ru.girchev.restaurant.domain.User;
import ru.girchev.restaurant.dto.RestaurantDTO;
import ru.girchev.restaurant.dto.VoteResultDto;
import ru.girchev.restaurant.service.RestaurantService;
import ru.girchev.restaurant.service.UserService;
import ru.girchev.restaurant.service.VotingService;

import java.util.List;


/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 21.11.15.
 */
@Controller
@RequestMapping("/vote")
@Scope("session")
@PreAuthorize("isAuthenticated()")
public class VotingController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private VotingService votingService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(value="/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public
    @ResponseBody
    VoteResultDto vote(@PathVariable Long restaurantId) {
        VoteResultDto voteResultDto = new VoteResultDto();
        if (votingService.checkVoteTime()) {
            voteResultDto.setVoted(votingService.vote(restaurantId, userService.getCurrentUser()));
        } else {
            voteResultDto.setMessage("Is too late. You can't vote today.");
        }
        return voteResultDto;
    }

}