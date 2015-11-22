package ru.girchev.restaurant.rest.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.girchev.restaurant.dto.MenuDTO;
import ru.girchev.restaurant.dto.MenuItemDTO;

import java.util.List;


/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 21.11.15.
 */
@Controller
@RequestMapping("/restaurant/{restaurantId}/menu/{menuId}/item")
@Scope("session")
public class MenuItemController {


    /**
     * get
     *
     * @return all objects
     */
    @RequestMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public @ResponseBody
    List<MenuItemDTO> getAll(@PathVariable Long restaurantId, @PathVariable Long menuId) {
        return null;
    }

    /**
     * get
     *
     * @param id
     * @return object
     */
    public @ResponseBody MenuItemDTO get(@PathVariable Long restaurantId, @PathVariable Long menuId, @PathVariable Long id) {
        return null;
    }

    /**
     * post
     *
     * @param object
     * @return created object
     */
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public @ResponseBody MenuItemDTO create(@PathVariable Long restaurantId, @PathVariable Long menuId, @RequestBody MenuItemDTO object) {
        return null;
    }

    /**
     * put
     *
     * @param id
     * @param object
     * @return updated object
     */
    @RequestMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public @ResponseBody MenuItemDTO update(@PathVariable Long restaurantId, @PathVariable Long menuId, @PathVariable Long id, @RequestBody MenuItemDTO object) {
        return null;
    }

    /**
     * delete
     *
     * @param id
     */
    @RequestMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public void delete(@PathVariable Long restaurantId, @PathVariable Long menuId, @PathVariable Long id) {

    }
}