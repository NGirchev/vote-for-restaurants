package ru.girchev.restaurant.rest.controller;

import ru.girchev.restaurant.dto.AbstractDTO;
import ru.girchev.restaurant.dto.RestaurantDTO;

import java.util.List;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 22.11.15.
 */
public interface RestfulApi <D extends AbstractDTO> {

    /**
     * get
     * @return all objects
     */
    List<D> getAll() throws Exception;

    /**
     * get
     * @return object
     */
    D get(Long id) throws Exception;

    /**
     * post
     * @param object
     * @return created object
     */
    D create(D object) throws Exception;

    /**
     * put
     * @param id
     * @param object
     * @return updated object
     */
    D update(Long id, D object) throws Exception;

    /**
     * delete
     * @param id
     */
    D delete(Long id) throws Exception;
}
