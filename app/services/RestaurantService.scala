package services
 
import com.google.inject.Inject
import models.{OrdersRow, ItemsRow, OrderList, ItemList}
 
import scala.concurrent.Future
 
class OrderService @Inject() (items: OrderList) {

  // ORDER SERVICES //
 
  def addOrderService(item: OrdersRow): Future[String] = {
    items.addOrder(item)
  }
 
  def deleteOrderService(id: Int): Future[Int] = {
    items.deleteOrder(id)
  }

  def deleteOrderByItemService(table: Int, item: Int): Future[Int] = {
    items.deleteOrderByItem(table, item)
  }
 
  def updateOrderService(item: OrdersRow): Future[Int] = {
    items.updateOrder(item)
  }
 
  def getOrderDetailService(id: Int): Future[Option[OrdersRow]] = {
    items.getOrderDetail(id)
  }

  def getOrdersOfTableService(table: Int): Future[Seq[OrdersRow]] = {
    items.getOrdersOfTable(table)
  }
 
  def listAllOrdersService: Future[Seq[OrdersRow]] = {
    items.listAllOrders
  }

  def getItemsByTableService(table: Int): Future[Seq[String]] = {
    items.getOrdersOfTable(table)
  } 

}

class ItemService @Inject() (items: ItemList) {
  // ITEM SERVICES //

  def addItemService(item: ItemsRow): Future[String] = {
    items.addItem(item)
  }
 
  def deleteItemService(id: Int): Future[Int] = {
    items.deleteItem(id)
  }
 
  def updateItemService(item: ItemsRow): Future[Int] = {
    items.updateItem(item)
  }
 
  def getItemDetailService(id: Int): Future[Option[ItemsRow]] = {
    items.getItemDetail(id)
  }
 
  def listAllItemsService: Future[Seq[ItemsRow]] = {
    items.listAllItems
  }
}
