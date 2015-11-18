package ru.girchev.restaurant.rest.controller;

import org.springframework.web.bind.annotation.*;
import ru.girchev.restaurant.rest.dto.Shop;


/**
 * Created by Girchev N.A. on 15.11.15.
 */
@RestController
@RequestMapping("/main")
public class MainController {

    @RequestMapping(value="{name}", method = RequestMethod.GET)
    public @ResponseBody
    Shop getShopInJSON(@PathVariable String name) {

        Shop shop = new Shop();
        shop.setName(name);
        shop.setStaffName(new String[]{"user", "user"});

        return shop;

    }

}