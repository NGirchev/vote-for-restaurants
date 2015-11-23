# vote-for-restaurants
A voting system for deciding where to have lunch.

How to start:
1. I used mysql 14.14, but you can use different db. You need to write your properties in file: 
    /vote-for-restaurants/restaurant-core/src/main/resources/db.properties
2. Class DataBaseInitializer need for fill db. You can remove them and use sql inserts))
3. Tested on tomcat 8 and jdk 1.8.065

Examples:

1.authorization (usernames: 
    "admin@restaurant-mail.com", 
    "user1@restaurant-mail.com", 
    "user2@restaurant-mail.com")
curl -i -X POST -d j_username='admin@restaurant-mail.com' -d j_password='pass' -c ./cookies.txt http://localhost:8080/restaurant/j_spring_security_check

2.list of restaurants with menu and items (ROLE_USER)
curl -X GET --cookie ./cookies.txt "http://localhost:8080/restaurant/rest/restaurant"

3.get restaurant by id with rating (ROLE_USER)
curl -X GET --cookie ./cookies.txt "http://localhost:8080/restaurant/rest/restaurant/2"

4.update restaurant
curl -v -X PUT --cookie ./cookies.txt "http://localhost:8080/restaurant/rest/restaurant/2" -H "Content-Type:application/json" --data '{"name": "Sea"}'

5.create new restaurant
curl -v -X POST --cookie ./cookies.txt "http://localhost:8080/restaurant/rest/restaurant" -H "Content-Type:application/json" --data '{"name": "Pushkin"}'

6.create new menu for today
curl -v -X POST --cookie ./cookies.txt "http://localhost:8080/restaurant/rest/restaurant/2/menu" -H "Content-Type:application/json" --data '{"items": [{"name":"Pizza","cost":"3.00"},{"name":"Beer","cost":"10.00"}]}'

7.mark restaurant as deleted
curl -v -X DELETE --cookie ./cookies.txt "http://localhost:8080/restaurant/rest/restaurant/3"

8.get menu item
curl -v -X GET --cookie ./cookies.txt "http://localhost:8080/restaurant/rest/restaurant/2/menu/2/item/2"

9.delete menu item
curl -v -X DELETE --cookie ./cookies.txt "http://localhost:8080/restaurant/rest/restaurant/2/menu/2/item/2"

10.update menu item
curl -v -X PUT --cookie ./cookies.txt "http://localhost:8080/restaurant/rest/restaurant/2/menu/2/item/3" -H "Content-Type:application/json" --data '{"name": "lobster","cost": "50.0"}'

11.create menu item
curl -v -X POST --cookie ./cookies.txt "http://localhost:8080/restaurant/rest/restaurant/2/menu/2/item" -H "Content-Type:application/json" --data '{"name": "lobster","cost": "50.0"}'

12.vote (ROLE_USER)
curl -v -X POST --cookie ./cookies.txt "http://localhost:8080/restaurant/rest/vote/2"
