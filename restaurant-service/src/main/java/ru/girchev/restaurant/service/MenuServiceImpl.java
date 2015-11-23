package ru.girchev.restaurant.service;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.girchev.restaurant.domain.*;
import ru.girchev.restaurant.dto.MenuDTO;
import ru.girchev.restaurant.dto.RestaurantDTO;
import ru.girchev.restaurant.repository.MenuItemRepository;
import ru.girchev.restaurant.service.exception.MenuException;
import ru.girchev.restaurant.mapper.MenuMapper;
import ru.girchev.restaurant.repository.MenuRepository;
import ru.girchev.restaurant.repository.RestaurantRepository;
import ru.girchev.restaurant.util.DateUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 22.11.15.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
    public Menu findLastMenu(Restaurant restaurant) {
        Menu lastMenu = null;
        if (Objects.nonNull(restaurant)) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Menu> cq = cb.createQuery(Menu.class);
            Root<Menu> from = cq.from(Menu.class);
            cq.select(from);
            cq.where(
                    cb.equal(from.get(Menu_.restaurant).get(Restaurant_.id), restaurant.getId()));
            cq.orderBy(cb.desc(from.get(Menu_.createdOn)));
            final List<Menu> menuResList = em.createQuery(cq).getResultList();
            if (!menuResList.isEmpty()) {
                lastMenu = menuResList.get(0);
            }
        }
        return lastMenu;
    }

    @Override
    public MenuDTO create(MenuDTO dto) throws MenuException {
        MenuDTO retVal = new MenuDTO();
        if (Objects.nonNull(dto)) {
            Restaurant restaurant = restaurantRepository.findByIdAndDeletedFalse(dto.getRestaurantId());
            Menu lastMenu = findLastMenu(restaurant);
            if (Objects.nonNull(lastMenu) && DateUtils.isToday(lastMenu.getCreatedOn())) {
                menuItemRepository.delete(lastMenu.getMenuItems());
                retVal = updateMenu(lastMenu, dto);
            } else {
                final Menu newMenuEntity = menuRepository.save(new Menu.Builder()
                        .withCreatedOn(new Date())
                        .withRestaurant(restaurant)
                        .build());
                retVal = updateMenu(newMenuEntity, dto);
            }
        }
        return retVal;
    }

    private MenuDTO updateMenu (final Menu menu, MenuDTO dto) {
        final List<MenuItem> menuItems = new ArrayList<>();
        if (Objects.nonNull(dto.getItems())) {
            dto.getItems().forEach(imd -> {
                menuItems.add(new MenuItem.Builder()
                        .withMenu(menu)
                        .withName(imd.getName())
                        .withPrice(new BigDecimal(imd.getCost()))
                        .build());
            });
        }
        List<MenuItem> savedMenuItems = menuItemRepository.save(menuItems);
        menu.setCreatedOn(new Date());
        menu.setMenuItems(savedMenuItems);
        Menu savedMenuEntity = menuRepository.save(menu);
        return new MenuMapper().map(savedMenuEntity);
    }

    @Override
    public MenuDTO findOne(Long id) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public MenuDTO update(Long id, MenuDTO dto) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<MenuDTO> getAll() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<MenuDTO> getAllForRestaurant(Long Id) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public MenuDTO delete(Long id) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
