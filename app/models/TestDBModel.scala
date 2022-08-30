/*
// COMMENTING ALL LINES OUT

package models

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.ExecutionContext
import models.Tables._
import scala.concurrent.Future
// import org.mindrot.jbcrypt.BCrypt

// ?-almost sure ??-not sure
class TestDBModel(db: Database)(implicit ec: ExecutionContext) {
    
    // change : Future[Int] to : Future[String]?
    def addOrder(tableNo: Int, item: Int, cookingTime: Int): Future[Int] = {
        db.run(Orders += OrdersRow(-1, tableNo, item, cookingTime))
    }

    // change : Future[Boolean] to : Future[Int]??
    def removeOrder(orderId: Int): Future[Boolean] = {
        db.run( Orders.filter(_.orderId === orderId).delete).map(count => count > 0)
    }

    // change : Future[Boolean] to : Future[Int]??
    // change : .result.headOption.delete to : .result.first.delete??
    // basically remove an order for an item for a table
    def removeItemForTable(tableNo: Int, item: Int): Future[Boolean] = {
        db.run(Orders.filter(_.tableNo === tableNo).filter(_.item === item).result.headOption.delete)
        // db.run(Orders.filter(_.tableNo === tableNo).filter(_.item === item).result.delete)
    }

    // val query = Orders.map(p => (p.item).filter(p.tableNo===tableNo))
    // Orders.map(_.item)
    def getOrdersByTable(tableNo: Int): Future[Seq[ItemInstance]] = {
        /*
        val action = sql"select item from Orders where Orders.tableNo === tableNo"
        db.run(action)*/
        db.run(Orders.map(order => (order.item).filter(order.tableNo===tableNo)).result)
        // db.run(Orders.map(order => (order.item).filter(_.tableNo===tableNo)).result)??
    }

    // get all the rows from 'Orders' table
    def getAllOrders: Future[Seq[OrdersRow]] = {
        dbConfig.db.run(Orders.result)
    } 

    // change : Future[Int] to : Future[String]?
    def addItem(name: String): Future[Int] = {
        db.run(Items += ItemsRow(-1, name))
    }

    // change : Future[Boolean] to : Future[Int]??
    def removeItem(itemId: Int): Future[Boolean] = {
        db.run(Items.filter(_.itemId === itemId).delete).map(count => count > 0)
    }

    def updateItem(itemId: Int): Future[Int] = {
        db.run(
        Items.filter(_.itemId === itemId)
                .map(x => (x.name))
                .update(ItemsRow.name)
        )
    }

    // get all the rows from 'Orders' table
    def getAllItems: Future[Seq[OrdersRow]] = {
        db.run(Items.result)
    } 
}




*/