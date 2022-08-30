# Restaurant-Management-APIs

# How to access and run this project?
- Install latest version of scala and sbt in your environment.
- open the **restaurant-apis** directory in you code editor(ie. VSCode).
- enter inside the **restaurant-apis** directory and run ```sbt run```

# Comments
- Every **item** is a new **order** for a **table**.
- Every **cooking time** for an **item** is determined(non-uniquely) by the http request made by the Tablet/Client.
- Making concurrent request is possible, a maximum number of 10 concurrent request is possible.
- No user authentication and authorization is used for the sake of simplicity.


# Notable Files 
- package controllers - restaurant-apis/app/controllers
    - controller code is mainly written in ```RestaurantController.scala``` file.
    - other files inside package ```controllers``` are not put in action, and used for rough/try/test etc. Currently all lines are commented out.

- package models - restaurant-apis/app/models
    - model code is mainly written in ```RestaurantModel.scala``` file.
    - ```CodeGen.scala``` file is used for generating **slick** tables from **sql**(restaurant-apis/sql/createTables.sql) statements. This file is not being used actively in this project. 
    - ```Tables.scala``` file is the generated file by running ```CodeGen.scala```. This file is not being used in this project. Currently all lines are commented out.
    - other files inside package ```models``` are not put in action, and used for rough/try/test etc. Currently all lines are commented out.
- package services - restaurant-apis/app/services
    - service code is written in ```RestaurantService.scala``` file.
- SQL - restaurant-apis/sql
    - sql files to create slick tables using **CodeGen.scala**(restaurant-apis/app/models/CodeGen.scala) file is written inside ```createTables.sql``` in plain SQL for PostgreSQL.
    - PostgreSQL user and database setup is written inside ```setup.sql``` file.
- test - restaurant-apis/test/controllers
    - unit tests for actively used controller methods, and forms are written inside the ```RestaurantControllerSpec.scala``` file.

# Setup the database
- PostgreSQL is used and configured in this project.
- Create/Configure a username, password for your PostgreSQL root/main user.
- Create/Configure a database and owner. You can run the commands in the terminal as logged in PostgreSQL user written in this file - ```restaurant-apis/sql/setup.sql```.
- Create/Configure tables. You can run the commands in the terminal as logged in PostgreSQL user written in this file - ```restaurant-apis/sql/createTables.sql```.

# Table Design
### Order Table
|orderId(PK)|     tableNo   |item(FK) | cookingTime |
|-----------|:-------------:|--------:|------------:|
|       1   |             1 |    1    |           5 |
|       2   |             2 |    1    |          10 |
|       3   |             2 |    3    |          15 |
|       4   |             3 |    5    |           8 |
|       5   |             4 |    1    |           7 |


### Item Table
| itemId(PK)|     name      |
|-----------|:-------------:|
|       1   |     pizza     |
|       2   |     pasta     |
|       3   |     chips     |
|       4   |     soup      |
|       5   |     burger    |


#### Requests for Order table
- Curl request using terminals are noted down here. But the requests can also be made by using popular services like **postman** etc.

1.
**URL**
GET     /api/orders                  controllers.RestaurantController.getAllOrders

**Runnable api call on the terminal**
```curl localhost:9000/api/orders```

**Successful response**
- Json list of all orders with respective elements.

2.
**URL**
GET     /api/:table_no/orders        controllers.RestaurantController.getOrderByTableId(table_no: Int)

**Runnable api call explaination** 
```curl localhost:9000/<tableNo>/orders```

**Runnable api call on the terminal** 
```curl localhost:9000/1/orders```

**Successful response**
- Json list of all order details associated with the table number.

3.
**URL**
GET     /api/:table_no/items         controllers.RestaurantController.getItemsByTableId(table_no: Int) 

**Runnable api call explaination** 
```curl localhost:9000/1<tableNo>/items```

