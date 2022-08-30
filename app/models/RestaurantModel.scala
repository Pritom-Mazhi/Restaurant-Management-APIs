package models

// package models<br>import com.google.inject.Inject
import javax.inject._
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import scala.concurrent.{ExecutionContext, Future}
// import slick.jdbc.MySQLProfile.api._
import slick.jdbc.PostgresProfile.api._
// import profile.api._
import slick.model.ForeignKeyAction
import slick.jdbc.{GetResult => GR}


// val profile = slick.jdbc.PostgresProfile
// val profile: slick.jdbc.JdbcProfile

// object Tables extends {
//   val profile = slick.jdbc.PostgresProfile
// } 

/** DDL for all tables. Call .create to execute. */
// lazy val schema: profile.SchemaDescription = Items.schema ++ Orders.schema
// @deprecated("Use .schema instead of .ddl", "3.0")
// def ddl = schema

// def table Orders
class Orders(_tableTag: Tag) extends profile.api.Table[OrdersRow](_tableTag, "orders") {
    def * = (orderId, tableNo, item, cookingTime) <> (OrdersRow.tupled, OrdersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(orderId), Rep.Some(tableNo), item, Rep.Some(cookingTime))).shaped.<>({r=>import r._; _1.map(_=> OrdersRow.tupled((_1.get, _2.get, _3, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column order_id SqlType(serial), AutoInc, PrimaryKey */
    val orderId: Rep[Int] = column[Int]("order_id", O.AutoInc, O.PrimaryKey)
    /** Database column table_no SqlType(int4) */
    val tableNo: Rep[Int] = column[Int]("table_no")
    /** Database column item SqlType(int4), Default(None) */
    val item: Rep[Option[Int]] = column[Option[Int]]("item", O.Default(None))
    /** Database column cooking_time SqlType(int4) */
    val cookingTime: Rep[Int] = column[Int]("cooking_time")

    /** Foreign key referencing Items (database name orders_item_fkey) */
    lazy val itemsFk = foreignKey("orders_item_fkey", item, Items)(r => Rep.Some(r.itemId), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }

// def table Items
class Items(_tableTag: Tag) extends profile.api.Table[ItemsRow](_tableTag, "items") {
    def * = (itemId, name) <> (ItemsRow.tupled, ItemsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(itemId), Rep.Some(name))).shaped.<>({r=>import r._; _1.map(_=> ItemsRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column item_id SqlType(serial), AutoInc, PrimaryKey */
    val itemId: Rep[Int] = column[Int]("item_id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(varchar), Length(20,true) */
    val name: Rep[String] = column[String]("name", O.Length(20,varying=true))
  }

// case Classes - DTO class
case class OrdersRow(orderId: Int, tableNo: Int, item: Int, cookingTime: Int)
case class ItemsRow(itemId: Int, name: String)

// /** Collection-like TableQuery object for table Orders */
// lazy val Orders = new TableQuery(tag => new Orders(tag))
// /** Collection-like TableQuery object for table Items */
// lazy val Items = new TableQuery(tag => new Items(tag))

case class OrderFormData(tableNo: Int, item: String, cookingTime: String)
object OrderForm {
  val form = Form(
    mapping(
      "tableNo" -> number,
      "item" -> text,
      "cookingTime" -> text
    )(OrderFormData.apply)(OrderFormData.unapply)
  )
}

case class ItemFormData(name: String)
object ItemForm {
  val form = Form(
    mapping(
      "name" -> text
    )(ItemFormData.apply)(ItemFormData.unapply)
  )
}


class OrderList @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._
 
  var orderList = TableQuery[Orders]
  // var itemList = TableQuery[Items]


// CRUD of ORDERs //
  def addOrder(orderInstance: OrdersRow): Future[String] = {
    dbConfig.db
      .run(orderList += orderInstance)
      .map(res => "Order instance successfully added")
      .recover {
        case ex: Exception => {
            printf(ex.getMessage())
            ex.getMessage
        }
      }
  }
 
  def deleteOrder(orderId: Int): Future[Int] = {
    dbConfig.db.run(orderList.filter(_.orderId === orderId).delete)
  }

  def deleteOrderByItem(tableNo: Int, item: Int): Future[Int] = {
    dbConfig.db.run(orderList.filter(_.tableNo === tableNo).filter(_.item === item).result.headOption.delete)
    
  } // not sure about the double usage of .filter option
 
  def updateOrder(orderInstance: OrdersRow): Future[Int] = {
    dbConfig.db
      .run(orderList.filter(_.orderId === orderInstance.orderId)
            .map(x => (x.item, x.cookingTime))
            .update(orderInstance.item, orderInstance.cookingTime)
      )
  }
 
  // get the details of a row from the 'Orders' table - by orderID
  def getOrderDetail(orderId: Int): Future[Option[OrdersRow]] = {
    dbConfig.db.run(orderList.filter(_.orderId === orderId).result.headOption)
  }

  // needs to be update
  // get the details of a row from the 'Orders' table - by tableNo
  def getOrdersOfTable(tableNo: Int): Future[Seq[OrdersRow]] = {
    dbConfig.db.run(orderList.filter(_.tableNo === tableNo).result)
  }

  // case class OrdersRow(orderId: Int, tableNo: Int, item: Option[Int] = None, cookingTime: Int)
  def getItemsByTable(tableNo: Int): Future[Seq[String]] = {
    val query = sql"select item from Orders where tableNo = ${tableNo};".as[String]
    // val queryResult : Future[Seq[String]] = db.run(query)
    // queryResult.map(_.item)
    dbConfig.db.run(orderList.run(query).result)
  }
 
  // get all the rows from 'Orders' table
  def listAllOrders: Future[Seq[OrdersRow]] = {
    dbConfig.db.run(orderList.result)
  }
  
}

class ItemList @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] {

  var itemList = TableQuery[Items]

  // CRUD of ITEMs in MODEL//
  def addItem(itemInstance: ItemsRow): Future[String] = {
    dbConfig.db
      .run(itemList += itemInstance)
      .map(res => "Item instance successfully added")
      .recover {
        case ex: Exception => {
            printf(ex.getMessage())
            ex.getMessage
        }
      }
  }
 
  def deleteItem(itemId: Int): Future[Int] = {
    dbConfig.db.run(itemList.filter(_.itemId === itemId).delete)
  }
 
  def updateItem(itemInstance: ItemsRow): Future[Int] = {
    dbConfig.db
      .run(itemList.filter(_.itemId === itemInstance.itemId)
            .map(x => (x.name))
            .update(itemInstance.name)
      )
  }
 
  // get the details of a row from the 'Items' table - by itemID
  def getItemDetail(itemId: Int): Future[Option[ItemsRow]] = {
    dbConfig.db.run(itemList.filter(_.itemId === itemId).result.headOption)
  }
 
  // get all the rows from 'Items' table
  def listAllItems: Future[Seq[ItemsRow]] = {
    dbConfig.db.run(itemList.result)
  }

}

