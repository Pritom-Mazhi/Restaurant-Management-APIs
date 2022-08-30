import scala.concurrent.Future

import org.scalatestplus.play._

import play.api.mvc._
import play.api.test._
import play.api.test.Helpers._

/* ALL TESTS FOR 1.ORDER CONTROLLERs ----- and ----- 2.ITEM CONTROLLERs
1. getAllOrders 
2. getOrderByTableId(table_no: Int)
3. getItemsByTableId(table_no: Int)
4. addOrder
5. updateOrder
6. deleteOrderByItem(table_no: Int, item_no: Int)
7. getAllItems 
8. addItem
9. getItemByItemId(itemId: Int) 
10. updateItem(itemId: Int) 
11. deleteItem(itemId: Int)
 */

class RestaurantControllerSpec extends PlaySpec with Results {

// TESTING the 1.ORDER CONTROLLERs
  "1# getAllOrders" should {
    "should be valid" in {
      val controller             = new RestaurantController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.getAllOrders().apply(FakeRequest())
      val bodyText: String       = contentAsString(result)
      bodyText mustBe "ok"
    }
  }

  "2# getOrderByTableId(table_no: Int)" should {
    "should be valid" in {
      val controller             = new RestaurantController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.getOrderByTableId(1).apply(FakeRequest())
      val bodyText: String       = contentAsString(result)
      bodyText mustBe "ok"
    }
  }

  "3# getItemsByTableId(table_no: Int)" should {
    "should be valid" in {
      val controller             = new RestaurantController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.getItemsByTableId(1).apply(FakeRequest())
      val bodyText: String       = contentAsString(result)
      bodyText mustBe "ok"
    }
  }

  "4_1# addOrder" should {
    "should be valid" in {
      val controller             = new RestaurantController(Helpers.stubControllerComponents())
      val fakeRequest = FakeRequest(POST, "/api/order", FakeHeaders(), AnyContentAsJson(Json.parse("""[{
            "tableNo": 1,
            "item": "1",
            "cookingTime": "5"
        }]""")))
      val result: Future[Result] = controller.addOrder().apply(fakeRequest())
      val bodyText: String       = contentAsString(result)
      bodyText mustBe "ok"  
    //   val resultJson: JsValue = contentAsJson(futureResult)(Timeout(2, TimeUnit.SECONDS))
    //   resultJson.toString mustBe """{"status":"success"}"""
    }
  }

  "4_2# addOrder" should {
    "should be valid" in {
      val controller             = new RestaurantController(Helpers.stubControllerComponents())
      val fakeRequest = FakeRequest(POST, "/api/order", FakeHeaders(), AnyContentAsJson(Json.parse("""[{
            "tableNo": 1,
            "item": "1,2,3",
            "cookingTime": "5,10,15"
        }]""")))
      val result: Future[Result] = controller.addOrder().apply(fakeRequest())
      val bodyText: String       = contentAsString(result)
      bodyText mustBe "ok"  
    //   val resultJson: JsValue = contentAsJson(futureResult)(Timeout(2, TimeUnit.SECONDS))
    //   resultJson.toString mustBe """{"status":"success"}"""
    }
  }

  "5# updateOrder" should {
    "should be valid" in {
      val controller             = new RestaurantController(Helpers.stubControllerComponents())
      val fakeRequest = FakeRequest(POST, "/api/order/1", FakeHeaders(), AnyContentAsJson(Json.parse("""[{
            "tableNo": 2,
            "item": "3",
            "cookingTime": "8"
        }]""")))
      val result: Future[Result] = controller.addOrder().apply(fakeRequest())
      val bodyText: String       = contentAsString(result)
      bodyText mustBe "ok"  
    //   val resultJson: JsValue = contentAsJson(futureResult)(Timeout(2, TimeUnit.SECONDS))
    //   resultJson.toString mustBe """{"status":"success"}"""
    }
  }

  "6# deleteOrderByItem(table_no: Int, item_no: Int)" should {
    "should be valid" in {
      val controller             = new RestaurantController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.deleteOrderByItem(1, 1).apply(FakeRequest())
      val bodyText: String       = contentAsString(result)
      bodyText mustBe "ok"
    }
  }

// TESTING the 2.ITEM CONTROLLERs
  "7# getAllItems" should {
    "should be valid" in {
      val controller             = new RestaurantController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.getAllItems().apply(FakeRequest())
      val bodyText: String       = contentAsString(result)
      bodyText mustBe "ok"
    }
  }

