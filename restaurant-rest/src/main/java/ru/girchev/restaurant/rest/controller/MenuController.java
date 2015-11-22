package ru.girchev.restaurant.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.girchev.restaurant.dto.MenuDTO;
import ru.girchev.restaurant.service.MenuService;

import java.util.List;


/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 21.11.15.
 */
@Controller
@RequestMapping("/restaurant/{restaurantId}/menu")
@Scope("session")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * get
     *
     * @return all objects
     */
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public @ResponseBody
    List<MenuDTO> getAll(@PathVariable Long restaurantId) {
        return menuService.getAll();
    }

    /**
     * get
     *
     * @param id
     * @return object
     */
    @RequestMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public @ResponseBody MenuDTO get(@PathVariable Long restaurantId, @PathVariable Long id) {
        return menuService.findOne(id);
    }

    /**
     * post
     *
     * @param object
     * @return created object
     */
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public @ResponseBody MenuDTO create(@PathVariable Long restaurantId, @RequestBody MenuDTO object) throws Exception{
        return menuService.create(object);
    }

    /**
     * put
     *
     * @param id
     * @param object
     * @return updated object
     */
    @RequestMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public @ResponseBody MenuDTO update(@PathVariable Long restaurantId, @PathVariable Long id, @RequestBody MenuDTO object) {
        return menuService.update(id, object);
    }

    /**
     * delete
     *
     * @param id
     */
    @RequestMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public void delete(@PathVariable Long restaurantId, @PathVariable Long id) {
        menuService.delete(id);
    }
}