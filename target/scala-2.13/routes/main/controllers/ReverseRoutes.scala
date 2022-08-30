// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:7
package controllers {

  // @LINE:7
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:7
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
  }

  // @LINE:10
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:10
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }

  // @LINE:14
  class ReverseRestaurantController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:18
    def deleteOrderByItem(table_no:Int, item_no:Int): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "api/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Int]].unbind("table_no", table_no)) + "/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Int]].unbind("item_no", item_no)))
    }
  
    // @LINE:16
    def getItemsByTableId(table_no:Int): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "api/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Int]].unbind("table_no", table_no)) + "/items")
    }
  
    // @LINE:24
    def deleteItem(itemId:Int): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "api/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Int]].unbind("itemId", itemId)))
    }
  
    // @LINE:22
    def updateItem(itemId:Int): Call = {
      
      Call("PUT", _prefix + { _defaultPrefix } + "api/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Int]].unbind("itemId", itemId)))
    }
  
    // @LINE:17
    def addOrder: Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "api/order")
    }
  
    // @LINE:21
    def getItemByItemId(itemId:Int): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "api/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Int]].unbind("itemId", itemId)) + "/items")
    }
  
    // @LINE:14
    def getAllOrders: Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "api/orders")
    }
  
    // @LINE:20
    def getAllItems: Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "api/items")
    }
  
    // @LINE:15
    def getOrderByTableId(table_no:Int): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "api/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Int]].unbind("table_no", table_no)) + "/orders")
    }
  
    // @LINE:23
    def addItem: Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "api/addItem")
    }
  
  }


}
