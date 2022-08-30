/*
// COMMENTING ALL LINES OUT


package controllers

import javax.inject._
import play.api.mvc._
import play.api.i18n._
import play.api.libs.json._
import models.Tables._
import play.api.db.slick.DatabaseConfigProvider
import scala.concurrent.ExecutionContext
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future
import scala.collection.mutable
import scala.concurrent.ExecutionContext.Implicits.global
import models._
import javax.lang.model.`type`.NullType
// import play.api.data.FormError



@Singleton
class TestDBController @Inject() (protected val dbConfigProvider: DatabaseConfigProvider, cc: ControllerComponents) 
    extends AbstractController(cc) with HasDatabaseConfigProvider[JdbcProfile] {

    private val model = new TestDBModel(db)

    // def load = Action { implicit request =>
    //     Ok(views.html.test())
    // }

    // HELPER FUNCTION //
    /*
    def withJsonBody[A](f: A => Future[Result])(implicit request: Request[AnyContent], reads: Reads[A]): Future[Result] = {
        request.body.asJson.map { body =>
        Json.fromJson[A](body) match {
            case JsSuccess(a, path) => f(a)
            case e @ JsError(_) => Future.successful(Redirect(routes.ErrorPageImplemented.load()))
        }
        }.getOrElse(Future.successful(Redirect(routes.ErrorPageImplemented.load())))
    }*/

    def addOrder = Action.async { implicit request =>

        var requestedTableNo: Int = 0
        // var requestedTableNo: Int = scala.compiletime.uninitialized
        request.body.asJson.map { json =>
        json.validate[(String)].map{ 
            case (tableNo, item, cookingTime) => {
                requestedTableNo=tableNo
                requestedItemList=item
                requestedCookingTime=cookingTime
            }
        }
    }
    
  }
  



        /*
        // val content = request.body
        // val requestedTableNo = request.body.asFormUrlEncoded.get("tableNo")
        val requestedTableNo = request.body.tableNo
        val requestedCookingTime = request.body.asFormUrlEncoded.get("cookingTime")
        val requestedItemListString = request.body.asFormUrlEncoded.get("item")
        */

        var requestedItemList = requestedItemListString.flatMap(_.split(","))
        
        val requestedItemListLength = requestedItemList.length

        for( a <- 1 to requestedItemListLength){
            val currentItem = requestedItemList(a).toInt
            model.addOrder(requestedTableNo, currentItem, cookingTime).map(count => Ok(Json.toJson(count > 0))) 
        }

    }
        /*
        withJsonBody[String] { requestedItemList =>
            for( a <- 1 to requestedItemListLength){
                val currentItem = requestedItemList(a).toInt
                model.addOrder(requestedTableNo, currentItem, cookingTime).map(count => Ok(Json.toJson(count > 0))) 
            }
            // model.addOrder(requestedTableNo, currentItem, cookingTime).map(count => Ok(Json.toJson(count > 0)))
        }*/

        /*
        val futureTraverseResult = Future.traverse(requestedItemList){ futureEveryItem =>
            var currentItem = requestedItemList(a).toInt
            model.addOrder(requestedTableNo, currentItem, cookingTime)    
        }

        futureTraverseResult.onComplete {
            case Success(results) => println(s"Results $results")
            case Failure(e)       => println(s"Error processing future operations, error = ${e.getMessage}")
        }*/


    // def removeOrder(table: Int, item: Int) = Action.async {
    def removeOrder = Action.async { implicit request =>
        
        val requestedOrderId = request.body.asFormUrlEncoded.get("orderId")
        model.removeOrder(requestedOrderId) map { res =>
            Redirect(routes.TestDBController.getAllOrders) 
        }
        /*
        withJsonBody[Int] { requestedOrderId =>
            model.removeOrder(requestedOrderId).map(removed => Ok(Json.toJson(removed)))
        }*/
    }

    // def removeItemForTable(table: Int, item: Int) = Action.async {
    def removeItemForTable = Action.async { implicit request =>
        
        val requestedTableNo = request.body.asFormUrlEncoded.get("tableNo")
        val requestedItem = request.body.asFormUrlEncoded.get("item")
        model.removeItemForTable(requestedTableNo, requestedItem) map { res =>
            Redirect(routes.TestDBController.getAllOrders) 
        }}
        
        /*
        withJsonBody[Int] { requestedAmbValue =>
            model.removeOrder(requestedTableNo, requestedItem).map(removed => Ok(Json.toJson(removed)))
        }*/

   

    def getOrdersByTable = Action.async { implicit request =>

        val requestedTableNo = request.body.asFormUrlEncoded.get("tableNo")
        model.getOrdersByTable(tableNo) 

        /*
        withJsonBody[Int] { tableNo =>
            model.getOrdersByTable(tableNo).map(orders => Ok(Json.toJson(orders)))
        }*/
    }

    def getAllOrders = Action.async { implicit request =>
        model.getAllOrders.map(orders => Ok(Json.toJson(orders)))
    }
    
}

*/