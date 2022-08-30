// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:7
  HomeController_0: controllers.HomeController,
  // @LINE:10
  Assets_1: controllers.Assets,
  // @LINE:14
  RestaurantController_2: controllers.RestaurantController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:7
    HomeController_0: controllers.HomeController,
    // @LINE:10
    Assets_1: controllers.Assets,
    // @LINE:14
    RestaurantController_2: controllers.RestaurantController
  ) = this(errorHandler, HomeController_0, Assets_1, RestaurantController_2, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_0, Assets_1, RestaurantController_2, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/orders""", """controllers.RestaurantController.getAllOrders"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/""" + "$" + """table_no<[^/]+>/orders""", """controllers.RestaurantController.getOrderByTableId(table_no:Int)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/""" + "$" + """table_no<[^/]+>/items""", """controllers.RestaurantController.getItemsByTableId(table_no:Int)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/order""", """controllers.RestaurantController.addOrder"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/""" + "$" + """table_no<[^/]+>/""" + "$" + """item_no<[^/]+>""", """controllers.RestaurantController.deleteOrderByItem(table_no:Int, item_no:Int)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/items""", """controllers.RestaurantController.getAllItems"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/""" + "$" + """itemId<[^/]+>/items""", """controllers.RestaurantController.getItemByItemId(itemId:Int)"""),
    ("""PUT""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/""" + "$" + """itemId<[^/]+>""", """controllers.RestaurantController.updateItem(itemId:Int)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/addItem""", """controllers.RestaurantController.addItem"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/""" + "$" + """itemId<[^/]+>""", """controllers.RestaurantController.deleteItem(itemId:Int)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:7
  private[this] lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
    HomeController_0.index(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val controllers_Assets_versioned1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned1_invoker = createInvoker(
    Assets_1.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )

  // @LINE:14
  private[this] lazy val controllers_RestaurantController_getAllOrders2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/orders")))
  )
  private[this] lazy val controllers_RestaurantController_getAllOrders2_invoker = createInvoker(
    RestaurantController_2.getAllOrders,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RestaurantController",
      "getAllOrders",
      Nil,
      "GET",
      this.prefix + """api/orders""",
      """""",
      Seq()
    )
  )

  // @LINE:15
  private[this] lazy val controllers_RestaurantController_getOrderByTableId3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/"), DynamicPart("table_no", """[^/]+""",true), StaticPart("/orders")))
  )
  private[this] lazy val controllers_RestaurantController_getOrderByTableId3_invoker = createInvoker(
    RestaurantController_2.getOrderByTableId(fakeValue[Int]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RestaurantController",
      "getOrderByTableId",
      Seq(classOf[Int]),
      "GET",
      this.prefix + """api/""" + "$" + """table_no<[^/]+>/orders""",
      """""",
      Seq()
    )
  )

  // @LINE:16
  private[this] lazy val controllers_RestaurantController_getItemsByTableId4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/"), DynamicPart("table_no", """[^/]+""",true), StaticPart("/items")))
  )
  private[this] lazy val controllers_RestaurantController_getItemsByTableId4_invoker = createInvoker(
    RestaurantController_2.getItemsByTableId(fakeValue[Int]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RestaurantController",
      "getItemsByTableId",
      Seq(classOf[Int]),
      "GET",
      this.prefix + """api/""" + "$" + """table_no<[^/]+>/items""",
      """""",
      Seq()
    )
  )

  // @LINE:17
  private[this] lazy val controllers_RestaurantController_addOrder5_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/order")))
  )
  private[this] lazy val controllers_RestaurantController_addOrder5_invoker = createInvoker(
    RestaurantController_2.addOrder,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RestaurantController",
      "addOrder",
      Nil,
      "POST",
      this.prefix + """api/order""",
      """""",
      Seq()
    )
  )

  // @LINE:18
  private[this] lazy val controllers_RestaurantController_deleteOrderByItem6_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/"), DynamicPart("table_no", """[^/]+""",true), StaticPart("/"), DynamicPart("item_no", """[^/]+""",true)))
  )
  private[this] lazy val controllers_RestaurantController_deleteOrderByItem6_invoker = createInvoker(
    RestaurantController_2.deleteOrderByItem(fakeValue[Int], fakeValue[Int]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RestaurantController",
      "deleteOrderByItem",
      Seq(classOf[Int], classOf[Int]),
      "DELETE",
      this.prefix + """api/""" + "$" + """table_no<[^/]+>/""" + "$" + """item_no<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:20
  private[this] lazy val controllers_RestaurantController_getAllItems7_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/items")))
  )
  private[this] lazy val controllers_RestaurantController_getAllItems7_invoker = createInvoker(
    RestaurantController_2.getAllItems,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RestaurantController",
      "getAllItems",
      Nil,
      "GET",
      this.prefix + """api/items""",
      """""",
      Seq()
    )
  )

  // @LINE:21
  private[this] lazy val controllers_RestaurantController_getItemByItemId8_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/"), DynamicPart("itemId", """[^/]+""",true), StaticPart("/items")))
  )
  private[this] lazy val controllers_RestaurantController_getItemByItemId8_invoker = createInvoker(
    RestaurantController_2.getItemByItemId(fakeValue[Int]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RestaurantController",
      "getItemByItemId",
      Seq(classOf[Int]),
      "GET",
      this.prefix + """api/""" + "$" + """itemId<[^/]+>/items""",
      """""",
      Seq()
    )
  )

  // @LINE:22
  private[this] lazy val controllers_RestaurantController_updateItem9_route = Route("PUT",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/"), DynamicPart("itemId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_RestaurantController_updateItem9_invoker = createInvoker(
    RestaurantController_2.updateItem(fakeValue[Int]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RestaurantController",
      "updateItem",
      Seq(classOf[Int]),
      "PUT",
      this.prefix + """api/""" + "$" + """itemId<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:23
  private[this] lazy val controllers_RestaurantController_addItem10_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/addItem")))
  )
  private[this] lazy val controllers_RestaurantController_addItem10_invoker = createInvoker(
    RestaurantController_2.addItem,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RestaurantController",
      "addItem",
      Nil,
      "POST",
      this.prefix + """api/addItem""",
      """""",
      Seq()
    )
  )

  // @LINE:24
  private[this] lazy val controllers_RestaurantController_deleteItem11_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/"), DynamicPart("itemId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_RestaurantController_deleteItem11_invoker = createInvoker(
    RestaurantController_2.deleteItem(fakeValue[Int]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RestaurantController",
      "deleteItem",
      Seq(classOf[Int]),
      "DELETE",
      this.prefix + """api/""" + "$" + """itemId<[^/]+>""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:7
    case controllers_HomeController_index0_route(params@_) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_0.index())
      }
  
    // @LINE:10
    case controllers_Assets_versioned1_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned1_invoker.call(Assets_1.versioned(path, file))
      }
  
    // @LINE:14
    case controllers_RestaurantController_getAllOrders2_route(params@_) =>
      call { 
        controllers_RestaurantController_getAllOrders2_invoker.call(RestaurantController_2.getAllOrders)
      }
  
    // @LINE:15
    case controllers_RestaurantController_getOrderByTableId3_route(params@_) =>
      call(params.fromPath[Int]("table_no", None)) { (table_no) =>
        controllers_RestaurantController_getOrderByTableId3_invoker.call(RestaurantController_2.getOrderByTableId(table_no))
      }
  
    // @LINE:16
    case controllers_RestaurantController_getItemsByTableId4_route(params@_) =>
      call(params.fromPath[Int]("table_no", None)) { (table_no) =>
        controllers_RestaurantController_getItemsByTableId4_invoker.call(RestaurantController_2.getItemsByTableId(table_no))
      }
  
    // @LINE:17
    case controllers_RestaurantController_addOrder5_route(params@_) =>
      call { 
        controllers_RestaurantController_addOrder5_invoker.call(RestaurantController_2.addOrder)
      }
  
    // @LINE:18
    case controllers_RestaurantController_deleteOrderByItem6_route(params@_) =>
      call(params.fromPath[Int]("table_no", None), params.fromPath[Int]("item_no", None)) { (table_no, item_no) =>
        controllers_RestaurantController_deleteOrderByItem6_invoker.call(RestaurantController_2.deleteOrderByItem(table_no, item_no))
      }
  
    // @LINE:20
    case controllers_RestaurantController_getAllItems7_route(params@_) =>
      call { 
        controllers_RestaurantController_getAllItems7_invoker.call(RestaurantController_2.getAllItems)
      }
  
    // @LINE:21
    case controllers_RestaurantController_getItemByItemId8_route(params@_) =>
      call(params.fromPath[Int]("itemId", None)) { (itemId) =>
        controllers_RestaurantController_getItemByItemId8_invoker.call(RestaurantController_2.getItemByItemId(itemId))
      }
  
    // @LINE:22
    case controllers_RestaurantController_updateItem9_route(params@_) =>
      call(params.fromPath[Int]("itemId", None)) { (itemId) =>
        controllers_RestaurantController_updateItem9_invoker.call(RestaurantController_2.updateItem(itemId))
      }
  
    // @LINE:23
    case controllers_RestaurantController_addItem10_route(params@_) =>
      call { 
        controllers_RestaurantController_addItem10_invoker.call(RestaurantController_2.addItem)
      }
  
    // @LINE:24
    case controllers_RestaurantController_deleteItem11_route(params@_) =>
      call(params.fromPath[Int]("itemId", None)) { (itemId) =>
        controllers_RestaurantController_deleteItem11_invoker.call(RestaurantController_2.deleteItem(itemId))
      }
  }
}
