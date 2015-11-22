package ru.girchev.restaurant.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 22.11.15.
 */
public class Main {

    public static void main(String[] strings){
        Random r = new Random();
        System.out.println(DISHES_NAMES.get(r.nextInt(DISHES_NAMES.size())));
    }

    private static final List<String> DISHES_NAMES = new ArrayList<>();
    static {
        DISHES_NAMES.add("Baker potato");
        DISHES_NAMES.add("Burger");
    }
}
