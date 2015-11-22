package ru.girchev.restaurant.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.girchev.restaurant.dto.RestaurantDTO;
import ru.girchev.restaurant.service.RestaurantService;

import java.util.List;


/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 21.11.15.
 */
@Controller
@RequestMapping("/restaurant")
@Scope("session")
public class RestaurantController implements RestfulApi<RestaurantDTO>{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    /**
     * get
     *
     * @return all objects
     */
    @Override
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public @ResponseBody List<RestaurantDTO> getAll() {
        logger.debug("REST: GET ALL RESTAURANTS");
        return restaurantService.getAll();
    }

    /**
     * get
     *
     * @param id
     * @return object
     */
    @Override
    @RequestMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public @ResponseBody
    RestaurantDTO get(@PathVariable Long id) {
        logger.debug("REST: GET RESTAURANT WITH ID:"+id);
        return restaurantService.findOne(id);
    }

    /**
     * post
     *
     * @param object
     * @return created object
     */
    @Override
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public @ResponseBody
    RestaurantDTO create(@RequestBody RestaurantDTO object) throws Exception {
        logger.debug("REST: CREATE RESTAURANT :"+object);
        return restaurantService.create(object);
    }

    /**
     * put
     *
     * @param id
     * @param object
     * @return updated object
     */
    @Override
    @RequestMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public @ResponseBody
    RestaurantDTO update(@PathVariable Long id, @RequestBody RestaurantDTO object) {
        logger.debug("REST: UPDATE :"+object);
        return restaurantService.update(id, object);
    }

    /**
     * delete
     *
     * @param id
     */
    @Override
    @RequestMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        logger.debug("REST: DELETE RESTAURANT :"+id);
        restaurantService.delete(id);
    }

}