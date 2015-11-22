package ru.girchev.restaurant.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import ru.girchev.restaurant.domain.*;
import ru.girchev.restaurant.repository.*;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 22.11.15.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Transactional
public class DataBaseInitializer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier(value = "transactionManager")
    protected JpaTransactionManager txManager;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @PostConstruct
    public void init() {
        logger.info("Initializing data");
        TransactionTemplate tmpl = new TransactionTemplate(txManager);
        tmpl.execute(new TransactionCallbackWithoutResult() {

            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                createUserAndRoles();
                createMenuAndMenuItems(createRestaurants());
            }
        });
        logger.info("Initializing data complited");
    }

    private void createUserAndRoles() {
        List<Role> roleList = new ArrayList<>();
        roleList.add(new Role(Role.ROLE_ADMIN));
        roleList.add(new Role(Role.ROLE_USER));
        roleRepository.save(roleList);
        List<User> userList = new ArrayList<>();
        userList.add(new User.Builder()
                .withEmail("admin@restaurant-mail.com")
                .withPassword("pass")
                .withRoles(Arrays.asList(roleList.get(0)))
                .build());
        userList.add(new User.Builder()
                .withEmail("user1@restaurant-mail.com")
                .withPassword("pass")
                .withRoles(Arrays.asList(roleList.get(1)))
                .build());
        userList.add(new User.Builder()
                .withEmail("user2@restaurant-mail.com")
                .withPassword("pass")
                .withRoles(Arrays.asList(roleList.get(1)))
                .build());
        userRepository.save(userList);
    }

    private void createMenuAndMenuItems(List<Restaurant> restaurantList) {
        List<Menu> resultList = new ArrayList<>();
        restaurantList.forEach(r -> {
            resultList.add(new Menu.Builder().withCreatedOn(new Date())
                    .withMenuItems(generateMenuItems())
                    .withRestaurant(r)
                    .build());
        });
        LocalDate today = LocalDate.now();
        today.minusDays(1);

        resultList.add(new Menu.Builder().withCreatedOn(Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .withMenuItems(generateMenuItems())
                .withRestaurant(restaurantList.get(0))
                .build());
        //update values
        List<Menu> savedMenus = menuRepository.save(resultList);
        savedMenus.forEach(m -> {
            List<MenuItem> menuItems = new ArrayList<MenuItem>();
            m.getMenuItems().forEach(mi -> {
                mi.setMenu(m);
                menuItems.add(mi);
            });
            menuItemRepository.save(menuItems);
        });
    }

    private static final List<String> DISHES_NAMES = new ArrayList<>();
    static {
        DISHES_NAMES.add("Baker potato");
        DISHES_NAMES.add("Burger");
        DISHES_NAMES.add("Casserole");
        DISHES_NAMES.add("Chicken salad");
        DISHES_NAMES.add("Crumble");
        DISHES_NAMES.add("Curry");
        DISHES_NAMES.add("Fish pie");
        DISHES_NAMES.add("Frittata");
        DISHES_NAMES.add("Houmous");
    }

    private List<MenuItem> generateMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        Random r = new Random();
        int count = r.nextInt(5)+2;
        for (int i=0; i<count; i++){
            menuItems.add(new MenuItem.Builder()
                    .withName(DISHES_NAMES.get(r.nextInt(DISHES_NAMES.size())))
                    .withPrice(new BigDecimal(r.nextInt(30) + 10))
                    .build());
        }
        return menuItemRepository.save(menuItems);
    }

    private List<Restaurant> createRestaurants() {
        List<Restaurant> resultList = new ArrayList<>();
        resultList.add(new Restaurant("Il-tokio"));
        resultList.add(new Restaurant("Tanuki"));
        resultList.add(new Restaurant("Gvozdy"));
        resultList.add(new Restaurant("O'Hara"));
        return restaurantRepository.save(resultList);
    }
}
