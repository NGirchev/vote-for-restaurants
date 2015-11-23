package ru.girchev.restaurant.service;

import ru.girchev.restaurant.domain.Restaurant;
import ru.girchev.restaurant.dto.AbstractDTO;
import ru.girchev.restaurant.dto.RestaurantDTO;

import java.util.List;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 22.11.15.
 */
public interface CrudService<D extends AbstractDTO>{

    D create(D dto) throws Exception;

    D findOne(Long id);

    D update(Long id, D dto);

    List<D> getAll();

    D delete(Long id);
}
