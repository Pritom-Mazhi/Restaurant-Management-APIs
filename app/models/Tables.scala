/*
// COMMENTING ALL LINES OUT


package models

// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.PostgresProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Items.schema ++ Orders.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Items
   *  @param itemId Database column item_id SqlType(serial), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(varchar), Length(20,true) */
  case class ItemsRow(itemId: Int, name: String)
  /** GetResult implicit for fetching ItemsRow objects using plain SQL queries */
  implicit def GetResultItemsRow(implicit e0: GR[Int], e1: GR[String]): GR[ItemsRow] = GR{
    prs => import prs._
    ItemsRow.tupled((<<[Int], <<[String]))
  }
  /** Table description of table items. Objects of this class serve as prototypes for rows in queries. */
  class Items(_tableTag: Tag) extends profile.api.Table[ItemsRow](_tableTag, "items") {
    def * = (itemId, name) <> (ItemsRow.tupled, ItemsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(itemId), Rep.Some(name))).shaped.<>({r=>import r._; _1.map(_=> ItemsRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column item_id SqlType(serial), AutoInc, PrimaryKey */
    val itemId: Rep[Int] = column[Int]("item_id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(varchar), Length(20,true) */
    val name: Rep[String] = column[String]("name", O.Length(20,varying=true))
  }
  /** Collection-like TableQuery object for table Items */
  lazy val Items = new TableQuery(tag => new Items(tag))

  /** Entity class storing rows of table Orders
   *  @param orderId Database column order_id SqlType(serial), AutoInc, PrimaryKey
   *  @param tableNo Database column table_no SqlType(int4)
   *  @param item Database column item SqlType(int4), Default(None)
   *  @param cookingTime Database column cooking_time SqlType(int4) */
  case class OrdersRow(orderId: Int, tableNo: Int, item: Option[Int] = None, cookingTime: Int)
  /** GetResult implicit for fetching OrdersRow objects using plain SQL queries */
  implicit def GetResultOrdersRow(implicit e0: GR[Int], e1: GR[Option[Int]]): GR[OrdersRow] = GR{
    prs => import prs._
    OrdersRow.tupled((<<[Int], <<[Int], <<?[Int], <<[Int]))
  }
  /** Table description of table orders. Objects of this class serve as prototypes for rows in queries. */
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
  /** Collection-like TableQuery object for table Orders */
  lazy val Orders = new TableQuery(tag => new Orders(tag))
}




*/