**Runnable api call on the terminal** 
```curl localhost:9000/1/items```

**Successful response**
- Json list of all ordered items associated with the table number.


4.
**URL**
POST    /api/order                   controllers.RestaurantController.addOrder

**Runnable api call explaination** 
```curl -v -d '{"tableNo": <tableNo>, "item": <ListOfItemAsString>, "cookingTime": <ListOfCookingTimeAsString>}' -H 'Content-Type: application/json' -X POST localhost:9000/api/order```

**Runnable api call on the terminal** 
```curl -v -d '{"tableNo": 1, "item": "1,2,3,4", "cookingTime": "5,7,10,15"}' -H 'Content-Type: application/json' -X POST localhost:9000/api/order```


- in case of multiple items, associated cooking-time will also be sent matching the items respectively. Requested items and cooking-times for that single table will be processed and enter into the database as separate orders for that particular table. 

**Successful response**
- Json list of all orders with respective elements.

5.
**URL**
PUT     /api/order/:orderId          controllers.RestaurantController.updateOrder(id: orderId)

**Runnable api call explaination** 
```curl -v -d '{"tableNo": <tableNo>, "item": <SingleItemAsString>, "cookingTime": <SingleCookingTimeAsString>}' -H 'Content-Type: application/json' -X PUT localhost:9000//api/order/<orderId>```

**Runnable api call on the terminal** 
```curl -v -d '{"tableNo": 1, "item": "1", "cookingTime": "5"}' -H 'Content-Type: application/json' -X PUT localhost:9000//api/order/1```

**Successful response**
- Json list of all orders with respective elements.

6.
**URL**
DELETE  /api/:table_no/:item_no      controllers.RestaurantController.deleteOrderByItem(table_no: Int, item_no: Int)

**Runnable api call explaination** 
```curl -X DELETE localhost:9000/api/<tableNo>/<itemId>```

**Runnable api call on the terminal** 
```curl -X DELETE localhost:9000/api/1/1```

**Successful response**
- Json list of all orders with respective elements.

7. 
**URL**
GET     /api/items                   controllers.RestaurantController.getAllItems

**Runnable api call on the terminal**
```curl localhost:9000/api/items```

**Successful response**
- Json list of all items with respective elements.

8.
**URL**
GET     /api/items/:itemId           controllers.RestaurantController.getItemByItemId(itemId: Int)

**Runnable api call explaination** 
```curl localhost:9000/items/<itemId>```

**Runnable api call on the terminal** 
```curl localhost:9000/items/1```

**Successful response**
- Json response containing details of the item with that item id.

9. 
**URL**
PUT     /api/items/:itemId                 controllers.RestaurantController.updateItem(itemId: Int) 

**Runnable api call explaination** 
```curl -v -d '{"name": <name>}' -H 'Content-Type: application/json' -X PUT localhost:9000//api/items/<itemId>```

**Runnable api call on the terminal** 
```curl -v -d '{"name": "ramen"}' -H 'Content-Type: application/json' -X PUT localhost:9000/api/items/1```

**Successful response**
- Json list of all items with respective elements.

10. 
**URL**
POST    /api/addItem                 controllers.RestaurantController.addItem

**Runnable api call explaination** 
```curl -v -d '{"name": <name>}' -H 'Content-Type: application/json' -X POST localhost:9000/api/addItem```

**Runnable api call on the terminal** 
```curl -v -d '{"name": <name>}' -H 'Content-Type: application/json' -X POST localhost:9000/api/addItem```

**Successful response**
- Json list of all items with respective elements.

11. 
**URL**
DELETE  /api/items/:itemId                 controllers.RestaurantController.deleteItem(itemId: Int)

**Runnable api call explaination** 
```curl -X DELETE localhost:9000/api/items/<itemId>```

**Runnable api call on the terminal** 
```curl -X DELETE localhost:9000/api/items/1```

**Successful response**
- Json list of all items with respective elements.




