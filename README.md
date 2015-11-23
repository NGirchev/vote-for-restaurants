# vote-for-restaurants
A voting system for deciding where to have lunch.

Examples:
list of restaurants with menu and items:
curl -X GET "http://localhost:8080/restaurant/rest/restaurant"

get restaurant by id with rating
curl -X GET "http://localhost:8080/restaurant/rest/restaurant/2"

update restaurant
curl -v -X PUT "http://localhost:8080/restaurant/rest/restaurant/2" -H "Content-Type:application/json" --data '{"name": "Sea"}'

create new restaurant
curl -v -X POST "http://localhost:8080/restaurant/rest/restaurant" -H "Content-Type:application/json" --data '{"name": "Pushkin"}'

create new menu for today
curl -v -X POST "http://localhost:8080/restaurant/rest/restaurant/2/menu" -H "Content-Type:application/json" --data '{"items": [{"name":"Pizza","cost":"3.00"},{"name":"Beer","cost":"10.00"}]}'

mark restaurant as deleted
curl -v -X DELETE "http://localhost:8080/restaurant/rest/restaurant/3"

get menu item
curl -v -X GET "http://localhost:8080/restaurant/rest/restaurant/2/menu/2/item/2"

delete menu item
curl -v -X DELETE "http://localhost:8080/restaurant/rest/restaurant/2/menu/2/item/2"

update menu item
curl -v -X PUT "http://localhost:8080/restaurant/rest/restaurant/2/menu/2/item/3" -H "Content-Type:application/json" --data '{"name": "lobster","cost": "50.0"}'

create menu item
curl -v -X POST "http://localhost:8080/restaurant/rest/restaurant/2/menu/2/item" -H "Content-Type:application/json" --data '{"name": "lobster","cost": "50.0"}'

vote
curl -v -X POST "http://localhost:8080/restaurant/rest/vote/2"