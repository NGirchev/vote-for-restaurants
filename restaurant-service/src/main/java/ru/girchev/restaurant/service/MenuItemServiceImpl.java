package ru.girchev.restaurant.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.girchev.restaurant.domain.Menu;
import ru.girchev.restaurant.domain.MenuItem;
import ru.girchev.restaurant.domain.Restaurant;
import ru.girchev.restaurant.dto.MenuItemDTO;
import ru.girchev.restaurant.dto.RestaurantDTO;
import ru.girchev.restaurant.mapper.MenuItemMapper;
import ru.girchev.restaurant.mapper.MenuMapper;
import ru.girchev.restaurant.mapper.RestaurantMapper;
import ru.girchev.restaurant.repository.MenuItemRepository;
import ru.girchev.restaurant.repository.MenuRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 22.11.15.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
@Transactional
public class MenuItemServiceImpl implements MenuItemService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public MenuItemDTO create(MenuItemDTO dto) throws Exception {
        MenuItemDTO retVal = new MenuItemDTO();
        if (Objects.nonNull(dto)) {
            Menu menuEntity = menuRepository.findOne(dto.getMenuId());
            if (Objects.nonNull(menuEntity)) {
                MenuItem itemEntity = menuItemRepository.save(new MenuItem.Builder()
                        .withMenu(menuEntity)
                        .withName(dto.getName())
                        .withPrice(new BigDecimal(dto.getCost()))
                        .build());
                menuEntity.getMenuItems().add(itemEntity);
                retVal = new MenuItemMapper().map(itemEntity);
            }
        }
        return retVal;
    }

    @Override
    public MenuItemDTO findOne(Long id) {
        MenuItemDTO retVal = new MenuItemDTO();
        MenuItem item = menuItemRepository.findOne(id);
        if (Objects.nonNull(item)) {
            retVal = new MenuItemMapper().map(item);
        }
        return retVal;
    }

    @Override
    public MenuItemDTO update(Long id, MenuItemDTO dto) {

        MenuItemDTO retVal = new MenuItemDTO();
        if (Objects.nonNull(dto)) {
            final MenuItem item = menuItemRepository.findOne(id);
            if (Objects.nonNull(item)) {
                item.setName(dto.getName());
                item.setPrice(new BigDecimal(dto.getCost()));
                retVal = new MenuItemMapper().map(menuItemRepository.save(item));
            }
        }
        return retVal;
    }

    @Override
    public List<MenuItemDTO> getAll() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public MenuItemDTO delete(Long id) {
        MenuItemDTO retVal = new MenuItemDTO();
        if (Objects.nonNull(id)) {
            final MenuItem item = menuItemRepository.findOne(id);
            if (Objects.nonNull(item)) {
                menuItemRepository.delete(item);
                retVal = new MenuItemMapper().map(item);
            }
        }
        return retVal;
    }

    @Override
    public List<MenuItemDTO> getAllForMenu(Long menuId) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
