package ru.girchev.restaurant.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.girchev.restaurant.domain.Menu;
import ru.girchev.restaurant.domain.Menu_;
import ru.girchev.restaurant.domain.Restaurant;
import ru.girchev.restaurant.domain.Restaurant_;
import ru.girchev.restaurant.dto.MenuDTO;
import ru.girchev.restaurant.dto.MenuItemDTO;
import ru.girchev.restaurant.dto.RestaurantDTO;
import ru.girchev.restaurant.exception.MenuException;
import ru.girchev.restaurant.mapper.MenuMapper;
import ru.girchev.restaurant.repository.MenuRepository;
import ru.girchev.restaurant.repository.RestaurantRepository;
import ru.girchev.restaurant.util.DateUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 22.11.15.
 */
@Service
public class MenuServiceImpl implements MenuService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private MenuRepository menuRepository;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Menu findLastMenu(Restaurant restaurant) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Menu> cq = cb.createQuery(Menu.class);
        Root<Menu> from = cq.from(Menu.class);
        cq.select(from);
        cq.where(
                cb.equal(from.get(Menu_.restaurant).get(Restaurant_.id), restaurant.getId()));
        cq.orderBy(cb.asc(from.get(Menu_.createdOn)));
        return em.createQuery(cq).getResultList().get(0);
    }

    @Override
    public MenuDTO create(MenuDTO dto) throws MenuException {
        MenuDTO retVal = new MenuDTO();
        if (Objects.nonNull(dto) && Objects.nonNull(dto.getItems())) {
            if (Objects.nonNull(dto.getId())) {
                throw new MenuException(MenuException.MESSAGE_ONE_PER_DAY);
            }
            Restaurant restaurant = restaurantRepository.findByIdAndDeletedFalse(dto.getId());
            if (checkMenuExist(restaurant)) {
                throw new MenuException(MenuException.MESSAGE_ONE_PER_DAY);
            }

            retVal = new MenuMapper().map(
                    menuRepository.save(new Menu.Builder()
                            .withCreatedOn(new Date())
                            .withRestaurant(restaurant)
                            .build()));
        }
        return retVal;
    }

    private boolean checkMenuExist(Restaurant restaurant) {
        Menu lastMenu = findLastMenu(restaurant);
        return DateUtils.isToday(lastMenu.getCreatedOn());
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
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