  "8# addItem" should {
    "should be valid" in {
      val controller             = new RestaurantController(Helpers.stubControllerComponents())
      val fakeRequest = FakeRequest(POST, "/api/addItem", FakeHeaders(), AnyContentAsJson(Json.parse("""[{
            "name": "Pizza"s
        }]""")))
      val result: Future[Result] = controller.addItem().apply(fakeRequest())
      val bodyText: String       = contentAsString(result)
      bodyText mustBe "ok"
    //   val resultJson: JsValue = contentAsJson(futureResult)(Timeout(2, TimeUnit.SECONDS))
    //   resultJson.toString mustBe """{"status":"success"}"""
    }
  }

  "9# updateItem(itemId: Int)" should {
    "should be valid" in {
      val controller             = new RestaurantController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.updateItem(1).apply(FakeRequest())
      val bodyText: String       = contentAsString(result)
      bodyText mustBe "ok"
    }
  }

  "10# " should {
    "should be valid" in {
      val controller             = new RestaurantController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.index().apply(FakeRequest())
      val bodyText: String       = contentAsString(result)
      bodyText mustBe "ok"
    }
  }

  "11# deleteItem(itemId: Int)" should {
    "should be valid" in {
      val controller             = new RestaurantController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.deleteItem(1).apply(FakeRequest())
      val bodyText: String       = contentAsString(result)
      bodyText mustBe "ok"
    }
  }
 
}


///////////////////// TESTING the FORMS --- 1.OrderForm and 2.ItemForm //////////////////////

// TESTING the FORMS --- 1.OrderForm
object OrderFormData {
  import play.api.data.Forms._
  import play.api.data._
  import play.api.i18n._
  import play.api.libs.json._

  val form = Form(
    mapping(
      "tableNo" -> number,
      "item" -> text,
      "cookingTime" -> text
    )(OrderFormData.apply)(OrderFormData.unapply)
  )

  case class OrderFormData(tableNo: Int, item: String, cookingTime: String)
}


class OrderFormData extends PlaySpecification with Results {
  import play.api.data._
  import play.api.i18n._
  import play.api.libs.json._
  import FormData._

  "Form" should {
    "be valid" in {
      val messagesApi = new DefaultMessagesApi(
        Map(
          "en" ->
            Map("error.min" -> "minimum!")
        )
      )
      implicit val request = {
        FakeRequest("POST", "/")
          .withFormUrlEncodedBody("tableNo" -> 1, "item" -> "1", "cookingTime" -> "5")
      }
      implicit val messages = messagesApi.preferred(request)

      def errorFunc(badForm: Form[OrderFormData]) = {
        BadRequest(badForm.errorsAsJson)
      }

      def successFunc(userData: OrderFormData) = {
        Redirect("/api/orders").flashing("success" -> "success form!")
      }

      val result = Future.successful(form.bindFromRequest().fold(errorFunc, successFunc))
      Json.parse(contentAsString(result)) must beEqualTo(Json.obj("tableNo" -> Json.arr("minimum!"), "item" -> Json.arr("minimum!"), "cookingTime" -> Json.arr("minimum!")))
    }
  }
}


// TESTING the FORMS --- 2.ItemForm
object ItemFormData {
  import play.api.data.Forms._
  import play.api.data._
  import play.api.i18n._
  import play.api.libs.json._

  val form = Form(
    mapping(
      "name" -> text
    )(ItemFormData.apply)(ItemFormData.unapply)
  )

  case class ItemFormData(name: String)
}


class ItemFormData extends PlaySpecification with Results {
  import play.api.data._
  import play.api.i18n._
  import play.api.libs.json._
  import FormData._

  "Form" should {
    "be valid" in {
      val messagesApi = new DefaultMessagesApi(
        Map(
          "en" ->
            Map("error.min" -> "minimum!")
        )
      )
      implicit val request = {
        FakeRequest("POST", "/")
          .withFormUrlEncodedBody("name" -> "pizza")
      }
      implicit val messages = messagesApi.preferred(request)

      def errorFunc(badForm: Form[OrderFormData]) = {
        BadRequest(badForm.errorsAsJson)
      }

      def successFunc(userData: OrderFormData) = {
        Redirect("/api/items").flashing("success" -> "success form!")
      }

      val result = Future.successful(form.bindFromRequest().fold(errorFunc, successFunc))
      Json.parse(contentAsString(result)) must beEqualTo(Json.obj("name" -> Json.arr("minimum!")))
    }
  }
}