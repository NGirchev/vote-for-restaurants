package ru.girchev.restaurant.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.girchev.restaurant.domain.MenuItem;
import ru.girchev.restaurant.dto.MenuItemDTO;
import ru.girchev.restaurant.service.exception.MenuException;
import ru.girchev.restaurant.service.MenuItemService;

import java.util.List;
import java.util.Objects;


/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 21.11.15.
 */
@Controller
@RequestMapping("/restaurant/{restaurantId}/menu/{menuId}/item")
@Scope("session")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    /**
     * get
     *
     * @return all objects
     */
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public @ResponseBody
    List<MenuItemDTO> getAll(@PathVariable Long restaurantId, @PathVariable Long menuId) throws MenuException {
        verify(restaurantId);
        verify(menuId);
        return menuItemService.getAllForMenu(menuId);
    }

    /**
     * get
     *
     * @param id
     * @return object
     */
    @RequestMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public @ResponseBody MenuItemDTO get(@PathVariable Long restaurantId, @PathVariable Long menuId, @PathVariable Long id) throws MenuException {
        verify(restaurantId);
        verify(menuId);
        return menuItemService.findOne(id);
    }

    /**
     * post
     *
     * @param object
     * @return created object
     */
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public @ResponseBody MenuItemDTO create(@PathVariable Long restaurantId, @PathVariable Long menuId, @RequestBody MenuItemDTO object) throws Exception {
        verify(restaurantId);
        verify(menuId);
        object.setMenuId(menuId);
        return menuItemService.create(object);
    }

    /**
     * put
     *
     * @param id
     * @param object
     * @return updated object
     */
    @RequestMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public @ResponseBody MenuItemDTO update(@PathVariable Long restaurantId, @PathVariable Long menuId, @PathVariable Long id, @RequestBody MenuItemDTO object) throws MenuException {
        verify(restaurantId);
        verify(menuId);
        return menuItemService.update(id, object);
    }

    /**
     * delete
     *
     * @param id
     */
    @RequestMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public @ResponseBody MenuItemDTO delete(@PathVariable Long restaurantId, @PathVariable Long menuId, @PathVariable Long id) throws MenuException {
        verify(restaurantId);
        verify(menuId);
        return menuItemService.delete(id);
    }

    private void verify(Long id) throws MenuException {
        if (Objects.isNull(id)) {
            throw new MenuException(MenuException.MESSAGE_NOT_EMPTY_ID);
        }
    }
}