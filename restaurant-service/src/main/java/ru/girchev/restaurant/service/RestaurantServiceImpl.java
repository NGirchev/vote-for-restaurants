package ru.girchev.restaurant.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.girchev.restaurant.domain.Menu;
import ru.girchev.restaurant.domain.Restaurant;
import ru.girchev.restaurant.dto.RestaurantDTO;
import ru.girchev.restaurant.mapper.MenuMapper;
import ru.girchev.restaurant.mapper.RestaurantMapper;
import ru.girchev.restaurant.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 22.11.15.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuService menuService;

    @Autowired
    private VotingService votingService;

    @Override
    public RestaurantDTO create(RestaurantDTO dto) {
        RestaurantDTO retVal = new RestaurantDTO();
        if (Objects.nonNull(dto) && StringUtils.isNoneBlank(dto.getName())) {
            retVal = new RestaurantMapper().map(
                    restaurantRepository.save(new Restaurant(dto.getName())));
            if (Objects.nonNull(dto.getMenu())) {
                try {
                    retVal.setMenu(menuService.create(dto.getMenu()));
                } catch (Exception e) {
                    logger.error(e.getLocalizedMessage(), e);
                }
            }
        }
        return retVal;
    }

    @Override
    public RestaurantDTO findOne(Long id) {
        RestaurantDTO retVal = new RestaurantDTO();
        Restaurant restaurant = restaurantRepository.findByIdAndDeletedFalse(id);
        if (Objects.nonNull(restaurant)) {
            retVal = new RestaurantMapper().map(restaurant);
            Menu menu = menuService.findLastMenu(restaurant);
            retVal.setMenu(new MenuMapper().map(menu));
            retVal.setRating(votingService.calculateRating(id));
        }
        return retVal;
    }

    @Override
    public RestaurantDTO update(Long id, RestaurantDTO dto) {
        RestaurantDTO retVal = new RestaurantDTO();
        if (Objects.nonNull(dto) && StringUtils.isNoneBlank(dto.getName())) {
            final Restaurant restaurant = restaurantRepository.findOne(id);
            if (Objects.nonNull(restaurant)) {
                restaurant.setName(dto.getName());
                retVal = new RestaurantMapper().map(restaurantRepository.save(restaurant));
            }
        }
        return retVal;
    }

    @Override
    public List<RestaurantDTO> getAll() {
        logger.debug("SERVICE: find all restaurants");
        List<RestaurantDTO> resultDto = new ArrayList<>();
        restaurantRepository.findAll().stream().forEach(r -> {
            RestaurantDTO rDto = new RestaurantMapper().map(r);
            Menu menu = menuService.findLastMenu(r);
            rDto.setMenu(new MenuMapper().map(menu));
            resultDto.add(rDto);
        });
        logger.debug("SERVICE: count of found restaurants: " + resultDto.size());
        return resultDto;
    }

    @Override
    public RestaurantDTO delete(Long id) {
        RestaurantDTO retVal = new RestaurantDTO();
        Restaurant restaurant = restaurantRepository.findByIdAndDeletedFalse(id);
        if (Objects.nonNull(restaurant)) {
            restaurant.setDeleted(true);
            retVal = new RestaurantMapper().map(restaurantRepository.save(restaurant));
        }
        return retVal;
    }

}
