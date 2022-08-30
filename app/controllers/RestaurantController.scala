package controllers

import javax.inject._
import play.api.mvc._
import play.api.i18n._
import play.api.libs.json._
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import play.api.data.FormError
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext
import scala.collection.mutable


import models._
import models.{OrdersRow, ItemsRow, OrderList, ItemList, OrderForm, ItemForm}
import services.{OrderService, ItemService}
import play.api.data.FormError
import scala.concurrent.ExecutionContext.Implicits.global

class RestaurantController @Inject()(
    cc: ControllerComponents,
    orderService: OrderService,
    itemService: ItemService,
) extends AbstractController(cc) {
 
    implicit val orderFormat = Json.format[OrdersRow]
    implicit val itemFormat = Json.format[ItemsRow]
 
    // check
    def getAllOrders() = Action.async { implicit request: Request[AnyContent] =>
        orderService.listAllOrdersService map { items =>
          Ok(Json.toJson(items))
        }
      }
     
    def getOrderByOrderId(id: Int) = Action.async { implicit request: Request[AnyContent] =>
        orderService.getOrderDetailService(id) map { item =>
            Ok(Json.toJson(item))
        }
    }

    // check
    def getOrderByTableId(table: Int) = Action.async { implicit request: Request[AnyContent] =>
        orderService.getOrdersOfTableService(table) map { items =>
            Ok(Json.toJson(items))
        }
    }

    // check
    def getItemsByTableId(table: Int) = Action.async { implicit request: Request[AnyContent] =>
        orderService.getItemsByTableService(table) map { items =>
            Ok(Json.toJson(items))
        }
    }
     
    // check
    def addOrder() = Action.async { implicit request: Request[AnyContent] =>

        val requestedTableNoOptStr: Option[String] = request.body.asFormUrlEncoded.flatMap(m => m.get("tableNo").flatMap(_.headOption))
        val requestedTableNoStr: String = requestedTableNoOptStr.fold("")(_.toString)
        val requestedTableNo: Int = requestedTableNoStr.toInt

        val requestedItemListOptStr: Option[String] = request.body.asFormUrlEncoded.flatMap(m => m.get("item").flatMap(_.headOption))
        val requestedItemListStr: String = requestedItemListOptStr.fold("")(_.toString)
        val requestedItemList = requestedItemListStr.split(",")
        val requestedItemListLength = requestedItemList.length

        val requestedCookingTimeListOptStr: Option[String] = request.body.asFormUrlEncoded.flatMap(m => m.get("cookingTime").flatMap(_.headOption))
        val requestedCookingTimeListStr: String = requestedCookingTimeListOptStr.fold("")(_.toString)
        val requestedCookingTimeList = requestedCookingTimeListStr.split(",")

        for( a <- 1 to requestedItemListLength){
            // orderService.addOrderService(OrdersRow(-1, requestedTableNo, requestedItemList(a).toInt, requestedCookingTimeList(a).toInt))

            OrderForm.form.bindFromRequest.fold(
                // if any error in submitted data
                    errorForm => {
                        errorForm.errors.foreach(println)
                        Future.successful(BadRequest("Error!"))
                    },
                    data => {
                        val newOrder = OrdersRow(-1, requestedTableNo, requestedItemList(a).toInt, requestedCookingTimeList(a).toInt)
                        orderService.addOrderService(newOrder).map( _ => Redirect(routes.RestaurantController.getAllOrders))
                })
        }
    }
        // val myListBuffer = new mutable.ListBuffer[OrdersRow]()

        /// Should I not access a list by index???
        // Future.traverse(requestedCookingTimeList.zipWithIndex) {
        // case (item, i) =>
        //     models.TriModel.addOrder(requestedTableNo, requestedItemList(i).toInt, requestedCookingTimeList(i).toInt)
        // } map { counts =>
        //     Ok(Json.toJson(counts.map(count => count > 0)))  
        // }


        // val content = request.body
        //// val requestedItemListString = request.body.asFormUrlEncoded.get("item") 
        // val requestedItemListString = request.body.asFormUrlEncoded.get("item").map(_.head)

        // val requestedItemListString: String = ""
        // val jsonBody = request.body.asJson 

        // printf(requestedItemListString.getClass)

        //// SPECIAL COMMENT//
        // var requestedItemListStringMutable = scala.collection.mutable.ArraySeq(requestedItemListString:_*)

        // splitAt
        // val requestedItemListBuffer = new mutable.ListBuffer[content.item]() - // backup strategy

        //// SPECIAL COMMENT//
        // input.replaceAll(" ,", "")  // removes whitespaces
        // val requestedItemList = requestedItemListString.split(",") 

        //// SPECIAL COMMENT// may be requestedItemListString in #62 is already a LIST OF STRING?
        // var requestedItemList = requestedItemListString.split(",") 
        // val arr = requestedItemListString.split(Array(','))

        // returns an mutable Array - UnNecessary? // ArrayBuffer??
        
        //// var requestedItemList = requestedItemListString.flatMap(_.split(","))
        // MAY BE returns an mutable Array?? - of CHARS?

        // requestedItemList = requestedItemList.foreach(x => x.toInt)
        // now list of integer

        //// val requestedItemListLength = requestedItemList.length        
        // )

        /* // Returning Unit???
        for( a <- 1 to requestedItemListLength){
            myArr.appended(requestedTableNo, requestedItemList(a).toInt, requestedCookingTimeList(a).toInt)
        } */

        /*
        val futureTraverseResult = Future.traverse(requestedItemList){ futureEveryItem =>
            futureEveryItem.map(
            currentItem => insiderTraverse(currentItem.toString)
            )
        }

        futureTraverseResult.onComplete {
            case Success(results) => println(s"Results $results")
            case Failure(e)       => println(s"Error processing future operations, error = ${e.getMessage}")
        }

        def insiderTraverse(currentItem : String) = {
            val currentItem: Int = currentItem.toInt

            OrderForm.form.bindFromRequest.fold(
            // if any error in submitted data
                errorForm => {
                    errorForm.errors.foreach(println)
                    Future.successful(BadRequest("Error!"))
                },
                data => {
                    val newOrder = OrdersRow(-1, data.tableNo, currentItem, data.cookingTime)
                    // orderService.addOrderService(newOrder).map( _ => Redirect(routes.RestaurantController.getAllOrders))
                    orderService.addOrderService(newOrder)
            })
        } */

        /* // FUTURE.SEQUENCE COMMENTS //
        def insider() = Action {
            for( counter <- 0 to (requestedItemListLength-1)) {
                val currentItem = requestedItemList(counter).toInt
                OrderForm.form.bindFromRequest.fold(
                // if any error in submitted data
                    errorForm => {
                        errorForm.errors.foreach(println)
                        Future.successful(BadRequest("Error!"))
                    },
                    data => {
                        val newOrder = OrdersRow(0, data.tableNo, currentItem, data.cookingTime)
                        orderService.addOrderService(newOrder).map( _ => Redirect(routes.RestaurantController.getAllOrders))
                })
            }
        } 

        val futureSequenceResults = Future.sequence(insider)
        futureSequenceResults.onComplete {
            case Success(results) => println(s"Ok")
            case Failure(e)       => println(s"Error processing future operations, error = ${e.getMessage}")
        } */

        

        /*
        for ( counter <- 0 to (requestedItemListLength-1))
        {
            val currentItem = requestedItemList(counter).toInt
            OrderForm.form.bindFromRequest.fold(
            // if any error in submitted data
                errorForm => {
                    errorForm.errors.foreach(println)
                    Future.successful(BadRequest("Error!"))
                },
                data => {
                    val newOrder = OrdersRow(0, data.tableNo, currentItem, data.cookingTime)
                    orderService.addOrderService(newOrder).map( _ => Redirect(routes.RestaurantController.getAllOrders))
            })

            /*
            val ordersRow: Option[OrdersRow] = jsonObject.flatMap(Json.fromJson[OrdersRow](_).asOpt)
            ordersRow match { 
                case Some(newElement) =>
                    // val nextId = todoList.map(_.id).max + 1
                    // val toBeAdded = OrdersRow(nextId, newItem.description, false)
                    val newOrder = OrdersRow(0, newElement.tableNo, currentItem, newElement.cookingTime)
                    OrderService.addOrderService(newOrder).map( _ => Redirect(routes.TodoController.getAll))
                case None =>
                    BadRequest
            } */

        }*/
        
    
     
    def updateOrder(id: Int) = Action.async { implicit request: Request[AnyContent] =>
        OrderForm.form.bindFromRequest.fold(
            // if any error in submitted data
            errorForm => {
                errorForm.errors.foreach(println)
                Future.successful(BadRequest("Error!"))
            },
            data => {
                val theOrder = OrdersRow(orderId, data.tableNo, data.item, data.cookingTime)
                orderService.updateOrderService(theOrder).map( _ => Redirect(routes.RestaurantController.getAllOrders))
            })
    }
     
    def deleteOrder(id: Int) = Action.async { implicit request: Request[AnyContent] =>
        orderService.deleteOrderService(id) map { res =>
            Redirect(routes.RestaurantController.getAllOrders)
        }
    }

    // check
    def deleteOrderByItem(table: Int, item: Int) = Action.async { implicit request: Request[AnyContent] =>
        orderService.deleteOrderByItemService(table, item) map { res =>
            Redirect(routes.RestaurantController.getAllOrders)
        }
    }



    def getAllItems() = Action.async { implicit request: Request[AnyContent] =>
        itemService.listAllItemsService map { items =>
          Ok(Json.toJson(items))
        }
    }

    def addItem() = Action.async { implicit request: Request[AnyContent] =>
        ItemForm.form.bindFromRequest.fold(
          // if any error in submitted data
          errorForm => {
            Future.successful(BadRequest("Error!"))
          },
          data => {
            val theItem = ItemsRow(-1, data.name)
            itemService.addItemService(theItem).map( _ => Redirect(routes.RestaurantController.getAllItems))
          })
      }

    def getItemByItemId(id: Int) = Action.async { implicit request: Request[AnyContent] =>
        itemService.getItemDetailService(id) map { item =>
            Ok(Json.toJson(item))
        }
    }

    def updateItem(id: Int) = Action.async { implicit request: Request[AnyContent] =>
        ItemForm.form.bindFromRequest.fold(
            // if any error in submitted data
            errorForm => {
                Future.successful(BadRequest("Error!"))
            },
            data => {
                val theItem = ItemsRow(itemId, data.name)
                itemService.updateItemService(theItem).map( _ => Redirect(routes.RestaurantController.getAllItems))
            })
    }

    def deleteItem(id: Int) = Action.async { implicit request: Request[AnyContent] =>
        itemService.deleteItemService(id) map { res =>
            Redirect(routes.RestaurantController.getAllItems)
        }
    }

}











////////////////////////////////////////////// PREVIOUS /////////////////////////////////////////////////////////

/**
@Singleton
class RestaurantController @Inject() (protected val dbConfigProvider: DatabaseConfigProvider, cc: ControllerComponents)(implicit ec: ExecutionContext) 
    extends AbstractController(cc) with HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

//   private val modelItems = new Orders(db)
//   private val modelOrders = new Items(db)

  def load = Action { implicit request =>
    Ok(great)
  }

//   def findItemNameById(id : Int) : Future[Option[String]] = {
//     val query = sql"select name from items where id = ${id};".as[String]
//     val queryResult : Future[Vector[String]] = db.run(query)
//     queryResult.map(_.headOption)
//   }

    implicit val orderListJson = Json.format[TodoListItem]
    implicit val newTodoListJson = Json.format[NewTodoListItem]

    // curl localhost:9000/orders
    def getAll(): Action[AnyContent] = Action {
    if (order.isEmpty) NoContent else Ok(Json.toJson(order))
    }

    // private val orderList = new OrdersRow()
    implicit val orderFormat = Json.format[Orders]
    def getAll(): Action[AnyContent] = Action {
    if (order.isEmpty) {
        NoContent
    } else {
        Ok(Json.toJson(order))
    }
    }
} */


