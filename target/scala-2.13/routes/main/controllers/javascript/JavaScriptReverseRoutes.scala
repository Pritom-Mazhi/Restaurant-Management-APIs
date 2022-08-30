// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:7
package controllers.javascript {

  // @LINE:7
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:7
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
  }

  // @LINE:10
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:10
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }

  // @LINE:14
  class ReverseRestaurantController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:18
    def deleteOrderByItem: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RestaurantController.deleteOrderByItem",
      """
        function(table_no0,item_no1) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "api/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Int]].javascriptUnbind + """)("table_no", table_no0)) + "/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Int]].javascriptUnbind + """)("item_no", item_no1))})
        }
      """
    )
  
    // @LINE:16
    def getItemsByTableId: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RestaurantController.getItemsByTableId",
      """
        function(table_no0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Int]].javascriptUnbind + """)("table_no", table_no0)) + "/items"})
        }
      """
    )
  
    // @LINE:24
    def deleteItem: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RestaurantController.deleteItem",
      """
        function(itemId0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "api/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Int]].javascriptUnbind + """)("itemId", itemId0))})
        }
      """
    )
  
    // @LINE:22
    def updateItem: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RestaurantController.updateItem",
      """
        function(itemId0) {
          return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "api/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Int]].javascriptUnbind + """)("itemId", itemId0))})
        }
      """
    )
  
    // @LINE:17
    def addOrder: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RestaurantController.addOrder",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/order"})
        }
      """
    )
  
    // @LINE:21
    def getItemByItemId: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RestaurantController.getItemByItemId",
      """
        function(itemId0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Int]].javascriptUnbind + """)("itemId", itemId0)) + "/items"})
        }
      """
    )
  
    // @LINE:14
    def getAllOrders: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RestaurantController.getAllOrders",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/orders"})
        }
      """
    )
  
    // @LINE:20
    def getAllItems: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RestaurantController.getAllItems",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/items"})
        }
      """
    )
  
    // @LINE:15
    def getOrderByTableId: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RestaurantController.getOrderByTableId",
      """
        function(table_no0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Int]].javascriptUnbind + """)("table_no", table_no0)) + "/orders"})
        }
      """
    )
  
    // @LINE:23
    def addItem: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RestaurantController.addItem",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/addItem"})
        }
      """
    )
  
  }


}
